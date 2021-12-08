package util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 27.05.2021
 * Constants for the applications. Can be imported as static to get direct access
 */
public class Constant {

    // Application window width(x)
    public static int WIDTH;

    // Application window height(y)
    public static int HEIGHT;

    // Cycle of how often the Timer should update the Application window
    public static final int DELAY = 8;

    // Current Mouse position
    public static int MOUSE_X = 0;
    public static int MOUSE_Y = 0;

    // Range of Pixel that will be generated
    public static final int MINPARTICLE = 100;
    public static final int MAXPARTICLE = 150;

    // Render settings map for better look and feel
    public static Map<Object, Object> RENDERINGSETTINGS = new HashMap<>();
    public static void renderSettings(boolean anti, boolean dither, boolean inter, boolean textAnti) {
        if (anti) {
            RENDERINGSETTINGS.put(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (dither) {
            RENDERINGSETTINGS.put(RenderingHints.KEY_DITHERING,  RenderingHints.VALUE_DITHER_DEFAULT);
        }

        if (inter) {
            RENDERINGSETTINGS.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
        }

        if (textAnti) {
            RENDERINGSETTINGS.put(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

}
