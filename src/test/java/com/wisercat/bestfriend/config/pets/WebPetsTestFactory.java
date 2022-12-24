package com.wisercat.bestfriend.config.pets;

import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.service.pets_service.add_use_case.PetsAddRepository;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
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
            public List<PetDto> getAll() {
                return null;
            }

            @Override
            public PetDto getPetByIdByUsername(String username, Long id) {
                return null;
            }

            @Override
            public List<PetDto> getUserPetsByPages(String username, int page, int size) {
                return null;
            }
        };
    }

    public static PetsAddService getPetsAddServiceImpl() {
        return new PetsAddService() {
            @Override
            public PetDto save(PetDto pet) {
                return null;
            }
        };
    }

    public static PetsGetRepository getPetsGetRepositoryImpl() {
        return new PetsGetRepository() {
            @Override
            public Iterable<PetDto> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<PetDto> findAll(org.springframework.data.domain.Pageable pageable) {
                return null;
            }

            @Override
            public Optional<PetDto> getById(Long id) {
                return null;
            }

            @Override
            public List<PetDto> getAll() {
                return null;
            }

            @Override
            public List<PetDto> getAllByOwnerUsername(String username, Pageable pageable) {
                return null;
            }
        };
    }

    public static PetsAddRepository getPetsAddRepositoryImpl() {
        return new PetsAddRepository() {

            @Override
            public PetDto save(PetDto petDto) {
                return null;
            }
        };
    }

}
