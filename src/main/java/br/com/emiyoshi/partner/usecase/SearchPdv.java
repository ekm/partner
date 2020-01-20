package br.com.emiyoshi.partner.usecase;

import br.com.emiyoshi.partner.domain.Pdv;
import br.com.emiyoshi.partner.repository.PdvRepository;
import br.com.emiyoshi.partner.usecase.exception.PartnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class SearchPdv {

    private final PdvRepository pdvRepository;
    private final double distance;

    @Autowired
    public SearchPdv(
            PdvRepository pdvRepository, @Value("${coverage.area.distance}") double distance) {
        this.pdvRepository = pdvRepository;
        this.distance = distance;
    }

    public Pdv findByIdQuery(Long id) {
        return pdvRepository
                .findByIdQuery(id)
                .orElseThrow(() -> new PartnerNotFoundException("Partner nao encontrado"));
    }

    public Pdv findByCoordinate(double lng, double lat) {
        final GeoResults<Pdv> pdvGeoResult =
                pdvRepository.findByCoverageAreaCoordinatesNear(new Point(lng, lat));
        if (pdvGeoResult.getContent().isEmpty()) {
            throw new PartnerNotFoundException("Partner nao encontrado");
        }
        return pdvGeoResult.getContent().get(0).getContent();
    }
}
