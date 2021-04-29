package br.com.alura.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "LIVROS")
public class Livro extends Produto {

	@Column(name = "AUTOR")
	private String autor;

	@Column(name = "EDITORA")
	private String editora;

	@Column(name = "PAGINAS")
	private Integer paginas;

	public Livro() {
	}

	public Livro(String autor, String editora, Integer paginas) {
		this.autor = autor;
		this.editora = editora;
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}

	public Integer getPaginas() {
		return paginas;
	}

}
