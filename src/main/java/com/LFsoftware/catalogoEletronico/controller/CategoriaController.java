package com.LFsoftware.catalogoEletronico.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<CategoriaDTO>> listarCategorias(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction

			){
		// variaveiz opcionais são declaradas na url atravez da ? e concatenadas com &
		//?page=3&linesPerPage=2
		
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction)  ,orderBy);

		Page<CategoriaDTO> categorias = categoriaServi.listarCategoriasPaginadas(pagerequest);
		
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
	@PutMapping(value ="/{id}")
	public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO dto){
		dto = categoriaServi.atualizarCategoria(id, dto);
		 	
		return ResponseEntity.ok().body(dto);
	};
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
		categoriaServi.deletarCategoria(id);
		 	
		return ResponseEntity.noContent().build();
	};

}
