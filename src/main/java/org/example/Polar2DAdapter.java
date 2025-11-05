package org.example;

public class Polar2DAdapter implements IPolar2D, IVector {

    private final Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector) {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle() { // [cite: 80]
        double[] components = this.srcVector.getComponents();
        double x = components[0];
        double y = components[1];
        return Math.atan2(y, x);
    }

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector param) {
        return this.srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }
}