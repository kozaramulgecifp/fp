package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;

public class MapMarkerAdvanced extends MapMarkerCircle {
    public static final double defaultMarkerSize = 15.0;
    private String tweet = "";
    private String image = "";

    public MapMarkerAdvanced(Layer layer, Coordinate coord, String title, String tweet, String image,
            Color color) {
        super(layer, title, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(Color.BLACK);
        setBackColor(color);
        this.image = image;
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public String getImage() {
        return image;
    }
}