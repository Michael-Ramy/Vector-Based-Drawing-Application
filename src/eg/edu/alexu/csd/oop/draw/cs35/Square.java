package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends Rectangle {

	public void draw(Graphics canvas) {
		canvas.setColor(this.getColor());
		Point pos2 = new Point((int) Math.round(this.getProperties().get("x")),
				(int) Math.round(this.getProperties().get("y")));
		int w = (int) Math.abs(this.position.x - pos2.x);
		this.position = new Point(Math.min(this.position.x, pos2.x), Math.min(this.position.y, pos2.y));
		canvas.setColor(this.getFillColor());
		canvas.fillRect(this.position.x, this.position.y, w, w);
		canvas.setColor(this.getColor());
		canvas.drawRect(this.position.x, this.position.y, w, w);
	}

	public Object clone() throws CloneNotSupportedException {
		Shape shape = new Rectangle();
		shape.setPosition(new Point(this.position.x + 10, this.position.y + 10));
		Map map = new HashMap<>();
		map.put("x", this.properties.get("x") + 10);
		map.put("y", this.properties.get("y") + 10);
		shape.setProperties(new HashMap<>(map));
		shape.setColor(this.getColor());
		shape.setFillColor(this.fillColor);
		return shape;
	}
}