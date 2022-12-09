import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

public class MyTreeCellRenderer implements TreeCellRenderer {	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		return new JPanel() {
			public void paintComponent(Graphics g) {
				if (!(value instanceof DefaultMutableTreeNode)) return;
				if (selected) {
					g.setColor(new Color(150,150,255));
					g.fillRect(0, 0, 600, 20);
				}
				g.setColor(Color.black);
				String s = "" + ((DefaultMutableTreeNode)value).getUserObject();
				g.drawString(s, 15, 10);
				if (leaf) {
					g.drawImage((new ImageIcon("red-ball.gif")).getImage(), 1, 1, tree);
				} else {
					g.drawImage((new ImageIcon("blue-ball.gif")).getImage(), 1, 1, tree);	
				}
			}
			public Dimension getPreferredSize() {
				return new Dimension(600,20);
			}
		};
	}	
}
