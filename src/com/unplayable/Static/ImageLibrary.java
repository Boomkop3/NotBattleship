package com.unplayable.Static;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLibrary {
    public final BufferedImage SHIP_PIECE_DESTROYED_OVERLAY;
    private ImageLibrary() throws Exception {
    private static ImageLibrary instance;
        // ToDo insert image from resources
        this.SHIP_PIECE_DESTROYED_OVERLAY = ImageIO.read(getClass().getResource("/Sprites/exploded.png"));
    }

    public static ImageLibrary getInstance() throws Exception {
        if (instance == null){
            instance = new ImageLibrary();
        }
        return instance;
    }
}
