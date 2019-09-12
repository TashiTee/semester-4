
public class TestTriangle {

    public static void main(String[] args) {

        //  creates a Triangle2D object r1
        Triangle2D r1 = new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3), new MyPoint(5, 3.5));

        System.out.println("Area is " + r1.getArea());
        System.out.println("Perimeter is " + r1.getPerimeter());
        System.out.println("Point is inside triangle: " + r1.contains(new MyPoint(3,3)));
        System.out.println("Triangle inside this triangle: " + r1.contains(new Triangle2D(2.9, 2, 4, 1, 1, 3.4)));
        System.out.println("Triangle overlaps this triangle: " + r1.overlaps(new Triangle2D(2, 5.5, 4, -3, 2, 6.5)));

        // creates another Triangle2D object r2
        Triangle2D r2 = new Triangle2D(new MyPoint(0, 0), new MyPoint(0, 2), new MyPoint(2, 0));

        System.out.println("Area is " + r2.getArea());
        System.out.println("Perimeter is " + r2.getPerimeter());
        System.out.println("Point is inside triangle: " + r2.contains(new MyPoint(1,1)));
        System.out.println("Triangle inside this triangle: " + r2.contains(new Triangle2D(4, 5, 10.5, 3.2, -0.5, -10.5)));
        System.out.println("Triangle overlaps this triangle: " + r2.overlaps(new Triangle2D(1, 1.7, -1, 1.7, 0, -3)));
    }
}


