package com.wisercat.bestfriend.config.pets;

import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import com.wisercat.bestfriend.controller.pets_controller.edit_use_case.PetsEditService;
import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.RegistrationPetDto;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.mapper.Mapper;
import com.wisercat.bestfriend.service.pets_service.add_use_case.PetsAddRepository;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public final class WebPetsTestFactory {
    private WebPetsTestFactory() {}

    public static PetsGetService getPetsGetServiceImpl() {
        return new PetsGetService() {
            @Override
            public PetDto getById(Long id) {
                return null;
            }

            @Override
            public PetDto getPetByIdByUsername(String username, Long id) {
                return null;
            }

            @Override
            public Page<PetDto> getAll(Pageable pageable) {
                return null;
            }

            @Override
            public Page<PetDto> getUserPetsByPages(String username, Pageable pageable) {
                return null;
            }
        };
    }

    public static PetsAddService getPetsAddServiceImpl() {
        return new PetsAddService() {
            @Override
            public PetDto save(RegistrationPetDto pet, String username) {
                return null;
            }
        };
    }

    public static PetsGetRepository getPetsGetRepositoryImpl() {
        return new PetsGetRepository() {
            @Override
            public Optional<Pet> getById(Long id) {
                return null;
            }

            @Override
            public Page<Pet> getAllByOwnerUsername(String username, Pageable pageable) {
                return null;
            }

            @Override
            public Optional<Pet> getPetByIdAndOwnerUsername(Long id, String ownerUsername) {
                return Optional.empty();
            }

            @Override
            public Iterable<Pet> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Pet> findAll(Pageable pageable) {
                return null;
            }
        };
    }

    public static PetsAddRepository getPetsAddRepositoryImpl() {
        return new PetsAddRepository() {

            @Override
            public Pet save(Pet pet) {
                return null;
            }
        };
    }

    public static Mapper<PetDto, Pet> getPetMapperImpl() {
        return new Mapper<PetDto, Pet>() {
            @Override
            public PetDto toDto(Pet pet) {
                return null;
            }

            @Override
            public Pet toEntity(PetDto petDto) {
                return null;
            }
        };
    }
}
