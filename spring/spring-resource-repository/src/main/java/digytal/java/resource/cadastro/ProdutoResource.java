package digytal.java.resource.cadastro;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.java.commons.http.Response;
import digytal.java.infra.exception.config.BusinessException;
import digytal.java.model.marca.Marca;
import digytal.java.model.produto.Produto;
import digytal.java.resource.ResourceRepository;

@RestController
@RequestMapping ("/produtos")
public class ProdutoResource extends ResourceRepository<Marca> {
	@GetMapping("/all")
	public Response other()  throws BusinessException {
		List list= list(Produto.class, "marcaId", "=", 3);
		return Response.ok(list);
	}
}
