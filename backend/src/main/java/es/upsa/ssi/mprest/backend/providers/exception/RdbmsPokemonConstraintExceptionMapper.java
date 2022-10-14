package es.upsa.ssi.mprest.backend.providers.exception;

import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsConstraintException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class RdbmsPokemonConstraintExceptionMapper implements ExceptionMapper<RdbmsConstraintException>
{
    @Override
    public Response toResponse(RdbmsConstraintException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity( exception )
                       .build();
    }
}
