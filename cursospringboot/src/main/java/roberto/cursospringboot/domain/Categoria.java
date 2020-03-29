package roberto.cursospringboot.domain;

import java.io.Serializable;

public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public Categoria() {	
	}

	public Categoria(Integer id, String nome) { //Feito direto no botão direito - source -Generate Constructor using fields
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {// aqui até linha 31 botão direito - source - Generate Getters and Setters (pros dois...)
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

	@Override
	public int hashCode() {//feito com botão direito - source - Generate hashCode and equals() (selecionado apenas o id)
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override 
	public boolean equals(Object obj) { //veio junto da linha 34 - o de cima é hash e este equals
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
