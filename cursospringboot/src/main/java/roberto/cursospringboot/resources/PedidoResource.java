package roberto.cursospringboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roberto.cursospringboot.domain.Pedido;
import roberto.cursospringboot.services.PedidoService;

@RestController // escrever e dar CTRL SHIFT O
@RequestMapping(value = "/pedidos") // padrão de mercado o que ta em string - (esse é o endpoint rest)
public class PedidoResource {

	@Autowired // instanciar automaticamente o objeto abaixo (Spring)
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // obtendo um dado
	public ResponseEntity<?> find(@PathVariable Integer id) { // uso do PathVariable do spring para o id da URL buscado
																// ir para cá
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj); // ResponseEntity já encapsula informações de uma resposta HTTP para
												// serviço REST
		// aqui ele vai retornar um ok do corpo do objeto obj
	}

}
