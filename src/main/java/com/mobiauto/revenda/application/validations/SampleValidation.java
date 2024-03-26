package com.mobiauto.revenda.application.validations;

import java.util.ArrayList;
import java.util.List;

import com.mobiauto.revenda.application.contracts.Validations;

public class SampleValidation implements Validations {
  private List<String> errors = new ArrayList<>();
  private Object object;

  public SampleValidation of(Object object) {
    SampleValidation validation = new SampleValidation();
    validation.object = object;
    return validation;
  }

  public SampleValidation notNull() {
    if (object == null) {
      errors.add("Não pode ser nulo");
    }
    return this;
  }

  public SampleValidation notEmpty() {
    if (object == null || object.toString().isEmpty()) {
      errors.add("Não pode ser vazio");
    }
    return this;
  }

  public SampleValidation isString() {
    if (!(object instanceof String)) {
      errors.add("Deve ser uma string");
    }
    return this;
  }

  public SampleValidation isInteger() {
    if (!(object instanceof Integer)) {
      errors.add("Deve ser um inteiro");
    }
    return this;
  }

  public SampleValidation sizeIs(int size) {
    if (!(object instanceof String) || ((String) object).length() != size) {
      errors.add("Deve ter " + size + " caracteres");
    }
    return this;
  }

  public String valid() {
    return this.errors.isEmpty() ? "" : String.join(",", errors) + ".";
  }
}
