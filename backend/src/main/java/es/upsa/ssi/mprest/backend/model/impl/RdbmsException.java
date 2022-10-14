package es.upsa.ssi.mprest.backend.model.impl;

import es.upsa.ssi.mprest.backend.exceptions.AppException;


public class RdbmsException extends AppException
{
    private String fieldKey;
    private String messageKey;
    private String value;

    public RdbmsException(String fieldKey, String messageKey, String value)
    {
        this.fieldKey = fieldKey;
        this.messageKey = messageKey;
        this.value = value;
    }

    public RdbmsException(String fieldKey, String messageKey, Throwable cause)
    {
        super(cause);
        this.fieldKey = fieldKey;
        this.messageKey = messageKey;
    }

    public RdbmsException(Throwable cause)
    {
        super(cause);
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getValue() {
        return value;
    }
}
