package br.com.emiyoshi.partner.api.dto;

import br.com.emiyoshi.partner.domain.Pdv;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PdvDto implements Serializable {

    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("tradingName")
    @NotEmpty
    private String tradingName;

    @JsonProperty("ownerName")
    @NotEmpty
    private String ownerName;

    @JsonProperty("document")
    @NotEmpty
    private String document;

    @JsonProperty("coverageArea")
    @Valid
    private CoverageAreaDto coverageArea;

    @JsonProperty("address")
    @Valid
    private AddressDto address;

    public PdvDto() {
    }

    ;

    private PdvDto(Builder builder) {
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

    public CoverageAreaDto getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(CoverageAreaDto coverageArea) {
        this.coverageArea = coverageArea;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Pdv toPdv() {
        return Pdv.builder()
                .id(this.getId())
                .document(this.getDocument())
                .ownerName(this.getOwnerName())
                .tradingName(this.getTradingName())
                .coverageArea(this.getCoverageArea().toCoverageArea())
                .address(this.getAddress().toAddress())
                .build();
    }

    public static PdvDto from(Pdv pdv) {
        return PdvDto.builder()
                .id(pdv.getId())
                .document(pdv.getDocument())
                .ownerName(pdv.getOwnerName())
                .tradingName(pdv.getTradingName())
                .coverageArea(
                        CoverageAreaDto.builder()
                                .type(CoverageAreaTypeDto.forValues(pdv.getCoverageArea().getType().toString()))
                                .coordinates(pdv.getCoverageArea().getCoordinates())
                                .build())
                .address(
                        AddressDto.builder()
                                .type(pdv.getAddress().getType().toString())
                                .coordinates(pdv.getAddress().getCoordinates())
                                .build())
                .build();
    }

    public static final class Builder {
        private Long id;
        private String tradingName;
        private String ownerName;
        private String document;
        private CoverageAreaDto coverageArea;
        private AddressDto address;

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

        public Builder coverageArea(CoverageAreaDto val) {
            coverageArea = val;
            return this;
        }

        public Builder address(AddressDto val) {
            address = val;
            return this;
        }

        public PdvDto build() {
            return new PdvDto(this);
        }
    }
}
