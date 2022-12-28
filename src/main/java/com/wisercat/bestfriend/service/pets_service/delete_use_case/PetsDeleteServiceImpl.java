package com.wisercat.bestfriend.service.pets_service.delete_use_case;

import com.wisercat.bestfriend.controller.pets_controller.delete_use_case.PetsDeleteService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetsDeleteServiceImpl implements PetsDeleteService {
    private final PetsDeleteRepository repository;
    private final Mapper<PetDto, Pet> mapper;
    @Override
    public PetDto delete(String username, Long id) {
        return mapper.toDto(repository.delete(username, id));
    }
}
