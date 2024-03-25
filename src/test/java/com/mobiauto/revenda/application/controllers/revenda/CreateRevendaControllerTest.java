package com.mobiauto.revenda.application.controllers.revenda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;

class CreateRevendaControllerTest {

  private CreateRevendaController createRevendaController;

  @BeforeEach
  void setUp() {
    createRevendaController = new CreateRevendaController();
  }

  @Test
  @DisplayName("Deve retornar um status 400 por esta com o corpo da requisição vazio")
  void deveRetornarStatus400PorCorpoDaRequisicaoVazio() {
    HttpRequest<CreateRevendaRequest> request = new HttpRequest(null);
    HttpResponse response = createRevendaController.handle(request);
    assertEquals("Corpo da requisição não pode ser vazio", response.getBody());
    assertEquals(400, response.getStatus());
  }

  @Test
  @DisplayName("Deve retornar um status 400 caso cnpj seja nulo")
  void deveRetornarStatus400PorCnpjNulo() {
    CreateRevendaRequest createRevendaRequest = new CreateRevendaRequest(null, "nome");
    HttpRequest<CreateRevendaRequest> request = new HttpRequest(createRevendaRequest);
    HttpResponse response = createRevendaController.handle(request);
    assertEquals("CNPJ não pode ser vazio", response.getBody());
    assertEquals(400, response.getStatus());
  }
}
