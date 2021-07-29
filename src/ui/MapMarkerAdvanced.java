package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;

public class MapMarkerAdvanced extends MapMarkerCircle {
    public static final double defaultMarkerSize = 5.0;
    public static final Color defaultColor = Color.red;

    public MapMarkerAdvanced(Layer layer, Coordinate coord, String title,
            Color color) {
        super(layer, title, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(Color.BLACK);
        setBackColor(color);
    }
}