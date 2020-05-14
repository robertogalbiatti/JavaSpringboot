package roberto.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roberto.cursospringboot.domain.Categoria;
import roberto.cursospringboot.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired //no Spring ele serve para 'repo' ser automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); //vai no banco e busca.
		return obj.orElse(null);
		}
}
