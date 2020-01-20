package br.com.emiyoshi.partner.usecase;

import br.com.emiyoshi.partner.domain.Pdv;
import br.com.emiyoshi.partner.repository.PdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatePdv {

    private final PdvRepository pdvRepository;

    @Autowired
    public CreatePdv(PdvRepository pdvRepository) {
        this.pdvRepository = pdvRepository;
    }

    public List<Pdv> create(List<Pdv> pdvs) {
        return pdvRepository.saveAll(pdvs);
    }
}
