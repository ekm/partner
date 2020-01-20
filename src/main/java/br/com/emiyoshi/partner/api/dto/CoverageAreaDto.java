package br.com.emiyoshi.partner.api.dto;

import br.com.emiyoshi.partner.domain.CoverageArea;
import br.com.emiyoshi.partner.domain.CoverageAreaType;
import br.com.emiyoshi.partner.domain.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoverageAreaDto implements Serializable {

    @NotNull
    private CoverageAreaTypeDto type;

    @NotEmpty
    private List<List<List<Point>>> coordinates;

    public CoverageAreaDto() {
    }

    private CoverageAreaDto(Builder builder) {
        setType(builder.type);
        setCoordinates(builder.coordinates);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CoverageAreaTypeDto getType() {
        return type;
    }

    public void setType(CoverageAreaTypeDto type) {
        this.type = type;
    }

    public List<List<List<Point>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Point>>> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean add(Point coordinate) {
        if (coordinates == null) coordinates = new ArrayList<>();

        List<List<Point>> lists = new ArrayList<>();
        lists.add(Arrays.asList(coordinate));
        return coordinates.add(lists);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("formato json inválido", e);
        }
    }

    public CoverageArea toCoverageArea() {
        return CoverageArea.builder()
                .type(CoverageAreaType.forValues(this.getType().toString()))
                .coordinates(this.coordinates)
                .build();
    }

    public static final class Builder {
        private CoverageAreaTypeDto type;
        private List<List<List<Point>>> coordinates;

        private Builder() {
        }

        public Builder type(CoverageAreaTypeDto val) {
            type = val;
            return this;
        }

        public Builder type(String val) {
            CoverageAreaTypeDto coverageAreaTypeDto = CoverageAreaTypeDto.forValues(val);
            type = coverageAreaTypeDto;
            return this;
        }

        public Builder coordinates(List<List<List<Point>>> val) {
            coordinates = val;
            return this;
        }

        public Builder coordinates(String val) {
            try {
                List<List<List<Point>>> coordList = new ArrayList<>();
                coordinates = new ObjectMapper().readValue(val, coordList.getClass());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return this;
        }

        public CoverageAreaDto build() {
            return new CoverageAreaDto(this);
        }
    }
}
