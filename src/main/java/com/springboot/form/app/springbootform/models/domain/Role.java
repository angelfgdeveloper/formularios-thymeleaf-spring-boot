package com.springboot.form.app.springbootform.models.domain;

public class Role {
  private Integer id;
  private String nombre;
  private String role;

  public Role() {
  }

  public Role(Integer id, String nombre, String role) {
    this.id = id;
    this.nombre = nombre;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) { // Comparamos si es la misma instancia
      return true;
    }

    if (!(obj instanceof Role)) { // Sino es una instancia de Role
      return false;
    }

    Role role = (Role)obj;
    return this.id != null && this.id.equals(role.getId()); //Compara el id del objeto de la instancia
  }

}
