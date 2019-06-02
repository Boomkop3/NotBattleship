package com.unplayable.Ship;

import java.awt.geom.Point2D;

public enum Rotation {
    North	(0, -1, 0),
    East	(1, 0, Math.PI/2),
    South	(0, 1, Math.PI),
    West	(-1, 0, Math.PI*1.5);

    public final Point2D offset;
    private final double theta;

    Rotation (int horizontalOffset, int verticalOffset, double theta){
    	this.offset = new Point2D.Double(horizontalOffset, verticalOffset);
    	this.theta = theta;
	}

	public Rotation rotateLeft(){
        return rotateRight().rotateRight().rotateRight();
    }

    public double getTheta(){
        return this.theta;
    }

    public Rotation rotateRight() {
        switch (this) {
            case North: {
                return East;
            }
            case East: {
                return South;
            }
            case South: {
                return West;
            }
            case West: {
                return North;
            }
        }
        return null;
    }
}
