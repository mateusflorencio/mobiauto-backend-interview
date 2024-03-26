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
import com.mobiauto.revenda.application.contracts.Validations;
import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.contracts.revenda.CreateRevenda;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

// TODO: Verificar porque esta retornando erro duas vezes nos testes de validação
class CreateRevendaControllerTest {

  @Mock
  private CreateRevenda createRevenda;

  @Mock
  private Validations validations;

  @InjectMocks
  private CreateRevendaController createRevendaController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Deve retornar um status 400 por esta com o corpo da requisição vazio")
  void deveRetornarStatus400PorCorpoDaRequisicaoVazio() {
    HttpRequest<CreateRevendaRequest> request = new HttpRequest<>(null);
    HttpResponse response = createRevendaController.handle(request);
    assertEquals("Corpo da requisição não pode ser vazio", response.getBody());
    assertEquals(400, response.getStatus());
  }

  @Test
  @DisplayName("Deve retornar um status 400 caso cnpj seja nulo")
  void deveRetornarStatus400PorCnpjNulo() {
    when(validations.of("nome")).thenReturn(validations);
    when(validations.notNull()).thenReturn(validations);
    when(validations.valid()).thenReturn("");

    when(validations.of(null)).thenReturn(validations);
    when(validations.sizeIs(14)).thenReturn(validations);
    when(validations.valid()).thenReturn("Erro no cnpj");

    HttpRequest<CreateRevendaRequest> request = new HttpRequest<>(new CreateRevendaRequest(null, "nome"));
    HttpResponse response = createRevendaController.handle(request);

    assertEquals("Erro no cnpj Erro no cnpj", response.getBody());
    assertEquals(400, response.getStatus());
  }

  @Test
  @DisplayName("Deve retornar um status 400 caso nome seja nulo")
  void deveRetornarStatus400PorNomeNulo() {
    when(validations.of("12345678901234")).thenReturn(validations);
    when(validations.notNull()).thenReturn(validations);
    when(validations.sizeIs(14)).thenReturn(validations);
    when(validations.valid()).thenReturn("");

    when(validations.of(null)).thenReturn(validations);
    when(validations.notNull()).thenReturn(validations);
    when(validations.valid()).thenReturn("Erro no nome");
    HttpRequest<CreateRevendaRequest> request = new HttpRequest<>(new CreateRevendaRequest("12345678901234", null));

    HttpResponse response = createRevendaController.handle(request);

    assertEquals("Erro no nome Erro no nome", response.getBody());
    assertEquals(400, response.getStatus());
  }

  @Test
  @DisplayName("Deve chamar createRevenda com sucesso")
  void deveChamarCreateRevendaComSucesso() throws RegistredException {
    HttpRequest<CreateRevendaRequest> request = new HttpRequest<>(new CreateRevendaRequest("12345678901234", "nome"));
    RevendaModel revendaModel = new RevendaModel("cnpj", "nome", 1);
    when(validations.of("nome")).thenReturn(validations);
    when(validations.notNull()).thenReturn(validations);
    when(validations.valid()).thenReturn("");
    when(validations.of("12345678901234")).thenReturn(validations);
    when(validations.sizeIs(14)).thenReturn(validations);
    when(createRevenda.create(new CreateRevendaDto("nome", "12345678901234"))).thenReturn(revendaModel);

    HttpResponse response = createRevendaController.handle(request);

    assertEquals(201, response.getStatus());
    assertEquals(revendaModel, response.getBody());
  }
}
