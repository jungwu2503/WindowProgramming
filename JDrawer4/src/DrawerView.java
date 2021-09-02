import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawerView extends JPanel
					implements MouseListener, MouseMotionListener {

	static int MAX = 100;
	Box pBox;
	Box boxes[] = new Box[MAX];;
	int nBox = 0;
	
	DrawerView() {
		pBox = null;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	// hook function
	// paint event
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < nBox; i++) {
			int minX = Math.min(boxes[i].x1, boxes[i].x2);
			int minY = Math.min(boxes[i].y1, boxes[i].y2);
			int width = Math.abs(boxes[i].x2-boxes[i].x1);
			int height = Math.abs(boxes[i].y2-boxes[i].y1);
			
			g.drawRect(minX, minY, width, height);
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int minX = Math.min(pBox.x1, pBox.x2);
		int minY = Math.min(pBox.y1, pBox.y2);
		int width = Math.abs(pBox.x2-pBox.x1);
		int height = Math.abs(pBox.y2-pBox.y1);
		
		Graphics g = getGraphics();
		g.setXORMode(getBackground());
//		g.setXORMode(Color.red);
		// white : white -> white
		// white : black -> black
		// black : white -> black
		// black : black -> white
		g.drawRect(minX, minY, width, height);
		
///		
		pBox.x2 = e.getX();
		pBox.y2 = e.getY();
		
		minX = Math.min(pBox.x1, pBox.x2);
		minY = Math.min(pBox.y1, pBox.y2);
		width = Math.abs(pBox.x2-pBox.x1);
		height = Math.abs(pBox.y2-pBox.y1);
		
		g.drawRect(minX, minY, width, height);
		
		/*
		int minX = Math.min(startX, oldX);
		int minY = Math.min(startY, oldY);
		int width = Math.abs(oldX-startX);
		int height = Math.abs(oldY-startY);
		
		Graphics g = getGraphics();
		g.setColor(getBackground());
		g.drawRect(minX, minY, width, height);
		
///		
		
		int endX = e.getX();
		int endY = e.getY();
		
		minX = Math.min(startX, endX);
		minY = Math.min(startY, endY);
		width = Math.abs(endX-startX);
		height = Math.abs(endY-startY);
		
		g.setColor(Color.black);
		g.drawRect(minX, minY, width, height);
		
		oldX = endX;
		oldY = endY;
		*/
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {	
		pBox = new Box(e.getX(), e.getY());
		
		boxes[nBox++] = pBox;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		pBox.x2 = e.getX();
		pBox.y2 = e.getY();
		
		int minX = Math.min(pBox.x1, pBox.x2);
		int minY = Math.min(pBox.y1, pBox.y2);
		int width = Math.abs(pBox.x2-pBox.x1);
		int height = Math.abs(pBox.y2-pBox.y1);
		
		Graphics g = getGraphics();
		g.drawRect(minX, minY, width, height);
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
