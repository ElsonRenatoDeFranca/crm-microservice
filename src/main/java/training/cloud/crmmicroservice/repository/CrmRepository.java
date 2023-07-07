package training.cloud.crmmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.cloud.crmmicroservice.model.Customer;

import java.util.List;

@Repository
public interface CrmRepository extends JpaRepository<Customer, String> {
    Customer findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<Customer> findByCountryName(String countryName);
}
