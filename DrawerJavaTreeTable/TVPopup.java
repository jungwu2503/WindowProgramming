// TVPopup.java

//{{MODELER_BEFORE_CLASS(TVPopup) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: FigurePopup
//Purpose of TVPopup class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

class TVPopup extends FigurePopup 
{

// Operations
    TVPopup(DrawerView view) {
      super(view," TV ",false);

      JMenuItem powerItem = new JMenuItem(" ON/OFF ");
      powerItem.addActionListener(view);
      powerItem.setActionCommand("ID_ONOFF_TV");
      _popupPtr.add(powerItem);

      JMenuItem antennaItem = new JMenuItem(" 안테나 조작 ");
      antennaItem.addActionListener(view);
      antennaItem.setActionCommand("ID_SET_ANTENNA");
      _popupPtr.add(antennaItem);
    }
}
