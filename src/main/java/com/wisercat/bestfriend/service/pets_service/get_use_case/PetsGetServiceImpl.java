package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetsGetServiceImpl implements PetsGetService {

    private final PetsGetRepository repository;
    private final Mapper<PetDto, Pet> mapper;

    @Override
    public PetDto getById(Long id) {
        return mapper.toDto(repository.getById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Pet with id (%s) has not been found", id))));

    }
    @Override
    public PetDto getPetByIdByUsername (String username, Long id){
        return mapper.toDto(repository.getPetByIdAndOwnerUsername(id, username)
                .orElseThrow(() ->new NotFoundException("This pet is not found")));
    }

    @Override
    public Page<PetDto> getAll(Pageable pageable) {
        Page<Pet> pets = repository.findAll(pageable);

        return new PageImpl<>(pets.getContent().stream().map(mapper::toDto).toList(),
                pageable,
                pets.getTotalElements());
    }

    @Override
    public Page<PetDto> getUserPetsByPages(String username, Pageable pageable) {
        Page<Pet> pets = repository.getAllByOwnerUsername(username, pageable);

        return new PageImpl<>(pets.getContent().stream().map(mapper::toDto).toList(),
                pageable,
                pets.getTotalElements());
    }
}

