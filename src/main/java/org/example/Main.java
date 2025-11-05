package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Używamy różnych klas zaimplementowanych na diagramie S4
        IVector v1 = new Vector2D(3, 4);
        IVector v2 = new Vector3DDecorator(new Vector2D(1, 2), 3);
        IVector v3 = new Vector3DInheritance(5, 12, -1);

        IVector[] vectors = {v1, v2, v3};
        String[] vectorNames = {"v1", "v2", "v3"};

        System.out.println("--- Zadanie 3: Współrzędne wektorów ---");

        for (int i = 0; i < vectors.length; i++) {
            IVector v = vectors[i];
            String name = vectorNames[i];

            // Współrzędne kartezjańskie
            System.out.printf("Wektor %s: KART.: %s\n",
                    name, arrayToString(v.getComponents()));

            // Współrzędne biegunowe (moduł i kąt)
            // Kąt liczymy dla składowej 2D wektora
            double abs = v.abs();
            double angle = getAngleForVector(v); // Użycie adaptera

            System.out.printf("Wektor %s: BIEG.: (r=%.4f, ang=%.4f rad)\n",
                    name, abs, angle);
        }

        System.out.println("\n--- Zadanie 4: Iloczyny skalarne i wektorowe ---");

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors.length; j++) {
                IVector v_i = vectors[i];
                IVector v_j = vectors[j];
                String name_i = vectorNames[i];
                String name_j = vectorNames[j];

                // Iloczyn skalarny
                double cdotResult = v_i.cdot(v_j);
                System.out.printf("Iloczyn SKALARNY %s \u2022 %s = %.4f\n",
                        name_i, name_j, cdotResult);

                // Iloczyn wektorowy
                // Musimy użyć metody cross(), która jest w klasach 3D
                IVector crossResult = crossProduct(v_i, v_j);
                System.out.printf("Iloczyn WEKTOROWY %s x %s = %s\n",
                        name_i, name_j, arrayToString(crossResult.getComponents()));
            }
            System.out.println(); // Odstęp
        }
    }

    private static double getAngleForVector(IVector v) {
        IVector vector2DPart = null;

        if (v instanceof Vector3DDecorator) {
            vector2DPart = ((Vector3DDecorator) v).getSrcV();
        } else if (v instanceof Vector3DInheritance) {
            vector2DPart = ((Vector3DInheritance) v).getSrcV();
        } else if (v instanceof Vector2D) {
            if (v instanceof PolarInheritance2D) {
                return ((PolarInheritance2D) v).getAngle();
            }
            vector2DPart = v;
        }

        if (vector2DPart instanceof Vector2D) {
            return new Polar2DAdapter((Vector2D) vector2DPart).getAngle();
        }

        return Double.NaN;
    }

    private static IVector crossProduct(IVector v1, IVector v2) {
        if (v1 instanceof Vector3DDecorator) {
            return ((Vector3DDecorator) v1).cross(v2);
        }
        if (v1 instanceof Vector3DInheritance) {
            return ((Vector3DInheritance) v1).cross(v2);
        }

        Vector3DDecorator v1_3d = new Vector3DDecorator(v1, 0);
        return v1_3d.cross(v2);
    }

    private static String arrayToString(double[] arr) {
        return Arrays.toString(arr);
    }
}