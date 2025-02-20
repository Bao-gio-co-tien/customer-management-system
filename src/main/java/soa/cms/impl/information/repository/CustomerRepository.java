package soa.cms.impl.information.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import soa.cms.impl.information.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
