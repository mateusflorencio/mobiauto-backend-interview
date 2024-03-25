package com.mobiauto.revenda.domain.usecases.revenda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.data.repositories.revenda.CreateRevendaRepository;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;
import com.mobiauto.revenda.domain.usecases.CreateRevendaUseCase;

class CreateRevendaUsecaseTest {

  @Mock
  private CreateRevendaRepository createRevendaRepository;

  @Mock
  private FindByCnpjRepository findByCnpjRepository;

  @InjectMocks
  private CreateRevendaUseCase createRevendaUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Deve lançar uma exceção caso o CNPJ já esteja cadastrado")
  void deveLancarUmErroCasoARevendaJaEstejaCadastrada() {
    RevendaModel revenda = new RevendaModel("123456789", "any_nome", 1);
    Optional<RevendaModel> optionalRevenda = Optional.of(revenda);
    when(findByCnpjRepository.findByCnpj("123456789")).thenReturn(optionalRevenda);

    assertThrows(RegistredException.class, () -> {
      createRevendaUseCase.create(new CreateRevendaDto("any_nome", "123456789"));
    });
  }

  @Test
  @DisplayName("Deve criar uma revenda")
  void deveCriarUmaRevenda() throws RegistredException {
    RevendaModel revenda = new RevendaModel("123456789", "any_nome", 1);
    Optional<RevendaModel> optionalRevenda = Optional.empty();
    when(findByCnpjRepository.findByCnpj("123456789")).thenReturn(optionalRevenda);
    when(createRevendaRepository.create(new CreateRevendaDto("any_nome", "123456789"))).thenReturn(revenda);

    RevendaModel revendaCriada = createRevendaUseCase.create(new CreateRevendaDto("any_nome", "123456789"));

    assertEquals(revenda, revendaCriada);
  }

  @Test
  @DisplayName("Deve chamar findByCnpjRepository com o cnpj correto")
  void deveChamarFindByCnpjRepositoryComOCnpjCorreto() throws RegistredException {

    createRevendaUseCase.create(new CreateRevendaDto("any_nome", "123456789"));

    // Verifica se o método findByCnpj foi chamado com o cnpj correto
    verify(findByCnpjRepository).findByCnpj("123456789");

  }

  @Test
  @DisplayName("Deve chamar createRevendaRepository com o dto correto")
  void deveChamarCreateRevendaRepositoryComODtoCorreto() throws RegistredException {
    CreateRevendaDto createRevendaDto = new CreateRevendaDto("any_nome", "123456789");

    createRevendaUseCase.create(createRevendaDto);

    verify(createRevendaRepository).create(createRevendaDto);
  }
}
