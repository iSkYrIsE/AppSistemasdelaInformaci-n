package es.upsa.ssi.mprest.frontend.resources;

import es.upsa.ssi.mprest.frontend.model.beans.Pokemon;
import es.upsa.ssi.mprest.frontend.model.BackendService;
import es.upsa.ssi.mprest.frontend.model.exceptions.BackendServiceException;
import es.upsa.ssi.mprest.frontend.model.exceptions.PokemonNotFound;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.upsa.ssi.mprest.frontend.model.exceptions.DataErrorsException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("pokemon")
public class PokemonResource {

    @Inject
    @RestClient
    BackendService backendService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestPokemon() {
        try {
            List<Pokemon> allPokemon = backendService.requestAllPokemon();

            return Response.ok()
                    .entity(new GenericEntity< List<Pokemon>>(allPokemon) {
                    })
                    .build();

        } catch (BackendServiceException ex) {
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }

    }

    @GET
    @Path("{numeropoke}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestPokemon(@PathParam("numeropoke") String numero) throws BackendServiceException {
        try {
            Pokemon pokemon = backendService.requestPokemonByNumero(numero);
            return Response.ok()
                    .entity(pokemon)
                    .build();

        } catch (PokemonNotFound ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ex.getDataError())
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPokemon(@BeanParam Pokemon pokemon) throws BackendServiceException {
        try {
            Pokemon newPokemon = backendService.requestCreatePokemon(pokemon.getNumeroPoke(),
                    pokemon.getNombrePoke(),
                    pokemon.getTipo1(),
                    pokemon.getTipo2(),
                    pokemon.getDescripcion());
            return Response.ok()
                    .entity(newPokemon)
                    .build();

        } catch (DataErrorsException dee) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(dee.getErrors())
                    .build();
        }
    }

    @PUT
    @Path("{numeropoke}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrCreatePokemon(@PathParam("numeropoke") String numero, Pokemon pokemon) throws BackendServiceException {
        try {
            Pokemon newPokemon = backendService.requestUpdateOrCreatePokemonByNumero(numero, pokemon);
            return Response.ok()
                    .entity(newPokemon)
                    .build();

        } catch (DataErrorsException dee) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(dee.getErrors())
                    .build();
        }
    }

    @DELETE
    @Path("{numeropoke}")
    public Response deletePokemon(@PathParam("numeropoke") String numero) throws BackendServiceException {
        try {
            backendService.requestDeletePokemonByNumero(numero);
            return Response.ok()
                    .build();

        } catch (DataErrorsException dee) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(dee.getErrors())
                    .build();
        }
    }

}
