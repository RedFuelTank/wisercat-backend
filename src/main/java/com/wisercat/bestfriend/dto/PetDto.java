package com.wisercat.bestfriend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisercat.bestfriend.dto.enums.CountryOrigin;
import com.wisercat.bestfriend.dto.enums.FurColor;
import com.wisercat.bestfriend.dto.enums.PetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class PetDto {
    @JsonProperty(namespace = "id")
    @Setter
    private Long id;
    @JsonProperty(required = true, namespace = "name")
    private final String name;
    @JsonProperty(required = true, namespace = "code")
    private final String code;
    @JsonProperty(required = true, namespace = "type")
    private final PetType type;
    @JsonProperty(required = true, namespace = "furColor")
    private final FurColor furColor;
    @JsonProperty(required = true, namespace = "countryOrigin")
    private final CountryOrigin countryOrigin;
}
