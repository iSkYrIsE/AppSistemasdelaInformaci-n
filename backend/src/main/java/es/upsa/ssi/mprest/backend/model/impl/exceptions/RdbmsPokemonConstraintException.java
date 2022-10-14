package es.upsa.ssi.mprest.backend.model.impl.exceptions;

import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import es.upsa.ssi.mprest.backend.model.impl.SQLConstraint;

import java.sql.SQLException;

public class RdbmsPokemonConstraintException extends RdbmsConstraintException
{
    private Pokemon pokemon;

    public RdbmsPokemonConstraintException(Pokemon pokemon, SQLConstraint constraint, SQLException sqlException)
    {
        super(constraint, sqlException);
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    @Override
    public String getValue()
    {
        switch ( getConstraint() )
        {
            case DUPLICATE_NUMERO:          return pokemon.getNumeroPoke();
            case DUPLICATE_NOMBRE:          return pokemon.getNombrePoke();
            default:                    return null;
        }
    }
}
