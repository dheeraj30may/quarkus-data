package com.quarkus.data.controller;

import com.quarkus.data.domain.Employee;
import com.quarkus.data.dto.EmploymentDTO;
import com.quarkus.data.repo.EmployeeRepository;
import com.quarkus.data.repo.EmploymentRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/employments")
public class EmploymentController {
   @Inject
   EmployeeRepository employeeRepository;
    @Inject
    EmploymentRepository repository;

    @GET
    @Path("/employee/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmploymentDTO> findByEmployee(@PathParam("employeeId") Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return repository.findByEmployeeOrderByStartDesc(employee.get());
    }
}
