package training.cloud.crmmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.cloud.crmmicroservice.persistence.entity.CustomerEntity;

import java.util.List;

@Repository
public interface CrmRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    List<CustomerEntity> findByCountryName(String countryName);
}
