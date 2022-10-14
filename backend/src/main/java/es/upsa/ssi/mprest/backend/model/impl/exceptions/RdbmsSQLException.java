package es.upsa.ssi.mprest.backend.model.impl.exceptions;

import es.upsa.ssi.mprest.backend.model.impl.RdbmsException;

import java.sql.SQLException;

public class RdbmsSQLException extends RdbmsException
{
    public RdbmsSQLException(SQLException sqlException) {
        super(sqlException);
    }

    public RdbmsSQLException(String fieldKey, String messageKey, SQLException cause) {
        super(fieldKey, messageKey, cause);
    }
}
