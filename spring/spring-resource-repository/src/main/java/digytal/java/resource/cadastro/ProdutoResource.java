package digytal.java.resource.cadastro;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.java.commons.http.Response;
import digytal.java.infra.exception.config.BusinessException;
import digytal.java.infra.sql.Search;
import digytal.java.model.produto.Produto;
import digytal.java.resource.ResourceRepository;

@RestController
@RequestMapping ("/produtos")
public class ProdutoResource extends ResourceRepository<Produto> {
	@PostMapping("/search")
	public Response search(@RequestBody Search search)  throws BusinessException {
		List list= list(Produto.class, search.conditions);
		return Response.ok(list);
	}
}
