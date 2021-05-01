package digytal.java.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersistRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(Object entity) {
		em.persist(entity);
	}
	@Transactional
	public void update(Object entity) {
		em.merge(entity);
	}
	public <E> E find(Class entityClass, Object id) {
		return (E) em.find(entityClass, id);
	}
	public List list(Class entityClass) {
		Query q = em.createQuery(String.format("SELECT e FROM %s e",entityClass.getName()));
		return q.getResultList();
	}
}
