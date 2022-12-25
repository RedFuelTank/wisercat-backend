package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import com.wisercat.bestfriend.service.mapper.pet.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PetDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent()
                .stream().map(mapper::toDto).toList();
    }

    @Override
    public List<PetDto> getUserPetsByPages(String username, Pageable pageable) {
        return repository.getAllByOwnerUsername(username, pageable)
                .stream().map(mapper::toDto).toList();
    }
}

