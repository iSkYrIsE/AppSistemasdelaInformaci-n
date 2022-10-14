package es.upsa.ssi.mprest.backend.providers.context.adapters;

import es.upsa.ssi.mprest.backend.model.impl.RdbmsException;
import es.upsa.ssi.mprest.backend.providers.context.beans.DataError;

import javax.json.bind.adapter.JsonbAdapter;
import java.util.Collections;
import java.util.List;

public class RdbmsExceptionToDataErrorJsonAdapter<T extends RdbmsException> implements JsonbAdapter<T , List<DataError>>
{
    @Override
    public List<DataError> adaptToJson(T exception) throws Exception {
        return Collections.singletonList( new DataError(exception.getFieldKey(), exception.getMessageKey(), exception.getValue()) );
    }

    @Override
    public T adaptFromJson(List<DataError> obj) throws Exception {
        return null;
    }
}
