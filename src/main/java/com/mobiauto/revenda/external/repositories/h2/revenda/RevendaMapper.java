package com.mobiauto.revenda.external.repositories.h2.revenda;

import com.mobiauto.revenda.data.dtos.revenda.RevendaDTO;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public class RevendaMapper {
  RevendaEntity toEntity(RevendaDTO revendaDTO) {
    return new RevendaEntity(revendaDTO.cnpj(), revendaDTO.nome());
  }

  RevendaModel toModel(RevendaEntity revendaEntity) {
    return new RevendaModel(revendaEntity.getCnpj(), revendaEntity.getNome(), revendaEntity.getId());
  }
}
