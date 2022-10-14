package es.upsa.ssi.mprest.backend.model.impl.exceptions;

import es.upsa.ssi.mprest.backend.model.impl.RdbmsException;

public class RdbmsNotFoundException extends RdbmsException
{
    public RdbmsNotFoundException(String fieldKey, String value)
    {
        super(fieldKey, "error.not_found", value);
    }
}

