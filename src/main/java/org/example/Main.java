package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // 1. Utworzenie trzech przykładowych wektorów [cite: 162]
        // Używamy różnych klas zaimplementowanych na diagramie S4
        IVector v1 = new Vector2D(3, 4);
        IVector v2 = new Vector3DDecorator(new Vector2D(1, 2), 3);
        IVector v3 = new Vector3DInheritance(5, 12, -1);

        IVector[] vectors = {v1, v2, v3};
        String[] vectorNames = {"v1", "v2", "v3"};

        System.out.println("--- Zadanie 3: Współrzędne wektorów ---");

        // 2. Wyświetlenie współrzędnych kartezjańskich i biegunowych [cite: 163]
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

        // 3. Wyświetlenie iloczynów skalarnych i wektorowych [cite: 164]
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

    /**
     * Metoda pomocnicza do obliczania kąta biegunowego dla dowolnego IVector.
     * Używa adaptera, jeśli wektor jest 2D, lub adaptuje część 2D wektora 3D.
     */
    private static double getAngleForVector(IVector v) {
        IVector vector2DPart = null;

        if (v instanceof Vector3DDecorator) {
            // Pobieramy dekorowany wektor (część 2D) [cite: 160]
            vector2DPart = ((Vector3DDecorator) v).getSrcV();
        } else if (v instanceof Vector3DInheritance) {
            // Tworzymy nowy wektor 2D z części 2D [cite: 160]
            vector2DPart = ((Vector3DInheritance) v).getSrcV();
        } else if (v instanceof Vector2D) {
            // To jest już wektor 2D (lub pochodny jak 2DPolarInheritance)
            if (v instanceof PolarInheritance2D) {
                // Ta klasa ma własną metodę getAngle [cite: 99]
                return ((PolarInheritance2D) v).getAngle();
            }
            vector2DPart = v;
        }

        // Adaptujemy część 2D do IPolar2D, aby uzyskać kąt [cite: 56]
        if (vector2DPart instanceof Vector2D) {
            return new Polar2DAdapter((Vector2D) vector2DPart).getAngle();
        }

        // Fallback, jeśli logika zawiedzie (nie powinno się zdarzyć)
        return Double.NaN;
    }

    /**
     * Metoda pomocnicza do wykonania iloczynu wektorowego.
     * Jeśli v1 jest 2D, jest dekorowany do 3D (z=0)[cite: 166].
     */
    private static IVector crossProduct(IVector v1, IVector v2) {
        if (v1 instanceof Vector3DDecorator) {
            // v1 jest już dekoratorem 3D [cite: 132]
            return ((Vector3DDecorator) v1).cross(v2);
        }
        if (v1 instanceof Vector3DInheritance) {
            // v1 jest klasą 3D (dziedziczenie) [cite: 153]
            return ((Vector3DInheritance) v1).cross(v2);
        }

        // v1 jest wektorem 2D, dekorujemy go do 3D (z=0) [cite: 166]
        Vector3DDecorator v1_3d = new Vector3DDecorator(v1, 0);
        return v1_3d.cross(v2);
    }

    /**
     * Metoda pomocnicza do formatowania tablicy double[] na String.
     */
    private static String arrayToString(double[] arr) {
        return Arrays.toString(arr);
    }
}