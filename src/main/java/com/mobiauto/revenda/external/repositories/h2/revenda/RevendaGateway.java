package com.mobiauto.revenda.external.repositories.h2.revenda;

import java.util.Optional;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.data.dtos.revenda.RevendaDTO;
import com.mobiauto.revenda.data.repositories.revenda.CreateRevendaRepository;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public class RevendaGateway implements CreateRevendaRepository, FindByCnpjRepository {
  private final RevendaRepository revendaRepository;
  private final RevendaMapper revendaMapper;

  public RevendaGateway(RevendaRepository revendaRepository, RevendaMapper revendaMapper) {
    this.revendaRepository = revendaRepository;
    this.revendaMapper = revendaMapper;
  }

  @Override
  public RevendaModel create(CreateRevendaDto createRevendaDto) {
    RevendaEntity revendaEntity = revendaMapper.toEntity(new RevendaDTO(createRevendaDto.nome(), createRevendaDto.cnpj()));
    RevendaEntity savedRevendaEntity = revendaRepository.save(revendaEntity);
    return revendaMapper.toModel(savedRevendaEntity);
  }

  @Override
  public Optional<RevendaModel> findByCnpj(String cnpj) {
    return revendaRepository.findByCnpj(cnpj).map(revendaMapper::toModel);
  }
}
