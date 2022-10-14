/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.mprest.frontend.model.beans;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.FormParam;

public class Pokemon {

    @JsonbProperty("numeroPoke")
    @FormParam("numeroPoke")
    private String numeroPoke;

    @JsonbProperty("nombrePoke")
    @FormParam("nombrePoke")
    private String nombrePoke;

    @JsonbProperty("tipo1")
    @FormParam("tipo1")
    private String tipo1;

    @JsonbProperty("tipo2")
    @FormParam("tipo2")
    private String tipo2;

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
