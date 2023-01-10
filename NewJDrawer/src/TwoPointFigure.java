import java.awt.*;

abstract public class TwoPointFigure extends Figure{

	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	
	TwoPointFigure(Color color, float thickness) {
		super(color, thickness);
	}

	TwoPointFigure(Color color, float thickness, int x, int y) {
		super(color, thickness);
		x1 = x2 = x;
		y1 = y2 = y;
	}
	
	TwoPointFigure(Color color, float thickness, int x1, int y1, int x2, int y2) {
		super(color, thickness);
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	void move(int dx, int dy) {
		x1 = x1 + dx; y1 = y1 + dy;
		x2 = x2 + dx; y2 = y2 + dy;
	}
	
	void makeRegion() {
		if (x1 > x2) {
			int tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if (y1 > y2) {
			int tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		
		int[] xpoints = new int[4];
		int[] ypoints = new int[4];
		
		xpoints[0] = x1; ypoints[0] = y1;
		xpoints[1] = x2; ypoints[1] = y1;
		xpoints[2] = x2; ypoints[2] = y2;
		xpoints[3] = x1; ypoints[3] = y2;
		
		region = new Polygon(xpoints, ypoints, 4);
	}
	
	void setXY2(int x, int y) {
		setX2(x); setY2(y);
	}
	
	void setXY12(int x1, int y1, int x2, int y2) {
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
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
