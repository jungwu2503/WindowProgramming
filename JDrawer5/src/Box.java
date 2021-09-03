import java.awt.*;

public class Box extends TwoPointFigure {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	Box() {
		super();
	}
	
	Box(int x, int y) {
		super(x,y);
	}
	
	void draw(Graphics g) {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int width = Math.abs(x1-x2);
		int height = Math.abs(y1-y2);
		
		g.drawRect(minX, minY, width, height);
	}
	
}
