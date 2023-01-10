import java.io.*;

import javax.swing.*;

public class Popup implements Serializable {

	JPopupMenu popupPtr;
	Popup(String title) {
		popupPtr = new JPopupMenu("�׸�");		
		popupPtr.add(title);
		popupPtr.addSeparator();
	}
	
	public void popup(JPanel view, int x, int y) {
		popupPtr.show(view , x, y);
	}
	
	public void hide() {
		popupPtr.setVisible(false);
	}
}
