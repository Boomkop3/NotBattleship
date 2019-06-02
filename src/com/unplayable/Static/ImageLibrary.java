package com.unplayable.Static;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLibrary {
    public final BufferedImage SHIP_PIECE_DESTROYED_OVERLAY;
    private static ImageLibrary instance;
    private ImageLibrary() throws Exception {
        this.SHIP_PIECE_DESTROYED_OVERLAY = ImageIO.read(getClass().getResource("/Sprites/exploded.png"));
    }

    public static ImageLibrary getInstance() {
        if (instance == null){
			try {
				instance = new ImageLibrary();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return instance;
    }
}
