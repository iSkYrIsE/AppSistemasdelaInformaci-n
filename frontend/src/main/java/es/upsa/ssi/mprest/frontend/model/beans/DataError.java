/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.mprest.frontend.model.beans;


import javax.json.bind.annotation.JsonbProperty;

//@JsonbTypeAdapter(DataErrorJsonAdapter.class)
public class DataError 
{
    @JsonbProperty(value = "field", nillable = true)
    private String field;
    @JsonbProperty(value = "value")
    private String value;
    @JsonbProperty(value = "message")
    private String message;


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
