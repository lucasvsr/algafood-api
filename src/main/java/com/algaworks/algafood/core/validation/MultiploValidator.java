package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> { // TODAS AS CLASSES DE VALIDAÇÃO DE ANOTAÇÕES DEVEM TERMINAR COM VALIDATOR, NÃO É ALGO DO SPRING, SÓ PADRÃO DE MERCADO
	
	private int numero;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) { //ESTE MÉTODO É EXECUTADO NA INICIALIZAÇÃO DA CLASSE
		
		this.numero = constraintAnnotation.numero();
		
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) { // ESTE MÉTODO DEVE CONTER A LÓGICA DE VALIDAÇÃO DA ANOTAÇÃO
		
		boolean valido = true;
		
		if(value != null) {
			
			var decimal = BigDecimal.valueOf(value.doubleValue());
			var multiplo = BigDecimal.valueOf(this.numero);
			
			var resto = decimal.remainder(multiplo);
			
			valido = BigDecimal.ZERO.compareTo(resto) == 0;
			
		}
		
		return valido;
		
	}

}
