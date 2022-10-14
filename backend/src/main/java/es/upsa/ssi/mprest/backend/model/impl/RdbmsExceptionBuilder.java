package es.upsa.ssi.mprest.backend.model.impl;

import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsPokemonConstraintException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsNotFoundException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsSQLException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public class RdbmsExceptionBuilder
{
    public static RdbmsException pokemonNotFound(String numero)
    {
        return new RdbmsNotFoundException("numeropoke", numero);
    }

    public static RdbmsException of(SQLException sqlException)
    {
        return new RdbmsSQLException(sqlException);
    }

    public static RdbmsException of(Pokemon pokemon, SQLException sqlException) throws RdbmsSQLException
    {
        return findConstraint(sqlException).map(sqlConstraint -> new RdbmsPokemonConstraintException(pokemon, sqlConstraint, sqlException))
                                           .map(RdbmsException.class::cast)
                                           .orElseGet(() -> of(sqlException) );
    }

    private static Optional<SQLConstraint> findConstraint(SQLException sqlException)
    {
        return Stream.of( SQLConstraint.values() )
                     .filter( sqlConstraint -> sqlConstraint.test(sqlException) )
                     .findFirst();
    }



}
