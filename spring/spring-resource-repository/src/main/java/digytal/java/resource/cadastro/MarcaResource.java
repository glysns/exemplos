package digytal.java.resource.cadastro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.java.commons.http.Response;
import digytal.java.infra.exception.config.BusinessException;
import digytal.java.model.marca.Marca;
import digytal.java.model.produto.Produto;
import digytal.java.resource.ResourceRepository;

@RestController
@RequestMapping ("/marcas")
public class MarcaResource extends ResourceRepository<Marca> {
	@GetMapping("/other/{id}")
	public Response one(@PathVariable("id") Integer id)  throws BusinessException {
		Object model= find(Produto.class, id);
		return Response.ok(model);
	}
}
