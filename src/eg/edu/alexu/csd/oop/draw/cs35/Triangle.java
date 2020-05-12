package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shapes {

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		int x1 = (int) getPosition().getX();
		int y1 = (int) getPosition().getY();
		int x2 = (getProperties().get("x")).intValue();
		int y2 = (getProperties().get("y")).intValue();

		int[] xs = { x1, x2, (x1 + x2) / 2 };
		int[] ys = { y1, y1, y2 };
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(getFillColor());
		g.fillPolygon(xs, ys, 3);
		g.setColor(getColor());
		g.drawPolygon(xs, ys, 3);
	}

}
