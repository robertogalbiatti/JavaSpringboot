package roberto.cursospringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import roberto.cursospringboot.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
