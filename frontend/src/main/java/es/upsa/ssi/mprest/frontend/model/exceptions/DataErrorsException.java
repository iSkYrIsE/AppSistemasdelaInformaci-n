package es.upsa.ssi.mprest.frontend.model.exceptions;

import es.upsa.ssi.mprest.frontend.model.beans.DataError;

import java.util.List;

public class DataErrorsException extends BackendServiceException
{
    private final List<DataError> errors;

    public DataErrorsException(List<DataError> errors) {
        super("Errores de los datos");
        this.errors = errors;
    }

    public List<DataError> getErrors() {
        return errors;
    }
}
