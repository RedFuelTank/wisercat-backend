package com.wisercat.bestfriend.custom_assert;

import com.wisercat.bestfriend.dto.PetDto;
import org.assertj.core.api.*;

import java.util.List;

public class PetDtoAssert extends AbstractAssert<PetDtoAssert, PetDto> {
    protected PetDtoAssert(PetDto petDto) {
        super(petDto, PetDtoAssert.class);
    }


    public static PetDtoAssert assertThat(PetDto petDto) {
        return new PetDtoAssert(petDto);
    }

    public static AbstractLongAssert<?> assertThat(Long id) {
        return Assertions.assertThat(id);
    }

    public static AbstractStringAssert<?> assertThat(String id) {
        return Assertions.assertThat(id);
    }

    public static <T> ListAssert<T> assertThat(List<T> list) {
        return Assertions.assertThat(list);
    }

    public static <T> ObjectAssert<T> assertThat(T object) {
        return Assertions.assertThat(object);
    }

    @Override
    public PetDtoAssert isEqualTo(Object expected) {
        return super.isEqualTo(expected);
    }

    public PetDtoAssert hasName() {
        isNotNull();
        if (actual.getName() == null) {
            failWithMessage(
                    "Expected pet dto to have name, but it was null"
            );
        }
        return this;
    }

    public PetDtoAssert hasId() {
        isNotNull();
        if (actual.getId() == null) {
            failWithMessage(
                    "Expected pet dto to have ID, but it was null"
            );
        }
        return this;
    }

    public PetDtoAssert hasCode() {
        isNotNull();
        if (actual.getCode() == null) {
            failWithMessage(
                    "Expected pet dto to have code, but it was null"
            );
        }
        return this;
    }

    public PetDtoAssert hasPetType() {
        isNotNull();
        if (actual.getType() == null) {
            failWithMessage(
                    "Expected pet dto to have type, but it was null"
            );
        }
        return this;
    }
    public PetDtoAssert hasFurColor() {
        isNotNull();
        if (actual.getFurColor() == null) {
            failWithMessage(
                    "Expected pet dto to have fur color, but it was null"
            );
        }
        return this;
    }

    public PetDtoAssert hasCountryOrigin() {
        isNotNull();
        if (actual.getCountryOrigin() == null) {
            failWithMessage(
                    "Expected pet dto to have country of origin, but it was null"
            );
        }
        return this;
    }


}
