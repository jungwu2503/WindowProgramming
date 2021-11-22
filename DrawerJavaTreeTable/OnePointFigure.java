// OnePointFigure.java

//{{MODELER_BEFORE_CLASS(OnePointFigure) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: Figure
//Purpose of OnePointFigure class:
/**
*/

import java.awt.*;

//}}MODELER_BEFORE_CLASS

abstract class OnePointFigure extends Figure 
{
// Attributes
    protected int _x1;
    protected int _y1;
    static final long serialVersionUID = 1404028040949684163L;

// Operations
	public int getX1() { return _x1; }
	public int getY1() { return _y1; }

    OnePointFigure(Color color,int x1,int y1) {
      super(color);
      _x1 = x1; _y1 = y1;
    }
    void drawDots(Graphics g) {
      _dotedFlag = true;
      g.setColor(Color.black);
      g.fillRect(_x1-DOTSIZE/2,_y1-DOTSIZE/2,DOTSIZE,DOTSIZE);
    }
    void drawing(Graphics g,int newX,int newY) {
      draw(g);
      _x1 = newX;
      _y1 = newY;
      draw(g);
    }
	public String toString(){
		return "" + _x1 + "," + _y1;
	}
    void move(int dx,int dy) {
      _x1 = _x1 + dx; _y1 = _y1 + dy;
    }
    void makeRegion() {
      int xpoints[] = new int[4];
      int ypoints[] = new int[4];
      xpoints[0] = _x1-3; ypoints[0] = _y1-3;
      xpoints[1] = _x1+3; ypoints[1] = _y1-3;
      xpoints[2] = _x1+3; ypoints[2] = _y1+3;
      xpoints[3] = _x1-3; ypoints[3] = _y1+3;
      _region = new Polygon(xpoints,ypoints,4);
    }
}
