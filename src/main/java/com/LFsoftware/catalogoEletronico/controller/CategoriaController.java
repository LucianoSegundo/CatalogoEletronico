package com.LFsoftware.catalogoEletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LFsoftware.catalogoEletronico.dto.CategoriaDTO;
import com.LFsoftware.catalogoEletronico.service.CategoriaService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaServi;
	
	public CategoriaController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		List<CategoriaDTO> categorias = categoriaServi.listarCategorias();
		
		return ResponseEntity.ok(categorias);
	};
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CategoriaDTO> retornarCategoria(@PathVariable Long id){
		return ResponseEntity.ok(categoriaServi.retornarCategoria(id));
	};

}
