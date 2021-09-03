import java.awt.Graphics;

abstract class TwoPointFigure {
	
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;

	public TwoPointFigure() {
		
	}
	
	public TwoPointFigure(int x, int y) {
		x1 = x2 = x;
		y1 = y2 = y;
	}
	
	abstract void draw(Graphics g);
	
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
