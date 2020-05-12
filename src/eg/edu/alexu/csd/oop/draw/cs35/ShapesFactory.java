package eg.edu.alexu.csd.oop.draw.cs35;

public class ShapesFactory {

	public Shapes makeShape(String shape) {
		if (shape.equals("Rectangle")) {
			return new Rectangle();
		} else if (shape.equals("Square")) {
			return new Square();
		} else if (shape.equals("Triangle")) {
			return new Triangle();
		} else if (shape.equals("Ellipse")) {
			return new Ellipse();
		} else if (shape.equals("Circle")) {
			return new Circle();
		} else if (shape.equals("Line")) {
			return new Line();
		}

		return null;
	}

}
