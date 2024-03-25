package com.mobiauto.revenda.application.controllers.revenda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mobiauto.revenda.application.contracts.HttpRequest;
import com.mobiauto.revenda.application.contracts.HttpResponse;
import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.contracts.revenda.CreateRevenda;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

class CreateRevendaControllerTest {

  @Mock
  private CreateRevenda createRevenda;

  @InjectMocks
  private CreateRevendaController createRevendaController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
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

  @Test
  @DisplayName("Deve retornar um status 400 caso nome seja nulo")
  void deveRetornarStatus400PorNomeNulo() {
    CreateRevendaRequest createRevendaRequest = new CreateRevendaRequest("cnpj", null);
    HttpRequest<CreateRevendaRequest> request = new HttpRequest(createRevendaRequest);
    HttpResponse response = createRevendaController.handle(request);
    assertEquals("Nome não pode ser vazio", response.getBody());
    assertEquals(400, response.getStatus());
  }

  @Test
  @DisplayName("Deve chamar createRevenda com sucesso")
  void deveChamarCreateRevendaComSucesso() throws RegistredException {
    CreateRevendaRequest createRevendaRequest = new CreateRevendaRequest("cnpj", "nome");
    HttpRequest<CreateRevendaRequest> request = new HttpRequest(createRevendaRequest);
    RevendaModel revendaModel = new RevendaModel("cnpj", "nome", 1);
    CreateRevendaDto createRevendaDto = new CreateRevendaDto("nome", "cnpj");
    when(createRevenda.create(createRevendaDto)).thenReturn(revendaModel);

    HttpResponse response = createRevendaController.handle(request);

    assertEquals(201, response.getStatus());
    assertEquals(revendaModel, response.getBody());
  }
}
