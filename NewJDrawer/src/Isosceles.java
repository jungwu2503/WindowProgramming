import java.awt.*;

public class Isosceles extends Box {

	protected boolean fillFlag;
	protected boolean EAST;
	protected boolean WEST;
	protected boolean SOUTH;
	protected boolean NORTH;
	
	Isosceles(Color color, float thickness) {
		super(color,thickness);
		fillFlag = false;
	}
	
	Isosceles(Color color, float thickness, int x, int y) {	
		super(color,thickness,x,y);
		fillFlag = false;
	}
	
	Isosceles(Color color, float thickness, int x1, int y1, int x2, int y2) {	
		super(color,thickness,x1,y1,x2,y2);
		fillFlag = false;
	}
	
	void setFill() {
		fillFlag = !fillFlag;		
	}
	
	void draw(Graphics g) {
		int width = Math.abs(x2-x1);
		int height = Math.abs(y2-y1);
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		
		setColorAndStroke(g);
		int[] xPoints = { minX+width/5*3, minX+width/5, minX+width/5*2, minX+width/5*2, minX+width/5*4, minX+width/5*4, minX+width };
		int[] yPoints = { minY, minY+height/2, minY+height/2, minY+height, minY+height, minY+height/2, minY+height/2 };
		
		if (EAST) {
			xPoints[0] = minX;
			xPoints[1] = minX;
			xPoints[2] = minX-width/2;
			xPoints[3] = minX-width/2;
			xPoints[4] = minX;
			xPoints[5] = minX;
			xPoints[6] = minX+width/2;
			yPoints[0] = minY+height/6;
			yPoints[1] = minY+height/3;
			yPoints[2] = minY+height/3;
			yPoints[3] = minY+height/3*2;
			yPoints[4] = minY+height/3*2;
			yPoints[5] = minY+height/6*5;
			yPoints[6] = minY+height/2;
		} else if (WEST) {
			xPoints[0] = minX+width/2;
			xPoints[1] = minX+width;
			xPoints[2] = minX+width;
			xPoints[3] = minX+width/2*3;
			xPoints[4] = minX+width/2*3;
			xPoints[5] = minX+width;
			xPoints[6] = minX+width;			
			yPoints[0] = minY+height/2;
			yPoints[1] = minY+height/6;
			yPoints[2] = minY+height/3;
			yPoints[3] = minY+height/3;
			yPoints[4] = minY+height/3*2;
			yPoints[5] = minY+height/3*2;
			yPoints[6] = minY+height/6*5;
		} else if (SOUTH) {
			xPoints[0] = minX+width/6;
			xPoints[1] = minX+width/3;
			xPoints[2] = minX+width/3;
			xPoints[3] = minX+width/3*2;
			xPoints[4] = minX+width/3*2;
			xPoints[5] = minX+width/6*5;
			xPoints[6] = minX+width/2;
			
			yPoints[0] = minY;
			yPoints[1] = minY;
			yPoints[2] = minY-height/2;
			yPoints[3] = minY-height/2;
			yPoints[4] = minY;
			yPoints[5] = minY;
			yPoints[6] = minY+height/2;
		}
		
		g.drawPolygon(xPoints,
				yPoints, yPoints.length);
		
		makeRegion();
		if (fillFlag == true) {
			g.fillPolygon(xPoints, yPoints, yPoints.length);
		}
	}	
	
	Figure copy() {
		Isosceles newIsosceles = new Isosceles(color,thickness,x1,y1,x2,y2);
		newIsosceles.popup = popup;
		newIsosceles.fillFlag = fillFlag;
		newIsosceles.move(MOVE_DX, MOVE_DY);
		return newIsosceles;
	}
	
	void makeRegion() {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int width = Math.abs(x2-x1);
		int height = Math.abs(y2-y1);
		
		int[] xPoints = { minX+width/5*3, minX+width/5, minX+width/5*2, minX+width/5*2, minX+width/5*4, minX+width/5*4, minX+width };
		int[] yPoints = { minY, minY+height/2, minY+height/2, minY+height, minY+height, minY+height/2, minY+height/2 };
		
		if (EAST) {
			xPoints[0] = minX;
			xPoints[1] = minX;
			xPoints[2] = minX-width/2;
			xPoints[3] = minX-width/2;
			xPoints[4] = minX;
			xPoints[5] = minX;
			xPoints[6] = minX+width;
			yPoints[0] = minY;
			yPoints[1] = minY+height/3;
			yPoints[2] = minY+height/3;
			yPoints[3] = minY+height/3*2;
			yPoints[4] = minY+height/3*2;
			yPoints[5] = minY+height;
			yPoints[6] = minY+height/2;
		} else if (WEST) {
			xPoints[0] = minX;
			xPoints[1] = minX+width;
			xPoints[2] = minX+width;
			xPoints[3] = minX+width/2*3;
			xPoints[4] = minX+width/2*3;
			xPoints[5] = minX+width;
			xPoints[6] = minX+width;			
			yPoints[0] = minY+height/2;
			yPoints[1] = minY;
			yPoints[2] = minY+height/3;
			yPoints[3] = minY+height/3;
			yPoints[4] = minY+height/3*2;
			yPoints[5] = minY+height/3*2;
			yPoints[6] = minY+height;
		} else if (SOUTH) {
			xPoints[0] = minX;
			xPoints[1] = minX+width/3;
			xPoints[2] = minX+width/3;
			xPoints[3] = minX+width/3*2;
			xPoints[4] = minX+width/3*2;
			xPoints[5] = minX+width;
			xPoints[6] = minX+width/2;
			
			yPoints[0] = minY;
			yPoints[1] = minY;
			yPoints[2] = minY-height/2;
			yPoints[3] = minY-height/2;
			yPoints[4] = minY;
			yPoints[5] = minY;
			yPoints[6] = minY+height;
		}
		/*int[] xPoints = { minX+width/2, minX, minX+width };
		int[] yPoints = { minY, minY+height, minY+height };
		
		if (EAST) {
			xPoints[0] = minX;
			xPoints[1] = minX;
			xPoints[2] = minX+width;
			yPoints[0] = minY;
			yPoints[1] = minY+height;
			yPoints[2] = minY+height/2;
		} else if (WEST) {
			xPoints[0] = minX;
			xPoints[1] = minX+width;
			xPoints[2] = minX+width;
			yPoints[0] = minY+height/2;
			yPoints[1] = minY;
			yPoints[2] = minY+height;
		} else if (SOUTH) {
			xPoints[0] = minX;
			xPoints[1] = minX+width;
			xPoints[2] = minX+width/2;
			yPoints[0] = minY;
			yPoints[1] = minY;
			yPoints[2] = minY+height;
		}*/
		
		region = new Polygon(xPoints, yPoints, yPoints.length);
	}
	
	Figure orientation(String cardinal) {
		if (cardinal.equals("East")) {
			EAST = true;
			WEST = false;
			SOUTH = false;
			NORTH = false;
		} else if (cardinal.equals("West")) {
			EAST = false;
			WEST = true;
			SOUTH = false;
			NORTH = false;
		} else if (cardinal.equals("South")) {
			EAST = false;
			WEST = false;
			SOUTH = true;
			NORTH = false;
		} else if (cardinal.equals("North")) {
			EAST = false;
			WEST = false;
			SOUTH = false;
			NORTH = true;
		} 
		Isosceles newIsosceles = new Isosceles(color,thickness,x1,y1,x2,y2);
		return newIsosceles;
	}
	
}