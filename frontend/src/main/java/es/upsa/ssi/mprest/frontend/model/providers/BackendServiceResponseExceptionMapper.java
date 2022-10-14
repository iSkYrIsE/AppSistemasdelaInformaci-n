/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.mprest.frontend.model.providers;

import es.upsa.ssi.mprest.frontend.model.beans.DataError;
import es.upsa.ssi.mprest.frontend.model.exceptions.BackendServiceErrorException;
import es.upsa.ssi.mprest.frontend.model.exceptions.BackendServiceException;
import es.upsa.ssi.mprest.frontend.model.exceptions.PokemonNotFound;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import es.upsa.ssi.mprest.frontend.model.exceptions.DataErrorsException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.util.List;

public class BackendServiceResponseExceptionMapper implements ResponseExceptionMapper< BackendServiceException > 
{
    @Override
    public BackendServiceException toThrowable(Response response) 
    {
        boolean entityIsBuffered = response.bufferEntity();
        
        switch ( response.getStatus() )
        {
            case 404:  List<DataError> errors = response.readEntity( new GenericType< List<DataError> >(){} );
                       return new PokemonNotFound( errors.get(0) ); //new PokemonNotFound( dataError.getValue() );

            case 400:  List<DataError> errors2 = response.readEntity( new GenericType< List<DataError> >(){} );
                       return new DataErrorsException( errors2 );
            
            case 500:  return new BackendServiceErrorException( "Error en el servicio" );
        }
        return null;
    }    
}
