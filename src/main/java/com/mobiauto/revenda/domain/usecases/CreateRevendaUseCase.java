package com.mobiauto.revenda.domain.usecases;

import java.util.Optional;

import com.mobiauto.revenda.data.dtos.revenda.CreateRevendaDto;
import com.mobiauto.revenda.data.repositories.revenda.CreateRevendaRepository;
import com.mobiauto.revenda.data.repositories.revenda.FindByCnpjRepository;
import com.mobiauto.revenda.domain.contracts.revenda.CreateRevenda;
import com.mobiauto.revenda.domain.exceptions.RegistredException;
import com.mobiauto.revenda.domain.models.revenda.RevendaModel;

public class CreateRevendaUseCase implements CreateRevenda{
    private final CreateRevendaRepository createRevendaRepository;
    private final FindByCnpjRepository findByCnpjRepository;

    public CreateRevendaUseCase(CreateRevendaRepository createRevendaRepository, FindByCnpjRepository findByCnpjRepository) {
        this.createRevendaRepository = createRevendaRepository;
        this.findByCnpjRepository = findByCnpjRepository;
    }

    @Override
    public
    RevendaModel create(CreateRevendaDto createRevendaDto) throws RegistredException {
        Optional<RevendaModel> revenda = findByCnpjRepository.findByCnpj(createRevendaDto.cnpj());

        if(revenda.isPresent()) {
            throw new RegistredException("Revenda já cadastrada");
        }

        return createRevendaRepository.create(createRevendaDto);
    }
}
