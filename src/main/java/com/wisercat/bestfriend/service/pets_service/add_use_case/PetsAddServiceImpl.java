package com.wisercat.bestfriend.service.pets_service.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import com.wisercat.bestfriend.dto.pet.RegistrationPetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsAddServiceImpl implements PetsAddService {
    private final PetsAddRepository repository;
    private final Mapper<PetDto, Pet> mapper;

    @Override
    public PetDto save(RegistrationPetDto pet, String username) {
        PetDto petDto = new PetDto(username, pet.getCode(), pet.getName(), pet.getType(), pet.getFurColor(), pet.getCountryOrigin());
        return mapper.toDto(repository.save(mapper.toEntity(petDto)));
    }
}
