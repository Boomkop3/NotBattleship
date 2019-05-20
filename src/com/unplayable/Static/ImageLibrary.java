package com.unplayable.Static;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLibrary {
    private final BufferedImage SHIP_PIECE_DESTROYED_OVERLAY;
    private static ImageLibrary instance;
    private ImageLibrary(){
        // ToDo insert image from resources
        this.SHIP_PIECE_DESTROYED_OVERLAY = ImageIO.read();
    }

    public static ImageLibrary getInstance(){
        if (instance == null){
            instance = new ImageLibrary();
        }
        return instance;
    }
}
