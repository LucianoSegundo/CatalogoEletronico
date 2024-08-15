package com.LFsoftware.catalogoEletronico.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.LFsoftware.catalogoEletronico.service.excecoes.EntidadeNaoEncontrada;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionhendler {

	// montando  a excessão personalizada enviada  no tentativa de acessar entidade não registrada.
	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ResponseEntity<StardartError> entidadNaoEncontrada(EntidadeNaoEncontrada e, HttpServletRequest request){
		StardartError erro = new StardartError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("recurso não encontrado");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}
