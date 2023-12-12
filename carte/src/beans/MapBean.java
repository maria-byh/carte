package beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.Painter;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class MapBean implements Serializable{
    private JXMapViewer mapViewer;
    private PropertyChangeSupport propertyChangeSupport;

    public MapBean() {
        mapViewer = new JXMapViewer();

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(36.7520605,3.4701802);
        mapViewer.setAddressLocation(geo);
        mapViewer.setZoom(2);
        
        MouseInputListener mm = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mm);
        mapViewer.addMouseMotionListener(mm);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
    }

    public JXMapViewer getMapViewer() {
        return mapViewer;
    }
    
    public static GeoPosition getGeoPositionForLocation(String location) {
        return geocodeLocation(location);
    }

    private static GeoPosition geocodeLocation(String location) {
        
        if (location.equalsIgnoreCase("Paris")) {
            return new GeoPosition(48.8566, 2.3522);  
        } else if (location.equalsIgnoreCase("Boudouaou")) {
            return new GeoPosition(36.7245452,3.4122024);
        } else if (location.equalsIgnoreCase("inim")) {
            return new GeoPosition(36.7520605,3.4701802);
        }else {
            return new GeoPosition(0, 0); 
        }
    }
    //////////////////////////////////////////
    /*
    public void setPosition(String place) {
        GeoPosition newPosition = getGeoPositionForPlace(place);
        mapViewer.setAddressLocation(newPosition);
    }

    private GeoPosition getGeoPositionForPlace(String place) {
        String apiKey =
        String apiUrl = String.format(Locale.US, "https://nominatim.openstreetmap.org/search?format=json&q=%s&limit=1", place);

        try {
            String response = HttpUtils.getResponse(new URL(apiUrl));
            double latitude = // parse latitude from JSON ;
            double longitude = // parse longitude from JSON ;
            return new GeoPosition(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    */
    public void setLayout(GroupLayout jXMapViewerLayout) {
        
        mapViewer.setLayout(jXMapViewerLayout);
    }
}