package org.example;

/**
 * Rozwiązanie problemu biegunowego przez dziedziczenie
 */
public class PolarInheritance2D extends Vector2D {

    public PolarInheritance2D(double x, double y) {
        super(x, y);
    }

    /**
     * Oblicza kąt [cite: 99]
     */
    public double getAngle() { // [cite: 100, 101, 102]
        // Używamy publicznej metody getComponents() z klasy bazowej,
        // ponieważ pola x i y są prywatne [cite: 10, 11]
        double[] comps = getComponents();
        return Math.atan2(comps[1], comps[0]);
    }
}