package com.wisercat.bestfriend.config.user;

import com.wisercat.bestfriend.controller.user_controller.get_use_case.UserGetService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.user.UserDto;

import java.util.List;

public class WebUserTestFactory {

    public static UserGetService getUserGetServiceImpl() {
        return new UserGetService() {
            @Override
            public UserDto getUserByUsername(String userUsername) {
                return null;
            }

            @Override
            public PetDto getPetByIdByUsername(String userUsername, Long petId) {
                return null;
            }

            @Override
            public List<PetDto> getUserPetsByPages(String userUsername, int pageNumber, int pageSize) {
                return null;
            }
        };
    }
}
