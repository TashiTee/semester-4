
import java.lang.Math;

public class MyPoint {
    double x;
    double y;

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between this instance of MyPoint and the
     * provided coordinates
     */

    public double distance(MyPoint point) {
        double x_distance = this.getX() - point.getX();
        double y_distance = this.getY() - point.getY();
        double arg1 = Math.pow(x_distance, 2);
        double arg2 = Math.pow(y_distance, 2);

        return Math.sqrt(arg1 + arg2);
    }
}