import java.awt.*;

import javax.swing.*;

public class Line extends TwoPointFigure {
	
	boolean hasTailFlag;
	boolean hasHeadFlag;
	int whichEnd;
	static int NOEND = 0;
	static int TAILEND = 1;
	static int HEADEND = 2;
	
	Line(Color color, float thickness) {
		super(color, thickness);
		hasTailFlag = false;
		hasHeadFlag = false;
	}
	
	Line(Color color, float thickness, int x, int y) {		 
		super(color,thickness,x,y);
		hasTailFlag = false;
		hasHeadFlag = false;
	}
	
	Line(Color color, float thickness, int x1, int y1, int x2, int y2) {	
		super(color,thickness,x1,y1,x2,y2);
		hasTailFlag = false;
		hasHeadFlag = false;
	}
	
	void popup(JPanel view, int x, int y) {
		//delegation
		double distance1 = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
		double distance2 = Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
		
		((LinePopup)popup).setEnableArrowItem(true);
		if (distance1 < distance2) {
			((LinePopup)popup).setArrowItem(hasTailFlag);		
			whichEnd = TAILEND;
		} else if (distance1 > distance2){
			((LinePopup)popup).setArrowItem(hasHeadFlag);	
			whichEnd = HEADEND;
		} else {
			((LinePopup)popup).setEnableArrowItem(false);
			whichEnd = NOEND;
		}
		
		popup.popup(view,x,y);		
	}
	
	void setArrow() {
		
		if (whichEnd == NOEND) return;
		else if (whichEnd == TAILEND) { 
			hasTailFlag = !hasTailFlag;
		} else { // HEADEND
			hasHeadFlag = !hasHeadFlag;
		}		
		
		popup.hide();
	}
	
	void draw(Graphics g) {		
		
		setColorAndStroke(g);
		g.drawLine(x1, y1, x2, y2); 
		
		if (hasTailFlag && !hasHeadFlag) {
			drawArrow(g,x2,y2,x1,y1);
		} else if (hasHeadFlag && !hasTailFlag) {
			drawArrow(g,x1,y1,x2,y2);
		} else if (hasTailFlag && hasHeadFlag) {
			drawArrow(g,x2,y2,x1,y1);
			drawArrow(g,x1,y1,x2,y2);
		}
	
	}
	
	void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
		int regionWidth = 20;
		int x = x1;
		int y = y1;
		int w = x2 - x1;
		int h = y2 - y1;
		double alpha;
		double theta = Math.atan((double)h/(double)w);
		
		if (w > 0) {
			alpha = (Math.PI / 2 - (Math.PI / 4 + 0.3) - theta);
		} else {
			alpha = (Math.PI / 2 + (Math.PI / 4 + 0.3) - theta);
		}
		
		int dx = (int)(regionWidth * Math.cos(alpha));
		int dy = (int)(regionWidth * Math.sin(alpha));
		
		int x3 = x2 - dx;
		int y3 = y2 + dy;
		
		g.drawLine(x2, y2, x3, y3);
		
		if (w > 0) {
			alpha = (Math.PI / 2 + (Math.PI / 4 + 0.3) - theta);
		} else {
			alpha = (Math.PI / 2 - (Math.PI / 4 + 0.3) - theta);
		}
		
		dx = (int)(regionWidth * Math.cos(alpha));
		dy = (int)(regionWidth * Math.sin(alpha));
		
		int x4 = x2 + dx;
		int y4 = y2 - dy;
		
		g.drawLine(x2, y2, x4, y4);
	}
	

	void makeRegion() {
		int regionWidth = 6;
		int x = x1;
		int y = y1;
		int w = x2 - x1;
		int h = y2 - y1;
		int sign_h = 1;
		if (h < 0) sign_h = -1;
		double angle;
		double theta = (w!=0) ? Math.atan((double)(h)/(double)(w)) : sign_h*Math.PI;
		if (theta < 0) theta = theta + 2 * Math.PI;
		angle = (theta + Math.PI /2);
		int dx = (int)(regionWidth * Math.cos(angle));
		int dy = (int)(regionWidth * Math.sin(angle));
		int xpoints[] = new int[4];
		int ypoints[] = new int[4];
		xpoints[0] = x + dx; ypoints[0] = y + dy;
		xpoints[1] = x - dx; ypoints[1] = y - dy;
		xpoints[2] = x + w - dx; ypoints[2] = y + h - dy;
		xpoints[3] = x + w + dx; ypoints[3] = y + h + dy;
		
		region = new Polygon(xpoints, ypoints, 4);
	}
	
	Figure copy() {
		Line newLine = new Line(color,thickness,x1,y1,x2,y2);
		newLine.hasTailFlag = hasTailFlag;
		newLine.hasHeadFlag = hasHeadFlag;
		newLine.whichEnd = whichEnd;
		newLine.popup = popup;
		newLine.move(MOVE_DX, MOVE_DY);
		return newLine;
	}
	
}