package br.com.emiyoshi.partner.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum AddressTypeDto implements Serializable {
    POINT("Point");

    private String description;

    private AddressTypeDto(String description) {
        this.description = description;
    }

    @JsonValue
    public String toString() {
        return description;
    }

    @JsonCreator
    public static AddressTypeDto forValues(@JsonProperty("description") String description) {
        for (AddressTypeDto addressType : AddressTypeDto.values()) {
            if (addressType.description.equalsIgnoreCase(description)) {
                return addressType;
            }
        }

        return null;
    }
}
