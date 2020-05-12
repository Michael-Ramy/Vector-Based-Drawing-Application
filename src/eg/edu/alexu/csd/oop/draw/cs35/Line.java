package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Graphics;
import java.awt.Point;

public class Line extends Shapes {
	
	public void draw(Graphics canvas) {
		Point pos2=new Point((int) Math.round(this.getProperties().get("x")),(int) Math.round(this.getProperties().get("y")));
		
		canvas.setColor(this.getColor());
		canvas.drawLine(this.position.x, this.position.y, pos2.x, pos2.y);
	}

}
