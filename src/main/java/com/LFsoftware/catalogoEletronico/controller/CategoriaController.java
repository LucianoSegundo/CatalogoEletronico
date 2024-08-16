package com.LFsoftware.catalogoEletronico.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.LFsoftware.catalogoEletronico.dto.CategoriaDTO;
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
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		List<CategoriaDTO> categorias = categoriaServi.listarCategorias();
		
		return ResponseEntity.ok(categorias);
	};
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CategoriaDTO> retornarCategoria(@PathVariable Long id){
		return ResponseEntity.ok(categoriaServi.retornarCategoria(id));
	};
	
	@PostMapping()
	public ResponseEntity<CategoriaDTO> inserirCategoria(@RequestBody CategoriaDTO dto){
		 dto= categoriaServi.inserirCategoria(dto);
		 // configurando para que seja retornado com o status 201 e com o endereço do recuros no r=cabeçalho da resposta
		 URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();   	
		 
		return ResponseEntity.created(uri).body(dto);
	};

}
