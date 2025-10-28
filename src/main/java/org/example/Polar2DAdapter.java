package org.example;

/**
 * Adapter implementujący IPolar2D oraz IVector [cite: 56, 77]
 */
public class Polar2DAdapter implements IPolar2D, IVector {

    // Referencja do obiektu adaptowanego (Vector2D) [cite: 57, 78, 114]
    private final Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector) {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle() { // [cite: 80]
        // Implementacja #TODO z diagramu [cite: 95, 96, 97]
        double[] components = this.srcVector.getComponents();
        double x = components[0];
        double y = components[1];
        // Użycie funkcji cyklometrycznej atan2
        return Math.atan2(y, x);
    }

    // Metody delegowane do obiektu srcVector [cite: 58]
    @Override
    public double abs() { // [cite: 79, 90]
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector param) { // [cite: 81, 91, 92]
        return this.srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() { // [cite: 82, 93, 94]
        return this.srcVector.getComponents();
    }
}