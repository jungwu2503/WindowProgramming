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

      JMenuItem deleteItem = new JMenuItem(" ����� ");
      deleteItem.addActionListener(view);
      deleteItem.setActionCommand("ID_DELETE_FIGURE");
      _popupPtr.add(deleteItem);

      JMenuItem copyItem = new JMenuItem(" �����ϱ� ");
      copyItem.addActionListener(view);
      copyItem.setActionCommand("ID_COPY_FIGURE");
      _popupPtr.add(copyItem);

      JMenu colorPopup = new JMenu(" �� ���ϱ� ");
      _popupPtr.add(colorPopup);

      JMenuItem blackItem = new JMenuItem(" ������ ");
      blackItem.addActionListener(view);
      blackItem.setActionCommand("ID_BLACK_COLOR");
      colorPopup.add(blackItem);

      JMenuItem redItem = new JMenuItem(" ������ ");
      redItem.addActionListener(view);
      redItem.setActionCommand("ID_RED_COLOR");
      colorPopup.add(redItem);

      JMenuItem greenItem = new JMenuItem(" �ʷϻ� ");
      greenItem.addActionListener(view);
      greenItem.setActionCommand("ID_GREEN_COLOR");
      colorPopup.add(greenItem);

      JMenuItem blueItem = new JMenuItem(" �Ķ��� ");
      blueItem.addActionListener(view);
      blueItem.setActionCommand("ID_BLUE_COLOR");
      colorPopup.add(blueItem);

      if (fillButtonFlag == true) {
         JMenuItem fillItem = new JMenuItem(" ä��� ");
         fillItem.addActionListener(view);
         fillItem.setActionCommand("ID_FILL_FIGURE");
         _popupPtr.add(fillItem);
      }
    }
}
