package com.mobiauto.revenda.application.controllers.revenda;

import java.util.Optional;

import com.mobiauto.revenda.application.contracts.Controllers;
import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;
import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.contracts.revenda.CreateRevenda;

public class CreateRevendaController implements Controllers<CreateRevendaRequest> {
  private final CreateRevenda createRevenda;

  public CreateRevendaController(CreateRevenda createRevenda) {
    this.createRevenda = createRevenda;
  }

  public HttpResponse handle(HttpRequest<CreateRevendaRequest> request) {

    try {
      Optional<CreateRevendaRequest> body = request.getBody();
      if (Optional.ofNullable(body).isEmpty()) {
        return HttpResponse.badRequest().body("Corpo da requisição não pode ser vazio");
      }

      CreateRevendaDto createRevendaDto = new CreateRevendaDto(body.get().nome(), body.get().cnpj());

      if (createRevendaDto.cnpj() == null) {
        return HttpResponse.badRequest().body("CNPJ não pode ser vazio");
      }

      if (createRevendaDto.nome() == null) {
        return HttpResponse.badRequest().body("Nome não pode ser vazio");
      }

      return HttpResponse.created().body(createRevenda.create(createRevendaDto));
    } catch (Exception e) {
      return HttpResponse.internalServerError().body("Erro interno no servidor");
    }
  }
}
