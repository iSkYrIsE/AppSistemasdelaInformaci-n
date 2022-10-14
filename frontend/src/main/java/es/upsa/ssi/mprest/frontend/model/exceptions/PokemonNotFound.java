
package es.upsa.ssi.mprest.frontend.model.exceptions;

import es.upsa.ssi.mprest.frontend.model.beans.DataError;

public class PokemonNotFound extends BackendServiceException
{
    private DataError dataError;

    public PokemonNotFound(DataError dataError)
    {
        super( String.format("No existe el pokemon con numero [%s]", dataError.getValue()) );
        this.dataError = dataError;
    }

    public DataError getDataError() {
        return dataError;
    }
        
}
