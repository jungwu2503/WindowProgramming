import java.awt.Graphics;

public class Line extends TwoPointFigure {
	
	Line() {
		super();
	}
	
	Line(int x, int y) {
		super(x,y);
	}
	
	void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}
	
}