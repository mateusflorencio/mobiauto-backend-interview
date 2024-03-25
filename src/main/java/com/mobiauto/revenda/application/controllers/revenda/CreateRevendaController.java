package com.mobiauto.revenda.application.controllers.revenda;

import java.util.Optional;

import com.mobiauto.revenda.application.contracts.Controllers;
import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;

public class CreateRevendaController implements Controllers<CreateRevendaRequest>{
  public HttpResponse handle(HttpRequest<CreateRevendaRequest> request) {
    try {
      Optional<CreateRevendaRequest> body = request.getBody();
      if(Optional.ofNullable(body).isEmpty()) {
        return HttpResponse.badRequest().body("Corpo da requisição não pode ser vazio");
      }
      return HttpResponse.ok();
    } catch (Exception e) {
      return HttpResponse.internalServerError().body("Erro interno no servidor");
    }
  }
}
