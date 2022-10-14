package es.upsa.ssi.mprest.backend.model.beans;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

public class Pokemon {

    @NotNull(message = "{field.numeroPoke}: {javax.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.numeroPoke}: {javax.validation.constraints.NotBlank.message}")
    @Size(min = 1, max = 4, message = "{field.numeroPoke}: {javax.validation.constraints.Size.message}")
    @JsonbProperty("numeroPoke")
    @FormParam("numeroPoke")
    private String numeroPoke;

    @NotNull(message = "{field.nombrePoke}: {javax.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.nombrePoke}: {javax.validation.constraints.NotBlank.message}")
    @Size(min = 3, max = 11, message = "{field.nombrePoke}: {javax.validation.constraints.Size.message}")
    @JsonbProperty("nombrePoke")
    @FormParam("nombrePoke")
    private String nombrePoke;

    @NotNull(message = "{field.tipo1}: {javax.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.tipo1}: {javax.validation.constraints.NotBlank.message}")
    @Size(min = 4, max = 10, message = "{field.tipo1}: {javax.validation.constraints.Size.message}")
    @JsonbProperty("tipo1")
    @FormParam("tipo1")
    private String tipo1;

    @Size(min = 4, max = 10, message = "{field.tipo2}: {javax.validation.constraints.Size.message}")
    @JsonbProperty(value = "tipo2", nillable = true)
    @FormParam("tipo2")
    private String tipo2;

    @NotNull(message = "{field.descripcion}: {javax.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.descripcion}: {javax.validation.constraints.NotBlank.message}")
    @Size(min = 2, max = 256, message = "{field.descripcion}: {javax.validation.constraints.Size.message}")
    @JsonbProperty("descripcion")
    @FormParam("descripcion")
    private String descripcion;

    public Pokemon() {
    }

    @JsonbCreator
    public Pokemon(@JsonbProperty("numeroPoke") String numeroPoke,
            @JsonbProperty("nombrePoke") String nombrePoke,
            @JsonbProperty("tipo1") String tipo1,
            @JsonbProperty("tipo2") String tipo2,
            @JsonbProperty("descripcion") String descripcion) {
        this.numeroPoke = numeroPoke;
        this.nombrePoke = nombrePoke;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.descripcion = descripcion;
    }

    public String getNumeroPoke() {
        return numeroPoke;
    }

    public String getNombrePoke() {
        return nombrePoke;
    }

    public String getTipo1() {
        return tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
