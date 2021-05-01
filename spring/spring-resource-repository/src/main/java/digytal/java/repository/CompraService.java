package digytal.java.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import digytal.java.model.estoque.CompraEntity;

@Component
public class CompraService {
	@Autowired
	private CompraRepository cr;
	
	public Iterable<CompraEntity> compras(){
		Iterable<CompraEntity> it = cr.findAll();
		return it;
	}
	
}
