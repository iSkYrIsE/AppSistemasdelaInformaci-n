package es.upsa.ssi.mprest.backend.model;

import es.upsa.ssi.mprest.backend.exceptions.AppException;
import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import es.upsa.ssi.mprest.backend.model.impl.RdbmsExceptionBuilder;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    public List<Pokemon> select() throws AppException;
    public Optional<Pokemon> selectByNumero(String numero) throws AppException;
    public Pokemon insert(Pokemon pokemon) throws AppException;
    public Pokemon updateByNumero(String numero, Pokemon pokemon) throws AppException;
    public boolean deleteByNumero(String numero) throws AppException;

    public default Pokemon select(final String numero) throws AppException
    {
        Optional<Pokemon> optPokemon = selectByNumero(numero);
        if ( optPokemon.isPresent() ) return optPokemon.get();
        throw RdbmsExceptionBuilder.pokemonNotFound(numero);       
    }

    public default void delete(String numero) throws AppException
    {
        if (deleteByNumero(numero) == false) throw RdbmsExceptionBuilder.pokemonNotFound(numero);
    }

}
