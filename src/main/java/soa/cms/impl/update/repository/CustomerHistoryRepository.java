package soa.cms.impl.update.repository;

import com.example.customer.model.CustomerHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHistoryRepository extends MongoRepository<CustomerHistory, String> {
}
