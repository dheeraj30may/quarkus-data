package com.quarkus.data.controller;


import com.quarkus.data.domain.ChangeJobRequest;
import com.quarkus.data.domain.Employee;
import com.quarkus.data.domain.Organization;
import com.quarkus.data.dto.EmployeeDTO;
import com.quarkus.data.repo.EmployeeRepository;
import com.quarkus.data.repo.OrganizationRepository;
import com.quarkus.data.service.Service;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/employees")
public class EmployeeController {
    @Inject
    EmployeeRepository repository;
    @Inject
    OrganizationRepository organizationRepository;
    @Inject
    Service service;

    @GET
    @Path("/salary/{salary}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> findEmployeesBySalary(@PathParam("salary") int salary) {
        return repository.findBySalaryGreaterThan(salary);
    }

    @GET
    @Path("/organization/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> findEmployeesByOrganization(@PathParam("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        return repository.findByOrganization(organization.get());
    }

    @GET
    @Path("/salary-avg/age/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public int findAvgSalaryByAge(@PathParam("age") int age) {
        return repository.findAvgSalaryByAge(age);
    }

    @GET
    @Path("/salary-avg/organization/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int findAvgSalaryByAge(@PathParam("organizationId") Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        return repository.findAvgSalaryByOrganization(organization.get());
    }

    @POST
    @Path("/{departmentId}")
    public void addNewEmployee(Employee employee, @PathParam("departmentId") Long departmentId) {
        service.hireEmployee(employee, departmentId);
    }

    @PUT
    @Path("/change-job")
    public void changeJob(ChangeJobRequest request) {
        service.changeJob(request.getEmployeeId(), request.getTargetDepartmentId());
    }
}
