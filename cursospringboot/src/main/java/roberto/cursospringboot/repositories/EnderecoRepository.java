package roberto.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roberto.cursospringboot.domain.Endereco;

//Objeto que fará acesso aos dados do Objeto Categoria
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
