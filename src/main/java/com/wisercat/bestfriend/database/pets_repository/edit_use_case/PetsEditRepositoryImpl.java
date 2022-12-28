package com.wisercat.bestfriend.database.pets_repository.edit_use_case;

import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.pets_service.edit_use_case.PetsEditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class PetsEditRepositoryImpl implements PetsEditRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Pet edit(Pet pet) {
        Pet existData = manager.find(Pet.class, pet.getId());

        if (existData == null) {
            throw new NotFoundException(
                    String.format("Pet with id (%s) doesn't exist", pet.getId()));
        }

        manager.merge(pet);

        return pet;
    }
}
