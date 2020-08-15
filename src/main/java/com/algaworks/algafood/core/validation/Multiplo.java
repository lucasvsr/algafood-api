package com.algaworks.algafood.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_PARAMETER, TYPE_USE })
@Constraint(validatedBy = { MultiploValidator.class }) //AQUI FICAM AS CLASSES DE VALIDAÇÃO DA ANOTAÇÃO
public @interface Multiplo {
	
	String message() default "Múltiplo inválido"; //VC PODE COLOCAR A MENSAGEM AQUI OU MENCIONAR A MENSAGEM QUE VC CRIOU NO messages.properties

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	int numero();

}
