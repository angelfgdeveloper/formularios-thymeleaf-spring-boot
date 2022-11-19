package com.springboot.form.app.springbootform.services;

import java.util.List;

import com.springboot.form.app.springbootform.models.domain.Role;

public interface IRoleService {
  public List<Role> listar();
  public Role obtenerPorId(Integer id);
}
