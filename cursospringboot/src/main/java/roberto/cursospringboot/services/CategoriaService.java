package roberto.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.repositories.CategoriaRepository;
import roberto.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //no Spring ele serve para 'repo' ser automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); //vai no banco e busca.
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
				}
	
	public Categoria insert(Categoria obj) { //id deve ser nulo para que seja uma inserção, do contrário é uma atualização.
		obj.setId(null);
		return repo.save(obj);
	}
}
