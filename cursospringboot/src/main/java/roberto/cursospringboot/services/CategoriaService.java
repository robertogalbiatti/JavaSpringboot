package roberto.cursospringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.repositories.CategoriaRepository;
import roberto.cursospringboot.services.exceptions.DataIntegrityException;
import roberto.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //no Spring ele serve para 'repo' ser automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); //vai no banco e busca.
		return obj.orElseThrow(() -> new ObjectNotFoundException( //lança a exceção se não encontrar!!
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
				}
	
	public Categoria insert(Categoria obj) { //id deve ser nulo para que seja uma inserção, do contrário é uma atualização.
		obj.setId(null);
		return repo.save(obj);
	}
	
	//parecido com o insert, porém este nao precisar pegar o null pois já há id.
	public Categoria update(Categoria obj) {
		find(obj.getId());//verifica se o obj existe, usamos a exceção do método...
		return repo.save(obj);
	}
	
	public void deleteById(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {//mostrar uma exceção criada por nós
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll (){
		return repo.findAll();
	}
}
