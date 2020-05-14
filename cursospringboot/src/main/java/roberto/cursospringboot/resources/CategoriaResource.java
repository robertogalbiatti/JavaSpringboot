package roberto.cursospringboot.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roberto.cursospringboot.domain.Categoria;

@RestController //escrever e dar CTRL SHIFT O
@RequestMapping(value ="/categorias") //padrão de mercado o que ta em string - (esse é o endpoint rest)
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET) //obtendo um dado
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
				
		return lista;
	}

}
