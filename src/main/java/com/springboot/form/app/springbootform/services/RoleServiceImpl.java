package com.springboot.form.app.springbootform.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.form.app.springbootform.models.domain.Role;

@Service
public class RoleServiceImpl implements IRoleService {

  private List<Role> roles;

  public RoleServiceImpl() {
    this.roles = new ArrayList<>();
    roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
    roles.add(new Role(2, "Usuario", "ROLE_USER"));
    roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
  }

  @Override
  public List<Role> listar() {
    return this.roles;
  }

  @Override
  public Role obtenerPorId(Integer id) {
    Role resultado = null;
    for (Role role : roles) {
      if (id == role.getId()) {
        resultado = role;
        break;
      }
    }

    return resultado;
  }

}
