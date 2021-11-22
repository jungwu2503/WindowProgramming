// MainPopup.java

//{{MODELER_BEFORE_CLASS(MainPopup) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: Popup
//Purpose of MainPopup class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

class MainPopup extends Popup 
{

// Operations
    MainPopup(DrawerView view) {
      super(" 종류 ");

      JMenuItem pointItem = new JMenuItem(" 점 ");
      pointItem.addActionListener(view);
      pointItem.setActionCommand("ID_CREATE_POINT");
      _popupPtr.add(pointItem);

      JMenuItem lineItem = new JMenuItem(" 선 ");
      lineItem.addActionListener(view);
      lineItem.setActionCommand("ID_CREATE_LINE");
      _popupPtr.add(lineItem);

      JMenuItem boxItem = new JMenuItem(" 사각형 ");
      boxItem.addActionListener(view);
      boxItem.setActionCommand("ID_CREATE_BOX");
      _popupPtr.add(boxItem);

      JMenuItem circleItem = new JMenuItem(" 원 ");
      circleItem.addActionListener(view);
      circleItem.setActionCommand("ID_CREATE_CIRCLE");
      _popupPtr.add(circleItem);

      JMenuItem tvItem = new JMenuItem(" TV ");
      tvItem.addActionListener(view);
      tvItem.setActionCommand("ID_CREATE_TV");
      _popupPtr.add(tvItem);
      
      JMenuItem starItem = new JMenuItem(" STAR ");
      starItem.addActionListener(view);
      starItem.setActionCommand("ID_CREATE_STAR");
      _popupPtr.add(starItem);
    }
}
