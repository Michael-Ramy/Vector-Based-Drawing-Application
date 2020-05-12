package eg.edu.alexu.csd.oop.draw.cs35;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Operation {
	private String name;
	private Shape s1, s2;

	public Operation(String name, Shape shape) {
		this.name = name;
		this.s1 = shape;

	}

	public Operation(String name, Shape oldShape, Shape newShape) {
		this.name = name;
		this.s1 = oldShape;
		this.s2 = newShape;

	}

	public String getName() {
		return name;
	}

	public Shape getS1() {
		return s1;
	}

	public Shape getS2() {
		return s2;
	}

}
