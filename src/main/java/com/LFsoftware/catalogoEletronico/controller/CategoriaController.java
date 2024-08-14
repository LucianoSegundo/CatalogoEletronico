package com.LFsoftware.catalogoEletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LFsoftware.catalogoEletronico.entidades.Categoria;
import com.LFsoftware.catalogoEletronico.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaServi;
	
	public CategoriaController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> categorias = categoriaServi.listarCategorias();
		
		return ResponseEntity.ok(categorias);
	};

}
