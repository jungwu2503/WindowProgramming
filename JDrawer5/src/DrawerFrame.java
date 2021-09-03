import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawerFrame extends JFrame {
	
	DrawerView view;
	
	public DrawerFrame() {
		setTitle("Drawer");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setSize(screenWidth*2/3, screenHeight*2/3);
		setLocation(screenWidth/6, screenHeight/6);
		
		Image img = tk.getImage("ball.gif");
		setIconImage(img);
		
		Container container = this.getContentPane();
//		container.setBackground(Color.red);
		view = new DrawerView();
		container.add(view);
		
//		this.addKeyListener(new KeyAdapter() {
//			public void keyTyped(KeyEvent evt) {
//				Graphics g = getGraphics();
//				g.drawLine(0, 0, 200, 200);
//			}
//		}); 
		JMenuBar menus = new JMenuBar();
		setJMenuBar(menus);
		
		JMenu fileMenu = new JMenu("파일(F)");
		menus.add(fileMenu);
		//fileMenu.setMnemonic('F');
		
		JMenuItem newFile = new JMenuItem("새 파일(N)");
		fileMenu.add(newFile);
		newFile.setMnemonic('N');
		newFile.setIcon(new ImageIcon("new.gif"));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
										InputEvent.CTRL_MASK));
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("새 파일");
			}			
		});
		
		JMenuItem openFile = new JMenuItem("열기(O)");
		fileMenu.add(openFile);
		openFile.setMnemonic('O');
		openFile.setIcon(new ImageIcon("open.gif"));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
										InputEvent.CTRL_MASK));
		//lambda expression
		openFile.addActionListener( (e) ->	
			System.out.println("열기"));
		
		JMenuItem saveFile = new JMenuItem("저장(S)");
		fileMenu.add(saveFile);
		saveFile.setMnemonic('S');
		saveFile.setIcon(new ImageIcon("save.gif"));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
										InputEvent.CTRL_MASK));
		
		JMenuItem anotherFile = new JMenuItem("다른 이름으로 저장(A)");
		fileMenu.add(anotherFile);
		
		fileMenu.addSeparator();
		
		JMenuItem exitFile = new JMenuItem("종료(X)");
		fileMenu.add(exitFile);
		exitFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}			
		});
		
		JMenu figureMenu = new JMenu("그림(F)");
		menus.add(figureMenu);
		
		JMenuItem figureBox = new JMenuItem("Box (B)");
		figureMenu.add(figureBox);
		figureBox.addActionListener((e) -> canvas.setWhatToDraw(DrawerView.DRAW_BOX));
		
		JMenuItem figureLine = new JMenuItem("Line (B)");
		figureMenu.add(figureLine);
		figureBox.addActionListener((e) -> canvas.setWhatToDraw(DrawerView.DRAW_LINE));
		
		JMenu helpMenu = new JMenu("도움말(H)");
		menus.add(helpMenu);
		
		JMenuItem infoHelp = new JMenuItem("Drawer 정보(I)");
		helpMenu.add(infoHelp);
		infoHelp.addActionListener( (e) ->	
					{
								//JOptionPane.showMessageDialog(null,"Hello");				
								JOptionPane.showMessageDialog(null,"Author: Hong Gil-ding\r\nCompany: BUFS","Drawer 정보",JOptionPane.INFORMATION_MESSAGE);				
					});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}