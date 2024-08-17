package com.LFsoftware.catalogoEletronico.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.LFsoftware.catalogoEletronico.service.excecoes.BandoDadosException;
import com.LFsoftware.catalogoEletronico.service.excecoes.EntidadeNaoEncontradaexception;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionhendler {

	// montando  a excessão personalizada enviada  no tentativa de acessar entidade não registrada.
	@ExceptionHandler(EntidadeNaoEncontradaexception.class)
	public ResponseEntity<StardartError> entidadNaoEncontrada(EntidadeNaoEncontradaexception e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StardartError erro = new StardartError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("recurso não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(BandoDadosException.class)
	public ResponseEntity<StardartError> bancoDadoserro(BandoDadosException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StardartError erro = new StardartError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("exceção de banco de dados");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
}
