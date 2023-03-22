public class Triangle extends Shape {

  public Triangle(int x, int y) {
    super(x, y);
  }

  @Override
  public double area() {
    return (double) x * y / 2;
  }

  @Override
  public double perimeter() {
    var a = Math.sqrt(x * x + y * y);
    return x + y + a;
  }
}
