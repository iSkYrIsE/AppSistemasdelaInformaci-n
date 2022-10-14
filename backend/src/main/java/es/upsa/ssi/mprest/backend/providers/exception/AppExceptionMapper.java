
package es.upsa.ssi.mprest.backend.providers.exception;

import es.upsa.ssi.mprest.backend.exceptions.AppException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsPokemonConstraintException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsNotFoundException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsSQLException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AppExceptionMapper implements ExceptionMapper< AppException >
{

    @Override
    public Response toResponse(AppException exception) 
    {
        if ( exception instanceof RdbmsPokemonConstraintException )
        {
            RdbmsPokemonConstraintException rdbmsPokemonConstraintException = (RdbmsPokemonConstraintException) exception;
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity( exception )
                           .build();
        }
        else if ( exception instanceof RdbmsNotFoundException )
        {
            RdbmsNotFoundException rdbmsNotFoundException = (RdbmsNotFoundException) exception;
            return Response.status(Response.Status.NOT_FOUND)
                           .entity( exception )
                           .build();
        }
        else if ( exception instanceof RdbmsSQLException )
        {
            return Response.serverError()
                           .entity( exception )
                           .build();
        }
        else 
        {
            return Response.serverError()
                           .entity( exception )
                           .build();
        }
    }
    
}
