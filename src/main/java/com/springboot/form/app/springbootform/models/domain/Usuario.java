package com.springboot.form.app.springbootform.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

  // @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]")
  // [0-9] es igual a [\\d]
  // [.] o [,] 0 [.,]
  //@Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}") // Ejemplo: 123.456.789-K
  //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") // Ejemplo: 123.456.789-K
  private String identificador;

  //@NotEmpty(message = "El nombre no puede ser vacio") // Mensaje de error
  private String nombre;

  @NotEmpty
  private String apellido;

  @NotEmpty // Tiene que ser distinto de null
  @Size(min = 3, max = 8) // string largo
  private String username;

  @NotEmpty
  @Email(message = "El correo no es válido")
  private String email;

  @NotEmpty
  private String password;

  public Usuario() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

}
