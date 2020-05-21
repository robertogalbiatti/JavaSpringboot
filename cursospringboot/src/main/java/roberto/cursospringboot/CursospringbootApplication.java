package roberto.cursospringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.repositories.CategoriaRepository;

@SpringBootApplication
public class CursospringbootApplication implements CommandLineRunner { // permite implementar um método auxiliar pra
																		// executar uma ação quando a aplicação iniciar
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringbootApplication.class, args);
	}

@Override
public void run(String... args) throws Exception {
	
	Categoria cat1 = new Categoria(null, "Informática"); //lembrando que o banco cria automaticamente o número!
	Categoria cat2 = new Categoria(null, "Escritório");
	
	categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	
}

}
