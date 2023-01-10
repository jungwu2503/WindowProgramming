import java.awt.*;

class Star extends OnePointFigure {
	private static final long serialVersionUID = 1L;

	Star(Color color, float thickness, int x1, int y1) {
		super(color, thickness, x1, y1);
	}
	
	void drawStar(Graphics g, int ox, int oy, int radius) {
		int NDOT = 10;
		int[] x = new int[NDOT];
		int[] y = new int[NDOT];
		
		double theta = 0.0;
		double step = 2 * Math.PI / (NDOT/2);
		
		for (int i = 0; i < NDOT/2; i++) {
			x[2*i] = (int)(radius * Math.cos(theta - Math.PI / 2)) + ox;
			y[2*i] = (int)(radius * Math.sin(theta - Math.PI / 2)) + oy;
			
			theta += step;
		}
		
		radius = (int)(0.38*radius);
		for (int i = 0; i < NDOT/2; i++) {
			x[2*i+1] = (int)(radius * Math.cos(theta + Math.PI / 2 + Math.PI*2/5*3)) + ox;
			y[2*i+1] = (int)(radius * Math.sin(theta + Math.PI / 2 + Math.PI*2/5*3)) + oy;
			
			theta += step;
		}
		
		Polygon p = new Polygon(x,y,10);
		g.drawPolygon(p);
		
	}

	void draw(Graphics g) {

		setColorAndStroke(g);
		g.drawOval(x1 - 3, y1 - 3, 2, 2);
		g.fillOval(x1 - 3, y1 - 3, 3, 3);
		
		drawStar(g, x1, y1, 50);

	}

	Figure copy() {
		Point newStar = new Point(color, thickness, x1, y1);
		newStar.popup = popup;
		newStar.move(20, 10);
		return newStar;
	}
}