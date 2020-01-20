package br.com.emiyoshi.partner.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum CoverageAreaTypeDto implements Serializable {
    MULTIPOLYGON("MultiPolygon");

    private String description;

    private CoverageAreaTypeDto(String description) {
        this.description = description;
    }

    @JsonValue
    public String toString() {
        return description;
    }

    @JsonCreator
    public static CoverageAreaTypeDto forValues(@JsonProperty("description") String description) {
        for (CoverageAreaTypeDto coverageAreaType : CoverageAreaTypeDto.values()) {
            if (coverageAreaType.description.equalsIgnoreCase(description)) {
                return coverageAreaType;
            }
        }

        return null;
    }
}
