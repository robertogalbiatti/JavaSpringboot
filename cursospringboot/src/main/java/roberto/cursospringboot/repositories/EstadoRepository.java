package roberto.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roberto.cursospringboot.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
