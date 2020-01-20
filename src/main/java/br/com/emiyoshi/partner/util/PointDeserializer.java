package br.com.emiyoshi.partner.util;

import br.com.emiyoshi.partner.domain.Point;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointDeserializer extends JsonDeserializer<Point> {

    @Override
    public Point deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        if (jp.isExpectedStartArrayToken()) {
            return deserializeArray(jp, ctxt);
        }
        return null;
    }

    protected Point deserializeArray(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        Double x = extractDouble(jp, ctxt, false);
        Double y = extractDouble(jp, ctxt, false);

        List<Double> additionalElements = new ArrayList<>();
        while (jp.hasCurrentToken() && jp.getCurrentToken() != JsonToken.END_ARRAY) {
            Double element = extractDouble(jp, ctxt, true);
            if (!(element == null)) {
                additionalElements.add(element);
            }
        }

        Double[] aeArray = new Double[additionalElements.size()];

        return new Point(x, y);
    }

    private Double extractDouble(JsonParser jp, DeserializationContext ctxt, boolean optional)
            throws JsonParseException, IOException {
        JsonToken token = jp.nextToken();
        if (token == null) {
            if (!optional) {
                ctxt.reportWrongTokenException(this, JsonToken.VALUE_NUMBER_FLOAT, "token invalido");
            }
            return null;
        } else {
            switch (token) {
                case END_ARRAY:
                    if (!optional) {
                        ctxt.reportWrongTokenException(this, JsonToken.VALUE_NUMBER_FLOAT, "Token invalido");
                    }
                    return null;
                case VALUE_NUMBER_FLOAT:
                    return jp.getDoubleValue();
                case VALUE_NUMBER_INT:
                    return Double.valueOf(jp.getLongValue());
                case VALUE_STRING:
                    return jp.getValueAsDouble();
                default:
                    ctxt.reportWrongTokenException(
                            this, JsonToken.VALUE_NUMBER_FLOAT, "Tokeon invalido", token.name());
                    return null;
            }
        }
    }
}
