package com.wisercat.bestfriend.dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisercat.bestfriend.dto.pet.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.pet.enums.FurColor;
import com.wisercat.bestfriend.dto.pet.enums.PetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    @JsonProperty(namespace = "id")
    @Setter
    private Long id;
    @JsonProperty(required = true, namespace = "code")
    @NotNull
    @Size(min = 1, max = 50)
    private String code;
    @JsonProperty(required = true, namespace = "name")
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @JsonProperty(required = true, namespace = "type")
    @NotNull
    private PetType type;
    @JsonProperty(required = true, namespace = "furColor")
    @NotNull
    private FurColor furColor;
    @JsonProperty(required = true, namespace = "countryOrigin")
    @NotNull
    private CountryOrigin countryOrigin;

    public PetDto(String code, String name, PetType type, FurColor furColor, CountryOrigin countryOrigin) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.furColor = furColor;
        this.countryOrigin = countryOrigin;
    }
}
