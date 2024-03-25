package com.mobiauto.revenda.domain.usecases;

import java.util.Optional;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public class CreateRevendaUseCase {
  private final FindByCnpjRepository findByCnpjRepository;

  public CreateRevendaUseCase(FindByCnpjRepository findByCnpjRepository) {
    this.findByCnpjRepository = findByCnpjRepository;
  }

  public void create(CreateRevendaDto createRevendaDto) throws RegistredException {
    Optional<RevendaModel> revenda = findByCnpjRepository.findByCnpj(createRevendaDto.cnpj());

    if (revenda.isPresent()) {
      throw new RegistredException("Revenda já cadastrada");
    }
  }
}
