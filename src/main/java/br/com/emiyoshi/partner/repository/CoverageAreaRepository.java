package br.com.emiyoshi.partner.repository;

import br.com.emiyoshi.partner.domain.CoverageArea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverageAreaRepository extends MongoRepository<CoverageArea, String> {
}
