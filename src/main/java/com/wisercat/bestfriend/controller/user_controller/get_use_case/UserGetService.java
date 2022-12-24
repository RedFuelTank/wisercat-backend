package com.wisercat.bestfriend.controller.user_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.user.UserDto;

import java.util.List;

public interface UserGetService {

    UserDto getUserByUsername(String userUsername);

    PetDto getPetByIdByUsername(String userUsername, Long petId);

    List<PetDto> getUserPetsByPages(String userUsername, int pageNumber, int pageSize);
}
