/**
 * Triangle2D
 */
import java.awt.geom.Line2D;

public class Triangle2D {
    MyPoint p1;
    MyPoint p2;
    MyPoint p3;

    // a no arg constructor that creates a default
    public Triangle2D(double x1, double y1, double x2, double y2,
                      double x3, double y3) {
        this.p1 = new MyPoint(x1, y1);
        this.p2 = new MyPoint(x2, y2);
        this.p3 = new MyPoint(x3, y3);
    }

    /**
     * A constructor that creates a triangle with the specified points
     */

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     * A no-arg constructor that creates a default triangle with the points (0, 0), (1, 1), and (2, 5)
     */
     public Triangle2D() {
        this(new MyPoint(0, 0), new MyPoint(1, 1), new MyPoint(2, 5));
    }

    public MyPoint getP1() {
        return this.p1;
    }

    public MyPoint getP2() {
        return this.p2;
    }

    public MyPoint getP3() {
        return this.p3;
    }

    /**
     * returns the perimeter of the triangle.
     **/
    public double getPerimeter() {

        double s1 = p1.distance(p2);
        double s2 = p2.distance(p3);
        double s3 = p3.distance(p1);
        return s1 + s2 + s3;

    }

    /**
     * returns the area of the triangle
     **/
    public double getArea() {

        double s1 = p1.distance(p2);
        double s2 = p2.distance(p3);
        double s3 = p3.distance(p1);
        double s = (s1 + s2 + s3) / 2.0;

        return Math.pow(s * (s - s1) * (s - s2) * (s - s3), 0.5);
    }

    /**
     * returns true if the specified point is inside this triangle
     **/
    boolean contains(MyPoint p) {
        double tempA1 = new Triangle2D(p, p1, p2).getArea();
        double tempA2 = new Triangle2D(p, p2, p3).getArea();
        double tempA3 = new Triangle2D(p, p1, p3).getArea();
        double totalA = Math.round(tempA1 + tempA2 + tempA3);
        return Math.round(this.getArea()) == totalA;
    }

    /**
     * returns true if the specified triangle is inside this triangle.
     **/
    boolean contains(Triangle2D t) {
        boolean tempP1 = this.contains(t.getP1());
        boolean tempP2 = this.contains(t.getP2());
        boolean tempP3 = this.contains(t.getP3());
        return tempP1 & tempP2 & tempP3;
    }

    /**
     * returns true if the specified triangle overlaps with this triangle.
     **/
    public boolean overlaps(Triangle2D t) {

        MyPoint[] pt1 = getTrianglePoints();
        MyPoint[] pt2 = t.getTrianglePoints();

        // check first triangle
        for (int i = 0; i < 3; i++) {
            int maxI = (i + 1) % 3; // reach max indexes
            // check another triangle
            for (int j = 0; j < 3; j++) {
                int maxJ = (j + 1) % 3;  // when its 3 remainder becomes 0
                Line2D line1 = new Line2D.Double(pt2[i].x, pt2[i].y, pt2[maxI].x, pt2[maxI].y);
                Line2D line2 = new Line2D.Double(pt1[i].x, pt1[i].y, pt1[maxJ].x, pt1[maxJ].y);
                // Tests if the specified line segment intersects this line segment.
                if (line1.intersectsLine(line2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private MyPoint[] getTrianglePoints() {

        MyPoint[] points = new MyPoint[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        return points;
    }
}


