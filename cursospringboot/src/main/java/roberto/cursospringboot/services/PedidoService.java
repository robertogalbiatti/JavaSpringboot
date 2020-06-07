package roberto.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roberto.cursospringboot.domain.Pedido;
import roberto.cursospringboot.repositories.PedidoRepository;
import roberto.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired // no Spring ele serve para 'repo' ser automaticamente instanciada
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id); // vai no banco e busca.
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
