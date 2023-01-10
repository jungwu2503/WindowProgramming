import java.awt.*;

public class Box extends TwoPointFigure {

	protected boolean fillFlag;
	
	Box(Color color, float thickness) {	
		super(color,thickness);
		fillFlag = false;
	}
	
	Box(Color color, float thickness, int x, int y) {	
		super(color,thickness,x,y);
		fillFlag = false;
	}
	
	Box(Color color, float thickness, int x1, int y1, int x2, int y2) {	
		super(color,thickness,x1,y1,x2,y2);
		fillFlag = false;
	}
	
	void setFill() {
		fillFlag = !fillFlag;
		
	}
	
	void draw(Graphics g) {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int width = Math.abs(x2-x1);
		int height = Math.abs(y2-y1);
		
		setColorAndStroke(g);
		
		g.drawRect(minX, minY, width, height);
		
		if (fillFlag == true) {
			g.fillRect(minX, minY, width, height);
		}
	}	
	
	Figure copy() {
		Box newBox = new Box(color,thickness,x1,y1,x2,y2);
		newBox.popup = popup;
		newBox.fillFlag = fillFlag;
		newBox.move(MOVE_DX, MOVE_DY);
		return newBox;
	}
	
}
