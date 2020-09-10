package com.quarkus.data.repo;


import com.quarkus.data.domain.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {


   @JoinColumn(name="departments", nullable=true)
   @JoinColumn(name="employees", nullable=true)
   Optional<Organization> findByName(String name);
}
