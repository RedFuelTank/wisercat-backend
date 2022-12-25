package com.wisercat.bestfriend.model;

import com.wisercat.bestfriend.enums.CountryOrigin;
import com.wisercat.bestfriend.enums.FurColor;
import com.wisercat.bestfriend.enums.PetType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pet {
    @Id
    private Long id;
    @NonNull
    private String ownerUsername;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private PetType petType;
    @NonNull
    private FurColor furColor;
    @NonNull
    private CountryOrigin countryOrigin;

}
