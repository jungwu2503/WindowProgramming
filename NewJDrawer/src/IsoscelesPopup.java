import java.awt.*;

import javax.swing.*;

class IsoscelesPopup extends FigurePopup {
	
	IsoscelesPopup(DrawerView view) {
		super(view,"Isosceles",false);		
		
		JMenuItem fillItem = new JMenuItem("Fill");
		fillItem.addActionListener((evt) ->
			view.fillFigure());
		popupPtr.add(fillItem);
		
		JMenu orientation = new JMenu("orientation");
		//orientation.addActionListener((evt) -> 
		//	view.orientation());
		popupPtr.add(orientation);
		
		JMenuItem eastItem = new JMenuItem("East");
		eastItem.addActionListener((evt) ->
			view.orientation("East"));
		orientation.add(eastItem);

		JMenuItem westItem = new JMenuItem("West");
		westItem.addActionListener((evt) ->
			view.orientation("West"));
		orientation.add(westItem);
		
		JMenuItem southItem = new JMenuItem("South");
		southItem.addActionListener((evt) ->
			view.orientation("South"));
		orientation.add(southItem);
		
		JMenuItem northItem = new JMenuItem("North");
		northItem.addActionListener((evt) ->
			view.orientation("North"));
		orientation.add(northItem);
		
	}
	
}