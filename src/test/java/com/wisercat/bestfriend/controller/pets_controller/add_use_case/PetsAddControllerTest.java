package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.pet.RegistrationPetDto;
import com.wisercat.bestfriend.requestbuilder.pets.PetsPostRequestBuilder;
import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.enums.CountryOrigin;
import com.wisercat.bestfriend.enums.FurColor;
import com.wisercat.bestfriend.enums.PetType;
import com.wisercat.bestfriend.exception.DataAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.wisercat.bestfriend.config.WebTestConfig.*;
import static com.wisercat.bestfriend.config.pets.WebPetsTestFactory.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class PetsAddControllerTest {
    private PetsAddService service;
    private PetsPostRequestBuilder requestBuilder;

    @BeforeEach
    void init() {
        service = mock(getPetsAddServiceImpl().getClass());

        PetsAddController controller = new PetsAddController(service);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(getExceptionHandler())
                .setMessageConverters(getObjectMapperHttpMessageConverter())
                .build();
        requestBuilder = new PetsPostRequestBuilder(mockMvc);
    }

    @Nested
    @DisplayName("Save a new pet data")
    class Save {
        private final static String PET_NAME = "Fluffy";
        private final static String PET_OWNER_NAME = "user";
        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
        private final static PetType PET_TYPE = PetType.CAT;
        private final static Long PET_ID = 1L;
        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

        private PetDto output;
        private RegistrationPetDto input;

        @Nested
        @DisplayName("When the information of the created pet's data is valid")
        class WhenValidInformationIsProvided {
            @BeforeEach
            void init() {
                output = new PetDto(PET_ID, PET_OWNER_NAME, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                input = new RegistrationPetDto(PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                given(service.save(any(), any())).willReturn(output);
            }

            @Test
            @DisplayName("Should return HTTP response code 201")
            void shouldReturnHttpResponseCodeCreated() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(status().isCreated());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return correct HTTP response body")
            void shouldReturnCorrectHttpResponseBody() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(jsonPath("$.id", equalTo(PET_ID.intValue())))
                        .andExpect(jsonPath("$.name", equalTo(PET_NAME)))
                        .andExpect(jsonPath("$.code", equalTo(PET_CODE)))
                        .andExpect(jsonPath("$.type", equalTo(PET_TYPE.toString())))
                        .andExpect(jsonPath("$.furColor", equalTo(PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$.countryOrigin", equalTo(PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }

        @Nested
        @DisplayName("When the information of the created pet's data is invalid")
        class WhenInvalidInformationIsProvided {
            @Nested
            @DisplayName("When the field values are empty strings")
            class WhenFieldValuesAreEmpty {
                private static final String VALIDATION_ERROR_SIZE_NAME = "Size";

                @BeforeEach
                void init() {
                    input = new RegistrationPetDto("", "", PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                }

                @Test
                @DisplayName("Should return HTTP response code 400")
                void shouldReturnHttpResponseCodeBadRequest() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("Should return HTTP response with JSON media-type")
                void shouldReturnCorrectHttpResponseJsonMediaType() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                }

                @Test
                @DisplayName("Should return correct error's name for empty pet's data")
                void shouldReturnCorrectErrorNamePetCode() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(jsonPath(
                                    "$.errors[?(@.field == 'code')].name",
                                    contains(VALIDATION_ERROR_SIZE_NAME)
                            ))
                            .andExpect(jsonPath(
                                    "$.errors[?(@.field == 'name')].name",
                                    contains(VALIDATION_ERROR_SIZE_NAME)
                            ));
                }
            }

            @Nested
            @DisplayName("When the field values are null")
            class WhenFieldValuesAreNull {
                private static final String VALIDATION_ERROR_NULL_NAME = "NotNull";

                @BeforeEach
                void init() {
                    input = new RegistrationPetDto(null, null, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                }

                @Test
                @DisplayName("Should return HTTP response code 400")
                void shouldReturnHttpResponseCodeBadRequest() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("Should return correct HTTP response media-type")
                void shouldReturnCorrectHttpResponseMediaType() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                }

                @Test
                @DisplayName("Should return correct error's name for null pet's data")
                void shouldReturnCorrectErrorNamePetCode() throws Exception {
                    requestBuilder.save(input, PET_OWNER_NAME)
                            .andExpect(jsonPath(
                                    "$.errors[?(@.field == 'code')].name",
                                    contains(VALIDATION_ERROR_NULL_NAME)
                            ))
                            .andExpect(jsonPath(
                                    "$.errors[?(@.field == 'name')].name",
                                    contains(VALIDATION_ERROR_NULL_NAME)
                            ));
                }
            }
        }

        @Nested
        @DisplayName("When the information of the created pet isn't valid")
        class petHasSuccessfullyBeenAdded {

            @BeforeEach
            void returnPet() {
                input = new RegistrationPetDto(PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                output = new PetDto(PET_ID, PET_OWNER_NAME, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                given(service.save(any(), any())).willReturn(output);
            }

            @Test
            @DisplayName("Should return Http response code 201")
            void returnCorrectHttpStatusCodeCreated() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(status().isCreated());
            }

            @Test
            @DisplayName("Should return pet data with correct data")
            void returnCorrectPet() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(jsonPath("$.name", equalTo(PET_NAME)))
                        .andExpect(jsonPath("$.code", equalTo(PET_CODE)))
                        .andExpect(jsonPath("$.type", equalTo(PET_TYPE.toString())))
                        .andExpect(jsonPath("$.furColor", equalTo(PET_FUR_COLOR.toString())))
                        .andExpect(jsonPath("$.countryOrigin", equalTo(PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }

        @Nested
        @DisplayName("When pet has not successfully been added")
        class petHasNotSuccessfullyBeenAdded {
            private final static String DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE =
                    "This data is already exists in database";

            @BeforeEach
            void init() {
                output = new PetDto(PET_ID, PET_OWNER_NAME, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                input = new RegistrationPetDto(PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                given(service.save(any(), any()))
                        .willThrow(new DataAlreadyExistsException(
                                DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE
                        ));
            }

            @Test
            @DisplayName("Should return HTTP response code 409")
            void shouldReturnHttpResponseCodeConflict() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(status().isConflict());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(
                                content().contentType(MediaType.APPLICATION_JSON)
                        );
            }

            @Test
            @DisplayName("Should return correct HTTP response body")
            void shouldReturnCorrectHttpResponseBody() throws Exception {
                requestBuilder.save(input, PET_OWNER_NAME)
                        .andExpect(jsonPath("$.name", equalTo(
                                DataAlreadyExistsException.class.getSimpleName()
                        )))
                        .andExpect(jsonPath("$.message", equalTo(
                                DATA_ALREADY_EXISTS_EXCEPTION_MESSAGE
                        )));
            }
        }
    }
}