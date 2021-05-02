package digytal.java.resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import digytal.java.commons.Dto;
import digytal.java.repository.CrudRepository;

public abstract class ResourceRepository<D extends Dto> extends CrudRepository<D>{
	protected void process(D dto) throws BusinessException {} ;	
	@PostMapping("/save")
	@Transactional
	public Object post(@RequestBody D instance)  throws RuntimeException {
		//process(dto);
		Object response= insert(instance);
		return response;
	}
	
}
