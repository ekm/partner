package br.com.emiyoshi.partner.util;

import br.com.emiyoshi.partner.domain.Point;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Coordinates Serializer
 *
 * @author yutzlejp
 */
public class PointSerializer extends JsonSerializer<Point> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(Point value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        jgen.writeNumber(value.getCoordinates()[0]);
        jgen.writeNumber(value.getCoordinates()[1]);

        jgen.writeEndArray();
    }
}
