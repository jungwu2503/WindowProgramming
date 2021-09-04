import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MyButton extends JButton {
	
	int xPos;
	int yPos;
	
	MyButton(int x, int y) {
		super();
		
		xPos = x;
		yPos = y;
	}
	
	int getXPos() {
		return xPos;
	}
	
	int getYPos() {
		return yPos;
	}
	
}

class ButtonListener implements ActionListener {
	
	ButtonListener() {
		
	}
	
	public void actionPerformed(ActionEvent ev) {
		Object object = ev.getSource();
		
		if (object instanceof MyButton) {
			MyButton button = (MyButton)object;
			int x = button.getXPos();
			int y = button.getYPos();
			System.out.println(x + " " + y);
			button.setVisible(false);
		} 
		
	}
	
}

class MinesPanel extends JPanel implements ActionListener, MouseListener {
	
	int row;
	int col;
	int mineSize;
	int mine[][];
	boolean startFlag;
	static Point dir[];
	MyButton button[][];
	
	static { 
		dir = new Point[8];
		for(int i = 0; i < 8; i++) dir[i] = new Point();
		dir[0].x = -1; dir[0].y = -1;
		dir[1].x = 0; dir[1].y = -1;
		dir[2].x = 1; dir[2].y = -1;
		dir[3].x = 1; dir[3].y = 0;
		dir[4].x = 1; dir[4].y = 1;
		dir[5].x = 0; dir[5].y = 1;
		dir[6].x = -1; dir[6].y = 1;
		dir[7].x = -1; dir[7].y = 0;
	}
	
	MinesPanel() {
		row = 10;
		col = 10;
		
		setLayout(new GridLayout(row, col));
		
//		ButtonListener bl = new ButtonListener();
		
		mine = new int[row][col];
		button = new MyButton[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				button[i][j] = new MyButton(i, j);
				add(button[i][j]);
				button[i][j].addActionListener(this);
				button[i][j].addMouseListener(this);
//				button[i][j].addActionListener(bl);
			}
		}
		
		this.addMouseListener(this);
		mineSize = 10;
		startFlag = false;
		resetMineField();
	}
	
	public void mousePressed(MouseEvent e) {
		Object object = e.getSource();
		
		if ((object instanceof MyButton)) {	
			MyButton button = (MyButton)object;
			
			if (e.getButton() != MouseEvent.BUTTON3) return;
			
			if (button.getIcon() == null) {
				button.setIcon(new ImageIcon("flag.gif"));
			} else {
				button.setIcon(null);
			}
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		
		if ((object instanceof MinesPanel)) {	
			if (e.getClickCount() <= 2) return;
			int x = e.getX();
			int y = e.getY();
			
			Dimension sz = button[0][0].getSize();
			int w = sz.width;
			int h = sz.height;
			
			int i = y / h;
			int j = x / w;
			
			int n = mine[i][j];
			
			if (n == -1) {
				return;
			}
			
			ArrayList<MyButton> l = getNeighborButtons(i, j);
			
			if (n == l.size()) {
				return;
			}
			
			int nChecked = 0;
			for (JButton b : l) {
				if (b.getIcon() != null) {
					nChecked++;
				}
			}
			
			if (n != nChecked) {
				return;
			}
			
			for (MyButton b : l) {
				Icon icon = b.getIcon();
				int bx = b.getXPos();
				int by = b.getYPos();
				if (icon == null && mine[bx][by] != -1) {
					showButton(b, bx, by);
				} else if (icon == null && mine[bx][by] == -1) {
					System.out.println("Fails");
				}
			}
			
		}
		
	}
	
	ArrayList<MyButton> getNeighborButtons(int i, int j) {
		ArrayList<MyButton> l = new ArrayList<MyButton>();
		
		for (int index = 0; index < dir.length; index++) {
			int x = i + dir[index].x;
			int y = j + dir[index].y;
			
			if (x < 0 || y < 0 || x >= row || y >= col) continue;
			
			if (button[x][y].isVisible()) {
				l.add(button[x][y]);
			}	
		}
		return l;
	}
	
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
	public void actionPerformed(ActionEvent ev) {
		Object object = ev.getSource();
		
		if (!(object instanceof MyButton)) return; 
			
		MyButton button = (MyButton)object;
		int x = button.getXPos();
		int y = button.getYPos();
		if (mine[x][y] == -1) {
			System.out.println("Fails");
		} else {
			showButton(button,x, y);
		}
	}
	
	public void showButton(JButton button, int x, int y) {
		if (mine[x][y] > 0) {
			button.setVisible(false);
			return;
		}
		
		boolean visited[][] = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				visited[i][j] = false;
			}
		}
		
		showYourself(x, y, visited);
	}
	
	void showYourself(int x, int y, boolean v[][]) {
		if (v[x][y] == true) {
			return;
		}
		button[x][y].setVisible(false);
		v[x][y] = true;
		if (mine[x][y] > 0) {
			return;
		}
		for (int i = 0; i < dir.length; i++) {
			int nextX = x + dir[i].x;
			int nextY = y + dir[i].y;
			
			if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col) continue;
			
			showYourself(nextX, nextY, v);
		}
	}
	
	int getMineCount(int x, int y) {
		int i;
		int j;
		int count = 0;
		
		for (int index = 0; index < dir.length; index++) {
			i = x + dir[index].x;
			j = y + dir[index].y;
			
			if (i < 0 || j < 0) continue;
			if (i >= row || j >= col) continue;
			
			if (mine[i][j] == -1) {
				count++;
			}
				
		}
		
		return count;		
	}
	
	void resetMineField() {
		int i, j;
		startFlag = true;
		
		ArrayList<Point> l = new ArrayList<Point>();
		while (l.size() < mineSize) {
			double r = Math.random();
			int xPos = (int)(r*1000.0) % col;
			r = Math.random();
			int yPos = (int)(r*1000.0) % row;
			
			Point p = new Point(xPos, yPos);
			if (l.contains(p) == false) {
				l.add(p);
			}
			
		}
		
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				mine[i][j] = 0;
			}
		}
		
		for (Point p : l) {
			mine[p.x][p.y] = -1; 
		}
		
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				if (mine[i][j] != -1) {
					mine[i][j] = getMineCount(i, j);
				}
			}
		}
		
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				System.out.printf("%3d", mine[i][j]);
			}
			System.out.println();
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int i, j, k;
		
		Dimension d = this.getSize();
		int w = d.width / 10;
		int h = d.height / 10;
		
		for (i = 0; i <= 10; i++) {
			g.drawLine(0, i*h, d.width, i*h);
			g.drawLine(i*w, 0, i*w, d.height);
		}
		
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				int x, y;
				
				if (mine[i][j] != 0) {
					x = w * j + 5;
					y = h * i + 20;
					
					String number = "" + mine[i][j];
					g.drawString(number, x, y);
				}
				
			}
		}
		
	}
	
}

class MineTextField extends JTextField {
	
	MineTextField(String s) {
		super(s);
		
		setSize(new Dimension(50,30));
		setHorizontalAlignment(JTextField.LEFT);
		setEditable(false);
		setBorder(BorderFactory.createLoweredBevelBorder());
		setFont(new Font("SansSerif", Font.BOLD, 18));
		setBackground(Color.LIGHT_GRAY);
	}
	
}

class ClockPanel extends JPanel {
	
	JLabel clockLabel;
	MineTextField clockField;
	
	ClockPanel() {
		
		Image clockImage = Toolkit.getDefaultToolkit().getImage("clock.gif");
		MediaTracker tracker = new MediaTracker(this);
		tracker.addImage(clockImage,0);
		try
		{
			tracker.waitForID(0);
		}
		catch (InterruptedException e)
		{
		}
		clockLabel = new JLabel(new ImageIcon(clockImage));
		clockField = new MineTextField("0");
		
		add(clockLabel);
		add(clockField);
	}
	
}

class CountPanel extends JPanel {
	
	MineTextField countField;
	JLabel countLabel;
	
	CountPanel() {
		countField = new MineTextField("10");
		countLabel = new JLabel("Count");
		
		add(countField);
		add(countLabel);
	}
	
}

class StatePanel extends JPanel {
	
	ClockPanel clockPanel;
	CountPanel countPanel;
	
	StatePanel() {
		clockPanel = new ClockPanel();
		countPanel = new CountPanel();
		
		add(clockPanel);
		add(countPanel);
	}
	
	
}

class MinesFrame extends JFrame {
	
	MinesPanel minesPanel;
	StatePanel statePanel;
	
	MinesFrame() {
		setTitle("Mines");
		setSize(296,350);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
		minesPanel = new MinesPanel();
		statePanel = new StatePanel();
		getContentPane().add(minesPanel, "Center");
		getContentPane().add(statePanel, "South");
		
	}
	
	
	
}

public class MinesGame {

	public static void main(String[] args) {
		
		JFrame frame = new MinesFrame();
		frame.setVisible(true);		
		
	}

}
