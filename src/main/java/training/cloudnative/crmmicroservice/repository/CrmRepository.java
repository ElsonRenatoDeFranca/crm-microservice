package training.cloudnative.crmmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.cloudnative.crmmicroservice.model.Customer;

import java.util.List;

public interface CrmRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<Customer> findByCountryName(String countryName);
}
