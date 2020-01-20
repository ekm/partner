package br.com.emiyoshi.partner.api.dto;

import br.com.emiyoshi.partner.domain.Address;
import br.com.emiyoshi.partner.domain.AddressType;
import br.com.emiyoshi.partner.domain.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;

public class AddressDto {
    @NotNull
    private AddressTypeDto type;

    @NotNull
    private Point coordinates;

    public AddressDto() {
    }

    private AddressDto(Builder builder) {
        setType(builder.type);
        setCoordinates(builder.coordinates);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AddressTypeDto getType() {
        return type;
    }

    public void setType(AddressTypeDto type) {
        this.type = type;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("formato json inválido", e);
        }
    }

    public Address toAddress() {
        return Address.builder()
                .type(AddressType.forValues(this.getType().toString()))
                .coordinates(this.coordinates)
                .build();
    }

    public static final class Builder {
        private AddressTypeDto type;
        private Point coordinates;

        private Builder() {
        }

        public Builder type(AddressTypeDto val) {
            type = val;
            return this;
        }

        public Builder type(String val) {
            AddressTypeDto addressTypeDto = AddressTypeDto.forValues(val);
            type = addressTypeDto;
            return this;
        }

        public Builder coordinates(Point val) {
            coordinates = val;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(this);
        }
    }
}
