// DrawerView.java

//{{MODELER_BEFORE_CLASS(DrawerView) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: JComponent, ActionListener, MouseMotionListener
//Purpose of DrawerView class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

public class DrawerView extends JComponent implements ActionListener, MouseMotionListener 
{
// Attributes
    Popup mainPopup = null;
    Popup pointPopup = null;
    Popup linePopup = null;
    Popup boxPopup = null;
    Popup circlePopup = null;
    Popup tvPopup = null;
    Popup starPopup = null;
    static int DRAW_POINT = 1;
    static int DRAW_LINE = 2;
    static int DRAW_BOX = 3;
    static int DRAW_CIRCLE = 4;
    static int DRAW_TV = 5;
    static int DRAW_STAR = 6;
    static int NOTHING = 0;
    static int DRAWING = 1;
    static int MOVING = 2;
    Figure _selectedFigure;
    int _whatToDraw;
    int _actionMode;
    int _currentX;
    int _currentY;
    DrawerModel _model;
	TreeForm _form;
	TableForm _tableForm;

	void showTreeForm() {
		_form.showForm();
	}

	void showTableForm() {
		_tableForm.showForm();
	}

// Operations
    private void onContextMenu(MouseEvent e) {
      if (_selectedFigure != null) _selectedFigure.eraseDots(this);
      _selectedFigure = null;

      FigureList figures = getModel().getFigures();
         for(int i = 0; i < figures.size(); i++) {
            Figure ptr = figures.getAt(i);
            if (ptr.ptInRegion(e.getX(),e.getY())) {
               _selectedFigure = ptr;
               break;
            }
         }
 
         if (_selectedFigure != null) {
            _selectedFigure.popup(e);
         } else {
            mainPopup.popup(e);
         }   
    }
    private void onLButtonDown(java.awt.Point point) {
      if (_whatToDraw == 0) {
         // 그림을 선택하는 모드이다.
         if (_selectedFigure != null) {
            if (_selectedFigure.ptInRegion(point.x,point.y)) {
               _actionMode = MOVING;
               _currentX = point.x;
               _currentY = point.y;
               getModel().removeFigure(_selectedFigure);
               getModel().setModifiedFlag();
               return;
            }
            _selectedFigure = null;
         }
         // 새 그림 객체를 선택 한다.
         FigureList figures = getModel().getFigures();
         for(int i = 0; i < figures.size(); i++) {
            Figure ptr = figures.getAt(i);
            if (ptr.ptInRegion(point.x,point.y)) {
               _selectedFigure = ptr;
               break;
            }
         }
         repaint();
         return;
      } 

      if (_selectedFigure != null) _selectedFigure.eraseDots(this);
      _selectedFigure = null;

      // 새 그림을 그리기 위한 모드이다.
      if (_whatToDraw == DRAW_POINT) {
         _selectedFigure = new Point(Color.black,point.x,point.y);
         _selectedFigure.setPopup(pointPopup);
      } else if (_whatToDraw == DRAW_LINE) {
         _selectedFigure = new Line(Color.black,point.x,point.y,point.x,point.y);
         _selectedFigure.setPopup(linePopup);
      } else if (_whatToDraw == DRAW_BOX) {
         _selectedFigure = new Box(Color.black,point.x,point.y,point.x,point.y);
         _selectedFigure.setPopup(boxPopup);
      } else if (_whatToDraw == DRAW_CIRCLE) {
         _selectedFigure = new Circle(Color.black,point.x,point.y,point.x,point.y);
         _selectedFigure.setPopup(circlePopup);
      } else if (_whatToDraw == DRAW_STAR) {
         _selectedFigure = new Star(Color.black,point.x,point.y);
         _selectedFigure.setPopup(starPopup);
      } else if (_whatToDraw == DRAW_TV) {
         _selectedFigure = new TV(Color.black,point.x,point.y,true);
         _selectedFigure.setPopup(tvPopup);
         _whatToDraw = 0;
         _selectedFigure.makeRegion();
         getModel().addFigure(_selectedFigure);
         repaint();
         return;
      }
      _whatToDraw = 0;
      _actionMode = DRAWING;
      _selectedFigure.draw(getGraphics());
      getModel().setModifiedFlag();
    }
    private void onMouseMove(java.awt.Point point) {
      Graphics g = getGraphics();
      if (_actionMode == DRAWING) {
         g.setXORMode(getBackground());
         _selectedFigure.drawing(g,point.x,point.y);
      } else if (_actionMode == MOVING) {
         g.setXORMode(getBackground());
         _selectedFigure.move(g,point.x-_currentX,point.y-_currentY);
         _currentX = point.x;
         _currentY = point.y;
      }
    }
    private void onLButtonUp(java.awt.Point point) {
      if (_selectedFigure == null || _actionMode == NOTHING) {
         return;
      }
      Graphics g = getGraphics();
      if (_actionMode == DRAWING) {
         g.setXORMode(getBackground());
         _selectedFigure.drawing(g,point.x,point.y);
      }
      _selectedFigure.makeRegion();
      getModel().addFigure(_selectedFigure);
	  _form.addFigure(_selectedFigure);
	  _tableForm.addFigure(_selectedFigure);

      _actionMode = NOTHING;
      repaint();
    }
    private void setColorForSelectedFigure(Color color) {
      if (_selectedFigure == null) return;
      _selectedFigure.setColor(color);
      getModel().setModifiedFlag();
      repaint();
    }
    public DrawerView(int w,int h) {
      super();
      setSize(w,h);
      setPreferredSize(new Dimension(w,h));

     _model = new DrawerModel(this);

      mainPopup = new MainPopup(this);
      pointPopup = new FigurePopup(this," 점 ",false);
      linePopup = new FigurePopup(this," 선 ",false);
      boxPopup = new FigurePopup(this," 사각형 ",true);
      circlePopup = new FigurePopup(this," 원 ",true);
      tvPopup = new TVPopup(this);
      starPopup = new FigurePopup(this," 별 ",true);
      
	  _form = new TreeForm(this);
	  _tableForm = new TableForm(this);

      this.enableEvents(AWTEvent.MOUSE_EVENT_MASK);
      addMouseMotionListener(this);

      _selectedFigure = null;
      _whatToDraw = 0;
      _actionMode = NOTHING;
      _currentX = 0;
      _currentY = 0;
    }
    public DrawerModel getModel() {
      return _model;
    }
    public void resetSelectedFigure() {
      _selectedFigure = null;
    }
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      DrawerModel model = getModel();
      FigureList figures = model.getFigures();
      for(int i = 0; i < figures.size(); i++) {
         Figure ptr = figures.getAt(i);
         if (ptr != null) ptr.draw(g);
      }
      if (_selectedFigure != null && _actionMode == NOTHING) 
         _selectedFigure.drawDots(g);
    }
    public void paint(Graphics g) {
      this.paintComponent(g);
    }
    public void processMouseEvent(MouseEvent e) {
      if (e.isPopupTrigger()) {
         onContextMenu(e);
      } else if (e.getID() == MouseEvent.MOUSE_PRESSED &&
         e.getModifiers() == MouseEvent.BUTTON1_MASK) {
         onLButtonDown(new java.awt.Point(e.getX(),e.getY()));
      } else if (e.getID() == MouseEvent.MOUSE_RELEASED &&
         e.getModifiers() == MouseEvent.BUTTON1_MASK) {
         onLButtonUp(new java.awt.Point(e.getX(),e.getY()));
      } else {
         super.processMouseEvent(e);
      }
    }
    public void mouseDragged(MouseEvent  e) {
      onMouseMove(new java.awt.Point(e.getX(),e.getY()));
    }
    public void mouseMoved(MouseEvent e) {
      _actionMode = NOTHING;
    }
    void onCreatePoint() {
      _whatToDraw = DRAW_POINT;
    }
    void onCreateLine() {
      _whatToDraw = DRAW_LINE;
    }
    void onCreateBox() {
      _whatToDraw = DRAW_BOX;
    }
    void onCreateCircle() {
      _whatToDraw = DRAW_CIRCLE;
    }
    void onCreateTv() {
      _whatToDraw = DRAW_TV;
    }
    void onCreateStar() {
        _whatToDraw = DRAW_STAR;
    }
    void onDeleteFigure() {
      if (_selectedFigure == null) return;
      getModel().removeFigure(_selectedFigure);
      _selectedFigure = null;
      getModel().setModifiedFlag();
      repaint();
    }
    void onCopyFigure() {
      if (_selectedFigure == null) return;
      Figure newFigure = _selectedFigure.copy();
      newFigure.makeRegion();
      getModel().addFigure(newFigure);
      _selectedFigure = newFigure;
      getModel().setModifiedFlag();
      repaint();
    }
    void onFillFigure() {
      if (_selectedFigure == null) return;
      // 채우기를 위해서는 실행 시 클래스 식별과 타입 캐스팅이 필요하다.
      if (Figure.isKindOf(_selectedFigure,"TwoPointFigure")) {
         TwoPointFigure ptr = (TwoPointFigure)_selectedFigure;
         ptr.setFill();
      }
      getModel().setModifiedFlag();
      repaint();
    }
    void onBlackColor() {
      setColorForSelectedFigure(Color.black);
    }
    void onRedColor() {
      setColorForSelectedFigure(Color.red);
    }
    void onGreenColor() {
      setColorForSelectedFigure(Color.green);
    }
    void onBlueColor() {
      setColorForSelectedFigure(Color.blue);
    }
    void onOnOffTv() {
      if (_selectedFigure == null) return;
      // 이 작업을 위해서는 실행 시 클래스 식별을 이용해 TV 인가를 확인해야 한다.
      if (_selectedFigure.getClass().getName().equals("TV")) {
         TV pTV = (TV)_selectedFigure;
         pTV.pressPowerButton();
      }
      getModel().setModifiedFlag();
      repaint();
    }
    void onSetAntenna() {
      if (_selectedFigure == null) return;
      // 이 작업을 위해서는 실행 시 클래스 식별을 이용해 TV 인가를 확인해야 한다.
      if (_selectedFigure.getClass().getName().equals("TV")) {
         TV pTV = (TV)_selectedFigure;
         pTV.setAntenna();
      }
      getModel().setModifiedFlag();
      repaint();
    }
    public void actionPerformed(ActionEvent event) {
      String command = event.getActionCommand();
      if (command.equals("ID_CREATE_POINT")) onCreatePoint();
      else if (command.equals("ID_CREATE_LINE")) onCreateLine();
      else if (command.equals("ID_CREATE_BOX")) onCreateBox();
      else if (command.equals("ID_CREATE_CIRCLE")) onCreateCircle();
      else if (command.equals("ID_CREATE_TV")) onCreateTv();
      else if (command.equals("ID_CREATE_STAR")) onCreateStar();
      else if (command.equals("ID_DELETE_FIGURE")) onDeleteFigure();
      else if (command.equals("ID_COPY_FIGURE")) onCopyFigure();
      else if (command.equals("ID_FILL_FIGURE")) onFillFigure();
      else if (command.equals("ID_BLACK_COLOR")) onBlackColor();
      else if (command.equals("ID_RED_COLOR")) onRedColor();
      else if (command.equals("ID_GREEN_COLOR")) onGreenColor();
      else if (command.equals("ID_BLUE_COLOR")) onBlueColor();
      else if (command.equals("ID_ONOFF_TV")) onOnOffTv();
      else if (command.equals("ID_SET_ANTENNA")) onSetAntenna();
    }
}
