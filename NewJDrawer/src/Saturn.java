import java.awt.*;

public class Saturn extends OnePointFigure {

	private boolean fillFlag;
	
	Saturn(Color color, float thickness) {
		super(color, 0.1f);
		fillFlag = false;
	}
	
	Saturn(Color color, float thickness, int x, int y) {	
		super(color,0.1f,x,y);
		fillFlag = false;
	}
	
/*	Saturn(Color color, int x1, int y1, int x2, int y2) {	
		super(color,x1,y1,x2,y2);
		fillFlag = false;
	} */
	
	boolean getFillFlag() {
		return fillFlag;
	}
	
	void setFillFlag(boolean flag) {
		fillFlag = flag;
	}
	
	void setFill() {
		fillFlag = !fillFlag;		
	}
	
	void draw(Graphics g) {
		int minX = x1;
		int minY = y1;
		int width = 200;
		int height = 200;
		
		g.drawOval(x1 - 3, y1 - 3, 2, 2);
		g.fillOval(x1 - 3, y1 - 3, 3, 3);
		g.setColor(color);
		((Graphics2D)g).setStroke(new BasicStroke(0.1f));
		g.drawOval(minX, minY, width, height);
		g.drawArc(minX-70, minY+45, width*3/2+70, height-100, 124, 303);
		
		if (fillFlag == true) {
			g.fillOval(minX, minY, width, height);
			g.setColor(Color.black);
			g.drawArc(minX-70, minY+45, width*3/2+70, height-100, 124, 303);
		}
	}	
	
	Figure copy() {
		Saturn newSaturn = new Saturn(color,thickness,x1,y1);
		newSaturn.popup = popup;
		newSaturn.fillFlag = fillFlag;
		newSaturn.move(MOVE_DX, MOVE_DY);
		return newSaturn;
	}
	
}
