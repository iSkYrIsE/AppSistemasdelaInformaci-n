package es.upsa.ssi.mprest.backend.model.impl;

import java.sql.SQLException;
import java.util.function.Predicate;

public enum SQLConstraint implements Predicate<SQLException>
{
    DUPLICATE_NUMERO("PK_POKEMON", "numeropoke", "error.duplicate"),
    DUPLICATE_NOMBRE("UK_POKEMON.NOMBREPOKE", "nombrepoke", "error.duplicate"),
    NULL_NOMBRE("NN_POKEMON.NOMBREPOKE", "nombrepoke", "error.nn"),
    NULL_TIPO1("NN_POKEMON.TIPO1", "tipo1", "error.nn"),
    NULL_DESCRIPCION("NN_POKEMON.DESCRIPCION", "descripcion", "error.nn");

    private String constraintId;
    private String fieldKey;
    private String messageKey;

    private SQLConstraint(String constraintId, String fieldKey, String messageKey)
    {
        this.constraintId = constraintId;
        this.fieldKey = fieldKey;
        this.messageKey = messageKey;
    }

    public String getConstraintId()
    {
        return constraintId;
    }

    public String getFieldKey() {
        return fieldKey;
    }



    public String getMessageKey()
    {
        return messageKey;
    }


    @Override
    public boolean test(SQLException sqlException)
    {
        return sqlException.getMessage().contains( getConstraintId() );
    }

}
