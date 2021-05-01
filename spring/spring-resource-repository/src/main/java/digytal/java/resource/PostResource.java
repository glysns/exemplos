package digytal.java.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.java.infra.converter.ModelConveter;
import digytal.java.model.categoria.Categoria;
import digytal.java.model.categoria.CategoriaEntity;
import digytal.java.model.marca.Marca;
import digytal.java.model.marca.MarcaEntity;
import digytal.java.model.produto.Produto;
import digytal.java.model.produto.ProdutoEntity;
import digytal.java.repository.PersistRepository;

@RestController
@RequestMapping("/post")
public class PostResource {
	@Autowired
	private PersistRepository repository;
	@PostMapping("/marcas")
	public void marca(@RequestBody Marca dto) throws Exception {
		MarcaEntity entity = ModelConveter.getInstance(dto).newInstance();
		repository.save(entity);
	}
	@PostMapping("/categorias")
	public void marca(@RequestBody Categoria dto) throws Exception {
		CategoriaEntity entity = ModelConveter.getInstance(dto).newInstance();
		repository.save(entity);
	}
	@PostMapping("/produtos")
	public void produto(@RequestBody Produto dto) throws Exception {
		ProdutoEntity entity = ModelConveter.getInstance(dto).newInstance();
		repository.save(entity);
	}
}
