package roberto.cursospringboot.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.services.CategoriaService;

@RestController //escrever e dar CTRL SHIFT O
@RequestMapping(value ="/categorias") //padrão de mercado o que ta em string - (esse é o endpoint rest)
public class CategoriaResource {
	
	@Autowired //instanciar automaticamente o objeto abaixo (Spring)
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //obtendo um dado
	public ResponseEntity<?> find(@PathVariable Integer id) { //uso do PathVariable do spring para o id da URL buscado ir para cá
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj); //ResponseEntity já encapsula informações de uma resposta HTTP para serviço REST
		//aqui ele vai retornar um ok do corpo do objeto obj
	}
	
	//método para receber um Categoria no formato JSON e inserí-lo no banco de dados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ //resposta HTTP sem corpo -RequestBody faz o JSON ser convertido para Java automaticamente
		obj = service.insert(obj); //obj é inserido no Banco de Dados e o próprio banco vai atribuir novo ID e fornecer como argumento da URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //fromCurrentRequest() ele busca o "localhost:8080/categorias/" e é inserido o "1"
		return ResponseEntity.created(uri).build(); //vai retornar a resposta
	}

}
