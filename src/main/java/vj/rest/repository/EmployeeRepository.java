package vj.rest.repository;

import org.springframework.data.repository.CrudRepository;
import vj.rest.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findByFirstNameAndLastName(String lastname, String firstName);
}
