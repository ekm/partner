package br.com.emiyoshi.partner.domain;

import br.com.emiyoshi.partner.util.PointDeserializer;
import br.com.emiyoshi.partner.util.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "point")
@JsonDeserialize(using = PointDeserializer.class)
@JsonSerialize(using = PointSerializer.class)
public class Point implements Serializable {

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D)
    private double[] coordinates;

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Point(double x, double y) {
        coordinates = new double[]{x, y};
    }

    public Point() {
    }
}
