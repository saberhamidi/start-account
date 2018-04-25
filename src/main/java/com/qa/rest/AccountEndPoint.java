package com.qa.rest;

import com.qa.domain.Account;
import com.qa.repository.Repository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path("/accounts")
public class AccountEndPoint {

    @Inject
    private Repository repository;

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") @Min(1) Long id){
        Account account = repository.find(id);
        if (account == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account, @Context UriInfo uriInfo){
       String result =  repository.create(account);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteAccount(@PathParam("id") @Min(1) Long id){
        String responce = repository.delete(id);
        return Response.ok(responce).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(Account account){
        account = repository.update(account);
        return Response.ok(account).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(){
        List<Account> accounts = repository.findAll();
        if (accounts.size() == 0) {
            System.out.println("Method called");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.ok(accounts).build();
        }
    }
}
