package es.upsa.ssi.mprest.backend.model.impl;

import es.upsa.ssi.mprest.backend.exceptions.AppException;
import es.upsa.ssi.mprest.backend.model.Dao;
import es.upsa.ssi.mprest.backend.model.beans.Pokemon;
import es.upsa.ssi.mprest.backend.model.beans.PokemonBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;



@DataSourceDefinition(name = "jdbc/oracleDockerPayara",
                      className = "oracle.jdbc.pool.OracleDataSource",
                      url = "jdbc:oracle:thin:@localhost:1521:xe",
                      user = "system",
                      password = "oracle",
                      minPoolSize = 1,
                      maxPoolSize = 3
                     )

@ApplicationScoped
public class RdbmsDao implements Dao {

    @Resource(name = "jdbc/oracleDockerPayara")
    private DataSource dataSource;

    @Override
    public List<Pokemon> select() throws AppException {
        List<Pokemon> pokemones = new ArrayList<>();

        final String SQL_SELECT = "SELECT P.NUMEROPOKE, P.NOMBREPOKE, P.TIPO1, P.TIPO2, P.DESCRIPCION "
                + " FROM POKEMON P "
                + " ORDER BY P.NUMEROPOKE";

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            if (resultSet.next()) {
                do {
                    pokemones.add(PokemonBuilder.newBuilder()
                            .withNumeroPoke(resultSet.getString(1))
                            .withNombrePoke(resultSet.getString(2))
                            .withTipo1(resultSet.getString(3))
                            .withTipo2(resultSet.getString(4))
                            .withDescripcion(resultSet.getString(5))
                            .build()
                    );

                } while (resultSet.next());
            }

        } catch (SQLException sqlException) {
            throw RdbmsExceptionBuilder.of(sqlException);
        }

        return pokemones;

    }

    @Override
    public Optional<Pokemon> selectByNumero(String numero) throws AppException {
        final String SQL_SELECT = "SELECT P.NUMEROPOKE, P.NOMBREPOKE, P.TIPO1, P.TIPO2, P.DESCRIPCION "
                + " FROM POKEMON P "
                + " WHERE P.NUMEROPOKE = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);) {
            preparedStatement.setString(1, numero);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(PokemonBuilder.newBuilder()
                            .withNumeroPoke(resultSet.getString(1))
                            .withNombrePoke(resultSet.getString(2))
                            .withTipo1(resultSet.getString(3))
                            .withTipo2(resultSet.getString(4))
                            .withDescripcion(resultSet.getString(5))
                            .build()
                    );
                }
                return Optional.empty();
            }
        } catch (SQLException sqlException) {
            throw RdbmsExceptionBuilder.of(sqlException);
        }

    }



    @Override
    public Pokemon insert(Pokemon pokemon) throws AppException {
        final String SQL_INSERT = "INSERT INTO POKEMON(NUMEROPOKE, NOMBREPOKE, TIPO1, TIPO2, DESCRIPCION) "
                + " VALUES(?, ?, ?, ?, ?) ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setString(1, pokemon.getNumeroPoke());
            ps.setString(2, pokemon.getNombrePoke());
            ps.setString(3, pokemon.getTipo1());
            ps.setString(4, pokemon.getTipo2());
            ps.setString(5, pokemon.getDescripcion());
            ps.executeUpdate();
            return PokemonBuilder.newBuilder(pokemon)
                    .build();

        } catch (SQLException sqlException) {
            throw RdbmsExceptionBuilder.of(pokemon, sqlException);
        }

    }

    @Override
    public Pokemon updateByNumero(String numero, Pokemon pokemon) throws AppException {
        final String SQL_UPDATE = "UPDATE POKEMON P "
                + " SET P.NOMBREPOKE = ?, "
                + " P.TIPO1 = ?, "
                + " P.TIPO2 = ?, "
                + " P.DESCRIPCION = ? "
                + " WHERE P.NUMEROPOKE = ? ";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, pokemon.getNombrePoke());
            ps.setString(2, pokemon.getTipo1());
            ps.setString(3, pokemon.getTipo2());
            ps.setString(4, pokemon.getDescripcion());
            ps.setString(5, numero);
            int count = ps.executeUpdate();
            if (count == 1) {
                return PokemonBuilder.newBuilder(pokemon)
                        .withNumeroPoke(numero)
                        .build();
            }

            throw RdbmsExceptionBuilder.pokemonNotFound(pokemon.getNumeroPoke());

        } catch (SQLException sqlException) {
            throw RdbmsExceptionBuilder.of(pokemon, sqlException);
        }

    }

    @Override
    public boolean deleteByNumero(String numero) throws AppException {
        final String SQL_DELETE = "DELETE POKEMON P "
                + " WHERE P.NUMEROPOKE = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setString(1, numero);
            int count = ps.executeUpdate();
            return (count == 1);

        } catch (SQLException sqlException) {
            throw RdbmsExceptionBuilder.of(sqlException);
        }
    }
}
