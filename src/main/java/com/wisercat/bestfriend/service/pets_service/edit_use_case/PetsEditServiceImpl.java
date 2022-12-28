package com.wisercat.bestfriend.service.pets_service.edit_use_case;

import com.wisercat.bestfriend.controller.pets_controller.edit_use_case.PetsEditService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsEditServiceImpl implements PetsEditService {
    private final PetsEditRepository repository;
    private final Mapper<PetDto, Pet> mapper;


    @Override
    public PetDto edit(PetDto petDto) {
        return mapper.toDto(
                repository.edit(
                        mapper.toEntity(petDto)
                )
        );
    }
}
