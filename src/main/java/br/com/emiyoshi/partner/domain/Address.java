package br.com.emiyoshi.partner.domain;

import br.com.emiyoshi.partner.util.PointDeserializer;
import br.com.emiyoshi.partner.util.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Document(collection = "address")
public class Address {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    @JsonDeserialize(using = PointDeserializer.class)
    @JsonSerialize(using = PointSerializer.class)
    private Point coordinates;

    public Address() {
    }

    private Address(Builder builder) {
        setId(builder.id);
        setType(builder.type);
        setCoordinates(builder.coordinates);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public static final class Builder {
        private String id;
        private AddressType type;
        private Point coordinates;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(AddressType val) {
            type = val;
            return this;
        }

        public Builder coordinates(Point val) {
            coordinates = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
