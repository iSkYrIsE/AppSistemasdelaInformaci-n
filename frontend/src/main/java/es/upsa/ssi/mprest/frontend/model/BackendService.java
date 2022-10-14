/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.mprest.frontend.model;

import es.upsa.ssi.mprest.frontend.model.beans.Pokemon;
import es.upsa.ssi.mprest.frontend.model.exceptions.BackendServiceException;
import es.upsa.ssi.mprest.frontend.model.providers.BackendServiceResponseExceptionMapper;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@RegisterProvider(BackendServiceResponseExceptionMapper.class)
@Path("/api/pokemon")
public interface BackendService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> requestAllPokemon() throws BackendServiceException;

    @GET
    @Path("{numeropoke}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon requestPokemonByNumero(@PathParam("numeropoke") String numero) throws BackendServiceException;

    /*
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon requestCreatePokemon(@BeanParam Pokemon pokemon) throws BackendServiceException;
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon requestCreatePokemon(@FormParam("numeroPoke") String numeroPoke,
            @FormParam("nombrePoke") String nombrePoke,
            @FormParam("tipo1") String tipo1,
            @FormParam("tipo2") String tipo2,
            @FormParam("descripcion") String descripcion) throws BackendServiceException;

    @PUT
    @Path("{numeropoke}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon requestUpdateOrCreatePokemonByNumero(@PathParam("numeropoke") String numero,
             Pokemon pokemon) throws BackendServiceException;

    @DELETE
    @Path("{numeropoke}")
    public void requestDeletePokemonByNumero(@PathParam("numeropoke") String numero) throws BackendServiceException;

}
