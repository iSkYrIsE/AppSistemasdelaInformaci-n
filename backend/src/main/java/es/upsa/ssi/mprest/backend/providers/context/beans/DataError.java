package es.upsa.ssi.mprest.backend.providers.context.beans;

import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.ConstraintViolation;

public class DataError
{
    public static DataError newInstance(ConstraintViolation<Pokemon> constraintViolation)
    {
        return new DataError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage(), constraintViolation.getInvalidValue().toString());
    }


    @JsonbProperty(value ="field", nillable = true)
    private String field;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty(value = "value", nillable = false)
    private String value;

    public DataError() {
    }

    public DataError(String field, String message, String value) {
        this.field = field;
        this.message = message;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public String getValue() {
        return value;
    }
}

