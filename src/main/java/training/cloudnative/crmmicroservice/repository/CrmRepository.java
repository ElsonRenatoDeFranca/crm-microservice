package training.cloudnative.crmmicroservice.repository;

import training.cloudnative.crmmicroservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrmRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerId(String customerId);
    void deleteByCustomerId(String customerId);

    List<Customer> findByCountryName(String countryName);
}
