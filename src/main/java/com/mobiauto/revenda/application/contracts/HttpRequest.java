package com.mobiauto.revenda.application.contracts;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HttpRequest<B> {
  private Optional<B> body;
}
