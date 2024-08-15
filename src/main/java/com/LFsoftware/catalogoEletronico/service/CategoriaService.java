package com.LFsoftware.catalogoEletronico.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LFsoftware.catalogoEletronico.dto.CategoriaDTO;
import com.LFsoftware.catalogoEletronico.entidades.Categoria;
import com.LFsoftware.catalogoEletronico.repositorios.CategoriaRepository;
import com.LFsoftware.catalogoEletronico.service.excecoes.EntidadeNaoEncontrada;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public CategoriaService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = true)
	public List<CategoriaDTO> listarCategorias() {

		List<CategoriaDTO> lista = categoriaRepo.findAll()
				.stream()
				.map(x -> new CategoriaDTO(x))
				.collect(Collectors.toList());
		return lista;
	}
	
	public CategoriaDTO retornarCategoria(Long id) {
		var resposta = categoriaRepo.findById(id);
		// se o objeto não estver no banco será lançada uma excessão
		Categoria categoria = resposta.orElseThrow(()-> new EntidadeNaoEncontrada("entidade não encontrada"));
		return new CategoriaDTO(categoria);
		
		}
}
