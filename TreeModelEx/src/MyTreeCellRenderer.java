import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

public class MyTreeCellRenderer implements TreeCellRenderer {	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		return new JPanel() {
			public void paintComponent(Graphics g) {
				//setSize(new Dimension(50,10));
				if (!(value instanceof TreeNode)) return;
				String s = ((TreeNode)value).getData();
				g.drawString(s, 15, 10);
				if (leaf) {
					//g.setColor(Color.red);
					g.drawImage((new ImageIcon("red-ball.gif")).getImage(), 1, 1, tree);
				} else {
					//g.setColor(Color.blue);
					g.drawImage((new ImageIcon("blue-ball.gif")).getImage(), 1, 1, tree);	
				}
				/*if (expanded) {
					tree.expandRow(row);
					System.out.println(expanded);
				}*/
				//Dimension d = getSize();
				//System.out.println("Size = " + d);
				//g.fillRect(0, 0, d.width, d.height);
				//g.fillRect(0, 0, 2, 10);
			}
			public Dimension getPreferredSize() {
				return new Dimension(100,20);
			}
		};
	}	
}
