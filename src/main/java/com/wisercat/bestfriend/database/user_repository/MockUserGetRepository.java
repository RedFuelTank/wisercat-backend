package com.wisercat.bestfriend.database.user_repository;

import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.user_service.get_use_case.UserGetRepository;

import java.util.Optional;

public class MockUserGetRepository implements UserGetRepository {
    @Override
    public Optional<User> getUserByUsername(String userUsername) {
        return Optional.of(new User(userUsername));
    }
}
