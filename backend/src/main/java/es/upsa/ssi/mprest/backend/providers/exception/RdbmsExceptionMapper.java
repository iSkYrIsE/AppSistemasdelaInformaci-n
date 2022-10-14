package es.upsa.ssi.mprest.backend.providers.exception;

import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsSQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RdbmsExceptionMapper implements ExceptionMapper<RdbmsSQLException>
{
    @Override
    public Response toResponse(RdbmsSQLException exception)
    {
        return Response.serverError()
                .entity( exception )
                .build();
    }
}
