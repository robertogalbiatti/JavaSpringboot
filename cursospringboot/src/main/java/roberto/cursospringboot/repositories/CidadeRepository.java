package roberto.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roberto.cursospringboot.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
