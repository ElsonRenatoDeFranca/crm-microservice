package training.cloud.crmmicroservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<CustomerEntity> findByCountryName(String countryName);
}
