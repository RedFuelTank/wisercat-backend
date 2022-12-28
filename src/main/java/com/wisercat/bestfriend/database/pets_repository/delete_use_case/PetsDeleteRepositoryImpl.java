package com.wisercat.bestfriend.database.pets_repository.delete_use_case;

import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.pets_service.delete_use_case.PetsDeleteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.nio.file.AccessDeniedException;

@Repository
public class PetsDeleteRepositoryImpl implements PetsDeleteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Pet delete(String username, Long id) {
        Pet existData = manager.find(Pet.class, id);

        if (existData == null) {
            throw new NotFoundException(
                    String.format("Pet with id (%s) doesn't exist", id));
        }

        if (existData.getOwner().getUsername().equals(username)) {
            manager.remove(existData);
            return existData;
        } else {
            throw new IllegalArgumentException();
        }


    }
}
