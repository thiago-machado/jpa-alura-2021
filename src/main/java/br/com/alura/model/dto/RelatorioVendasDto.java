package br.com.alura.model.dto;

import java.time.LocalDate;

public class RelatorioVendasDto {

	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;

	public RelatorioVendasDto(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

}
