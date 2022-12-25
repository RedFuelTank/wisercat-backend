package com.wisercat.bestfriend.service.mapper.pet;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PetMapper implements Mapper<PetDto, Pet> {
    @Override
    public PetDto toDto(Pet pet) {
        return new PetDto(
                pet.getId(),
                pet.getOwnerUsername(),
                pet.getCode(),
                pet.getName(),
                pet.getPetType(),
                pet.getFurColor(),
                pet.getCountryOrigin()
        );
    }

    @Override
    public Pet toEntity(PetDto petDto) {
        return null;
    }
}
