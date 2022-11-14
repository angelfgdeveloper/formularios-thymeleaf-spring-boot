package com.springboot.form.app.springbootform.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    // if (value == null || value.isEmpty() || value.isBlank()) {
    if (value == null || !StringUtils.hasText(value)) { // distitno de blank y vacio
      return false;
    }

    return true;
  }
  
}
