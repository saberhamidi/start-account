package com.qa.rest;

import com.qa.domain.Account;
import com.qa.repository.Repository;

import javax.inject.Inject;
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
    public Response getAccount(Long id){
        Account account = repository.find(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account, @Context UriInfo uriInfo){
        account = repository.create(account);
        URI createdURI = uriInfo.getBaseUriBuilder().path(account.getAccountNumber().toString()).build();
        return Response.created(createdURI).build();
    }

//    public Response deleteAccount(Long id){
//
//    }
//
//    public Response updateAccount(Account account){
//
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(){
        List<Account> accounts = repository.findAll();
        if (accounts.size() == 0) {
            System.out.println("api method call");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.ok(accounts).build();
        }
    }

}
