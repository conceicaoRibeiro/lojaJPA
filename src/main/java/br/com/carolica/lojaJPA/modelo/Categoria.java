package br.com.carolica.lojaJPA.modelo;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")

public class Categoria {

	@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "tipo");
	}

	public String getNome() {
		return this.id.getNome();
	}

	public void setNome(String nome) {
		this.id.getNome();
	}

}