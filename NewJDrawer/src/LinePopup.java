import javax.swing.*;

class LinePopup extends FigurePopup {
	JCheckBox arrowBox;
	
	LinePopup(DrawerView view) {
		super(view,"Line",false);		
		
		arrowBox = new JCheckBox("Arrow");
		arrowBox.addActionListener((evt) -> 
			view.setArrow());
		popupPtr.add(arrowBox);
		
	}
	
	public void setEnableArrowItem(boolean flag) {
		arrowBox.setEnabled(flag);
	}
	
	public void setArrowItem(boolean flag) {
		arrowBox.setSelected(flag);
	}
	
}