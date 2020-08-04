package roberto.cursospringboot.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.dto.CategoriaDTO;
import roberto.cursospringboot.services.CategoriaService;

@RestController //escrever e dar CTRL SHIFT O
@RequestMapping(value ="/categorias") //padrão de mercado o que ta em string - (esse é o endpoint rest)
public class CategoriaResource {
	
	@Autowired //instanciar automaticamente o objeto abaixo (Spring)
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //obtendo um dado
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { //uso do PathVariable do spring para o id da URL buscado ir para cá
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj); //ResponseEntity já encapsula informações de uma resposta HTTP para serviço REST
		//aqui ele vai retornar um ok do corpo do objeto obj
	}
	
	//método para receber um Categoria no formato JSON e inserí-lo no banco de dados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){ //resposta HTTP sem corpo -RequestBody faz o JSON ser convertido para Java automaticamente
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj); //obj é inserido no Banco de Dados e o próprio banco vai atribuir novo ID e fornecer como argumento da URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //fromCurrentRequest() ele busca o "localhost:8080/categorias/" e é inserido o "1"
		return ResponseEntity.created(uri).build(); //vai retornar a resposta
	}
	
	//método PUT: mistura de GET e POST: temos que colocar o RequestBody para ele receber o objeto e vai receber o parâmetro "do GET".
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {//retornar uma resposta com corpo vazio
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() { //uso do PathVariable do spring para o id da URL buscado ir para cá
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	//diferenciar de findAll comum...
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page, 
			@RequestParam(value="linerPerPage", defaultValue="24")Integer linesPerPage, //colocar 24 pq é múltiplo de 1, 2, 4...sugestão 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) { //ascendente ou descendente
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));//page nao precisa do stream e collect
		return ResponseEntity.ok().body(listDTO);
	}
}
