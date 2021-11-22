// Popup.java

//{{MODELER_BEFORE_CLASS(Popup) - DO NOT DELETE THIS LINE
//OODesigner will reverse the code here.

//Organization: Pusan Univ. of Foreign Studies
//Author: Taegyun Kim
//Date: Saturday, January 10, 2004
//Super class: 
//Purpose of Popup class:
/**
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//}}MODELER_BEFORE_CLASS

class Popup 
{
// Attributes
    protected JPopupMenu _popupPtr;

// Operations
    Popup(String title) {
      super();
      _popupPtr = new JPopupMenu(title);
      if (title != null) {
          _popupPtr.add(title);
          _popupPtr.addSeparator();
      }
    }
    void popup(MouseEvent event) {
      _popupPtr.show(event.getComponent(),
                     event.getX(),event.getY());
    }
}
