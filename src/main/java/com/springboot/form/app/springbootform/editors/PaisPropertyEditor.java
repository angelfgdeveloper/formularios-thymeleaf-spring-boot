package com.springboot.form.app.springbootform.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.form.app.springbootform.services.IPaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

  @Autowired
  private IPaisService service;

  @Override
  public void setAsText(String idString) throws IllegalArgumentException {
    // if (idString != null && idString.length() > 0) {
    try {
      Integer id = Integer.parseInt(idString);
      this.setValue(service.buscarPorId(id));
    } catch (NumberFormatException e) {
      setValue(null);
    }
    // } else {
    //   setValue(null);
    // }
  }
  
}
