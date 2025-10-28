package org.example;

/**
 * Rozwiązanie problemu 3D przez dziedziczenie
 */
public class Vector3DInheritance extends Vector2D {

    // Dodatkowe pole z [cite: 149]
    private final double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public double[] getComponents() { // [cite: 152]
        // Pobieramy x, y z klasy bazowej
        double[] xy = super.getComponents();
        return new double[]{xy[0], xy[1], this.z};
    }

    @Override
    public double abs() { // [cite: 150]
        double[] comps = getComponents();
        return Math.sqrt(comps[0] * comps[0] + comps[1] * comps[1] + comps[2] * comps[2]);
    }

    @Override
    public double cdot(IVector param) { // [cite: 151]
        double[] thisComps = this.getComponents();
        double[] paramComps = param.getComponents();

        double x1 = thisComps[0];
        double y1 = thisComps[1];
        double z1 = thisComps[2];

        double x2 = paramComps.length > 0 ? paramComps[0] : 0;
        double y2 = paramComps.length > 1 ? paramComps[1] : 0;
        double z2 = paramComps.length > 2 ? paramComps[2] : 0;

        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    /**
     * Oblicza iloczyn wektorowy [cite: 153]
     */
    public Vector3DInheritance cross(IVector param) {
        double[] thisComps = this.getComponents();
        double[] paramComps = param.getComponents();

        double x1 = thisComps[0];
        double y1 = thisComps[1];
        double z1 = thisComps[2];

        // Uwzględnienie, że 'param' może być 2D (z=0) [cite: 166]
        double x2 = paramComps.length > 0 ? paramComps[0] : 0;
        double y2 = paramComps.length > 1 ? paramComps[1] : 0;
        double z2 = paramComps.length > 2 ? paramComps[2] : 0;

        // Metoda macierzowa [cite: 166]
        double x_res = y1 * z2 - z1 * y2;
        double y_res = z1 * x2 - x1 * z2;
        double z_res = x1 * y2 - y1 * x2;

        return new Vector3DInheritance(x_res, y_res, z_res);
    }

    /**
     * Zwraca nowy obiekt Vector2D [cite: 154, 160]
     */
    public IVector getSrcV() {
        double[] comps = super.getComponents(); // Pobiera x, y
        return new Vector2D(comps[0], comps[1]);
    }
}