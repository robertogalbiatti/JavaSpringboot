package roberto.cursospringboot.resources;

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

import roberto.cursospringboot.domain.Cliente;
import roberto.cursospringboot.dto.ClienteDTO;
import roberto.cursospringboot.services.ClienteService;

@RestController //escrever e dar CTRL SHIFT O
@RequestMapping(value ="/clientes") //padrão de mercado o que ta em string - (esse é o endpoint rest)
public class ClienteResource {
	
	@Autowired //instanciar automaticamente o objeto abaixo (Spring)
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //obtendo um dado
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { //uso do PathVariable do spring para o id da URL buscado ir para cá
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj); //ResponseEntity já encapsula informações de uma resposta HTTP para serviço REST
		//aqui ele vai retornar um ok do corpo do objeto obj
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDTO);
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
	public ResponseEntity<List<ClienteDTO>> findAll() { //uso do PathVariable do spring para o id da URL buscado ir para cá
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	//diferenciar de findAll comum...
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page, 
			@RequestParam(value="linerPerPage", defaultValue="24")Integer linesPerPage, //colocar 24 pq é múltiplo de 1, 2, 4...sugestão 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) { //ascendente ou descendente
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));//page nao precisa do stream e collect
		return ResponseEntity.ok().body(listDTO);
	}

}
