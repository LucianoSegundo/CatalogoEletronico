package com.LFsoftware.catalogoEletronico.dto;

import com.LFsoftware.catalogoEletronico.entidades.Categoria;

public class CategoriaDTO {

	private Long id;
	private String nome;
	
	public CategoriaDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}
	public CategoriaDTO(Categoria categoria) {
		this.nome = categoria.getNome();
		this.id = categoria.getId();
			}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
