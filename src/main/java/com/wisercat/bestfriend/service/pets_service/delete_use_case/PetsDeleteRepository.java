package com.wisercat.bestfriend.service.pets_service.delete_use_case;

import com.wisercat.bestfriend.model.Pet;

public interface PetsDeleteRepository {
    Pet delete(String username, Long id);
}
