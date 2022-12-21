package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.PostRequestBuilder;
import com.wisercat.bestfriend.dto.PetDto;
import com.wisercat.bestfriend.dto.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.enums.FurColor;
import com.wisercat.bestfriend.dto.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.wisercat.bestfriend.config.WebTestConfig.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class PetsAddControllerTest {
    private PetsAddService service;
    private PostRequestBuilder requestBuilder;

    @BeforeEach
    void init() {
        service = mock(getPetsAddUseCaseImpl().getClass());

        PetsAddController controller = new PetsAddController(service);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(getExceptionHandler())
                .setMessageConverters(getObjectMapperHttpMessageConverter())
                .build();
        requestBuilder = new PostRequestBuilder(mockMvc);
    }

    @Nested
    @DisplayName("Save a new pet data")
    class Save {
        private final static String PET_NAME = "Fluffy";
        private final static String PET_CODE = "7ebf40ac-146b-4c1f-a07f-64e2d21f215f";
        private final static PetType PET_TYPE = PetType.CAT;
        private final static Long PET_ID = 1L;
        private final static FurColor PET_FUR_COLOR = FurColor.BLACK;
        private final static CountryOrigin PET_COUNTRY_OF_ORIGIN = CountryOrigin.ESTONIA;

        private PetDto petDto;

        @Nested
        @DisplayName("When the information of the created pet's data is valid")
        class WhenValidInformationIsProvided {
            @BeforeEach
            void init() {
                petDto = new PetDto(PET_ID, PET_CODE, PET_NAME, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                given(service.save(any())).willReturn(petDto);
            }

            @Test
            @DisplayName("Should return HTTP response code 201")
            void shouldReturnHttpResponseCodeCreated() throws Exception {
                requestBuilder.save(petDto)
                        .andExpect(status().isCreated());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                System.out.println(requestBuilder.save(petDto).andReturn().getResponse().getContentAsString());
            }

            @Test
            @DisplayName("Should return correct HTTP response body")
            void shouldReturnCorrectHttpResponseBody() throws Exception {
                requestBuilder.save(petDto)
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
                    petDto = new PetDto("", "", PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                }

                @Test
                @DisplayName("Should return HTTP response code 400")
                void shouldReturnHttpResponseCodeBadRequest() throws Exception {
                    requestBuilder.save(petDto)
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("Should return HTTP response with JSON media-type")
                void shouldReturnCorrectHttpResponseJsonMediaType() throws Exception {
                    requestBuilder.save(petDto)
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                }

                @Test
                @DisplayName("Should return correct error's name for empty pet's data")
                void shouldReturnCorrectErrorNamePetCode() throws Exception {
                    requestBuilder.save(petDto)
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
                    petDto = new PetDto(null, null, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                }

                @Test
                @DisplayName("Should return HTTP response code 400")
                void shouldReturnHttpResponseCodeBadRequest() throws Exception {
                    requestBuilder.save(petDto)
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("Should return correct HTTP response media-type")
                void shouldReturnCorrectHttpResponseMediaType() throws Exception {
                    requestBuilder.save(petDto)
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                }

                @Test
                @DisplayName("Should return correct error's name for null pet's data")
                void shouldReturnCorrectErrorNamePetCode() throws Exception {
                    requestBuilder.save(petDto)
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
                PetDto petDto = new PetDto(PET_NAME, PET_CODE, PET_TYPE, PET_FUR_COLOR, PET_COUNTRY_OF_ORIGIN);
                given(service.save(petDto)).willReturn(petDto);
            }

            @Test
            void returnCorrectHttpStatusCodeCreated() throws Exception {
//                requestBuilder.addPet().andExpect(status().isCreated());
            }

            @Test
            @DisplayName("Should return pet data with correct data")
            void returnCorrectPet() throws Exception {
//                requestBuilder.addPet()
//                        .andExpect(jsonPath("$.name", equalTo(PET_NAME)))
//                        .andExpect(jsonPath("$.code", equalTo(PET_CODE)))
//                        .andExpect(jsonPath("$.type", equalTo(PET_TYPE.toString())))
//                        .andExpect(jsonPath("$.furColor", equalTo(PET_FUR_COLOR.toString())))
//                        .andExpect(jsonPath("$.countryOrigin", equalTo(PET_COUNTRY_OF_ORIGIN.toString())));
            }
        }

        @Nested
        @DisplayName("Pet has not successfully been added")
        class petHasNotSuccessfullyBeenAdded {

        }
    }
}