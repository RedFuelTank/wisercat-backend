//package com.wisercat.bestfriend.service.pets_service.get_use_case;
//
//import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
//import com.wisercat.bestfriend.dto.pet.PetDto;
//import com.wisercat.bestfriend.enums.CountryOrigin;
//import com.wisercat.bestfriend.enums.FurColor;
//import com.wisercat.bestfriend.enums.PetType;
//import com.wisercat.bestfriend.exception.NotFoundException;
//import com.wisercat.bestfriend.model.Pet;
//import com.wisercat.bestfriend.service.mapper.Mapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.wisercat.bestfriend.config.pets.WebPetsTestFactory.getPetMapperImpl;
//import static com.wisercat.bestfriend.custom_assert.PetDtoAssert.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static com.wisercat.bestfriend.config.pets.WebPetsTestFactory.getPetsGetRepositoryImpl;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.mock;
//
//class PetsGetServiceTest {
//    private PetsGetRepository repository;
//    private Mapper<PetDto, Pet> mapper;
//
//    private PetsGetService service;
//
//    @BeforeEach
//    void init() {
//        repository = mock(getPetsGetRepositoryImpl().getClass());
//        mapper = mock(getPetMapperImpl().getClass());
//        service = new PetsGetServiceImpl(repository, mapper);
//    }
//
//    @Nested
//    @DisplayName("Get data about specific pet from database")
//    class FindById {
//
//        private final static Long PET_ID = 1L;
//        private final static String PET_OWNER_NAME = "user";
//        private final static String PET_NAME = "Fluffy";
//        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
//        private final static PetType PET_TYPE = PetType.CAT;
//        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
//        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;
//
//        @Nested
//        @DisplayName("When specific data exists in database")
//        class SpecificDataExists {
//            @BeforeEach
//            void init() {
//                Pet pet = new Pet(PET_ID, PET_OWNER_NAME, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
//                PetDto petDto = new PetDto(PET_ID, PET_OWNER_NAME, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
//
//                given(repository.getById(PET_ID)).willReturn(Optional.of(pet));
//                given(mapper.toDto(pet)).willReturn(petDto);
//            }
//
//            @Test
//            @DisplayName("Should return not-null pet dto")
//            void shouldReturnNotNullData() {
//                PetDto petDto = service.getById(PET_ID);
//                assertThat(petDto).isNotNull();
//            }
//
//            @Test
//            @DisplayName("Should return PetDto with not-null fields")
//            void shouldReturnPetDtoNotNullData() {
//                PetDto petDto = service.getById(PET_ID);
//                assertThat(petDto)
//                        .hasId()
//                        .hasOwner()
//                        .hasCode()
//                        .hasName()
//                        .hasPetType()
//                        .hasFurColor()
//                        .hasCountryOrigin();
//            }
//
//            @Test
//            @DisplayName("Should return PetDto with correct data")
//            void shouldReturnPetDtoCorrectData() {
//                PetDto petDto = service.getById(PET_ID);
//                assertThat(petDto.getId())
//                        .as("Check PetDto id")
//                        .isEqualTo(PET_ID);
//                assertThat(petDto.getOwnerUsername())
//                        .as("Check PetDto owner's username")
//                        .isEqualTo(PET_OWNER_NAME);
//                assertThat(petDto.getCode())
//                        .as("Check PetDto code")
//                        .isEqualTo(PET_CODE);
//                assertThat(petDto.getName())
//                        .as("Check PetDto name")
//                        .isEqualTo(PET_NAME);
//                assertThat(petDto.getType())
//                        .as("Check PetDto pet's type")
//                        .isEqualTo(PET_TYPE);
//                assertThat(petDto.getFurColor())
//                        .as("Check PetDto pet's fur color")
//                        .isEqualTo(PET_FUR_COLOR);
//                assertThat(petDto.getCountryOrigin())
//                        .as("Check PetDto pets' country of origin")
//                        .isEqualTo(PET_COUNTRY_OF_ORIGIN);
//            }
//
//        }
//
//        @Nested
//        @DisplayName("When specific data doesn't exist in database")
//        class SpecificDataDoesntExist {
//
//            private static String NOT_FOUND_EXCEPTION_MESSAGE = String
//                    .format("Pet with id (%s) has not been found", PET_ID);
//
//            @BeforeEach
//            void init() {
//                given(repository.getById(PET_ID)).willReturn(Optional.empty());
//                given(mapper.toDto(any())).willReturn(any());
//            }
//
//            @Test
//            @DisplayName("Should throw NotFoundException")
//            void shouldThrowNotFoundException() {
//                assertThatThrownBy(() -> service.getById(PET_ID))
//                        .isInstanceOf(NotFoundException.class)
//                        .hasMessage(NOT_FOUND_EXCEPTION_MESSAGE);
//            }
//        }
//    }
//
//    @Nested
//    @DisplayName("Get data about all existing data from database")
//    class FindAll {
//        @Nested
//        @DisplayName("When no one pet's data exists in database")
//        class NoOnePetsDataExistsDatabase {
//            @BeforeEach
//            void init() {
//                Page<Pet> pets = new PageImpl<Pet>(List.of());
//                given(repository.findAll(any(Pageable.class)))
//                        .willReturn(pets);
//            }
//
//            @Test
//            @DisplayName("Should return non-null value")
//            void shouldReturnNonNullValue() {
//                assertThat(service.getAll(any(Pageable.class)))
//                        .isNotNull();
//            }
//
//            @Test
//            @DisplayName("Should return empty list")
//            void shouldReturnEmptyList() {
//                assertThat(service.getAll(any(PageRequest.class)))
//                        .hasSize(0);
//            }
//        }
//
//        @Nested
//        @DisplayName("When two pets data exist in database")
//        class TwoPetsDataExistDatabase {
//            private final static Long FIRST_PET_ID = 0L;
//            private final static String FIRST_PET_OWNER_NAME = "user";
//            private final static String FIRST_PET_NAME = "Fluffy";
//            private final static String FIRST_PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
//            private final static PetType FIRST_PET_TYPE = PetType.CAT;
//            private final static FurColor FIRST_PET_FUR_COLOR = FurColor.BLACK;
//            private final static CountryOrigin FIRST_PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;
//
//            private final static Long SECOND_PET_ID = 1L;
//            private final static String SECOND_PET_OWNER_NAME = "admin";
//            private final static String SECOND_PET_NAME = "Bunny";
//            private final static String SECOND_PET_CODE = "40ec994c-84e5-476d-96fa-ac87525a0af6";
//            private final static PetType SECOND_PET_TYPE = PetType.RABBIT;
//            private final static FurColor SECOND_PET_FUR_COLOR = FurColor.BROWN;
//            private final static CountryOrigin SECOND_PET_COUNTRY_OF_ORIGIN = CountryOrigin.FINLAND;
//
//            @BeforeEach
//            void init() {
//                Page<Pet> pets = mock(Page.class);
//
//                Pet firstPet = new Pet(
//                        FIRST_PET_ID,
//                        FIRST_PET_OWNER_NAME,
//                        FIRST_PET_CODE,
//                        FIRST_PET_NAME,
//                        FIRST_PET_TYPE,
//                        FIRST_PET_FUR_COLOR,
//                        FIRST_PET_COUNTRY_OF_ORIGIN
//                );
//
//                Pet secondPet = new Pet(
//                        SECOND_PET_ID,
//                        SECOND_PET_OWNER_NAME,
//                        SECOND_PET_CODE,
//                        SECOND_PET_NAME,
//                        SECOND_PET_TYPE,
//                        SECOND_PET_FUR_COLOR,
//                        SECOND_PET_COUNTRY_OF_ORIGIN
//                );
//                pets.getContent().add(firstPet);
//                pets.getContent().add(secondPet);
//                given(repository.findAll(any(PageRequest.class))).willReturn(pets);
//            }
//
//            @Test
//            @DisplayName("Should return non-null value")
//            void shouldReturnNonNullValue() {
//                assertThat(service.getAll(any(PageRequest.class)))
//                        .isNotNull();
//            }
//
//            @Test
//            @DisplayName("Should return two pets data")
//            void shouldReturnTwoPetsData() {
//                assertThat(service.getAll(any(PageRequest.class)))
//                        .hasSize(2);
//            }
//
//            @Test
//            @DisplayName("Should return correct data of both pets")
//            void shouldReturnNonNullDataTwoPets() {
//                assertThat(service.getAll(any(PageRequest.class))).allSatisfy(petDto -> {
//                    assertThat(petDto).isNotNull();
//                });
//            }
//        }
//
//
//    }
//}