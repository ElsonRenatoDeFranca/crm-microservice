package training.cloudnative.crmmicroservice.repository;

import training.cloudnative.crmmicroservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmployeeId(String employeeId);
    void deleteByEmployeeId(String employeeId);
    List<Employee> findByCountryName(String countryName);
}
