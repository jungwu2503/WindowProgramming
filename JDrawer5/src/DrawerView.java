import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.*;

public class DrawerView extends JPanel
					implements MouseListener, MouseMotionListener {

	public static int DRAW_BOX = 1;
	public static int DRAW_LINE = 2;
	
	private int whatToDraw;
	private Box pBox;
	private ArrayList<Box> boxes = new ArrayList<Box>();
	private Line pLine;
	private ArrayList<Line> lines = new ArrayList<Line>();
	private Circle pCircle;
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	
	DrawerView() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	void setWhatToDraw(int figureType) {
		whatToDraw = figureType;
	}
	
	// hook function
	// paint event
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		for (int i = 0; i < boxes.size(); i++) {
			Box pBox = boxes.get(i);
			pBox.draw(g);
		}	
		
		for (int i = 0; i < lines.size(); i++) {
			Line pLine = lines.get(i);
			pLine.draw(g);
		}	
		
		for (int i = 0; i < circles.size(); i++) {
			Circle pCircle = circles.get(i);
			pCircle.draw(g);
		}	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		Graphics g = getGraphics();
		g.setXORMode(getBackground());
		
		if (whatToDraw == DRAW_BOX) {
			pBox.drawing(g,e.getX(), e.getY());
		} else if (whatToDraw == DRAW_LINE) {
			pLine.drawing(g,e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {	
		
		if (whatToDraw == DRAW_BOX) {
			pBox = new Box(e.getX(), e.getY());
			boxes.add(pBox);
		} else if (whatToDraw == DRAW_LINE) {
			pLine = new Line(e.getX(), e.getY());
			lines.add(pLine);
		}
		

		
		pCircle = new Circle(e.getX(), e.getY());
		circles.add(pCircle);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		Graphics g = getGraphics();
		
		if (whatToDraw == DRAW_BOX) {
			pBox.setXY2(e.getX(), e.getY());				
			pBox.draw(g);	
		} else if (whatToDraw == DRAW_LINE) {
			pLine.setXY2(e.getX(), e.getY());				
			pLine.draw(g);
		}
		
		pCircle.setXY2(e.getX(), e.getY());				
		pCircle.draw(g);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
