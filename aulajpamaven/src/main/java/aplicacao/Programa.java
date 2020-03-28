package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com"); //ID nulo para o próprio BD dar o ID
		Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
		Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");
		
		//salvando os dados no banco de dados: EntityManager cria a conexão com o BD e o Factory instancia os objetos.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa"); //isso vem do arquivo persistence.xml
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p1);//salva no BD
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		System.out.println("Pronto");
		
	}

}
