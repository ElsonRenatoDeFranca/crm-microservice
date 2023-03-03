package training.cloud.crmmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.cloud.crmmicroservice.model.Customer;

import java.util.List;

public interface CrmRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<Customer> findByCountryName(String countryName);
}
