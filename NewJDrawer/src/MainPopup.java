import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainPopup extends Popup {
	
	MainPopup(DrawerView view) {
		super("±×¸²");
		
		JMenuItem pointItem = new JMenuItem(view.getPointAction());
		popupPtr.add(pointItem);
		
		JMenuItem starItem = new JMenuItem(view.getStarAction());
		popupPtr.add(starItem);
		
		JMenuItem boxItem = new JMenuItem(view.getBoxAction());
		popupPtr.add(boxItem);
		
		JMenuItem isoscelesItem = new JMenuItem(view.getIsoscelesAction());
		popupPtr.add(isoscelesItem);
		
		JMenuItem lineItem = new JMenuItem(view.getLineAction());
		popupPtr.add(lineItem);

		JMenuItem rTriangleItem = new JMenuItem(view.getRegularTriangleAction());
		popupPtr.add(rTriangleItem);
		
		JMenuItem circleItem = new JMenuItem(view.getCircleAction());
		popupPtr.add(circleItem);

		JMenuItem saturnItem = new JMenuItem(view.getSaturnAction());
		popupPtr.add(saturnItem);
		
		JMenuItem tvItem = new JMenuItem(view.getTVAction());
		popupPtr.add(tvItem);
		
		JMenuItem kiteItem = new JMenuItem(view.getKiteAction());
		popupPtr.add(kiteItem);

		JMenuItem textItem = new JMenuItem(view.getTextAction());
		popupPtr.add(textItem);
	}
	
}
