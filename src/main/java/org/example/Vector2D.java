package org.example;

/**
 * Klasa Vector2D implementująca IVector [cite: 12]
 */
public class Vector2D implements IVector {

    // Prywatne pola dla współrzędnych [cite: 10, 11]
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs() { // [cite: 14, 18]
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public double cdot(IVector param) { // [cite: 15, 19]
        double[] paramComps = param.getComponents();

        // Współrzędne tego wektora (z=0) [cite: 166]
        double x1 = this.x;
        double y1 = this.y;
        double z1 = 0;

        // Współrzędne wektora 'param'
        double x2 = paramComps.length > 0 ? paramComps[0] : 0;
        double y2 = paramComps.length > 1 ? paramComps[1] : 0;
        double z2 = paramComps.length > 2 ? paramComps[2] : 0;

        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    @Override
    public double[] getComponents() { // [cite: 13, 20]
        return new double[]{this.x, this.y};
    }
}