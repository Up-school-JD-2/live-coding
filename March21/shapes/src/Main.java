public class Main {

  public static void main(String[] args) {
    Shape shape = new Shape(1, 2);
    var area = shape.area();
    var perimeter = shape.perimeter();
    // System.out.println("Area: " + area + " perimeter : " + perimeter);

    Shape shapeRectangle = new Rectangle(2, 2);
    Shape shapeCircle = new Circle(3, 3);

    double perimeter1 = shapeRectangle.perimeter();
    double perimeter2 = shapeCircle.perimeter();

    //System.out.println("Perimeter rectangle: " + perimeter1 + " Perimeter circle : " + perimeter2);

    Rectangle rectangle = new Rectangle(5, 6);

    int sayi =5;
    sayi =150;
    shapeRectangle = new Circle(5, 6); // run time polymorphism
    printPerimeter(rectangle);
  }

  public static void printPerimeter(Shape shape) { // shape / miras alan herkes
    double perimeter = shape.perimeter();
    System.out.println("Shape perimeter:  " + perimeter);
  }
}