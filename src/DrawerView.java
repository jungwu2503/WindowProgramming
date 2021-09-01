import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawerView extends JPanel {

	DrawerView() {
	}
	// hook function
	// paint event
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(0, 0, 200, 200);
	}
	
}
