package br.com.emiyoshi.partner.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PdvsDto implements Serializable {

    @JsonProperty("pdvs")
    @Valid
    private List<PdvDto> pdvs;

    public List<PdvDto> getPdvs() {
        return pdvs;
    }

    public void setPdvs(List<PdvDto> pdvs) {
        this.pdvs = pdvs;
    }

    public boolean add(PdvDto pdv) {
        if (pdvs == null) pdvs = new ArrayList<>();

        return pdvs.add(pdv);
    }

    public PdvsDto(List<PdvDto> pdvs) {
        this.pdvs = pdvs;
    }

    public PdvsDto() {
    }
}
