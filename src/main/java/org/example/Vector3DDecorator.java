package org.example;

public class Vector3DDecorator implements IVector {

    private final IVector srcVector;
    private final double z;

    public Vector3DDecorator(IVector srcVector, double z) {
        this.srcVector = srcVector;
        this.z = z;
    }

    @Override
    public double[] getComponents() { // [cite: 131]
        double[] srcComps = this.srcVector.getComponents();
        double x = srcComps.length > 0 ? srcComps[0] : 0;
        double y = srcComps.length > 1 ? srcComps[1] : 0;
        return new double[]{x, y, this.z};
    }

    @Override
    public double abs() { // [cite: 129]
        double[] comps = getComponents();
        return Math.sqrt(comps[0] * comps[0] + comps[1] * comps[1] + comps[2] * comps[2]);
    }

    @Override
    public double cdot(IVector param) { // [cite: 130]
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

    public Vector3DDecorator cross(IVector param) {
        double[] thisComps = this.getComponents();
        double[] paramComps = param.getComponents();

        double x1 = thisComps[0];
        double y1 = thisComps[1];
        double z1 = thisComps[2];

        double x2 = paramComps.length > 0 ? paramComps[0] : 0;
        double y2 = paramComps.length > 1 ? paramComps[1] : 0;
        double z2 = paramComps.length > 2 ? paramComps[2] : 0;

        double x_res = y1 * z2 - z1 * y2;
        double y_res = z1 * x2 - x1 * z2;
        double z_res = x1 * y2 - y1 * x2;

        return new Vector3DDecorator(new Vector2D(x_res, y_res), z_res);
    }

    public IVector getSrcV() {
        return this.srcVector;
    }
}