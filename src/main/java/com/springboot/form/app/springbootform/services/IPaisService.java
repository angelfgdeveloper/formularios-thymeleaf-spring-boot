package com.springboot.form.app.springbootform.services;

import java.util.List;

import com.springboot.form.app.springbootform.models.domain.Pais;

public interface IPaisService {
  public List<Pais> listar();
  public Pais buscarPorId(Integer id);
}
