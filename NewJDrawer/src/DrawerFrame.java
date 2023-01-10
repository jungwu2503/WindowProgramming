import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class DrawerFrame extends JFrame {
	
	static class ZoomBox extends JComboBox implements ActionListener {
		DrawerView canvas;
		static String[] size = { "100", "80", "50" };
			
		ZoomBox(DrawerView canvas) {
			super(size);
			this.canvas = canvas;
			setMaximumSize(new Dimension(1500,200));
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox) e.getSource();
			String ratio = (String)box.getSelectedItem();
			canvas.zoom(Integer.parseInt(ratio));
		}
		
	}
	
	static class ThicknessBox extends JComboBox implements ActionListener {
		DrawerView canvas;
		//static String[] thick = { "1", "5", "10" };
		static ImageIcon thinLine = new ImageIcon("thinLine.gif");
		static ImageIcon normalLine = new ImageIcon("normalLine.gif");
		static ImageIcon thickLine = new ImageIcon("thickLine.gif");
		static ImageIcon[] thick = { thinLine, normalLine, thickLine }; 

		ThicknessBox(DrawerView canvas) {
			super(thick);
			this.canvas = canvas;
			setMaximumSize(new Dimension(50, 200));
			//setPreferredSize(new Dimension(50,20));
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox) e.getSource();
			//String thickness = (String)box.getSelectedItem();
			ImageIcon thicknessImage = (ImageIcon)box.getSelectedItem();
			//System.out.println(thicknessImage);
			int thickness = 1;
			if (thicknessImage == null) {
				thickness = 1;
			} else if (thicknessImage == thinLine) {
				thickness = 1;
			} else if (thicknessImage == normalLine) {
				thickness = 5;
			} else if (thicknessImage == thickLine) {
				thickness = 10;
			}
			
			//canvas.setThickness(Integer.parseInt(thickness));
			canvas.setThickness(thickness);
		}
		
	} 
	
	static class ColorBox extends JComboBox implements ActionListener {
		DrawerView canvas;
		static String[] color = { "black", "red", "orange", "yellow", "green", "blue", "purple" };
		//static String sBlack = "black";
		
		/*@Override
		public void setForeground(Color fg) {
			//super.setForeground(fg);
			for (int i = 0; i < 7; i++) {
				color[i]
			}
		}*/
		
		ColorBox(DrawerView canvas) {
			super(color);
			this.canvas = canvas;
			setMaximumSize(new Dimension(1500, 200));
			addActionListener(this);
			
			//color[0].setForeground(color[0]);
		}
		
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox) e.getSource();
			String color = (String)box.getSelectedItem();
			
			canvas.setColor(color);
		}		
	}
	
	static class PrintableView implements Printable {
		DrawerView canvas;
		String fileName;
		PrintableView(DrawerView canvas, String fileName) {
			this.canvas = canvas;
			this.fileName = fileName;
		}
		public int print(Graphics g, PageFormat format, int pagenum) {
			if (pagenum > 0) return Printable.NO_SUCH_PAGE;
			
			Graphics2D g2 = (Graphics2D)g;
			double pageX = format.getImageableX()+1;
			double pageY = format.getImageableY()+1;
			g2.translate(pageX, pageY);
			
			int pageWidth = (int)format.getImageableWidth()-2;
			int pageHeight = (int)format.getImageableHeight()-2;
			
			g2.drawRect(-1, -1, pageWidth+2, pageHeight+2);
			
			g2.setClip(0,0,pageWidth,pageHeight);
			g2.scale(0.5, 0.5);
			
			canvas.paint(g);
			
			g2.scale(2.0, 2.0);
			g2.drawString(fileName, 0, pageHeight);
			
			return Printable.PAGE_EXISTS;
		}
	}
	
	DrawerView canvas;
	StatusBar statusBar;
	FigureDialog dialog;
	TableDialog tableDialog;
	TreeDialog treeDialog;
	String fileName = "noname.jdr";
	
	public void writePosition(String s) {
		// delegation
		statusBar.writePosition(s);
	}
	
	public void writeFigureType(String s) {
		statusBar.writeFigureType(s); 
	}
	
	public void doOpen() {
		JFileChooser chooser = 
				new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("JDrawer file", "jdr"));
		int value = chooser.showOpenDialog(null);
		if (value != JFileChooser.APPROVE_OPTION) return;
		fileName = chooser.getSelectedFile().getPath();
		canvas.doOpen(fileName);
		setTitle("Drawer - [" + fileName + "]");
	}
	
	public void doSaveAs() {
		JFileChooser chooser = 
				new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setFileFilter(new FileNameExtensionFilter("JDrawer file", "jdr"));
		int value = chooser.showSaveDialog(null);
		if (value != JFileChooser.APPROVE_OPTION) return;
		fileName = chooser.getSelectedFile().getPath();
		if (fileName.endsWith(".jdr") == false)
			fileName = fileName + ".jdr";
		setTitle("Drawer - [" + fileName + "]");
		canvas.doSave(fileName);
	}
	
	public void doPrint() {
		PrinterJob job = PrinterJob.getPrinterJob();
		
		PageFormat page = job.defaultPage();
		page.setOrientation(PageFormat.LANDSCAPE);
		
		Printable printable = new PrintableView(canvas,fileName);
		job.setPrintable(printable,page);	
		
		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException ex) {
				JOptionPane.showMessageDialog(this, ex.toString(), "PrinterException", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	DrawerFrame() {
		setTitle("Drawer - [noname.jdr]");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setSize(screenWidth*2/3, screenHeight*2/3);
		setLocation(screenWidth/6, screenHeight/6);
		Image img = tk.getImage("England.png");
		setIconImage(img);
		
		Container container = this.getContentPane();		
		statusBar = new StatusBar();
		container.add(statusBar,"South");
		canvas = new DrawerView(this);
		JScrollPane sp = new JScrollPane(canvas);
		container.add(sp,"Center");
		//sp.getVerticalScrollBar().setBlockIncrement(100);
		
		sp.registerKeyboardAction(
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JScrollBar scrollBar = sp.getVerticalScrollBar();
						scrollBar.setValue(scrollBar.getValue() + scrollBar.getBlockIncrement());
					}
				}
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN
				, 0), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		sp.registerKeyboardAction(
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JScrollBar scrollBar = sp.getVerticalScrollBar();
						scrollBar.setValue(scrollBar.getValue() - scrollBar.getBlockIncrement());
					}
				}
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP
				, 0), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		sp.registerKeyboardAction(
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JScrollBar scrollBar = sp.getHorizontalScrollBar();
						scrollBar.setValue(scrollBar.getValue() + scrollBar.getBlockIncrement());
					}
				}
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN
				, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		sp.registerKeyboardAction(
				new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						JScrollBar scrollBar = sp.getHorizontalScrollBar();
						scrollBar.setValue(scrollBar.getValue() - scrollBar.getBlockIncrement());
					}
				}
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP
				, InputEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		sp.registerKeyboardAction((evt) -> canvas.increaseHeight()				
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN
				, InputEvent.ALT_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		sp.registerKeyboardAction((evt) -> canvas.increaseWidth()
				, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP
				, InputEvent.ALT_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW
		);
		
		JToolBar selectToolBar = new JToolBar();
		selectToolBar.add(canvas.getPointAction());
		selectToolBar.add(canvas.getStarAction());
		selectToolBar.add(canvas.getBoxAction());
		selectToolBar.add(canvas.getIsoscelesAction());
		selectToolBar.add(canvas.getLineAction());
		selectToolBar.add(canvas.getRegularTriangleAction());
		selectToolBar.add(canvas.getCircleAction());
		selectToolBar.add(canvas.getSaturnAction());
		selectToolBar.add(canvas.getTVAction());
		selectToolBar.add(canvas.getKiteAction());
		selectToolBar.add(canvas.getTextAction());
		//selectToolBar.add(new JSeparator());
		selectToolBar.add(new ZoomBox(canvas));
		selectToolBar.add(new ThicknessBox(canvas));
		selectToolBar.add(new ColorBox(canvas));
		selectToolBar.add(javax.swing.Box.createGlue());
		container.add(selectToolBar,BorderLayout.NORTH);
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension sz = canvas.getSize();
				String s = "" + sz.width + " X " + sz.height + " px";
				statusBar.writeSize(s);
			}
		});
		
		JMenuBar menus = new JMenuBar();
		setJMenuBar(menus);
		
		JMenu fileMenu = new JMenu("파일(F)");
		menus.add(fileMenu);
		
		JMenuItem newFile = new JMenuItem("새 파일(N)");
		fileMenu.add(newFile);
		newFile.setMnemonic('N');
		newFile.setIcon(new ImageIcon("new.gif"));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
								InputEvent.CTRL_DOWN_MASK));
		newFile.addActionListener( (e) -> canvas.doFileNew());
		
		JMenuItem openFile = new JMenuItem("열기(O)");
		fileMenu.add(openFile);
		openFile.setMnemonic('O');
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
								InputEvent.CTRL_DOWN_MASK));
		openFile.setIcon(new ImageIcon("open.gif"));
		openFile.addActionListener( (e) ->
			doOpen()		
		);
		
		JMenuItem saveFile = new JMenuItem("저장(S)");
		fileMenu.add(saveFile);
		saveFile.setMnemonic('S');
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
								InputEvent.CTRL_DOWN_MASK));
		saveFile.setIcon(new ImageIcon("save.gif"));
		saveFile.addActionListener((e) -> canvas.doSave(fileName));
		
		JMenuItem anotherFile = new JMenuItem("다른 이름으로 저장(A)");
		fileMenu.add(anotherFile);
		anotherFile.addActionListener((e) -> doSaveAs());
		
		fileMenu.addSeparator();
		
		JMenuItem printFile = new JMenuItem("프린트(p)");
		fileMenu.add(printFile);
		printFile.addActionListener((e) -> doPrint());

		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem("종료(X)");
		fileMenu.add(exit);
		exit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu figureMenu = new JMenu("그림(D)");
		menus.add(figureMenu);
		
		JMenuItem figurePoint = new JMenuItem(canvas.getPointAction());
		figureMenu.add(figurePoint);

		JMenuItem figureStar = new JMenuItem(canvas.getStarAction());
		figureMenu.add(figureStar);
		
		JMenuItem figureBox = new JMenuItem(canvas.getBoxAction());
		figureMenu.add(figureBox);

		JMenuItem figureIsosceles = new JMenuItem(canvas.getIsoscelesAction());
		figureMenu.add(figureIsosceles);

		JMenuItem figureLine = new JMenuItem(canvas.getLineAction());
		figureMenu.add(figureLine);
		
		JMenuItem figureRegularTriangle = new JMenuItem(canvas.getRegularTriangleAction());
		figureMenu.add(figureRegularTriangle);

		JMenuItem figureCircle = new JMenuItem(canvas.getCircleAction());
		figureMenu.add(figureCircle);

		JMenuItem figureTV = new JMenuItem(canvas.getTVAction());
		figureMenu.add(figureTV);
		
		JMenuItem figureKite = new JMenuItem(canvas.getKiteAction());
		figureMenu.add(figureKite);
		
		JMenuItem figureText = new JMenuItem(canvas.getTextAction());
		figureMenu.add(figureText);
		
		JMenu toolMenu = new JMenu("도구(T)");
		menus.add(toolMenu);
		
		JMenuItem modalTool = new JMenuItem("Modal (M)");
		toolMenu.add(modalTool);
		modalTool.addActionListener( (e) -> {
					if (dialog == null) {
						dialog = new FigureDialog("Figure Dialog", canvas);
						dialog.setModal(true);
					}
					dialog.setVisible(true);
				});
		
		JMenuItem modalessTool = new JMenuItem("Modaless (S)");
		toolMenu.add(modalessTool);
		modalessTool.addActionListener( (e) -> {
					if (dialog == null) {
						dialog = new FigureDialog("Figure Dialog", canvas);
						dialog.setModal(false);
					}
					dialog.setVisible(true);
				});
		
		JMenuItem tableTool = new JMenuItem("Table (T)");
		toolMenu.add(tableTool);
		tableTool.addActionListener( (e) -> {
					if (tableDialog == null) {
						tableDialog = new TableDialog("Table Dialog", canvas);
						tableDialog.setModal(true);
					}					
					tableDialog.setVisible(true);
				});

		JMenuItem treeTool = new JMenuItem("Tree (R)");
		toolMenu.add(treeTool);
		treeTool.addActionListener( (e) -> {
					if (treeDialog == null) {
						treeDialog = new TreeDialog("Tree Dialog", canvas);
						treeDialog.setModal(true);
					}					
					treeDialog.setVisible(true);
				});
		
		JMenu zoomMenu = new JMenu("zoom");
		toolMenu.add(zoomMenu);
		
		JMenu rulerMenu = new JMenu("Ruler(R)");
		menus.add(rulerMenu);
		
		/*JMenuItem rulerOn = new JMenuItem("Ruler-On");
		rulerMenu.add(rulerOn);
		rulerOn.addActionListener( (e) -> {
					canvas.rulerOn();
				});*/
		JCheckBoxMenuItem rulerOn = new JCheckBoxMenuItem("Ruler-On");
		rulerMenu.add(rulerOn);
		rulerOn.addActionListener( (e) -> {
					if(rulerOn.getState())
						canvas.rulerOn();
					else canvas.rulerOff();
				});
		
		/*JMenuItem rulerOff = new JMenuItem("Ruler-Off");
		rulerMenu.add(rulerOff);
		rulerOff.addActionListener( (e) -> {
					canvas.rulerOff();
				});*/
		
		JMenuItem zoom100 = new JMenuItem("100%");
		zoomMenu.add(zoom100);
		zoom100.addActionListener((e) -> canvas.zoom(100));
		
		JMenuItem zoom80 = new JMenuItem("80%");
		zoomMenu.add(zoom80);
		zoom80.addActionListener((e) -> canvas.zoom(80));
		
		JMenuItem zoom50 = new JMenuItem("50%");
		zoomMenu.add(zoom50);
		zoom50.addActionListener((e) -> canvas.zoom(50));
		
		
		JMenu helpMenu = new JMenu("도움말(H)");
		menus.add(helpMenu);
				
		JMenuItem infoHelp = new JMenuItem("Drawer 정보(I)");
		helpMenu.add(infoHelp);
		infoHelp.addActionListener( (e) ->
					JOptionPane.showMessageDialog(null,"Author: Kim\r\nCompany: Kims Company","Information",JOptionPane.INFORMATION_MESSAGE)
				);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
}