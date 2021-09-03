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
			boxes[i].draw(g);
			
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		Graphics g = getGraphics();
		g.setXORMode(getBackground());
		
		pBox.drawing(g,e.getX(), e.getY());
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
		
		Graphics g = getGraphics();
		
		pBox.setXY2(e.getX(), e.getY());				
		pBox.draw(g);		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
