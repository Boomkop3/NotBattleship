package com.unplayable.Ship;

import java.awt.geom.Point2D;

public enum Rotation {
    North	(0, -1),
    East	(1, 0),
    South	(0, 1),
    West	(-1, 0);

    public final Point2D offset;
    Rotation (int horizontalOffset, int verticalOffset){
    	this.offset = new Point2D.Double(horizontalOffset, verticalOffset);
	}
}