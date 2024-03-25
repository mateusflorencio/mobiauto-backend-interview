package com.mobiauto.revenda.application.contracts;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HttpRequest<B> {
  private Optional<B> body;

  public HttpRequest(B body) {
    this.body = Optional.ofNullable(body);
  }
}
