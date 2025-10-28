package org.example;

/**
 * Interfejs IVector
 */
public interface IVector {
    /**
     * Oblicza moduł wektora [cite: 6, 18]
     */
    double abs();

    /**
     * Oblicza iloczyn skalarny [cite: 7, 19]
     */
    double cdot(IVector param);

    /**
     * Zwraca wartości składowych wektora [cite: 8, 20]
     */
    double[] getComponents();
}