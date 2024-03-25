package com.mobiauto.revenda.domain.models.revenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RevendaModel {
  private Number id;
  private String cnpj;
  private String nome;

  public RevendaModel(String cnpj, String nome, Number id) {
    this.cnpj = cnpj;
    this.nome = nome;
    this.id = id;
  }
}
