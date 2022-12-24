package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;

import java.util.List;

import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetsGetServiceImpl implements PetsGetService {

    private final PetsGetRepository repository;

    @Override
    public PetDto getById(Long id) {
        return repository.getById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Pet with id (%s) has not been found", id)
                ));
    }

    @Override
    public List<PetDto> getAll() {
        return repository.getAll();
    }
}
