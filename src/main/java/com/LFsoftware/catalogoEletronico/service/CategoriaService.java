package com.LFsoftware.catalogoEletronico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LFsoftware.catalogoEletronico.dto.CategoriaDTO;
import com.LFsoftware.catalogoEletronico.entidades.Categoria;
import com.LFsoftware.catalogoEletronico.repositorios.CategoriaRepository;
import com.LFsoftware.catalogoEletronico.service.excecoes.BandoDadosException;
import com.LFsoftware.catalogoEletronico.service.excecoes.EntidadeNaoEncontradaexception;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public CategoriaService() {

	}

	@Transactional(readOnly = true)
	public Page<CategoriaDTO> listarCategoriasPaginadas(PageRequest pageRequest) {
		Page<Categoria> lista = categoriaRepo.findAll(pageRequest);

		return lista.map(x -> new CategoriaDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoriaDTO retornarCategoria(Long id) {
		var resposta = categoriaRepo.findById(id);
		// se o objeto não estver no banco será lançada uma excessão
		Categoria categoria = resposta.orElseThrow(() -> new EntidadeNaoEncontradaexception("entidade não encontrada"));
		return new CategoriaDTO(categoria);

	}

	@Transactional
	public CategoriaDTO inserirCategoria(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
		categoria = categoriaRepo.save(categoria);

		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO dto) {
		try {
			// lembrar que o getreferencebyid não funciona fora de uma transação
			Categoria entidade = categoriaRepo.getReferenceById(id);
			entidade.setNome(dto.getNome());
			entidade = categoriaRepo.save(entidade);
			return new CategoriaDTO(entidade);

		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaexception("Nenhuma entidade com esse ID = " + id + " foi encontrada");
		}
	}

	public void deletarCategoria(Long id) {
		try {
			categoriaRepo.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaexception("Nenhuma entidade com esse ID = " + id + " foi encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new BandoDadosException("violação de integridade");
		}
	}
}
