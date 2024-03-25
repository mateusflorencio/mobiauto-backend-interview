package com.mobiauto.revenda.domain.usecases.revenda;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;
import com.mobiauto.revenda.domain.usecases.CreateRevendaUseCase;

class CreateRevendaTest {

  @Mock
  private FindByCnpjRepository findByCnpjRepository;

  @InjectMocks
  private CreateRevendaUseCase createRevendaUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Deve lançar uma exceção caso a revenda já esteja cadastrada")
  void deveLancarUmErroCasoARevendaJaEstejaCadastrada() {
    RevendaModel revenda = new RevendaModel("123456789", "any_nome");
    Optional<RevendaModel> optionalRevenda = Optional.of(revenda);
    when(findByCnpjRepository.findByCnpj("123456789")).thenReturn(optionalRevenda);

    assertThrows(RegistredException.class, () -> {
      createRevendaUseCase.create(new CreateRevendaDto("any_nome", "123456789"));
    });
  }
}
