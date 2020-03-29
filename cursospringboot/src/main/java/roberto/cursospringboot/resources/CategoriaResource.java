package roberto.cursospringboot.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //escrever e dar CTRL SHIFT O
@RequestMapping(value ="/categorias") //padrão de mercado o que ta em string
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET) //obtendo um dado
	public String listar() {
		return "REST está funcionando";
	}

}
