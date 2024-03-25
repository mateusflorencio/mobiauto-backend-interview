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
  private String email;

  public RevendaModel(String cnpj, String email) {
    this.cnpj = cnpj;
    this.email = email;
  }
}
