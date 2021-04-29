package digytal.resource;

import digytal.commons.Dto;
import digytal.repository.CrudRepository;

public abstract class ResourceRepository<D extends Dto> extends CrudRepository<D>{
	public void process(D dto) {} ;	
}
