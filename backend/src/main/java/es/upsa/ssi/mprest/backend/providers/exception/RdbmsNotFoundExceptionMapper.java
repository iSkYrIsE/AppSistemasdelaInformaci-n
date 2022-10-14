package es.upsa.ssi.mprest.backend.providers.exception;

import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RdbmsNotFoundExceptionMapper implements ExceptionMapper<RdbmsNotFoundException>
{
    @Override
    public Response toResponse(RdbmsNotFoundException exception)
    {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity( exception )
                       .build();
    }
}
