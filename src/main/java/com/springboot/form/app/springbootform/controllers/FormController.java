package com.springboot.form.app.springbootform.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import java.util.HashMap;
// import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.app.springbootform.Validation.UsuarioValidador;
import com.springboot.form.app.springbootform.editors.NombreMayusculaEditor;
import com.springboot.form.app.springbootform.editors.PaisPropertyEditor;
import com.springboot.form.app.springbootform.editors.RolesEditor;
import com.springboot.form.app.springbootform.models.domain.Pais;
import com.springboot.form.app.springbootform.models.domain.Role;
import com.springboot.form.app.springbootform.models.domain.Usuario;
import com.springboot.form.app.springbootform.services.IPaisService;
import com.springboot.form.app.springbootform.services.IRoleService;

@Controller
@SessionAttributes("user") // nombre del objeto que queremos que persista
public class FormController {

  @Autowired
  private UsuarioValidador validador;

  @Autowired
  private IPaisService paisService;

  @Autowired
  private IRoleService roleService;

  @Autowired
  private PaisPropertyEditor paisEditor;

  @Autowired
  private RolesEditor rolesEditor;

  @InitBinder // Registra los validadores
  public void initBinder(WebDataBinder binder) {
    // binder.setValidator(validador);
    binder.addValidators(validador); // asignamos un nuevo validador

    // Modificar formato de fecha
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false); //Si es estricto, que no sea tolerante
    binder.registerCustomEditor(
      Date.class, 
      "fechaNacimiento",
      new CustomDateEditor(dateFormat, true) // true => no debe ser vacio
    );

    // Modificar nombre a mayusculas
    binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());

    // Pais
    binder.registerCustomEditor(Pais.class, "pais", paisEditor);

    // Roles
    binder.registerCustomEditor(Role.class, "roles", rolesEditor);
  }

  @ModelAttribute("listaPaises")
  public List<Pais> listaPaises() {
    // return Arrays.asList(
    //   new Pais(1, "ES", "España"),
    //   new Pais(2, "MX", "México"),
    //   new Pais(3, "CL", "Chile"),
    //   new Pais(4, "AR", "Argentina"),
    //   new Pais(5, "PR", "Perú"),
    //   new Pais(6, "CO", "Colombia"),
    //   new Pais(7, "VE", "Venezuela")
    //   );

    return paisService.listar();
  }

  @ModelAttribute("paises")
  public List<String> paises() {
    return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
  }

  @ModelAttribute("paisesMap")
  public Map<String, String> paisesMap() {
    Map<String, String> paises = new HashMap<String, String>();
    paises.put("ES", "España");
    paises.put("MX", "México");
    paises.put("CL", "Chile");
    paises.put("AR", "Argentina");
    paises.put("PR", "Perú");
    paises.put("CO", "Colombia");
    paises.put("VE", "Venezuela");
    return paises;
  }

  // Checkboxes
  @ModelAttribute("listaRolesString")
  public List<String> listaRolesString() {
    List<String> roles = new ArrayList<>();
    roles.add("ROLE_ADMIN");
    roles.add("ROLE_USER");
    roles.add("ROLE_MODERATOR");

    return roles;
  }

  @ModelAttribute("listaRolesMap")
  public Map<String, String> listaRolesMap() {
    Map<String, String> roles = new HashMap<String, String>();
    roles.put("ROLE_ADMIN", "Administrador");
    roles.put("ROLE_USER", "Usuario");
    roles.put("ROLE_MODERATOR", "Moderador");
    return roles;
  }

  @ModelAttribute("listaRoles")
  public List<Role> listaRoles() {
    return this.roleService.listar();
  }

  // Genero
  @ModelAttribute("genero")
  public List<String> genero() {
    return Arrays.asList("Hombre", "Mujer");
  }

  @GetMapping("/form")
  public String form(Model model) {
    Usuario usuario = new Usuario();

    // Nombres por default
    usuario.setNombre("Luis");
    usuario.setApellido("FG");
    usuario.setIdentificador("123.456.789-K"); // Se pierde al pasar a la vista
    usuario.setHabilitar(true);
    usuario.setValorSecreto("Algún valor secreto ****");

    usuario.setPais(new Pais(2, "MX", "México")); // Por default
    usuario.setRoles(Arrays.asList(
      new Role(2, "Usuario", "ROLE_USER")
      // new Role(3, "Moderador", "ROLE_MODERATOR")
    ));

    model.addAttribute("titulo", "Formulario de usuario");
    model.addAttribute("user", usuario); // Evitamos el error en el value del input
    return "form";
  }

  // #2 optimizado
  @PostMapping("/form")
  // BindingResult => Obtiene los errores del primer objeto a validar
  public String procesar(
    @Valid @ModelAttribute("user") Usuario usuario, // @ModelAttribute("user") cambiar el nombre de forma automatica
    BindingResult result, 
    Model model
    // SessionStatus status
  ) {
    //validador.validate(usuario, result); // Para añadir nuestros validadores personalizados
    
    if (result.hasErrors()) { 
      model.addAttribute("titulo", "Resultado del formulario");
      // #1 errores manuales
      // Valida si tiene errores
      // Map<String, String> errores = new HashMap<>(); // Forma manual de obtener errores
      // result.getFieldErrors().forEach(err -> {
      //   errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage())); // key => nombre del campo | value => mensaje de error
      // });

      // model.addAttribute("error", errores);

      return "form"; // Regresar a la vista
    }

    // Objeto completo
    // model.addAttribute("usuario", usuario);
    //status.setComplete(); // completar el proceso y elimina el objeto de la sesion 

    return "redirect:/ver";
  }

  @GetMapping("/ver")
  public String ver(
    @SessionAttribute(name = "user", required = false) Usuario usuario,
    Model model,
    SessionStatus status
  ) {

    if (usuario == null) {
      return "redirect:/form";
    }

    model.addAttribute("titulo", "Resultado del formulario");

    status.setComplete(); // completar el proceso y elimina el objeto de la sesion 
    return "resultado";
  }

  // #1
  // @PostMapping("/form")
  // public String procesar(
  //   Model model, 
  //   @RequestParam(name = "username") String username,
  //   @RequestParam(name = "email") String email,
  //   @RequestParam(name = "password") String password
  // ) {

  //   Usuario usuario = new Usuario(); // Entity o clase pojo
  //   usuario.setUsername(username);
  //   usuario.setEmail(email);
  //   usuario.setPassword(password);

  //   // model.addAttribute("username", username);
  //   // model.addAttribute("email", email);
  //   // model.addAttribute("password", password);

  //   // Objeto completo
  //   model.addAttribute("usuario", usuario);

  //   return "resultado";
  // }
  
}
