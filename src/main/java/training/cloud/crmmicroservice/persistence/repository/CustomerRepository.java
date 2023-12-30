package training.cloud.crmmicroservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.cloud.crmmicroservice.persistence.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<Customer> findByCountryName(String countryName);
}
