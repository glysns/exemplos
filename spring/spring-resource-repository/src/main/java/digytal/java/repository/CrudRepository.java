package digytal.java.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import digytal.java.commons.Dto;
import digytal.java.commons.Model;
import digytal.java.infra.converter.ModelConveter;

public class CrudRepository <D extends Dto> {
	@PersistenceContext
	protected EntityManager em;
	protected Class<D> dto;
	public CrudRepository() {
		this.dto = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	private Class getEntity() {
		return getEntity(dto);
	}
	private Class getEntity(Class dto) {
		try {
			return Class.forName(String.format("%s.%sEntity", dto.getPackage().getName(), dto.getSimpleName().replaceAll("Entity", "")));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	public Object insert(Object instance) {
		try {
			Model entity = null;
			if(instance instanceof Model) {
				entity =(Model) instance;
			}else {
				entity = ModelConveter.getInstance(instance).newInstance();
			}
			commit(entity);
			return entity.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao converter a requisição " + instance.getClass().getName());
		}
	}
	
	private Object commit(Model entity) {
		Object id = entity.getId();
		if (id != null)
			em.merge(entity);
		else {
			em.persist(entity);
			em.flush();
			id = entity.getId();
		}
		return id;
	}
	
	public boolean exists(String field, Object param) {
		return findBy(field, param)!=null;
	}
	public <D>D findBy(String field, Object param) {
		try {
			String sql = String.format("SELECT e FROM  %s e WHERE e.%s = :param", dto.getName(),field);
			Object entity= em.createQuery(sql).setParameter("param", param ).getSingleResult();
			return (D) entity;
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}
	public <E> E find(Object id) {
		Object entity = em.find(getEntity(), id);
		return (E) entity;
	}
	public <E> E find(Class type, Object id) {
		Object entity = em.find(getEntity(type), id);
		return (E) entity;
		//return convert(entity);
	}
}
