package com.quarkus.data.repo;
import com.quarkus.data.domain.Employee;
import com.quarkus.data.domain.Organization;
import com.quarkus.data.dto.EmployeeDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<EmployeeDTO> findBySalaryGreaterThan(int salary);

    List<EmployeeDTO> findByOrganization(Organization organization);

    int findAvgSalaryByAge(int age);

    int findAvgSalaryByOrganization(Organization organization);

}
