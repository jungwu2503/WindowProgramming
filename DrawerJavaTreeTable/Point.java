// Point.java

//{{MODELER_BEFORE_CLASS(Point) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: OnePointFigure
//Purpose of Point class:
/**
*/

import java.awt.*;

//}}MODELER_BEFORE_CLASS

class Point extends OnePointFigure 
{
// Attributes
    static final long serialVersionUID = 2455740096438958603L;

// Operations
    Point(Color color,int x1,int y1) {
      super(color,x1,y1);
    }
    void draw(Graphics g) {
      g.setColor(_color);
      g.drawOval(_x1-3,_y1-3,5,5);
      g.fillOval(_x1-3,_y1-3,6,6);
    }
    Figure copy() {
      Point newPoint = new Point(_color,_x1,_y1);
      newPoint._popup = _popup;
      newPoint.move(20,10);
      return newPoint;
    }
}
