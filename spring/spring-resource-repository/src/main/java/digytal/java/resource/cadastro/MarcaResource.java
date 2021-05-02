package digytal.java.resource.cadastro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.java.model.marca.Marca;
import digytal.java.resource.ResourceRepository;

@RestController
@RequestMapping ("/marcas")
public class MarcaResource extends ResourceRepository<Marca> {

}
