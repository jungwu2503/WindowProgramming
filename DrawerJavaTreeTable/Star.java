import java.awt.*;

class Star extends OnePointFigure {
	private static final long serialVersionUID = 1L;

	Star(Color color, int x1, int y1) {
		super(color, x1, y1);
	}

	void draw(Graphics g) {
		int XMAX = _x1;
		int YMAX = _y1;
		int XORG = XMAX / 2;
		int YORG = YMAX / 2;
		int RADIUS = 50;
		int NDOTS = 5;

		g.setColor(_color);
		g.drawOval(_x1 - 3, _y1 - 3, 5, 5);
		g.fillOval(_x1 - 3, _y1 - 3, 6, 6);

		int i;
		double step = 2 * Math.PI / NDOTS;
		double theta = 0.0;

		int oldX = (int) (RADIUS * Math.cos(theta - Math.PI / 2)) + _x1;
		int oldY = (int) (RADIUS * Math.sin(theta - Math.PI / 2)) + _y1;

		for (i = 0; i < NDOTS + 1; i++) {
			int x, y;

			x = (int) (RADIUS * Math.cos(theta - Math.PI / 2)) + _x1;
			y = (int) (RADIUS * Math.sin(theta - Math.PI / 2)) + _y1;

			g.drawLine(oldX, oldY, x, y);
			oldX = x;
			oldY = y;

			theta = theta + step;
		}

		RADIUS = (int) (0.38 * RADIUS);

		oldX = (int) (RADIUS * Math.cos(theta - Math.PI / 2 - Math.PI)) + _x1;
		oldY = (int) (RADIUS * Math.sin(theta - Math.PI / 2 - Math.PI)) + _y1;

		for (i = 0; i < NDOTS + 1; i++) {
			int x, y;

			x = (int) (RADIUS * Math.cos(theta - Math.PI / 2 - Math.PI)) + _x1;
			y = (int) (RADIUS * Math.sin(theta - Math.PI / 2 - Math.PI)) + _y1;

			g.drawLine(oldX, oldY, x, y);
			oldX = x;
			oldY = y;

			theta = theta + step;
		}

	}

	Figure copy() {
		Point newStar = new Point(_color, _x1, _y1);
		newStar._popup = _popup;
		newStar.move(20, 10);
		return newStar;
	}
}
