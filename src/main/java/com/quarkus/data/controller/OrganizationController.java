package com.quarkus.data.controller;


import com.quarkus.data.domain.Organization;
import com.quarkus.data.repo.OrganizationRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/organizations")
public class OrganizationController {
    @Inject
    OrganizationRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Long addOrganization( Organization organization) {
        Organization organization1 = repository.save(organization);
        return organization1.getId();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Organization> findOrganization(@PathParam("name") String name) {
        return repository.findByName(name);
    }
}
