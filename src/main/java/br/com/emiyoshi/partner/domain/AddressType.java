package br.com.emiyoshi.partner.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum AddressType implements Serializable {
    POINT("Point");

    private String description;

    private AddressType(String description) {
        this.description = description;
    }

    @JsonValue
    public String toString() {
        return description;
    }

    @JsonCreator
    public static AddressType forValues(@JsonProperty("description") String description) {
        for (AddressType addressType : AddressType.values()) {
            if (addressType.description.equalsIgnoreCase(description)) {
                return addressType;
            }
        }

        return null;
    }
}
