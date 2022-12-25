package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.exception.InvalidParameterException;
import com.wisercat.bestfriend.requestbuilder.pets.PetsGetRequestBuilder;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.enums.CountryOrigin;
import com.wisercat.bestfriend.enums.FurColor;
import com.wisercat.bestfriend.enums.PetType;
import com.wisercat.bestfriend.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.wisercat.bestfriend.config.pets.WebPetsTestFactory.*;
import static com.wisercat.bestfriend.config.WebTestConfig.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetsGetControllerTest {

    private PetsGetRequestBuilder requestBuilder;
    private PetsGetService service;

    @BeforeEach
    void init() {
        service = mock(getPetsGetServiceImpl().getClass());
        PetsGetController controller = new PetsGetController(service);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(getObjectMapperHttpMessageConverter())
                .setControllerAdvice(getExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        requestBuilder = new PetsGetRequestBuilder(mockMvc);
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
                requestBuilder.getById(PET_ID)
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getById(PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return JSON with correct data")
            void shouldReturnCorrectData() throws Exception {
                requestBuilder.getById(PET_ID)
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
                requestBuilder.getById(PET_ID)
                        .andExpect(status().isNotFound());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getById(PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return HTTP response with correct body")
            void shouldReturnHttpResponseEmptyBody() throws Exception {
                requestBuilder.getById(PET_ID)
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
            requestBuilder.getAll()
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should return HTTP response with JSON media-type")
        void shouldReturnCorrectMethodType() throws Exception {
            requestBuilder.getAll()
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

        @Nested
        @DisplayName("Should return list with no pets data")
        class ReturnListNoData {
            @BeforeEach
            void init() {
                given(service.getAll(any(PageRequest.class))).willReturn(List.of());
            }

            @Test
            @DisplayName("Should return empty list")
            void shouldReturnEmptyList() throws Exception {
                requestBuilder.getAll()
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

                given(service.getAll(any(PageRequest.class)))
                        .willReturn(List.of(firstPetDto, secondPetDto));
            }

            @Test
            @DisplayName("Should return correct data of first pet")
            void shouldReturnCorrectDataFirstPet() throws Exception {
                requestBuilder.getAll()
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
                requestBuilder.getAll()
                        .andExpect(jsonPath("$[1].id", equalTo(SECOND_PET_ID.intValue())))
                        .andExpect(jsonPath("$[1].name", equalTo(SECOND_PET_NAME)))
                        .andExpect(jsonPath("$[1].code", equalTo(SECOND_PET_CODE)))
                        .andExpect(jsonPath("$[1].type", equalTo(SECOND_PET_TYPE.toString())))
                        .andExpect(jsonPath("$[1].furColor", equalTo(SECOND_PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$[1].countryOrigin", equalTo(SECOND_PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }

    }

    @Nested
    @DisplayName("Display information about specific user's pet")
    class GetPetByIdByUsername {
        private final static Long PET_ID = 1L;
        private final static String PET_NAME = "Fluffy";
        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
        private final static PetType PET_TYPE = PetType.CAT;
        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

        private PetDto petDto;

        private static final String USER_USERNAME = "admin";


        @Nested
        @DisplayName("Pet data has successfully been found")
        class DataHasSuccessfullyBeenFound {
            @BeforeEach
            void init() {
                petDto = new PetDto(PET_ID, USER_USERNAME, PET_CODE, PET_NAME,
                        PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);

                given(service.getPetByIdByUsername(USER_USERNAME, PET_ID)).willReturn(petDto);
            }

            @Test
            @DisplayName("Should return HTTP response code 200")
            void shouldReturnHttpResponseCodeOK() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return JSON with correct data")
            void shouldReturnCorrectData() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
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

            public static final String PET_HAS_NOT_BEEN_FOUND_MESSAGE =
                    String.format("User %s doesn't have pet with id %s", USER_USERNAME, PET_ID);

            @BeforeEach
            void init() {
                given(service.getPetByIdByUsername(USER_USERNAME, PET_ID))
                        .willThrow(new NotFoundException(PET_HAS_NOT_BEEN_FOUND_MESSAGE));
            }

            @Test
            @DisplayName("Should return HTTP response code 404")
            void shouldReturnHttpResponseCodeNotFound() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
                        .andExpect(status().isNotFound());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return HTTP response with correct body")
            void shouldReturnHttpResponseEmptyBody() throws Exception {
                requestBuilder.getPetByIdByUsername(USER_USERNAME, PET_ID)
                        .andExpect(jsonPath("$.name", equalTo(NotFoundException.class.getSimpleName())))
                        .andExpect(jsonPath("$.message", equalTo(PET_HAS_NOT_BEEN_FOUND_MESSAGE)));
            }
        }
    }

    @Nested
    @DisplayName("Display information about user's pets which restricted by pages")
    class GetPetsByUsernamePageable {
        private final static int PAGE_NUMBER_ONE = 1;
        private final static int PAGE_NUMBER_TWO = 2;
        private final static int PAGE_SIZE = 2;
        private final static String USER_USERNAME = "admin";

        @Nested
        @DisplayName("When parameters are valid")
        class ValidParameters {
            private static final String PETS_OWNER_USERNAME = "user";
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


            @Nested
            @DisplayName("Should return user's pets collection of first page")
            class PetsFirstPage {
                @BeforeEach
                void init() {
                    PetDto firstPetDto = new PetDto(
                            FIRST_PET_ID,
                            PETS_OWNER_USERNAME,
                            FIRST_PET_CODE,
                            FIRST_PET_NAME,
                            FIRST_PET_TYPE,
                            FIRST_PET_FUR_COLOR,
                            FIRST_PET_COUNTRY_OF_ORIGIN
                    );
                    PetDto secondPetDto = new PetDto(
                            SECOND_PET_ID,
                            PETS_OWNER_USERNAME,
                            SECOND_PET_CODE,
                            SECOND_PET_NAME,
                            SECOND_PET_TYPE,
                            SECOND_PET_FUR_COLOR,
                            SECOND_PET_COUNTRY_OF_ORIGIN
                    );
                    given(service.getUserPetsByPages(anyString(), any(PageRequest.class)))
                            .willReturn(List.of(
                                    firstPetDto,
                                    secondPetDto
                            ));
                }

                @Test
                @DisplayName("Should return HTTP response code 200")
                void shouldReturnHttpResponseCodeOk() throws Exception {
                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER_ONE, PAGE_SIZE)
                            .andExpect(status().isOk());
                }

                @Test
                @DisplayName("Should return HTTP response with JSON media-type")
                void shouldReturnHttpResponseJsonMediaType() throws Exception {
                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER_ONE, PAGE_SIZE)
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                }

                @Test
                @DisplayName("Should return correct data of first pet")
                void shouldReturnHttpResponseCorrectBody() throws Exception {
                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER_ONE, PAGE_SIZE)
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
                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER_ONE, PAGE_SIZE)
                            .andExpect(jsonPath("$[1].id", equalTo(SECOND_PET_ID.intValue())))
                            .andExpect(jsonPath("$[1].name", equalTo(SECOND_PET_NAME)))
                            .andExpect(jsonPath("$[1].code", equalTo(SECOND_PET_CODE)))
                            .andExpect(jsonPath("$[1].type", equalTo(SECOND_PET_TYPE.toString())))
                            .andExpect(jsonPath("$[1].furColor", equalTo(SECOND_PET_FUR_COLOR.toString())))
                            .andExpect(jsonPath("$[1].countryOrigin", equalTo(SECOND_PET_COUNTRY_OF_ORIGIN.toString())));
                }
            }

            @Nested
            @DisplayName("Should return user's pets collection of second page (empty)")
            class PetsSecondPage {

            }

        }
//        @Nested
//        @DisplayName("When parameters are not valid")
//        class InvalidParameters {
//            private final static int PAGE_NUMBER = 1;
//
//            @Nested
//            @DisplayName("When page number is incorrect")
//            class PageNumberIncorrect {
//                private final static int INCORRECT_PAGE_NUMBER = 0;
//
//                private final static String INVALID_PAGE_NUMBER_EXCEPTION_MESSAGE =
//                        String.format("Page number (%s) is unacceptable value", INCORRECT_PAGE_NUMBER);
//
//                @Test
//                @DisplayName("Should return HTTP response code 400 (incorrect page number)")
//                void shouldReturnHttpResponseCodeBadRequestPageNumber() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, INCORRECT_PAGE_NUMBER, PAGE_SIZE)
//                            .andExpect(status().isBadRequest());
//                }
//
//                @Test
//                @DisplayName("Should return HTTP response with JSON media-type")
//                void shouldReturnHttpResponseJsonMediaType() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, INCORRECT_PAGE_NUMBER, PAGE_SIZE)
//                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                }
//
//                @Test
//                @DisplayName("Should return HTTP response body with correct exception message")
//                void shouldReturnHttpResponseBodyException() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, INCORRECT_PAGE_NUMBER, PAGE_SIZE)
//                            .andExpect(jsonPath("$.name", equalTo(InvalidParameterException.class.getSimpleName())))
//                            .andExpect(jsonPath("$.message", equalTo(INVALID_PAGE_NUMBER_EXCEPTION_MESSAGE)));
//                }
//
//
//            }
//
//            @Nested
//            @DisplayName("When page size is incorrect")
//            class PageSizeIncorrect {
//                private final static int INCORRECT_PAGE_SIZE = 0;
//
//                private final static String INVALID_PAGE_SIZE_EXCEPTION_MESSAGE =
//                        String.format("Page size (%s) is unacceptable value", INCORRECT_PAGE_SIZE);
//
//                @Test
//                @DisplayName("Should return HTTP response code 400 (incorrect page number)")
//                void shouldReturnHttpResponseCodeBadRequestPageNumber() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER, INCORRECT_PAGE_SIZE)
//                            .andExpect(status().isBadRequest());
//                }
//
//                @Test
//                @DisplayName("Should return HTTP response with JSON media-type")
//                void shouldReturnHttpResponseJsonMediaType() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER, INCORRECT_PAGE_SIZE)
//                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//                }
//
//                @Test
//                @DisplayName("Should return HTTP response body with correct exception message")
//                void shouldReturnHttpResponseBodyException() throws Exception {
//                    requestBuilder.getUserPetsByPages(USER_USERNAME, PAGE_NUMBER, INCORRECT_PAGE_SIZE)
//                            .andExpect(jsonPath("$.name", equalTo(InvalidParameterException.class.getSimpleName())))
//                            .andExpect(jsonPath("$.message", equalTo(INVALID_PAGE_SIZE_EXCEPTION_MESSAGE)));
//                }
//
//
//            }
//
//        }
    }

}