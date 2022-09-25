package com.springboot.form.app.springbootform.controllers;

// import java.util.HashMap;
// import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.app.springbootform.models.domain.Usuario;

@Controller
@SessionAttributes("user") // nombre del objeto que queremos que persista
public class FormController {

  @GetMapping("/form")
  public String form(Model model) {
    Usuario usuario = new Usuario();

    // Nombres por default
    usuario.setNombre("Luis");
    usuario.setApellido("FG");
    usuario.setIdentificador("123.456.789-K"); // Se pierde al pasar a la vista

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
    Model model,
    SessionStatus status
  ) {
    model.addAttribute("titulo", "Resultado del formulario");

    if (result.hasErrors()) { 
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
    model.addAttribute("usuario", usuario);
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
