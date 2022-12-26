package com.wisercat.bestfriend.dto.pet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisercat.bestfriend.enums.CountryOrigin;
import com.wisercat.bestfriend.enums.FurColor;
import com.wisercat.bestfriend.enums.PetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegistrationPetDto {
    @JsonProperty(required = true, namespace = "code")
    @Size(min = 1, max = 50)
    private String code;
    @JsonProperty(required = true, namespace = "name")
    @Size(min = 1, max = 50)
    private String name;
    @JsonProperty(required = true, namespace = "type")
    private PetType type;
    @JsonProperty(required = true, namespace = "furColor")
    private FurColor furColor;
    @JsonProperty(required = true, namespace = "countryOrigin")
    private CountryOrigin countryOrigin;
}
