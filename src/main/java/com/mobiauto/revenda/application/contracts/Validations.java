package com.mobiauto.revenda.application.contracts;

public interface Validations {
    Validations notNull();
    Validations notEmpty();
    Validations sizeIs(int size);
    Validations isString();
    Validations isInteger();
    String valid();
    Validations of(Object object);
}
