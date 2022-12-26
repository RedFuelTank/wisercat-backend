package com.wisercat.bestfriend.service.mapper.pet;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.mapper.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class PetMapper implements Mapper<PetDto, Pet> {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public PetDto toDto(Pet pet) {
        return new PetDto(
                pet.getId(),
                pet.getOwner().getUsername(),
                pet.getCode(),
                pet.getName(),
                pet.getPetType(),
                pet.getFurColor(),
                pet.getCountryOrigin()
        );
    }

    @Override
    public Pet toEntity(PetDto petDto) {
        User user = manager.find(User.class, petDto.getOwnerUsername());
        return new Pet(
                petDto.getId(),
                user,
                petDto.getCode(),
                petDto.getName(),
                petDto.getType(),
                petDto.getFurColor(),
                petDto.getCountryOrigin()
        );
    }
}
