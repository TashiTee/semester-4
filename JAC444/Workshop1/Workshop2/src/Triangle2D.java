/**
 * Triangle2D
 */
public class Triangle2D {
    MyPoint p1;
    MyPoint p2;
    MyPoint p3;

    public Triangle2D() {
        this.p1 = new MyPoint(0, 0);
        this.p1 = new MyPoint(1, 1);
        this.p1 = new MyPoint(2, 5);
    }

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p1 = p2;
        this.p1 = p3;
    }

    public MyPoint getP1() {
        return this.p1;
    }

    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }

    public MyPoint getP2() {
        return this.p2;
    }

    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }

    public MyPoint getP3() {
        return this.p3;
    }

    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }

    public double getArea() {

    }

    public double getPerimeter() {

    }

    public boolean contains(MyPoint p) {

    }

    public boolean contains(Triangle2D t) {

    }

    public boolean overlaps(Triangle2D t) {

    }
}