package org.firstinspires.ftc.teamcode.Utilities.Geometry;

public class fVector2D {
    public double x,y;

    public fVector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static fVector2D fromHeadingAndMagnitude(double h, double m){
        return new fVector2D(Math.cos(h) * m, Math.sin(h) * m);
    }

    public fVector2D mult(double scalar) {
        return new fVector2D(x * scalar, y * scalar);
    }

    public fVector2D divide(double scalar) {
        return new fVector2D(x / scalar, y / scalar);
    }

    public fVector2D subt(fVector2D other) {
        return new fVector2D(x - other.x, y - other.y);
    }

    public double dot(fVector2D other) {
        return x * other.x + y * other.y;
    }

    public double magnitude() {
        return Math.hypot(x, y);
    }

    public fVector2D unit() {
        return this.divide(magnitude());
    }

    public fVector2D rotate(double angle) {
        return new fVector2D(
                x * Math.cos(angle) - y * Math.sin(angle),
                x * Math.sin(angle) + y * Math.cos(angle));
    }

    public double cross(fVector2D other) {
        return x * other.y - y * other.x;
    }

    @Override
    public String toString() {
        return String.format("{%.2f, %.2f}", x, y);
    }
}
