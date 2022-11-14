package com.springboot.form.app.springbootform.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Requerido {
  // String message() default "Campo apellido es requerido";
  String message() default "{Requerido.user.apellido}"; // messages.properties

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
