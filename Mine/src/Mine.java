import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Mine extends JFrame {

	JPanel jp = new JPanel();
	JPanel jp2 = new JPanel();
	JButton reset = new JButton();
	Random rnd = new Random();
	int[][] mine = new int[10][10];
	int[][] mine2 = new int[15][15];
	int[][] mine3 = new int[20][20];
	JButton[][] jb = new JButton[10][10]; 
	JButton[][] jb2 = new JButton[15][15];
	JButton[][] jb3 = new JButton[20][20];
	JMenuBar mb = new JMenuBar();
	JMenu menu = new JMenu("파일");
	
	ImageIcon icon0 = new ImageIcon("../mine0.png"); 
	ImageIcon icon1 = new ImageIcon("../mine1.png");
	ImageIcon icon2 = new ImageIcon("../mine2.png");
	ImageIcon icon3 = new ImageIcon("../mine3.png");
	ImageIcon icon4 = new ImageIcon("../mine4.png");
	ImageIcon icon5 = new ImageIcon("../mine5.png");
	ImageIcon icon6 = new ImageIcon("../mine6.png");
	ImageIcon icon7 = new ImageIcon("../mine7.png");
	ImageIcon icon8 = new ImageIcon("../mine8.png");
	ImageIcon icon9 = new ImageIcon("../mine9.png");
	ImageIcon icon_mine = new ImageIcon("mine.png");
	ImageIcon icon_reset = new ImageIcon("../reset.png");
	ImageIcon icon_flag = new ImageIcon("../flag.png");
	ImageIcon icon_flagx = new ImageIcon("../flagx.png");
	ImageIcon logo = new ImageIcon("../logo.png");
	
public Mine() {
	super("지뢰찾기");
	setResizable(true);
	
	this.setIconImage(logo.getImage());
	
	if (level == 0) level = 1;
	if (level == 1) {
		menu.add(new JMenuItem("새로 시작하기")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Mine();
			}
		});
		menu.addSeparator();
		menu.add(new JMenuItem("초급")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Mine();
			}
		});
		menu.add(new JMenuItem("중급")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				level = 2;
				dispose();
				new Mine();
			}
		});
		menu.add(new JMenuItem("고급")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				level = 3;
				dispose();
				new Mine();
			}
		});
		menu.addSeparator();
		menu.add(new JMenuItem("종료")).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
			
		mb.add(menu);
		this.setJMenuBar(mb);
		this.setLayout(new BorderLayout());
		add(jp);
		add(jp2, "North");
		jp2.add(reset);
		reset.setIcon(icon_reset);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "'리셋'되었습니다.");
				dispose();
				new Mine();
			}
		});
		
		while (true) {
			int i, j = 0;
			for (int count = 0; count < 10; ) {
				i = rnd.nextInt(10);
				j = rnd.nextInt(10);
				
				if (mine[i][j] != -1) {
					mine[i][j] = -1;
					count++;
				}					
			}
			break;
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mine[i][j] == 0) {
					jb[i][j] = new JButton();
					jb[i][j].setPreferredSize(new Dimension(20,20));
					jb[i][j].addActionListener(new buttonListener());
					jb[i][j].addMouseListener(new clickListener());
					jb[i][j].setBackground(Color.gray);
					jp.add(jb[i][j]);
				}
				else if (mine[i][j] != 0) {
					jb[i][j] = new JButton();
					jb[i][j].setPreferredSize(new Dimension(20,20));
					jb[i][j].addActionListener(new mineListener());
					jb[i][j].addMouseListener(new clickListener());
					jb[i][j].setBackground(Color.gray);
					jp.add(jb[i][j]);
				}					
			}
		}
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					int count = 0;
					if (mine[i][j] != -1) {
						if (i >= 0 && j >= 0 && i <= 9 && j <= 9) {
							if (i-1 >= 0 && j-1 >= 0) 
								if (mine[i-1][j-1] == -1) count++;
							if (i-1 >= 0) 
								if (mine[i-1][j] == -1) count++;
							if (i-1 >= 0 && j+1 <= 9) 
								if (mine[i-1][j+1] == -1) count++;
							if (j-1 >= 0) 
								if (mine[i][j-1] == -1) count++;
							if (j+1 <= 9) 
								if (mine[i][j+1] == -1) count++;
							if (i+1 <= 9 && j-1 >= 0) 
								if (mine[i+1][j-1] == -1) count++;
							if (i+1 <= 9) 
								if (mine[i+1][j] == -1) count++;
							if (i+1 <= 9 && j+1 <= 9) 
								if (mine[i+1][j+1] == -1) count++;
						}
						mine[i][j] = count;
					}
					count = 0;
				}
			}
			ending = false;
			setVisible(true);
			setSize(280,365);
			setResizable(false);
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
			
			
			
			
		if (level == 2) {
			setResizable(true);
			JMenuBar mb = new JMenuBar();
			JMenu menu = new JMenu("파일");
				
			menu.add(new JMenuItem("새로 시작하기")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Mine();
				}
			});
			menu.addSeparator();
			menu.add(new JMenuItem("초급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					level = 1;
					dispose();
					new Mine();
				}
			});
			menu.add(new JMenuItem("중급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Mine();
				}
			});
			menu.add(new JMenuItem("고급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					level = 3;
					dispose();
					new Mine();
				}
			});
			menu.addSeparator();
			menu.add(new JMenuItem("종료")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mb.add(menu);
				
			this.setJMenuBar(mb);				
			this.setLayout(new BorderLayout());
			add(jp);
			add(jp2, "North");
			jp2.add(reset);
			
			reset.setIcon(icon_reset);
			reset.setBorderPainted(false);
			reset.setContentAreaFilled(false);
			reset.setFocusPainted(false);
			reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "'리셋'되었습니다.");
					dispose();
					new Mine();
				}
			});
			
			while (true) {
				int i, j = 0;
				for (int count = 0; count < 30; ) {
					i = rnd.nextInt(15);
					j = rnd.nextInt(15);
					
					if (mine2[i][j] != -1) {
						mine2[i][j] = -1;
						count++;
					}					
				}
				break;
			}
			
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (mine2[i][j] == 0) {
						jb2[i][j] = new JButton();
						jb2[i][j].setPreferredSize(new Dimension(20,20));
						jb2[i][j].addActionListener(new buttonListener());
						jb2[i][j].addMouseListener(new clickListener());
						jb2[i][j].setBackground(Color.gray);
						jp.add(jb2[i][j]);
					}
					else if (mine2[i][j] != 0) {
						jb2[i][j] = new JButton();
						jb2[i][j].setPreferredSize(new Dimension(20,20));
						jb2[i][j].addActionListener(new mineListener());
						jb2[i][j].addMouseListener(new clickListener());
						jb2[i][j].setBackground(Color.gray);
						jp.add(jb2[i][j]);
					}					
				}
			}
				
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					int count = 0;
					if (mine2[i][j] != -1) {
						if (i >= 0 && j >= 0 && i <= 14 && j <= 14) {
							if (i-1 >= 0 && j-1 >= 0) 
								if (mine2[i-1][j-1] == -1) count++;
							if (i-1 >= 0) 
								if (mine2[i-1][j] == -1) count++;
							if (i-1 >= 0 && j+1 <= 14) 
								if (mine2[i-1][j+1] == -1) count++;
							if (j-1 >= 0) 
								if (mine2[i][j-1] == -1) count++;
							if (j+1 <= 14) 
								if (mine2[i][j+1] == -1) count++;
							if (i+1 <= 14 && j-1 >= 0) 
								if (mine2[i+1][j-1] == -1) count++;
							if (i+1 <= 14) 
								if (mine2[i+1][j] == -1) count++;
							if (i+1 <= 14 && j+1 <= 14) 
								if (mine2[i+1][j+1] == -1) count++;
						}
						mine2[i][j] = count;
					}
					count = 0;
				}
			}
			ending = false;
			setVisible(true);
			setSize(400,490);
			setResizable(false);
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
				
				
				
				
		if (level == 3) {
			setResizable(true);
			JMenuBar mb = new JMenuBar();
			JMenu menu = new JMenu("파일");
			
			menu.add(new JMenuItem("새로 시작하기")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Mine();
				}
			});
			menu.addSeparator();
			menu.add(new JMenuItem("초급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					level = 1;
					dispose();
					new Mine();
				}
			});
			menu.add(new JMenuItem("중급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					level = 2;
					dispose();
					new Mine();
				}
			});
			menu.add(new JMenuItem("고급")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Mine();
				}
			});
			menu.addSeparator();
			menu.add(new JMenuItem("종료")).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mb.add(menu);
			
			this.setJMenuBar(mb);				
			this.setLayout(new BorderLayout());
			add(jp);
			add(jp2, "North");
			jp2.add(reset);
					
			reset.setIcon(icon_reset);
			reset.setBorderPainted(false);
			reset.setContentAreaFilled(false);
			reset.setFocusPainted(false);
			reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "'리셋'되었습니다.");
					dispose();
					new Mine();
				}
			});
			
			while (true) {
				int i, j = 0;
				for (int count = 0; count < 40; ) {
					i = rnd.nextInt(20);
					j = rnd.nextInt(20);
					
					if (mine3[i][j] != -1) {
						mine3[i][j] = -1;
						count++;
					}					
				}
				break;
			}
			
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					if (mine3[i][j] == 0) {
						jb3[i][j] = new JButton();
						jb3[i][j].setPreferredSize(new Dimension(20,20));
						jb3[i][j].addActionListener(new buttonListener());
						jb3[i][j].addMouseListener(new clickListener());
						jb3[i][j].setBackground(Color.gray);
						jp.add(jb3[i][j]);
					}
					else if (mine3[i][j] != 0) {
						jb3[i][j] = new JButton();
						jb3[i][j].setPreferredSize(new Dimension(20,20));
						jb3[i][j].addActionListener(new mineListener());
						jb3[i][j].addMouseListener(new clickListener());
						jb3[i][j].setBackground(Color.gray);
						jp.add(jb3[i][j]);
					}					
				}
			}
			
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					int count = 0;
					if (mine3[i][j] != -1) {
						if (i >= 0 && j >= 0 && i <= 19 && j <= 19) {
							if (i-1 >= 0 && j-1 >= 0) 
								if (mine3[i-1][j-1] == -1) count++;
							if (i-1 >= 0) 
								if (mine3[i-1][j] == -1) count++;
							if (i-1 >= 0 && j+1 <= 19) 
								if (mine3[i-1][j+1] == -1) count++;
							if (j-1 >= 0) 
								if (mine3[i][j-1] == -1) count++;
							if (j+1 <= 19) 
								if (mine3[i][j+1] == -1) count++;
							if (i+1 <= 19 && j-1 >= 0) 
								if (mine3[i+1][j-1] == -1) count++;
							if (i+1 <= 19) 
								if (mine3[i+1][j] == -1) count++;
							if (i+1 <= 19 && j+1 <= 19) 
								if (mine3[i+1][j+1] == -1) count++;
						}
						mine3[i][j] = count;
					}
					count = 0;
				}
			}
			ending = false;
			setVisible(true);
			setSize(520,615);
			setResizable(false);
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
				
private class buttonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (level == 1) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (e.getSource() == jb[i][j] && jb[i][j].getBackground() == Color.darkGray) {
						check(i, j);
					}
				}
			}
		}
						
						
		if (level == 2) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (e.getSource() == jb2[i][j] && jb2[i][j].getBackground() == Color.darkGray) {
						check(i, j);
					}
				}
			}
		}
						
						
		if (level == 3) {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					if (e.getSource() == jb3[i][j] && jb3[i][j].getBackground() == Color.darkGray) {
						check(i, j);
					}
				}
			}
		}
	}
}
				
private class mineListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (ending == false) JOptionPane.showMessageDialog(null, "지뢰 클릭!"); {
			
			if (level == 1) {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (mine[i][j] == -1) {
							jb[i][j].setBackground(Color.darkGray);
							jb[i][j].setIcon(icon_mine);
							ending = true;
						}
						else if (mine[i][j] != -1 && jb[i][j].getBackground() != Color.darkGray) {
							jb[i][j].setBorderPainted(false);
							jb[i][j].setBackground(Color.darkGray);
							jb[i][j].setIcon(icon_flagx);
						}
						else 
							jb[i][j].setBorderPainted(false);
						
						jb[i][j].setBorderPainted(false);
						jb[i][j].setBackground(Color.darkGray);									
					}
				}
			}
			
			
			if (level == 2) {
				for (int i = 0; i < 15; i++) {
					for (int j = 0; j < 15; j++) {
						if (mine2[i][j] == -1) {
							jb2[i][j].setBackground(Color.darkGray);
							jb2[i][j].setIcon(icon_mine);
							ending = true;
						}
						else if (mine2[i][j] != -1 && jb2[i][j].getBackground() != Color.darkGray) {
							jb2[i][j].setBorderPainted(false);
							jb2[i][j].setBackground(Color.darkGray);
							jb2[i][j].setIcon(icon_flagx);
						}
						else 
							jb2[i][j].setBorderPainted(false);
						
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setBackground(Color.darkGray);									
					}
				}
			}
			
			
			if (level == 3) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (mine3[i][j] == -1) {
							jb3[i][j].setBackground(Color.darkGray);
							jb3[i][j].setIcon(icon_mine);
							ending = true;
						}
						else if (mine3[i][j] != -1 && jb3[i][j].getBackground() != Color.darkGray) {
							jb3[i][j].setBorderPainted(false);
							jb3[i][j].setBackground(Color.darkGray);
							jb3[i][j].setIcon(icon_flagx);
						}
						else 
							jb3[i][j].setBorderPainted(false);
												
				}
			}
		}
		
	}
		
	}

}
			
			
private class clickListener implements MouseListener {
	
	public void mouseClicked(MouseEvent e) {
	
	}
	
	public void mousePressed(MouseEvent e) {
		if (level == 1) {
			if (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (e.getSource() == jb[i][j] && jb[i][j].isBorderPainted() == true && jb[i][j].getBackground() == Color.darkGray) {
							jb[i][j].setIcon(icon_flag);
							jb[i][j].setBackground(Color.GRAY);
						} else if (e.getSource() == jb[i][j] && jb[i][j].isBorderPainted() == true && jb[i][j].getBackground() == Color.GRAY) {
							jb[i][j].setIcon(null);
							jb[i][j].setBackground(Color.darkGray);
						}
					}
				}
			}
		}
		
		if (level == 2) {
			if (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
				for (int i = 0; i < 15; i++) {
					for (int j = 0; j < 15; j++) {
						if (e.getSource() == jb2[i][j] && jb2[i][j].isBorderPainted() == true && jb2[i][j].getBackground() == Color.darkGray) {
							jb2[i][j].setIcon(icon_flag);
							jb2[i][j].setBackground(Color.GRAY);
						} else if (e.getSource() == jb2[i][j] && jb2[i][j].isBorderPainted() == true && jb2[i][j].getBackground() == Color.GRAY) {
							jb2[i][j].setIcon(null);
							jb2[i][j].setBackground(Color.darkGray);
						}
					}
				}
			}
		}
		
		if (level == 3) {
			if (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (e.getSource() == jb3[i][j] && jb3[i][j].isBorderPainted() == true && jb3[i][j].getBackground() == Color.darkGray) {
							jb3[i][j].setIcon(icon_flag);
							jb3[i][j].setBackground(Color.GRAY);
						} else if (e.getSource() == jb3[i][j] && jb3[i][j].isBorderPainted() == true && jb3[i][j].getBackground() == Color.GRAY) {
							jb3[i][j].setIcon(null);
							jb3[i][j].setBackground(Color.darkGray);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}

private void check(int i, int j) {
	if (level == 1) {
		try {
			if (mine[i][j] == 0 && jb[i][j].isBorderPainted() == true) {
				jb[i][j].setIcon(icon0);
				jb[i][j].setBorderPainted(false);
				jb[i][j].setContentAreaFilled(false);
				jb[i][j].setFocusPainted(false);
				check(i+1, j);
				check(i-1, j);
				check(i, j+1);
				check(i, j-1);
				check(i+1, j+1);
				check(i-1, j-1);
				check(i+1, j-1);
				check(i-1, j+1);
			} else {
				if (jb[i][j].isBorderPainted() == true) {
					
					switch (mine[i][j]) {
					case 1:
						jb[i][j].setIcon(icon1);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 2:
						jb[i][j].setIcon(icon2);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 3:
						jb[i][j].setIcon(icon3);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 4:
						jb[i][j].setIcon(icon4);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 5:
						jb[i][j].setIcon(icon5);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 6:
						jb[i][j].setIcon(icon6);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 7:
						jb[i][j].setIcon(icon7);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 8:
						jb[i][j].setIcon(icon8);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					case 9:
						jb[i][j].setIcon(icon9);
						jb[i][j].setBorderPainted(false);
						jb[i][j].setContentAreaFilled(false);
						jb[i][j].setFocusPainted(false);
						break;
					}
				}							
			}
			victory();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}

	}
	
	
	if (level == 2) {
		try {
			if (mine2[i][j] == 0 && jb2[i][j].isBorderPainted() == true) {
				jb2[i][j].setIcon(icon0);
				jb2[i][j].setBorderPainted(false);
				jb2[i][j].setContentAreaFilled(false);
				jb2[i][j].setFocusPainted(false);
				check(i+1, j);
				check(i-1, j);
				check(i, j+1);
				check(i, j-1);
				check(i+1, j+1);
				check(i-1, j-1);
				check(i+1, j-1);
				check(i-1, j+1);
			} else {
				if (jb2[i][j].isBorderPainted() == true) {
					switch (mine2[i][j]) {
					case 1:
						jb2[i][j].setIcon(icon1);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 2:
					jb2[i][j].setIcon(icon2);
					jb2[i][j].setBorderPainted(false);
					jb2[i][j].setContentAreaFilled(false);
					jb2[i][j].setFocusPainted(false);
					break;
					case 3:
						jb2[i][j].setIcon(icon3);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 4:
						jb2[i][j].setIcon(icon4);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 5:
						jb2[i][j].setIcon(icon5);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 6:
						jb2[i][j].setIcon(icon6);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 7:
						jb2[i][j].setIcon(icon7);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 8:
						jb2[i][j].setIcon(icon8);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					case 9:
						jb2[i][j].setIcon(icon9);
						jb2[i][j].setBorderPainted(false);
						jb2[i][j].setContentAreaFilled(false);
						jb2[i][j].setFocusPainted(false);
						break;
					}
				}							
			}
			victory();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}

	}
	
	
	
	if (level == 3) {
		try {
			if (mine3[i][j] == 0 && jb3[i][j].isBorderPainted() == true) {
				jb3[i][j].setIcon(icon0);
				jb3[i][j].setBorderPainted(false);
				jb3[i][j].setContentAreaFilled(false);
				jb3[i][j].setFocusPainted(false);
				check(i+1, j);
				check(i-1, j);
				check(i, j+1);
				check(i, j-1);
				check(i+1, j+1);
				check(i-1, j-1);
				check(i+1, j-1);
				check(i-1, j+1);
			} else {
				if (jb3[i][j].isBorderPainted() == true) {
					switch (mine3[i][j]) {
					case 1:
						jb3[i][j].setIcon(icon1);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 2:
					jb3[i][j].setIcon(icon2);
					jb3[i][j].setBorderPainted(false);
					jb3[i][j].setContentAreaFilled(false);
					jb3[i][j].setFocusPainted(false);
					break;
					case 3:
						jb3[i][j].setIcon(icon3);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 4:
						jb3[i][j].setIcon(icon4);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 5:
						jb3[i][j].setIcon(icon5);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 6:
						jb3[i][j].setIcon(icon6);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 7:
						jb3[i][j].setIcon(icon7);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 8:
						jb3[i][j].setIcon(icon8);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					case 9:
						jb3[i][j].setIcon(icon9);
						jb3[i][j].setBorderPainted(false);
						jb3[i][j].setContentAreaFilled(false);
						jb3[i][j].setFocusPainted(false);
						break;
					}
				}							
			}
			victory();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}

	}
	
}

private void victory() {
	if (level == 1) {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (this.mine[i][j] != -1 && this.jb[i][j].isBorderPainted() == false) {
					count++;
				}
			}
		}
		if (count >= 90) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (jb[i][j].isBorderPainted() == true) {
						jb[i][j].setBorderPainted(false);
						if (mine[i][j] == -1)
							jb[i][j].setIcon(icon_mine);
					}
				}
			}
			if (ending == false) {
				JOptionPane.showMessageDialog(null, "승리");
				ending = true;
			}
		}
	}
	
	
	if (level == 2) {
		int count = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (this.mine2[i][j] != -1 && this.jb2[i][j].isBorderPainted() == false) {
					count++;
				}
			}
		}
		if (count >= 195) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (jb2[i][j].isBorderPainted() == true) {
						jb2[i][j].setBorderPainted(false);
						if (mine2[i][j] == -1)
							jb2[i][j].setIcon(icon_mine);
					}
				}
			}
			if (ending == false) {
				JOptionPane.showMessageDialog(null, "승리");
				ending = true;
			}
		}
	}
	
	
	if (level == 3) {
		int count = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (this.mine3[i][j] != -1 && this.jb3[i][j].isBorderPainted() == false) {
					count++;
				}
			}
		}
		if (count >= 360) {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					if (jb3[i][j].isBorderPainted() == true) {
						jb3[i][j].setBorderPainted(false);
						if (mine3[i][j] == -1)
							jb3[i][j].setIcon(icon_mine);
					}
				}
			}
			if (ending == false) {
				JOptionPane.showMessageDialog(null, "승리");
				ending = true;
			}
		}
	}

}
	
	
	public static void main(String[] args) {
		new Mine();
	}
	static int level = 0;
	static boolean ending = false;

}
