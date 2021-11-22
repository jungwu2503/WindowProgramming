// Box.java

//{{MODELER_BEFORE_CLASS(Box) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: TwoPointFigure
//Purpose of Box class:
/**
*/

import java.awt.*;

//}}MODELER_BEFORE_CLASS

class Box extends TwoPointFigure 
{
// Attributes
    private boolean _fillFlag;
    static final long serialVersionUID = -886226141944528320L;

// Operations
    Box(Color color,int x1,int y1,int x2,int y2) {
      super(color,x1,y1,x2,y2);
      _fillFlag = false;
    }
    void setFill() {
      _fillFlag = !_fillFlag;
    }
    void draw(Graphics g) {
      g.setColor(_color);
      if (_fillFlag == false) {
         g.drawRect(_x1,_y1,_x2-_x1,_y2-_y1);
      } else {
         g.fillRect(_x1,_y1,_x2-_x1,_y2-_y1);
      }
    }
    Figure copy() {
      Box newBox = new Box(_color,_x1,_y1,_x2,_y2);
      newBox._fillFlag = _fillFlag;
      newBox._popup = _popup;
      newBox.move(20,10);
      return newBox;
    }
}
