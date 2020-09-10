package com.quarkus.data.repo;


import com.quarkus.data.domain.Employee;
import com.quarkus.data.domain.Employment;
import com.quarkus.data.dto.EmploymentDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends CrudRepository<Employment, Long> {

    List<EmploymentDTO> findByEmployeeOrderByStartDesc(Employee employee);

    Employment findByEmployeeAndEndIsNull(Employee employee);
}
