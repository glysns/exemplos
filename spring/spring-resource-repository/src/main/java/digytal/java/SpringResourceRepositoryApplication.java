package digytal.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import digytal.java.model.categoria.Categoria;
import digytal.java.model.categoria.CategoriaEntity;
import digytal.java.model.marca.MarcaEntity;
import digytal.java.repository.PersistRepository;

@SpringBootApplication
public class SpringResourceRepositoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringResourceRepositoryApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(PersistRepository pr) throws Exception {
		return args -> {
			MarcaEntity marca = new MarcaEntity();
			marca.setAtivo(true);
			marca.setNome("DELL");
			pr.save(marca);
			
			
			CategoriaEntity categoria = new CategoriaEntity();
			categoria.setNome("NOTEBOOKS");
			
			pr.save(categoria);
			
		};
	}	

}
