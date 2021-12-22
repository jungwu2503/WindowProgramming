import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LadderFrame extends JFrame {
	
	int[][] ladder = new int[7][];
	int num;
	int h = 0;
	Scanner input = new Scanner(System.in);
	String[] names;
	//boolean isConceal;
	
	LadderFrame() {
		super("사다리게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LadderPanel ladderPanel = new LadderPanel();
		setContentPane(ladderPanel);
		setSize(1000,1000);
		setResizable(false);
		setVisible(true);
		
		String userCnt = JOptionPane.showInputDialog("사다리타기 인원 입력");
		num = Integer.parseInt(userCnt);
		names = new String[num];
		for (int i = 0; i < num; i++) {
			names[i] = JOptionPane.showInputDialog("참여자 이름 입력");
		}
		
		path(num);		
	}
	
	public void path(int num) {
		int cnt = (int)(Math.random()*(num*2))+num;
		ladder = new int[7][num];
		for (int i = 0; i < 7; i++) { // 초기화
			for (int j = 0; j < num; j++) {
				ladder[i][j] = 1;
			}
		}
		
		// 참여자
		int namePointX = 60;
		for (int j = 0; j < num; j++) {
			namePointX = 60 + (60*(j+1)) - 10;
			
			JLabel la = new JLabel(names[j]);
			la.setSize(40,40);
			la.setLocation(namePointX, 20);
			add(la);
		}
		
		// btn 생성
		int btnX = 60;
		JButton[] btn = new JButton[num];
		for (int j = 0; j < num; j++) {
			btnX = 55 + (55 * (j + 1));
			btn[j] = new JButton(Integer.toString(j));
			btn[j].setSize(45,45);
			btn[j].setLocation(btnX,65);
			btn[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)e.getSource();
					String getNum = btn.getText();
					int num = Integer.parseInt(getNum);
					h = 0;
					DFS(num);
				}
			});
			add(btn[j]);
		}
		
		// 경로 랜덤 생성
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < num-1; j++) {
				int pathCheck = (int)(Math.random()*2)+1;
				if (pathCheck == 2 && cnt > 0) {
					if (ladder[i][j-1] == 1 && ladder[i][j+1] == 1) {
						ladder[i][j] = pathCheck;
						int left_or_right = (int)(Math.random()*2)+1;
						if (left_or_right==1) {
							ladder[i][j-1] = 3;
							ladder[i][j] = 2; //left
						} else {
							ladder[i][j+1] = 2;
							ladder[i][j] = 3; //right
						}
						cnt--;
					}
				}
			}
		}
		
		
		// 가리기 btn 생성
		int concealBtnX = 60;
		JButton concealBtn = new JButton("결과 확인");
		concealBtnX = 55 * num;
		concealBtn.setSize(concealBtnX,45);
		concealBtn.setLocation(110,410);		
		concealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				concealBtn.setLocation(1000,1000);
			}
		});
		add(concealBtn);
				
/*		// 가리기 btn 생성
		if (!isConceal) {
			int concealBtnX = 60;
			JButton concealBtn = new JButton("결과 확인");
			concealBtnX = 55 * num;
			concealBtn.setSize(concealBtnX,45);
			concealBtn.setLocation(110,410);		
			concealBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					concealBtn.setVisible(false);
				}
			});
			add(concealBtn);
			isConceal = true;
		} */
		
		// 당첨, 꽝
		int win = 0;
		int lose = -1;
		int lastNum;
		boolean winCheck = false;
		int pointX = 60;
		for (int j = 0; j < num; j++) {
			lastNum = (int)(Math.random()*2)+1;
			pointX = 60 + (60*(j+1));
			if (lastNum == 2 && winCheck == false) {
				ladder[6][j] = win;
				winCheck = true;
				JLabel la = new JLabel("당첨");
				la.setSize(40,40);
				la.setLocation(pointX, 410);
				add(la);
			}
			else if (j == num-1 && winCheck == false) {
				ladder[6][j] = win;
				winCheck = true;
				JLabel la = new JLabel("당첨");
				la.setSize(40,40);
				la.setLocation(pointX, 410);
				add(la);
			}
			else {
				ladder[6][j] = lose;
				JLabel la = new JLabel("꽝");
				la.setSize(40,40);
				la.setLocation(pointX, 410);
				add(la);
			}
		}
		
		repaint();
	}
	
	class LadderPanel extends JPanel {
		LadderPanel() {
			setSize(500,500);
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x1 = 60, x2, y1 = 60, y2;
			for (int i = 1; i < 6; i++) {
				y1 += 60;
				x1 = 60;
				for (int j = 0; j < num; j++) {
					x1 = 60+(60*(j+1));
					x2 = x1;
					y2 = y1 + 60;
					if (ladder[i][j] == 1) {
						g.drawLine(x1, y1, x2, y2);
					} else if (ladder[i][j] == 2) {
						g.drawLine(x1, y1, x2, y2);
						g.drawLine(x1, y1+30, x1-30, y1+30);
					} else if (ladder[i][j] == 3) {
						g.drawLine(x1, y1, x2, y2);
						g.drawLine(x1, y1+30, x2+30, y1+30);
					} 
				}
			}
		}
		
	}
	
	public void DFS(int start) {
		int w = start;
		while (h < 6) {
			if (ladder[h][w] == 1) 
				h++;
			else if (ladder[h][w] == 2) {
				w--;
				h++;
			}
			else if (ladder[h][w] == 3) {
				w++;
				h++;
			}
		}
		if (h == 6) {
			switch(ladder[h][w]) {
			case -1:
				JOptionPane.showMessageDialog(null, "꽝이에요!");
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "당첨이에요!");
				break;
			}			
		}
		
	}
	
}





/*
public class LadderFrame extends JFrame {
	
	StartPanel startPanel;
//	JTextField playerNumField;
	
	LadderFrame() {
		setTitle("Ladder!");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setSize(screenWidth*2/3, screenHeight*2/3);
		setLocation(screenWidth/6, screenHeight/6);
		Image img = tk.getImage("Ladder.png");
		setIconImage(img);
		
		startPanel = new StartPanel();		
//		startPanel.add(playerNumField);
		add(startPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}


class StartPanel extends JPanel implements ActionListener {

	JLabel label;
	JTextField playerNumField;
	JButton okBtn;
	
	StartPanel() {		
		setLayout(new FlowLayout(3));
		
		label = new JLabel("사다리타기 인원 입력 ");
		add(label);
		
		playerNumField = new JTextField();
		playerNumField.setPreferredSize(new Dimension(50,40));
				
		add(playerNumField);
		
		okBtn = new JButton("OK");
		okBtn.addActionListener(this);
			
		add(okBtn);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okBtn) {
			new LadderPanel(playerNumField.getText());
		}
	}
	
}

class LadderPanel extends JPanel {
	
	LadderPanel() {
	}
	
	LadderPanel(String num) {
		
	}
	
}

*/