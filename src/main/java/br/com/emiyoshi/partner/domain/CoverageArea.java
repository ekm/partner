package br.com.emiyoshi.partner.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document(collection = "coverage-area")
public class CoverageArea implements Serializable {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private CoverageAreaType type;

    private List<List<List<Point>>> coordinates;
    //  private String coordinates;

    public CoverageArea() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private CoverageArea(Builder builder) {
        id = builder.id;
        setType(builder.type);
        setCoordinates(builder.coordinates);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CoverageAreaType getType() {
        return type;
    }

    public void setType(CoverageAreaType type) {
        this.type = type;
    }

    public List<List<List<Point>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Point>>> coordinates) {
        this.coordinates = coordinates;
    }

    public static final class Builder {
        private String id;
        private CoverageAreaType type;
        private List<List<List<Point>>> coordinates;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(CoverageAreaType val) {
            type = val;
            return this;
        }

        public Builder coordinates(List<List<List<Point>>> val) {
            coordinates = val;
            return this;
        }

        public CoverageArea build() {
            return new CoverageArea(this);
        }
    }
}
