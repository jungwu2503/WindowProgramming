import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawerView extends JPanel
					implements MouseListener, MouseMotionListener {

	int startX;
	int startY;
	int oldX;
	int oldY;
	
	DrawerView() {
		startX = 0;
		startY = 0;
		oldX = 0;
		oldY = 0;
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	// hook function
	// paint event
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int minX = Math.min(startX, oldX);
		int minY = Math.min(startY, oldY);
		int width = Math.abs(oldX-startX);
		int height = Math.abs(oldY-startY);
		
		Graphics g = getGraphics();
		g.setXORMode(getBackground());
//		g.setXORMode(Color.red);
		// white : white -> white
		// white : black -> black
		// black : white -> black
		// black : black -> white
		g.drawRect(minX, minY, width, height);
		
///		
		
		int endX = e.getX();
		int endY = e.getY();
		
		minX = Math.min(startX, endX);
		minY = Math.min(startY, endY);
		width = Math.abs(endX-startX);
		height = Math.abs(endY-startY);
		
		g.drawRect(minX, minY, width, height);
		
		oldX = endX;
		oldY = endY;
		
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
		startX = e.getX();
		startY = e.getY();
		
		oldX = startX;
		oldY = startY;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int endX = e.getX();
		int endY = e.getY();
		
		Graphics g = getGraphics();
		g.drawRect(startX, startY, endX-startX, endY-startY);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
