package com.mobiauto.revenda.data.repositories.revenda;

import java.util.Optional;

import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public interface FindByCnpjRepository {
  Optional<RevendaModel> findByCnpj(String cnpj);
}
