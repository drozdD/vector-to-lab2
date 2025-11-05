package org.example;

public interface IVector {
    double abs();

    double cdot(IVector param);

    double[] getComponents();
}