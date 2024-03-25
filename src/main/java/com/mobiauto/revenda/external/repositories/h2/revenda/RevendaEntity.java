package com.mobiauto.revenda.external.repositories.h2.revenda;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "revenda")
@Getter
@Setter
@NoArgsConstructor
public class RevendaEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public RevendaEntity(String cnpj, String nome) {
    this.cnpj = cnpj;
    this.nome = nome;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 14)
  private String cnpj;

  @Column(nullable = false, length = 20, name = "nome")
  private String nome;

}
