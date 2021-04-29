package br.com.alura.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "PRODUTO_SIMILARIDADE")
public class ProdutoSimilaridade {

	@EmbeddedId
	private ProdutoSimilaridadeId id;

	private Boolean ruim;

	public ProdutoSimilaridade() {
	}
	
	public ProdutoSimilaridade(Long produtoId, Long produtoReferenciaId, Boolean ruim) {
		this.id = new ProdutoSimilaridadeId(produtoId, produtoReferenciaId);
		this.ruim = ruim;
	}

	public ProdutoSimilaridadeId getId() {
		return id;
	}

	public void setId(ProdutoSimilaridadeId id) {
		this.id = id;
	}

	public Boolean getRuim() {
		return ruim;
	}

	public void setRuim(Boolean ruim) {
		this.ruim = ruim;
	}

}
