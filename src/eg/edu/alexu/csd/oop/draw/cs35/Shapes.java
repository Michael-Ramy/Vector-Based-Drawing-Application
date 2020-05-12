package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Shapes implements eg.edu.alexu.csd.oop.draw.Shape {
	Point position;
	Map<String, Double> properties = new HashMap<>();
	Color color;
	Color fillColor;

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		this.position = position;

	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		this.fillColor = color;
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return fillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub

	}

	public Object clone() throws CloneNotSupportedException {
		return null;
	}
}
