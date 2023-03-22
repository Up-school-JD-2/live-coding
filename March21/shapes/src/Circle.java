public class Circle extends Shape {

  public Circle(int x, int y) {
    super(x, y);
  }

  @Override
  public double area() {
    return Math.PI * x * x;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * x;
  }
}
