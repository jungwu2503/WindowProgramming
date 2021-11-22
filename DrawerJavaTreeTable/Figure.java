// Figure.java

//{{MODELER_BEFORE_CLASS(Figure) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: Object, Serializable
//Purpose of Figure class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;

//}}MODELER_BEFORE_CLASS

abstract class Figure extends Object implements Serializable 
{
// Attributes
    protected static int DOTSIZE = 6;
    protected Color _color;
    protected transient Polygon _region;
    protected transient Popup _popup;
    protected transient boolean _dotedFlag;
    static final long serialVersionUID = 1394672178295825629L;

// Operations
    Figure(Color color) {
     _color = color;
     _region = null;
     _popup = null;
     _dotedFlag = false;
    }
	public int getX1() { return -1; }
	public int getY1() { return -1; }
	public int getX2() { return -1; }
	public int getY2() { return -1; }

	public String toString(){
		return null;
	}
    void setPopup(Popup popup) {
      _popup = popup;
    }
    void setColor(Color color) {
      _color = color;
    }
    void popup(MouseEvent event) {
      _popup.popup(event);
    }
    abstract void draw(Graphics g);
    abstract void drawDots(Graphics g);
    void eraseDots(DrawerView view) {
      _dotedFlag = false;
      view.repaint();
    }
    abstract void drawing(Graphics g,int newX,int newY);
    void move(Graphics g,int dx,int dy) {
      draw(g);
      move(dx,dy);
      draw(g);
    }
    abstract void move(int dx,int dy);
    abstract Figure copy();
    abstract void makeRegion();
    boolean ptInRegion(int x,int y) {
      if (_region != null) {
         return _region.contains(x,y);
      } else {
         return false;
      }
    }
    static boolean isKindOf(Object obj,String className) {
      // 이 함수는 obj 객체가 className 클래스의 하위 클래스 객체인가를 검사한다.
      Class objClass = obj.getClass();
      String objClassName = objClass.getName();
      while(objClass != null) {
         if (objClassName.equals(className)) return true;
         objClass = objClass.getSuperclass();
         if (objClass != null) objClassName = objClass.getName();
      }
      return false;
    }
}
