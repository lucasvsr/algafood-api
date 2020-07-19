package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeSemEstadoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoJaCadastradoException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);

	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now())
											  .mensagem("O tipo de mídia não é suportado").build();

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);

	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
		
		Problema problema = Problema.builder().dataHora(LocalDateTime.now())
				  							  .mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
		
	}
	
	@ExceptionHandler(CidadeSemEstadoException.class)
	public ResponseEntity<?> tratarCidadeSemEstadoException(CidadeSemEstadoException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now())
				  							  .mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(problema);
		
	}
	
	@ExceptionHandler(EstadoJaCadastradoException.class)
	public ResponseEntity<?> tratarEstadoJaCadastradoException(EstadoJaCadastradoException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now())
				  							  .mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
		
	}
	
	@ExceptionHandler(CidadeJaCadastradaException.class)
	public ResponseEntity<?> tratarCidadeJaCadastradaException(CidadeJaCadastradaException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now())
				  							  .mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
		
	}

}
