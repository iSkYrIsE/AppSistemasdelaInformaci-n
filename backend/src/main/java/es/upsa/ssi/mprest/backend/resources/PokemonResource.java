package es.upsa.ssi.mprest.backend.resources;

import es.upsa.ssi.mprest.backend.exceptions.AppException;
import es.upsa.ssi.mprest.backend.model.Dao;
import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsNotFoundException;
import es.upsa.ssi.mprest.backend.providers.context.beans.DataError;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("pokemon")
public class PokemonResource {

    @Inject
    Dao dao;

    @Inject
    Validator validator;

    @Context
    private UriInfo uriInfo;

    @Inject
    LinksBuilder linksBuilder;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAllPokemon() throws AppException {
        return Response.ok()
                .entity(dao.select())
                .build();
    }

    @GET
    @Path("{numeropoke}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectPokemonByNumero(@PathParam("numeropoke") String numero) throws AppException {
        return Response.ok()
                .entity(dao.select(numero))
                .build();
    }

    

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPokemon(@BeanParam Pokemon form) throws AppException {
        return this.produceResponseForInvalidData(form)
                .orElse(createPokemon(form));
    }

    @PUT
    @Path("{numeropoke}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPokemon(@PathParam("numeropoke") String numero, Pokemon pokemon) throws AppException {
        Optional<Response> optResponse = produceResponseForInvalidData(pokemon);
        if (optResponse.isPresent()) {
            return optResponse.get();
        }

        try {
            Pokemon updatedPokemon = dao.updateByNumero(numero, pokemon);
            return Response.ok()
                    .entity(updatedPokemon)
                    .links(linksBuilder.selectPokemonByNumero(uriInfo, updatedPokemon.getNumeroPoke(), "self"))
                    .build();

        } catch (RdbmsNotFoundException exception) {
            Pokemon newPokemon = dao.insert(pokemon);
            return Response.created(linksBuilder.selectPokemonByNumero(uriInfo, newPokemon.getNumeroPoke(), "self").getUri())
                    .entity(newPokemon)
                    .build();
        }
    }

    @DELETE
    @Path("{numeropoke}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePokemon(@PathParam("numeropoke") String numero) throws AppException {
        dao.delete(numero);
        return Response.noContent()
                .build();
    }

    private Response createPokemon(Pokemon pokemon) throws AppException {
        Pokemon newPokemon = dao.insert(pokemon);
        return Response.created(linksBuilder.selectPokemonByNumero(uriInfo, newPokemon.getNumeroPoke(), "self").getUri())
                .entity(newPokemon)
                .build();
    }

    private Optional< Response> produceResponseForInvalidData(Pokemon pokemon) {
        Response response = null;
        Set<ConstraintViolation<Pokemon>> errors = validator.validate(pokemon);
        if (!errors.isEmpty()) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity(errors.stream()
                            .map(DataError::newInstance)
                            .collect(Collectors.toList()))
                    .build();
        }
        return Optional.ofNullable(response);
    }

}
