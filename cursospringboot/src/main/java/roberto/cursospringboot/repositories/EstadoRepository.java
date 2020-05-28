package roberto.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roberto.cursospringboot.domain.Estado;

//Objeto que fará acesso aos dados do Objeto Categoria
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
