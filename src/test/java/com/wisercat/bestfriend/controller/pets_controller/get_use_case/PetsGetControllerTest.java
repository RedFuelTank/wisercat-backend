package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.requestbuilder.pets.PetsGetRequestBuilder;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.pet.enums.FurColor;
import com.wisercat.bestfriend.dto.pet.enums.PetType;
import com.wisercat.bestfriend.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.wisercat.bestfriend.config.pets.WebPetsTestFactory.*;
import static com.wisercat.bestfriend.config.WebTestConfig.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
class PetsGetControllerTest {

    private PetsGetRequestBuilder petsGetRequestBuilder;
    private PetsGetService service;

    @BeforeEach
    void init() {
        service = mock(getPetsGetServiceImpl().getClass());
        PetsGetController controller = new PetsGetController(service);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(getObjectMapperHttpMessageConverter())
                .setControllerAdvice(getExceptionHandler())
                .build();
        petsGetRequestBuilder = new PetsGetRequestBuilder(mockMvc);
    }

    @Nested
    @DisplayName("Display data of specific pet")
    class GetById {
        private final static Long PET_ID = 1L;
        private final static String PET_NAME = "Fluffy";
        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
        private final static PetType PET_TYPE = PetType.CAT;
        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

        private PetDto petDto;


        @Nested
        @DisplayName("Pet data has successfully been found")
        class dataHasSuccessfullyBeenFound {
            @BeforeEach
            void init() {
                petDto = new PetDto(PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                petDto.setId(PET_ID);
                given(service.getById(PET_ID)).willReturn(petDto);
            }

            @Test
            @DisplayName("Should return HTTP response code 200")
            void shouldReturnHttpResponseCodeOK() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return JSON with correct data")
            void shouldReturnCorrectData() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(jsonPath("$.id", equalTo(PET_ID.intValue())))
                        .andExpect(jsonPath("$.name", equalTo(PET_NAME)))
                        .andExpect(jsonPath("$.code", equalTo(PET_CODE)))
                        .andExpect(jsonPath("$.type", equalTo(PET_TYPE.toString())))
                        .andExpect(jsonPath("$.furColor", equalTo(PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$.countryOrigin", equalTo(PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }


        @Nested
        @DisplayName("Pet data has not successfully been found")
        class DataHasNotSuccessfullyBeenFound {

            public static final String PET_HAS_NOT_BEEN_FOUND_MESSAGE = "Pet has not been found";

            @BeforeEach
            void init() {
                given(service.getById(PET_ID))
                        .willThrow(new NotFoundException(PET_HAS_NOT_BEEN_FOUND_MESSAGE));
            }

            @Test
            @DisplayName("Should return HTTP response code 404")
            void shouldReturnHttpResponseCodeNotFound() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(status().isNotFound());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return HTTP response with correct body")
            void shouldReturnHttpResponseEmptyBody() throws Exception {
                petsGetRequestBuilder.getById(PET_ID)
                        .andExpect(jsonPath("$.name", equalTo(NotFoundException.class.getSimpleName())))
                        .andExpect(jsonPath("$.message", equalTo(PET_HAS_NOT_BEEN_FOUND_MESSAGE)));
            }
        }
    }

    @Nested
    @DisplayName("Display data of every pet")
    class GetAll {

        @Test
        @DisplayName("Should return the HTTP status 200")
        void shouldReturnHttpStatusOk() throws Exception {
            petsGetRequestBuilder.getAll()
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should return HTTP response with JSON media-type")
        void shouldReturnCorrectMethodType() throws Exception {
            petsGetRequestBuilder.getAll()
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

        @Nested
        @DisplayName("Should return list with no pets data")
        class ReturnListNoData {
            @BeforeEach
            void init() {
                given(service.getAll()).willReturn(List.of());
            }

            @Test
            @DisplayName("Should return empty list")
            void shouldReturnEmptyList() throws Exception {
                petsGetRequestBuilder.getAll()
                        .andExpect(jsonPath("$", hasSize(0)));
            }
        }

        @Nested
        @DisplayName("Should return list with 2 pets data")
        class ReturnListTwoPetsData {
            private static final Long FIRST_PET_ID = 0L;
            private final static String FIRST_PET_NAME = "Fluffy";
            private final static String FIRST_PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
            private final static PetType FIRST_PET_TYPE = PetType.CAT;
            private final static FurColor FIRST_PET_FUR_COLOR = FurColor.BLACK;
            private final static CountryOrigin FIRST_PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

            private final static Long SECOND_PET_ID = 1L;
            private final static String SECOND_PET_NAME = "Bunny";
            private final static String SECOND_PET_CODE = "40ec994c-84e5-476d-96fa-ac87525a0af6";
            private final static PetType SECOND_PET_TYPE = PetType.RABBIT;
            private final static FurColor SECOND_PET_FUR_COLOR = FurColor.BROWN;
            private final static CountryOrigin SECOND_PET_COUNTRY_OF_ORIGIN = CountryOrigin.FINLAND;

            @BeforeEach
            void init() {
                PetDto firstPetDto = new PetDto(
                        FIRST_PET_CODE,
                        FIRST_PET_NAME,
                        FIRST_PET_TYPE,
                        FIRST_PET_FUR_COLOR,
                        FIRST_PET_COUNTRY_OF_ORIGIN);
                firstPetDto.setId(FIRST_PET_ID);

                PetDto secondPetDto = new PetDto(
                        SECOND_PET_CODE,
                        SECOND_PET_NAME,
                        SECOND_PET_TYPE,
                        SECOND_PET_FUR_COLOR,
                        SECOND_PET_COUNTRY_OF_ORIGIN
                );
                secondPetDto.setId(SECOND_PET_ID);

                given(service.getAll()).willReturn(List.of(firstPetDto, secondPetDto));
            }

            @Test
            @DisplayName("Should return correct data of first pet")
            void shouldReturnCorrectDataFirstPet() throws Exception {
                petsGetRequestBuilder.getAll()
                        .andExpect(jsonPath("$[0].id", equalTo(FIRST_PET_ID.intValue())))
                        .andExpect(jsonPath("$[0].name", equalTo(FIRST_PET_NAME)))
                        .andExpect(jsonPath("$[0].code", equalTo(FIRST_PET_CODE)))
                        .andExpect(jsonPath("$[0].type", equalTo(FIRST_PET_TYPE.toString())))
                        .andExpect(jsonPath("$[0].furColor", equalTo(FIRST_PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$[0].countryOrigin", equalTo(FIRST_PET_COUNTRY_OF_ORIGIN.toString())));
            }

            @Test
            @DisplayName("Should return correct data of second pet")
            void shouldReturnCorrectDataSecondPet() throws Exception {
                petsGetRequestBuilder.getAll()
                        .andExpect(jsonPath("$[1].id", equalTo(SECOND_PET_ID.intValue())))
                        .andExpect(jsonPath("$[1].name", equalTo(SECOND_PET_NAME)))
                        .andExpect(jsonPath("$[1].code", equalTo(SECOND_PET_CODE)))
                        .andExpect(jsonPath("$[1].type", equalTo(SECOND_PET_TYPE.toString())))
                        .andExpect(jsonPath("$[1].furColor", equalTo(SECOND_PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$[1].countryOrigin", equalTo(SECOND_PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }

    }

}