package br.com.alura.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProdutoSimilaridadeId implements Serializable {

	private Long produtoId;

	private Long produtoReferenciaId;

	public ProdutoSimilaridadeId() {
	}

	public ProdutoSimilaridadeId(Long produtoId, Long produtoReferenciaId) {
		this.produtoId = produtoId;
		this.produtoReferenciaId = produtoReferenciaId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getProdutoReferenciaId() {
		return produtoReferenciaId;
	}

	public void setProdutoReferenciaId(Long produtoReferenciaId) {
		this.produtoReferenciaId = produtoReferenciaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produtoId == null) ? 0 : produtoId.hashCode());
		result = prime * result + ((produtoReferenciaId == null) ? 0 : produtoReferenciaId.hashCode());
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
		ProdutoSimilaridadeId other = (ProdutoSimilaridadeId) obj;
		if (produtoId == null) {
			if (other.produtoId != null)
				return false;
		} else if (!produtoId.equals(other.produtoId))
			return false;
		if (produtoReferenciaId == null) {
			if (other.produtoReferenciaId != null)
				return false;
		} else if (!produtoReferenciaId.equals(other.produtoReferenciaId))
			return false;
		return true;
	}

}
