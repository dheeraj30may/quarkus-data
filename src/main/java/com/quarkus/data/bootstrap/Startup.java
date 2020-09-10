package com.quarkus.data.bootstrap;

import com.quarkus.data.domain.Department;
import com.quarkus.data.domain.Employee;
import com.quarkus.data.domain.Organization;
import com.quarkus.data.repo.DepartmentRepository;
import com.quarkus.data.repo.EmployeeRepository;
import com.quarkus.data.repo.OrganizationRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Arrays;

@ApplicationScoped
public class Startup {


    @Inject
    private DepartmentRepository departmentRepository;
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private OrganizationRepository organizationRepository;

    void onStart(@Observes StartupEvent ev) {
        Organization accenture = new Organization("Accenture", "Hartford");
        Organization organizationAccentureSaved = organizationRepository.save(accenture);

        Department techdepartment = new Department("Technology");
        techdepartment.setOrganization(accenture);
        Department departmentSaved = departmentRepository.save(techdepartment);

        Employee dheeraj = new Employee("Dheeraj", 30, "Developer", 50000);
        dheeraj.setOrganization(organizationAccentureSaved);
        dheeraj.setDepartment(departmentSaved);

        Employee sandeep = new Employee("Sandeep", 30, "Developer", 60000);
        dheeraj.setOrganization(organizationAccentureSaved);
        dheeraj.setDepartment(departmentSaved);

        Employee saurabh = new Employee("Saurabh", 50, "Developer", 50000);
        dheeraj.setOrganization(organizationAccentureSaved);
        dheeraj.setDepartment(departmentSaved);


        Department hrDepartment = new Department("HR");
        hrDepartment.setOrganization(accenture);
        Department hrDepSAved = departmentRepository.save(hrDepartment);

        Employee sunil = new Employee("Sunil", 30, "Developer", 40000);
        dheeraj.setOrganization(organizationAccentureSaved);
        dheeraj.setDepartment(hrDepSAved);

        Employee arun = new Employee("arun", 40, "Developer", 40000);
        dheeraj.setOrganization(organizationAccentureSaved);
        dheeraj.setDepartment(hrDepSAved);

        employeeRepository.saveAll(Arrays.asList(dheeraj, sandeep, saurabh, sunil, arun));
    }

    void onStop(@Observes ShutdownEvent ev) {


    }
}
