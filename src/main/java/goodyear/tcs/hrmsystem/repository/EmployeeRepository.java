package goodyear.tcs.hrmsystem.repository;

import goodyear.tcs.hrmsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmployeeId(String employeeId);
    void deleteByEmployeeId(String employeeId);
}
