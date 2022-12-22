package com.wisercat.bestfriend.database.pets_repository.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;
import com.wisercat.bestfriend.dto.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.enums.FurColor;
import com.wisercat.bestfriend.dto.enums.PetType;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PetsGetRepositoryImpl implements PetsGetRepository {

    @Override
    public Optional<PetDto> getById(Long id) {
        return switch (id.intValue()) {
            case 0 -> Optional.of(new PetDto(
                    0L,
                    "10723d04-b391-4ffd-914c-10b8568ada41",
                    "l2Fb",
                    PetType.CAT,
                    FurColor.BLACK,
                    CountryOrigin.ESTONIA
            ));
            case 1 -> Optional.of(new PetDto(
                    1L,
                    "b3b5dc30-b915-417a-972c-23499ea1282e",
                    "e5017htR",
                    PetType.RABBIT,
                    FurColor.BROWN,
                    CountryOrigin.FINLAND
            ));
            default -> Optional.empty();
        };
    }

    @Override
    public List<PetDto> getAll() {
        return List.of(
                new PetDto(
                        0L,
                        "10723d04-b391-4ffd-914c-10b8568ada41",
                        "l2Fb",
                        PetType.CAT,
                        FurColor.BLACK,
                        CountryOrigin.ESTONIA
                ),
                new PetDto(
                        1L,
                        "b3b5dc30-b915-417a-972c-23499ea1282e",
                        "e5017htR",
                        PetType.RABBIT,
                        FurColor.BROWN,
                        CountryOrigin.FINLAND
                )
        );
    }
}
