package roberto.cursospringboot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Produto  implements Serializable { //significa que os objetos podem ser convertidos para bytes, podendo assim ser gravados em arquivos, em redes...
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerando automaticamente estratégias dos Id's (chave primária)
	private Integer id;
	private String nome;
	private Double preco;
	
	@ManyToMany //no JPA quando é relação N-N coloca-se isso em um dos dois lados (aqui é no Produto)
	@JoinTable(name = "PRODUTO_CATEGORIA",              // e então essa notação define quem é a tabela que faz o N-N no banco
		joinColumns = @JoinColumn(name = "produto_id"), //esta é a chave estrangeira correspondente ao produto
		inverseJoinColumns = @JoinColumn(name = "categoria_id") //a outra chave estrangeira que referencia
			)
	private List<Categoria> categorias = new ArrayList<>();
	
	public Produto() {

	}

	public Produto(Integer id, String nome, Double preco) { //lembrando que categorias ja foi criada la em cima, nao poe no const.
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
