package com.mobiauto.revenda.domain.contracts.revenda;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public interface CreateRevenda {
    RevendaModel create(CreateRevendaDto createRevendaDto) throws RegistredException;
}
