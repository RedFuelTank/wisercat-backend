package com.wisercat.bestfriend.service.pets_service.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsAddServiceImpl implements PetsAddService {
    private final PetsAddRepository repository;

    @Override
    public PetDto save(PetDto petDto) {
        return repository.save(petDto);
    }
}
