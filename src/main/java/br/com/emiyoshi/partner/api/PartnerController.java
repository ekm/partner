package br.com.emiyoshi.partner.api;

import br.com.emiyoshi.partner.api.dto.PdvDto;
import br.com.emiyoshi.partner.api.dto.PdvsDto;
import br.com.emiyoshi.partner.domain.Pdv;
import br.com.emiyoshi.partner.usecase.CreatePdv;
import br.com.emiyoshi.partner.usecase.SearchPdv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final CreatePdv createPdv;
    private final SearchPdv searchPdv;

    @Autowired
    public PartnerController(CreatePdv createPdv, SearchPdv searchPdv) {
        this.createPdv = createPdv;
        this.searchPdv = searchPdv;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createPartner(@Valid @RequestBody PdvsDto pdvs) {
        List<Pdv> pdvList = pdvs.getPdvs().stream().map(PdvDto::toPdv).collect(Collectors.toList());
        final List<Pdv> pdvsFromRepository = createPdv.create(pdvList);
        return "Partners criados: " + pdvsFromRepository.size();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public PdvDto getPartnerById(@PathVariable Long id) {
        Pdv pdv = searchPdv.findByIdQuery(id);
        return PdvDto.from(pdv);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PdvDto getPartnerByCoordinate(
            @RequestParam("lng") double lng, @RequestParam("lat") double lat) {
        Pdv pdv = searchPdv.findByCoordinate(lng, lat);
        return PdvDto.from(pdv);
    }
}
