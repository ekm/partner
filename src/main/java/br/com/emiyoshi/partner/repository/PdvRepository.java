package br.com.emiyoshi.partner.repository;

import br.com.emiyoshi.partner.domain.Pdv;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PdvRepository extends MongoRepository<Pdv, String> {

    @Query("{id : ?0}")
    Optional<Pdv> findByIdQuery(Long id);

    GeoResults<Pdv> findByCoverageAreaCoordinatesNear(Point location);
}
