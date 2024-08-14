package com.LFsoftware.catalogoEletronico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFsoftware.catalogoEletronico.entidades.Categoria;
import com.LFsoftware.catalogoEletronico.repositorios.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public CategoriaService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Categoria> listarCategorias(){
		return categoriaRepo.findAll();
	}
}
