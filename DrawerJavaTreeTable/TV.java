// TV.java

//{{MODELER_BEFORE_CLASS(TV) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: TwoPointFigure
//Purpose of TV class:
/**
*/

import java.awt.*;

//}}MODELER_BEFORE_CLASS

class TV extends TwoPointFigure 
{
// Attributes
    private boolean _antennaFlag;
    private Box _frame;
    private Box _screen;
    private Circle _channelButton;
    private Circle _volumnButton;
    private Circle _menuButton;
    private Box _powerButton;
    private Line _antenna1;
    private Line _antenna2;
    private static int FRAME_WIDTH = 150;
    private static int FRAME_HEIGHT = 90;
    private static int ANTENNA_WIDTH = 30;
    private static int ANTENNA_HEIGHT = 40;
    private static int FRAME_GAP = 12;
    private static int SCREEN_WIDTH = 105;
    private static int SCREEN_HEIGHT = FRAME_HEIGHT-2*FRAME_GAP;
    private static int SWITCH_GAP = 17;
    private static int SWITCH_SIZE = 10;
    private static int POWER_SWITCH_WIDTH = 15;
    private static int POWER_SWITCH_HEIGHT = 8;
    private static int TOTAL_WIDTH = FRAME_WIDTH;
    private static int TOTAL_HEIGHT = FRAME_HEIGHT+ANTENNA_HEIGHT;
    static final long serialVersionUID = -5763823445846811002L;

// Operations
    TV(Color color,int x,int y,boolean antennaOption) {
      super(Color.black,x,y,x+TOTAL_WIDTH,y+TOTAL_HEIGHT);

      int x1,y1,x2,y2;
      x1 = x;               
      y1 = y + ANTENNA_HEIGHT;
      x2 = x + FRAME_WIDTH;
      y2 = y + TOTAL_HEIGHT;
      _frame = new Box(Color.black,x1,y1,x2,y2);
      x1 = x1 + FRAME_GAP;
      y1 = y1 + FRAME_GAP;
      x2 = x1 + SCREEN_WIDTH;
      y2 = y1 + SCREEN_HEIGHT;
      _screen = new Box(color,x1,y1,x2,y2);
      x1 = x2 + FRAME_GAP;
      y1 = y1 + FRAME_GAP/2;
      x2 = x1 + SWITCH_SIZE;
      y2 = y1 + SWITCH_SIZE;
      _channelButton = new Circle(Color.black,x1,y1,x2,y2);
      y1 = y1 + SWITCH_GAP;
      y2 = y1 + SWITCH_SIZE;
      _volumnButton = new Circle(Color.black,x1,y1,x2,y2);
      y1 = y1 + SWITCH_GAP;
      y2 = y1 + SWITCH_SIZE;
      _menuButton = new Circle(Color.black,x1,y1,x2,y2);
      x1 = x1 - FRAME_GAP/3 + 2;
      y1 = y1 + SWITCH_GAP + 2;
      x2 = x1 + POWER_SWITCH_WIDTH;
      y2 = y1 + POWER_SWITCH_HEIGHT;
      _powerButton = new Box(Color.black,x1,y1,x2,y2);
      _antennaFlag = antennaOption;
      if (antennaOption == true) {
         int cx = x + TOTAL_WIDTH/2;
         x1 = cx - ANTENNA_WIDTH;
         y1 = y;
         x2 = cx;
         y2 = y + ANTENNA_HEIGHT;
         _antenna1 = new Line(Color.black,x1,y1,x2,y2);
         x1 = cx + ANTENNA_WIDTH;
         _antenna2 = new Line(Color.black,x1,y1,x2,y2);
      } else {
         _antenna1 = null;
         _antenna2 = null;
      }
    }
    void pressPowerButton() {
      _screen.setFill();
      _powerButton.setFill();
    }
    void setAntenna() {
      if (_antennaFlag == true) {
         _antenna1 = null;
         _antenna2 = null;
      } else {
         int cx = _x1 + TOTAL_WIDTH/2;
         int x1 = cx - ANTENNA_WIDTH;
         int y1 = _y1;
         int x2 = cx;
         int y2 = _y1 + ANTENNA_HEIGHT;
         _antenna1 = new Line(Color.black,x1,y1,x2,y2);
         x1 = cx + ANTENNA_WIDTH;
         _antenna2 = new Line(Color.black,x1,y1,x2,y2);
      }
      _antennaFlag = !_antennaFlag;
    }
    void setColor(Color color) {
      super.setColor(color);
      _screen.setColor(color);
    }
    void draw(Graphics g) {
      _frame.draw(g);
      _screen.draw(g);
      _channelButton.draw(g);
      _volumnButton.draw(g);
      _menuButton.draw(g);
      _powerButton.draw(g);
      if (_antenna1 != null) {
         _antenna1.draw(g);
      }
      if (_antenna2 != null) {
         _antenna2.draw(g);
      }
    }
    void move(int dx,int dy) {
      super.move(dx,dy);
      _frame.move(dx,dy);
      _screen.move(dx,dy);
      _channelButton.move(dx,dy);
      _volumnButton.move(dx,dy);
      _menuButton.move(dx,dy);
      _powerButton.move(dx,dy);
      if (_antenna1 != null) {
         _antenna1.move(dx,dy);
      }
      if (_antenna2 != null) {
         _antenna2.move(dx,dy);
      }
    }
    Figure copy() {
      TV newTV = new TV(_color,_x1,_y1,_antennaFlag);
      newTV._popup = _popup;
      newTV.setColor(_color);
      newTV.move(50,50);
      return newTV;
    }
}
