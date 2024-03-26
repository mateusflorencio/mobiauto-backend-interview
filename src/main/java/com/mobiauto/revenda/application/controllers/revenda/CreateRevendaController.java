package com.mobiauto.revenda.application.controllers.revenda;

import java.util.Optional;

import com.mobiauto.revenda.application.contracts.Controllers;
import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;
import com.mobiauto.revenda.application.contracts.Validations;
import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.contracts.revenda.CreateRevenda;

public class CreateRevendaController implements Controllers<CreateRevendaRequest> {
  private final CreateRevenda createRevenda;
  private final Validations validations;

  public CreateRevendaController(CreateRevenda createRevenda, Validations validations) {
    this.createRevenda = createRevenda;
    this.validations = validations;
  }

  public HttpResponse handle(HttpRequest<CreateRevendaRequest> request) {
    try {
      Optional<CreateRevendaRequest> body = request.getBody();
      if (Optional.ofNullable(body).isEmpty() || !body.isPresent()) {
        return HttpResponse.badRequest().body("Corpo da requisição não pode ser vazio");
      }

      CreateRevendaDto createRevendaDto = new CreateRevendaDto(body.get().nome(), body.get().cnpj());
      String nameErros = validations.of(createRevendaDto.nome()).notNull().valid();
      String cnpjErros = validations.of(createRevendaDto.cnpj()).notNull().sizeIs(14).valid();

      if (!nameErros.isEmpty() || !cnpjErros.isEmpty()) {
        return HttpResponse.badRequest().body(nameErros + " " + cnpjErros);
      }

      return HttpResponse.created().body(createRevenda.create(createRevendaDto));
    } catch (Exception e) {
      if (e.getMessage().equals("CNPJ já cadastrado")) {
        return HttpResponse.badRequest().body("CNPJ já cadastrado");
      }
      return HttpResponse.internalServerError().body("Erro interno no servidor");
    }
  }
}
