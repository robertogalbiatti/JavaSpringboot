package roberto.cursospringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import roberto.cursospringboot.domain.Cliente;
import roberto.cursospringboot.dto.ClienteDTO;
import roberto.cursospringboot.repositories.ClienteRepository;
import roberto.cursospringboot.services.exceptions.DataIntegrityException;
import roberto.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //no Spring ele serve para 'repo' ser automaticamente instanciada
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id); //vai no banco e busca.
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				}
		
	//parecido com o insert, porém este nao precisar pegar o null pois já há id.
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());//verifica se o obj existe, usamos a exceção do método...
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void deleteById(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {//mostrar uma exceção criada por nós
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
		
	}
	
	public List<Cliente> findAll (){
		return repo.findAll();
	}
	//retorna uma página de categorias - Page e PageRequest são do Spring!
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //direction tem que converter de String para Direction
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest); //sobrecarga de métodos!
	}
	
	//neste momento nao estamos fazerndo, portanto estamos lançando uma exceção caso ele seja chamado.
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
		
	}
	//nao precisa ficar exposto
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
