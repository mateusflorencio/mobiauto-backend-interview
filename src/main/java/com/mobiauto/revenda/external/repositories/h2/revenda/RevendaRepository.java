package com.mobiauto.revenda.external.repositories.h2.revenda;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RevendaRepository extends JpaRepository<RevendaEntity, Long> {
  
  Optional<RevendaEntity> findByCnpj(String cnpj);
}
