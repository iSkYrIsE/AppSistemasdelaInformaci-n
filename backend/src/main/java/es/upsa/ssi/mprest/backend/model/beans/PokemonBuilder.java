package es.upsa.ssi.mprest.backend.model.beans;


public class PokemonBuilder {
    public static PokemonBuilder newBuilder()
    {
        return new PokemonBuilder();
    }

    public static PokemonBuilder newBuilder(Pokemon pokemon)
    {
        return new PokemonBuilder(pokemon.getNumeroPoke(), pokemon.getNombrePoke(), pokemon.getTipo1(), pokemon.getTipo2(), pokemon.getDescripcion());
    }

    private String numeroPoke;
    private String nombrePoke;
    private String tipo1;
    private String tipo2;
    private String descripcion;

    private PokemonBuilder() {
    }

    private PokemonBuilder(String numeroPoke, String nombrePoke, String tipo1, String tipo2, String descripcion) {
        this.numeroPoke = numeroPoke;
        this.nombrePoke = nombrePoke;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.descripcion = descripcion;
    }



    public PokemonBuilder withNumeroPoke(String numeroPoke) {
        this.numeroPoke = numeroPoke;
        return this;
    }

    public PokemonBuilder withNombrePoke(String nombrePoke) {
        this.nombrePoke = nombrePoke;
        return this;
    }

    public PokemonBuilder withTipo1(String tipo1) {
        this.tipo1 = tipo1;
        return this;
    }

    public PokemonBuilder withTipo2(String tipo2) {
        this.tipo2 = tipo2;
        return this;
    }

    public PokemonBuilder withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Pokemon build()
    {
        return new Pokemon(numeroPoke, nombrePoke, tipo1, tipo2, descripcion);
    }

}
