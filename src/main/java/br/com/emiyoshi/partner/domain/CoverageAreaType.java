package br.com.emiyoshi.partner.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum CoverageAreaType implements Serializable {
    MULTIPOLYGON("MultiPolygon");

    private String description;

    private CoverageAreaType(String description) {
        this.description = description;
    }

    @JsonValue
    public String toString() {
        return description;
    }

    @JsonCreator
    public static CoverageAreaType forValues(@JsonProperty("description") String description) {
        for (CoverageAreaType coverageAreaType : CoverageAreaType.values()) {
            if (coverageAreaType.description.equalsIgnoreCase(description)) {
                return coverageAreaType;
            }
        }

        return null;
    }
}
