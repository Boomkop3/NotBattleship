package com.unplayable.Static;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLibrary {
    private final BufferedImage SHIP_PIECE_DESTROYED_OVERLAY;
    private ImageLibrary instance;
    private ImageLibrary(){
        // ToDo insert image from resources
        this.SHIP_PIECE_DESTROYED_OVERLAY = ImageIO.read();
    }

    public ImageLibrary getInstance(){
        if (this.instance == null){
            this.instance = new ImageLibrary();
        }
        return this.instance;
    }
}
