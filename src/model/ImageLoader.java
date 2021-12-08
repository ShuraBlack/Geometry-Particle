package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageLoader {

    private Image image;

    public ImageLoader(String filename) {
        try {
            image = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(image);
    }
}
