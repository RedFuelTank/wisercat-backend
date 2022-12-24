package com.wisercat.bestfriend.database.pets_repository.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.pet.enums.FurColor;
import com.wisercat.bestfriend.dto.pet.enums.PetType;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public class MockGetRepository implements PetsGetRepository {

    @Override
    public Optional<PetDto> getById(Long id) {
        return switch (id.intValue()) {
            case 0 -> {
                PetDto petDto = new PetDto(
                        "10723d04-b391-4ffd-914c-10b8568ada41",
                        "l2Fb",
                        PetType.CAT,
                        FurColor.BLACK,
                        CountryOrigin.ESTONIA
                );
                petDto.setId(0L);
                yield Optional.of(petDto);
            }
            case 1 -> {
                PetDto petDto = new PetDto(
                        "b3b5dc30-b915-417a-972c-23499ea1282e",
                        "e5017htR",
                        PetType.RABBIT,
                        FurColor.BROWN,
                        CountryOrigin.FINLAND
                );
                petDto.setId(1L);
                yield Optional.of(petDto);
            }
            default -> Optional.empty();
        };
    }

    @Override
    public List<PetDto> getAll() {
        PetDto firstPetDto = new PetDto(
                "10723d04-b391-4ffd-914c-10b8568ada41",
                "l2Fb",
                PetType.CAT,
                FurColor.BLACK,
                CountryOrigin.ESTONIA
        );
        firstPetDto.setId(0L);

        PetDto secondPetDto = new PetDto(
                "b3b5dc30-b915-417a-972c-23499ea1282e",
                "e5017htR",
                PetType.RABBIT,
                FurColor.BROWN,
                CountryOrigin.FINLAND
        );
        secondPetDto.setId(1L);
        return List.of(
                firstPetDto,
                secondPetDto
        );
    }

    @Override
    public List<PetDto> getAllByOwnerUsername(String username, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<PetDto> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PetDto> findAll(org.springframework.data.domain.Pageable pageable) {
        return null;
    }
}
