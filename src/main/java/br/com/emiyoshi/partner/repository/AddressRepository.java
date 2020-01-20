package br.com.emiyoshi.partner.repository;

import br.com.emiyoshi.partner.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
