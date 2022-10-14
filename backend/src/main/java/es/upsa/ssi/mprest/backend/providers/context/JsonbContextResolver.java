package es.upsa.ssi.mprest.backend.providers.context;

import es.upsa.ssi.mprest.backend.model.impl.RdbmsException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsPokemonConstraintException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsNotFoundException;
import es.upsa.ssi.mprest.backend.model.impl.exceptions.RdbmsSQLException;
import es.upsa.ssi.mprest.backend.providers.context.adapters.RdbmsExceptionToDataErrorJsonAdapter;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonbContextResolver implements ContextResolver<Jsonb>
{
    private final JsonbConfig rdbmsExceptionJsonbConfig;

    public JsonbContextResolver()
    {
        rdbmsExceptionJsonbConfig = new JsonbConfig().withAdapters(new RdbmsExceptionToDataErrorJsonAdapter<RdbmsPokemonConstraintException>(){} )
                                                     .withAdapters( new RdbmsExceptionToDataErrorJsonAdapter<RdbmsNotFoundException>(){} )
                                                     .withAdapters( new RdbmsExceptionToDataErrorJsonAdapter<RdbmsSQLException>(){} );
    }

    @Override
    public Jsonb getContext(Class<?> type)
    {
        if (RdbmsException.class.isAssignableFrom( type ))
        {
            return JsonbBuilder.newBuilder()
                               .withConfig(rdbmsExceptionJsonbConfig)
                               .build();
        }

        return JsonbBuilder.newBuilder()
                           .build();
    }


}
