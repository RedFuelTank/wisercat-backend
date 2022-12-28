package com.wisercat.bestfriend.model;

import com.wisercat.bestfriend.enums.CountryOrigin;
import com.wisercat.bestfriend.enums.FurColor;
import com.wisercat.bestfriend.enums.PetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @OneToOne
    @JoinColumn(name = "owner_username")
    private User owner;
    @NonNull
    @Column(name = "code")
    private String code;
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PetType type;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fur_color")
    private FurColor furColor;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "country_origin")
    private CountryOrigin countryOrigin;

}
