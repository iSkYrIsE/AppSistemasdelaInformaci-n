package es.upsa.ssi.mprest.backend.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class LinksBuilder
{
    public Link selectPokemonByNumero(UriInfo uriInfo, String numero, String rel)
    {
        return Link.fromUriBuilder( uriInfo.getBaseUriBuilder()
                                           .path(PokemonResource.class)
                                           .path(PokemonResource.class, "selectPokemonByNumero"))
                   .rel(rel)
                   .build(numero);
    }
}
