package com.wisercat.bestfriend.database.user_repository;

import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.user_service.get_use_case.UserGetRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserGetRepositoryImpl implements UserGetRepository {
    @PersistenceContext
    private EntityManager manager;
    public Optional<User> getUserByUsername(String userUsername) {
        return Optional.ofNullable(manager.find(User.class, userUsername));
    }
}
