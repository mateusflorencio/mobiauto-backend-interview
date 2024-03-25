package com.mobiauto.revenda.data.repositories.revenda;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public interface CreateRevendaRepository {
  RevendaModel create(CreateRevendaDto createRevendaDto);
}
