// Circle.java

//{{MODELER_BEFORE_CLASS(Circle) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: TwoPointFigure
//Purpose of Circle class:
/**
*/

import java.awt.*;

//}}MODELER_BEFORE_CLASS

class Circle extends TwoPointFigure 
{
// Attributes
    private boolean _fillFlag;
    static final long serialVersionUID = -4691629886470951305L;

// Operations
    Circle(Color color,int x1,int y1,int x2,int y2) {
      super(color,x1,y1,x2,y2);
      _fillFlag = false;
    }
    void setFill() {
      _fillFlag = !_fillFlag;
    }
    void draw(Graphics g) {
      g.setColor(_color);
      g.drawOval(_x1,_y1,_x2-_x1,_y2-_y1);
      if (_fillFlag == true) {
         g.fillOval(_x1,_y1,_x2-_x1+1,_y2-_y1+1);
      }
    }
    Figure copy() {
      Circle newCircle = new Circle(_color,_x1,_y1,_x2,_y2);
      newCircle._fillFlag = _fillFlag;
      newCircle._popup = _popup;
      newCircle.move(20,10);
      return newCircle;
    }
}
