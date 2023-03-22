public class Rectangle extends Shape {

  public Rectangle(int x, int y) {
    super(x, y);
  }

  @Override
  public double area() {
    return x * y;
  }

  @Override
  public double perimeter() {
    return 2 * (x + y);
  }

  public void printInfo() {
    System.out.println("Rectangle info printed.");
  }
}

// Shape shape = new Rectangle();
// shape.