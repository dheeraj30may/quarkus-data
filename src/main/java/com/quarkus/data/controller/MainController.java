package com.quarkus.data.controller;


import com.quarkus.data.domain.ChangeJobRequest;
import com.quarkus.data.domain.Department;
import com.quarkus.data.domain.Employee;
import com.quarkus.data.domain.Organization;
import com.quarkus.data.repo.DepartmentRepository;
import com.quarkus.data.repo.EmployeeRepository;
import com.quarkus.data.repo.OrganizationRepository;
import com.quarkus.data.service.Service;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/api")
public class MainController {

    @Inject
    DepartmentRepository departmentRepository;
    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    OrganizationRepository organizationRepository;
    @Inject
    Service service;

    @GET
    @Path("/organization")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @GET
    @Path("/organization/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Organization> findOrganization(@PathParam("name") String name) {
        return organizationRepository.findByName(name);
    }

    @GET
    @Path("/organization/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Organization> findOrganizationById(@PathParam("id") Long id) {
        return organizationRepository.findById(id);
    }

    @POST
    @Path("/add-organization")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization addOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @POST
    @Path("/change-job")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeJob(@Valid ChangeJobRequest request) {
        service.changeJob(request.getEmployeeId(), request.getTargetDepartmentId());
    }

    @POST
    @Path("/employee/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addNewEmployee(@Valid Employee employee, @PathParam("departmentId") Long departmentId) {
        service.hireEmployee(employee, departmentId);
    }

    @POST
    @Path("/departments/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Department addNewDepartment(@Valid Department department, @PathParam("organizationId") Long organizationId) {
        organizationRepository.findById(organizationId).ifPresent(organization -> {
            department.setOrganization(organization);
            departmentRepository.save(department);
        });
        return department;
    }
}
