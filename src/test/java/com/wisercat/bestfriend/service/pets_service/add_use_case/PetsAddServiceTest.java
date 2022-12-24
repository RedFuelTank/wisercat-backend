package com.wisercat.bestfriend.service.pets_service.add_use_case;

import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.pet.enums.FurColor;
import com.wisercat.bestfriend.dto.pet.enums.PetType;
import com.wisercat.bestfriend.exception.DataAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.wisercat.bestfriend.config.pets.WebPetsTestConfig.getPetsAddRepositoryImpl;
import static com.wisercat.bestfriend.custom_assert.PetDtoAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;


class PetsAddServiceTest {
    private PetsAddRepository repository;
    private PetsAddService service;

    private PetDto insert;
    private PetDto output;

    @BeforeEach
    void init() {
        repository = mock(getPetsAddRepositoryImpl().getClass());
        service = new PetsAddServiceImpl(repository);
    }

    @Nested
    @DisplayName("When data has successfully been saved")
    class DataHasSuccessfullyBeenSaved {

        private final static Long PET_ID = 1L;
        private final static String PET_NAME = "Fluffy";
        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
        private final static PetType PET_TYPE = PetType.CAT;
        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

        @BeforeEach
        void init() {
            insert = new PetDto(PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
            output = new PetDto(PET_ID, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
            given(repository.save(insert))
                    .willReturn(output);
        }

        @Test
        @DisplayName("Should return not-null value")
        void shouldReturnNonNullValue() {
            assertThat(service.save(insert))
                    .isNotNull();
        }

        @Test
        @DisplayName("Should return not-null fields of data")
        void shouldReturnNotNullData() {
            assertThat(service.save(insert))
                    .hasId()
                    .hasName()
                    .hasCode()
                    .hasPetType()
                    .hasFurColor()
                    .hasCountryOrigin();
        }

        @Test
        @DisplayName("Should return correct data")
        void shouldReturnCorrectData() {
            PetDto petDto = service.save(insert);

            assertThat(petDto.getId()).isEqualTo(PET_ID);
            assertThat(petDto.getName()).isEqualTo(PET_NAME);
            assertThat(petDto.getCode()).isEqualTo(PET_CODE);
            assertThat(petDto.getType()).isEqualTo(PET_TYPE);
            assertThat(petDto.getFurColor()).isEqualTo(PET_FUR_COLOR);
            assertThat(petDto.getCountryOrigin()).isEqualTo(PET_COUNTRY_OF_ORIGIN);
        }
    }

    @Nested
    @DisplayName("When data has not successfully been saved")
    class DataHasNotSuccessfullyBeenSaved {
        private final static String DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE =
                "This data is already exists in database";

        @BeforeEach
        void init() {
            given(repository.save(any()))
                    .willThrow(new DataAlreadyExistsException(
                            DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE
                    ));
        }

        @Test
        @DisplayName("Should throw exception")
        void shouldThrowException() {
            assertThatThrownBy(() -> service.save(any()))
                    .isExactlyInstanceOf(DataAlreadyExistsException.class)
                    .hasMessage(DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
    }
}