package br.com.alura.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "INFORMATICA")
public class Informatica extends Produto {

	@Column(name = "MARCA")
	private String marca;

	@Column(name = "MODELO")
	private String modelo;

	public Informatica() {
	}

	public Informatica(String marca, String modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}
}
