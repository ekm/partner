package br.com.emiyoshi.partner.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "pdv")
public class Pdv implements Serializable {

    @Id
    private Long id;

    private String tradingName;

    private String ownerName;

    private String document;

    private CoverageArea coverageArea;

    private Address address;

    public Pdv() {
    }

    ;

    private Pdv(Builder builder) {
        setId(builder.id);
        setTradingName(builder.tradingName);
        setOwnerName(builder.ownerName);
        setDocument(builder.document);
        setCoverageArea(builder.coverageArea);
        setAddress(builder.address);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public CoverageArea getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(CoverageArea coverageArea) {
        this.coverageArea = coverageArea;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static final class Builder {
        private Long id;
        private String tradingName;
        private String ownerName;
        private String document;
        private CoverageArea coverageArea;
        private Address address;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder tradingName(String val) {
            tradingName = val;
            return this;
        }

        public Builder ownerName(String val) {
            ownerName = val;
            return this;
        }

        public Builder document(String val) {
            document = val;
            return this;
        }

        public Builder coverageArea(CoverageArea val) {
            coverageArea = val;
            return this;
        }

        public Builder address(Address val) {
            address = val;
            return this;
        }

        public Pdv build() {
            return new Pdv(this);
        }
    }
}
