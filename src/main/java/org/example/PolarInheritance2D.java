package org.example;

public class PolarInheritance2D extends Vector2D {

    public PolarInheritance2D(double x, double y) {
        super(x, y);
    }

    public double getAngle() {
        double[] comps = getComponents();
        return Math.atan2(comps[1], comps[0]);
    }
}