package com.springboot.form.app.springbootform.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.form.app.springbootform.models.domain.Pais;

@Service
public class PaiseServiceImpl implements IPaisService {

  private List<Pais> lista;

  public PaiseServiceImpl() {
    this.lista =  Arrays.asList(
      new Pais(1, "ES", "España"),
      new Pais(2, "MX", "México"),
      new Pais(3, "CL", "Chile"),
      new Pais(4, "AR", "Argentina"),
      new Pais(5, "PR", "Perú"),
      new Pais(6, "CO", "Colombia"),
      new Pais(7, "VE", "Venezuela")
      );
  }

  @Override
  public List<Pais> listar() {
    return lista;
  }

  @Override
  public Pais buscarPorId(Integer id) {
    Pais resultado = null;
    for (Pais pais : this.lista) {
      if (id == pais.getId()) {
        resultado = pais;
        break;
      }
    }

    return resultado;
  }
  
}
