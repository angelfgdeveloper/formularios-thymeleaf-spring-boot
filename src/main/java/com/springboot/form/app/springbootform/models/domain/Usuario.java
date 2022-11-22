package com.springboot.form.app.springbootform.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
// import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
// import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.lang.NonNull;

import com.springboot.form.app.springbootform.Validation.IdentificadorRegex;
import com.springboot.form.app.springbootform.Validation.Requerido;

public class Usuario {

  // @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]")
  // [0-9] es igual a [\\d]
  // [.] o [,] 0 [.,]
  //@Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}") // Ejemplo: 123.456.789-K
  //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") // Ejemplo: 123.456.789-K
  @IdentificadorRegex
  private String identificador;

  //@NotEmpty(message = "El nombre no puede ser vacio") // Mensaje de error
  private String nombre;

  //@NotEmpty // Tiene que ser distinto de null
  @Requerido // Etiqueta personalizada con uso de (Requerido y RequeridoValidador)
  private String apellido;

  @NotBlank // No puede estar vacio
  @Size(min = 3, max = 8) // string largo
  private String username;

  @NotEmpty
  @Email(message = "El correo no es válido")
  private String email;

  @NotNull
  @Min(5)
  @Max(5000)
  private Integer cuenta;

  @NotEmpty
  private String password;

  @NotNull // Para objetos
  @Past // Fecha pasada a la actual
  // @Future // Fecha futura
  // @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date fechaNacimiento;

  //@NotEmpty // strings
  // @Valid
  @NotNull
  private Pais pais;

  // private List<String> roles;
  @NotEmpty
  private List<Role> roles;

  private Boolean habilitar;

  @NotEmpty
  private String genero;

  private String valorSecreto;

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

  public Integer getCuenta() {
    return cuenta;
  }

  public void setCuenta(Integer cuenta) {
    this.cuenta = cuenta;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Pais getPais() {
    return pais;
  }

  public void setPais(Pais pais) {
    this.pais = pais;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Boolean getHabilitar() {
    return habilitar;
  }

  public void setHabilitar(Boolean habilitar) {
    this.habilitar = habilitar;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getValorSecreto() {
    return valorSecreto;
  }

  public void setValorSecreto(String valorSecreto) {
    this.valorSecreto = valorSecreto;
  }

}
