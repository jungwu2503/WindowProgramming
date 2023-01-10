import javax.swing.*;

class TVPopup extends FigurePopup {
	
	TVPopup(DrawerView view) {
		super(view,"TV",false);		
		
		JMenuItem powerItem = new JMenuItem("On/Off");
		powerItem.addActionListener((evt) -> 
			view.onOffTV());
		popupPtr.add(powerItem);
		
		JMenuItem antennaItem = new JMenuItem("Antenna");
		antennaItem.addActionListener((evt) -> 
			view.setAntenna());
		popupPtr.add(antennaItem);
		
	}
	
}