import java.awt.*;

public class Box {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	Box() {
		
	}
	
	Box(int x, int y) {
		x1 = x2 = x;
		y1 = y2 = y;
	}
	
	void draw(Graphics g) {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int width = Math.abs(x1-x2);
		int height = Math.abs(y1-y2);
		
		g.drawRect(minX, minY, width, height);
	}
	
	void drawing(Graphics g, int newX, int newY) {
		draw(g);
		setXY2(newX,newY);
		draw(g);
	}
	
	void setXY2(int x, int y) {
		setX2(x);
		setY2(y);
	}
	
	int getX1() {
		return x1;
	}
	
	int getY1() {
		return y1;
	}
	
	int getX2() {
		return x2;
	}
	
	int getY2() {
		return y2;
	}

	void setX1(int x) {
		x1 = x;
	}
	
	void setY1(int y) {
		y1 = y;
	}
	
	void setX2(int x) {
		x2 = x;
	}

	void setY2(int y) {
		y2 = y;
	}
}
