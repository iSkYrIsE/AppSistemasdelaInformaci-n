package es.upsa.ssi.mprest.frontend.model.beans.json;

import es.upsa.ssi.mprest.frontend.model.beans.DataError;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.adapter.JsonbAdapter;

public class DataErrorJsonAdapter implements JsonbAdapter<DataError, JsonObject> {
    
    @Override
    public JsonObject adaptToJson(DataError dataError) throws Exception {
        JsonObjectBuilder job = Json.createObjectBuilder();
        if (dataError.getField() != null) {
            job.add("field", dataError.getField());
        }
        if (dataError.getMessage() != null) {
            job.add("message", dataError.getMessage());
        }
        if (dataError.getValue() != null) {
            job.add("value", dataError.getValue());
        }
        return job.build();
    }

    @Override
    public DataError adaptFromJson(JsonObject jo) throws Exception {
        DataError dataError = new DataError();
        if (jo.containsKey("field")) {
            dataError.setField(jo.getString("field"));
        }
        if (jo.containsKey("value")) {
            dataError.setValue(jo.getString("value"));
        }
        if (jo.containsKey("message")) {
            dataError.setMessage(jo.getString("message"));
        }
        return dataError;
    }
}
