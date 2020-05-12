package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shapes {

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) canvas;
		Point pos2=new Point((int) Math.round(this.getProperties().get("x")),(int) Math.round(this.getProperties().get("y")));
		int h = (int) Math.abs(this.position.y - pos2.y);
		int w = (int) Math.abs(this.position.x - pos2.x);
		this.position = new Point(Math.min(this.position.x, pos2.x), Math.min(this.position.y, pos2.y));
		g2.setColor(this.getFillColor());
		g2.fill((new Ellipse2D.Double(this.position.x, this.position.y, w, h)) );
		g2.setColor(this.getColor());
		g2.draw((new Ellipse2D.Double(this.position.x, this.position.y, w, h)) );
	}
}
