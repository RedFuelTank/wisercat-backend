package com.wisercat.bestfriend.database.pets_repository.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.exception.DataAlreadyExistsException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.pets_service.add_use_case.PetsAddRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PetAddRepositoryImpl implements PetsAddRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Pet save(Pet pet) {
        manager.persist(pet);
        return pet;
    }
}
