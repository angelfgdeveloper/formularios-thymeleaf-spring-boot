package com.springboot.form.app.springbootform.Validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.form.app.springbootform.models.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

  // Validador de forma personalizada
  @Override
  public boolean supports(Class<?> clazz) {
    return Usuario.class.isAssignableFrom(clazz); // Validar al tipo de usuario
  }

  @Override
  public void validate(Object target, Errors errors) {
    Usuario user = (Usuario) target; // cast

    // Forma 1 => mas directo
    //ValidationUtils.rejectIfEmpty(errors, "nombre", "NotEmpty.user.nombre");

    // Forma 2
    // if (user.getNombre().isEmpty()) {
    //   errors.rejectValue("nombre", "NotEmpty.user.nombre");
    // }

    // Forma 3
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.user.nombre");

    // if (!user.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")) {
    //   errors.rejectValue("identificador", "pattern.user.identificador");
    // }
  }
  
}
