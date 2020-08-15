package com.algaworks.algafood.api.exceptionhandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeSemEstadoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoJaCadastradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String CAMPOS_INVALIDOS = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

	private static final String MSG_ERRO_GENERICA_USER_FINAL = "Ocorreu um erro inesperado no sistema. Tente novamente e se o problema"
			+ " persistir, entre em contato com o administrador do sistema.";
	
	@Autowired
	private MessageSource source;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagem = CAMPOS_INVALIDOS;
		List<Problem.Object> objects = getErrors(ex.getBindingResult());
		
		Problem problem = createProblemBuilder(status, ProblemType.DADOS_INVALIDOS, 
											   mensagem, mensagem)
						  .objects(objects).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}

	private List<Problem.Object> getErrors(BindingResult bindingResult) {
		
		return bindingResult.getAllErrors()
									   .stream().map(objectError -> {
										   
										   String message = source.getMessage(objectError, LocaleContextHolder.getLocale());
										   String name = objectError.getObjectName();
										   
										   if(objectError instanceof FieldError) {
											   
											   name = ((FieldError) objectError).getField();
											   
										   }
										   	
										   return Problem.Object.builder()
										   				 .name(name)
											   			 .userMessage(message).build();
										   })
									   .collect(Collectors.toList());
	}
	
	@ExceptionHandler(ValidacaoException.class)
	protected ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
		
		String mensagem = CAMPOS_INVALIDOS;
		List<Problem.Object> objects = getErrors(ex.getBindingResult());
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = createProblemBuilder(status, ProblemType.DADOS_INVALIDOS, mensagem, mensagem)
							.objects(objects)
							.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		
		String detail = MSG_ERRO_GENERICA_USER_FINAL;
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		Problem problem = createProblemBuilder(status,
											   ProblemType.ERRO_DE_SISTEMA, detail, null).build();
		
		ex.printStackTrace();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
									   status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String URI = ((ServletWebRequest)request).getRequest().getRequestURI();
		String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente", URI);
		
		Problem problem = createProblemBuilder(status,
				   							   ProblemType.RECURSO_NAO_ENCONTRADO,
				   							   detail, null).build();
		
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof NumberFormatException) {
			
			return handleMethodArgumentTypeMismatchException((NumberFormatException) rootCause, headers, status, request, ex.getRequiredType().getSimpleName());
			
		}
		
		Problem problem = createProblemBuilder(status,
				   ProblemType.MENSAGEM_INCOMPREENSIVEL,
				   "A URL está invalida", null).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
		
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			NumberFormatException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request, String tipoParametro) {
		
		//NÃO FICOU COMO FOI DEFINIDO NO DESAFIO 8.25
		
		String URI = ((ServletWebRequest)request).getRequest().getRequestURI();
		String parametro = pegarTextoEntreAspas(rootCause.getLocalizedMessage());
		String texto = "O recurso '%s' deve receber um parâmetro do tipo %s e recebeu '%s'";
		String detail = String.format(texto, URI, tipoParametro, parametro);
	
		Problem problem = createProblemBuilder(status, 
											   ProblemType.MENSAGEM_INCOMPREENSIVEL,
											   detail, null).build();
		
		return handleExceptionInternal(rootCause, problem, headers, status, request);
		
	}

	private String pegarTextoEntreAspas(String localizedMessage) {
		String regex = "\"([^\"]*)\""; // regex com um grupo entre aspas
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(localizedMessage); // linha é a variável que contém a linha que foi lida do arquivo
		String textoEntreAspas = null;
		
		if (matcher.find()) {
		    textoEntreAspas = matcher.group(1); // obtém o grupo lido da regex
		}
		
		return textoEntreAspas;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
			
		} else if (rootCause instanceof PropertyBindingException) {
			
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
			
		}

		Problem problem = createProblemBuilder(status,
											   ProblemType.MENSAGEM_INCOMPREENSIVEL,
											   "O corpo da requisição está inválido. Verique erro de sintaxe.", null).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				  .map(ref -> ref.getFieldName())
				  .collect(Collectors.joining("."));
		
		String detail = null;
		
		if(ex instanceof IgnoredPropertyException)
			detail = String.format("O campo '%s' não pode ser informado nesta requisição", path);
		
		if(ex instanceof UnrecognizedPropertyException)
			detail = String.format("O campo '%s' não existe", path);
		
		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail, null).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
								  .map(ref -> ref.getFieldName())
								  .collect(Collectors.joining("."));
		
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. "
		   		+ "Corrija e informe um valor compátivel com o tipo %s.", path, ex.getValue(),
		   																	   	ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL,
				   detail, null).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEstadoNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		Problem problem = createProblemBuilder(status, ProblemType.RECURSO_NAO_ENCONTRADO,
				e.getMessage(), e.getMessage()).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		Problem problem = createProblemBuilder(status, 
											   ProblemType.NEGOCIO_EXCEPTION, 
											   e.getMessage(),
											   null).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		
		Problem problem = createProblemBuilder(status,
											   ProblemType.ENTIDADE_EM_USO,
											   e.getMessage(),
											   "Não é possível remover. Em uso.").build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(CidadeSemEstadoException.class)
	public ResponseEntity<?> tratarCidadeSemEstadoException(CidadeSemEstadoException e, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = "A cidade não possui um estado";
		
		Problem problem = createProblemBuilder(status, ProblemType.NEGOCIO_EXCEPTION,
											   detail, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(EstadoJaCadastradoException.class)
	public ResponseEntity<?> tratarEstadoJaCadastradoException(EstadoJaCadastradoException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, ProblemType.NEGOCIO_EXCEPTION,
											   detail, detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(CidadeJaCadastradaException.class)
	public ResponseEntity<?> tratarCidadeJaCadastradaException(CidadeJaCadastradaException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, ProblemType.NEGOCIO_EXCEPTION,
											   detail, detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {

			body = Problem.builder().title(status.getReasonPhrase()).status(status.value()).build();

		} else if (body instanceof String) {

			body = Problem.builder().title((String) body).status(status.value()).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, 
														ProblemType problemType, 
														String detail, String userMessage) { 
		// ProblemBuilder é uma subclasse criada pelo @Builder da classe Problem (Lombok)

		return Problem.builder().status(status.value())
								.type(problemType.getUri())
								.title(problemType.getTitle())
								.detail(detail)
								.userMessage(userMessage == null ? MSG_ERRO_GENERICA_USER_FINAL : userMessage);
		

	}

}
