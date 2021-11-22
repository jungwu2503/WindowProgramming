// FigurePopup.java

//{{MODELER_BEFORE_CLASS(FigurePopup) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: Popup
//Purpose of FigurePopup class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

class FigurePopup extends Popup 
{

// Operations
    FigurePopup(DrawerView view,String title,boolean fillButtonFlag) {
      super(title);

      JMenuItem deleteItem = new JMenuItem(" 지우기 ");
      deleteItem.addActionListener(view);
      deleteItem.setActionCommand("ID_DELETE_FIGURE");
      _popupPtr.add(deleteItem);

      JMenuItem copyItem = new JMenuItem(" 복사하기 ");
      copyItem.addActionListener(view);
      copyItem.setActionCommand("ID_COPY_FIGURE");
      _popupPtr.add(copyItem);

      JMenu colorPopup = new JMenu(" 색 정하기 ");
      _popupPtr.add(colorPopup);

      JMenuItem blackItem = new JMenuItem(" 검정색 ");
      blackItem.addActionListener(view);
      blackItem.setActionCommand("ID_BLACK_COLOR");
      colorPopup.add(blackItem);

      JMenuItem redItem = new JMenuItem(" 빨강색 ");
      redItem.addActionListener(view);
      redItem.setActionCommand("ID_RED_COLOR");
      colorPopup.add(redItem);

      JMenuItem greenItem = new JMenuItem(" 초록색 ");
      greenItem.addActionListener(view);
      greenItem.setActionCommand("ID_GREEN_COLOR");
      colorPopup.add(greenItem);

      JMenuItem blueItem = new JMenuItem(" 파랑색 ");
      blueItem.addActionListener(view);
      blueItem.setActionCommand("ID_BLUE_COLOR");
      colorPopup.add(blueItem);

      if (fillButtonFlag == true) {
         JMenuItem fillItem = new JMenuItem(" 채우기 ");
         fillItem.addActionListener(view);
         fillItem.setActionCommand("ID_FILL_FIGURE");
         _popupPtr.add(fillItem);
      }
    }
}
