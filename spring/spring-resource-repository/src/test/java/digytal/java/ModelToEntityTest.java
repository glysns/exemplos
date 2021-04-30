package digytal.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import digytal.java.infra.converter.ModelConveter;
import digytal.java.model.cadastro.marca.Marca;
import digytal.java.model.cadastro.marca.MarcaEntity;
import digytal.java.model.cadastro.produto.Produto;
import digytal.java.model.cadastro.produto.ProdutoEntity;

public class ModelToEntityTest {
	@Test
	void modelToEntity(){
		try {
			Marca dto = new Marca();
			dto.nome="FORD";
			dto.id=1234;
			dto.ativo=true;
			MarcaEntity entity = ModelConveter.getInstance(dto).newInstance();
			assertEquals(dto.id, entity.getId());
			assertEquals(dto.nome, entity.getNome());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void modelToEntityAssociation(){
		try {
			Marca marca = new Marca();
			marca.nome="FORD";
			marca.id=1234;
			
			Produto dto = new Produto();
			dto.nome="FORD";
			dto.id=1234;
			dto.marca=marca;
			dto.ativo=true;
			
			
			ProdutoEntity entity = ModelConveter.getInstance(dto).newInstance();
			assertEquals(dto.id, entity.getId());
			assertEquals(dto.nome, entity.getNome());
			
			assertNotNull(entity.getMarca());
			assertEquals(dto.marca.id, entity.getMarca().getId());
			assertEquals(dto.marca.nome, entity.getMarca().getNome());
			assertEquals(dto.ativo, entity.isAtivo());
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
