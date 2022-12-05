import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

public class TreeFrame extends JFrame {
	static int WIDTH = 600;
	static int HEIGHT = 800;
	JTree tree;
	JPanel panel;
	MyTreeModel model;
	JButton setRootBtn;
	JTextField rootField;
	JLabel parentLabel;
	JTextField parentField;
	JLabel childLabel;
	JTextField childField;
	JButton addBtn;
	JButton deleteBtn;
	JScrollPane sp;
	JMenuBar menuBar;
	JMenu menu;
	JSplitPane splitPane;
	JPanel funcPanel;
	JMenu serMenu;
	JMenu txtMenu;
	JMenuItem serSave;
	JMenuItem serLoad;
	JMenuItem txtSave;
	JMenuItem txtLoad;
	
	TreeFrame() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("File");
		menuBar.add(menu);
		
		serMenu = new JMenu("Serialize");
		txtMenu = new JMenu("Text");
		
		serLoad = new JMenuItem("Load");
		serSave = new JMenuItem("Save");
		txtLoad = new JMenuItem("Load");
		txtSave = new JMenuItem("Save");

		serMenu.add(serLoad);
		serMenu.add(serSave);
		txtMenu.add(txtLoad);
		txtMenu.add(txtSave);
		serLoad.addActionListener((e) -> serialLoad());
		serSave.addActionListener((e) -> serialSave());
		txtLoad.addActionListener((e) -> textLoad());
		txtSave.addActionListener((e) -> textSave());
		
		menu.add(serMenu);
		menu.add(txtMenu);
		
		panel = new JPanel();
		funcPanel = new JPanel();
		funcPanel.setLayout(new BoxLayout(funcPanel, BoxLayout.PAGE_AXIS));
		
		model = new MyTreeModel();
		tree = new JTree();
		sp = new JScrollPane(tree);
		getContentPane().add(panel);
		//panel.add(sp);
		//panel.add(funcPanel);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, funcPanel);
		Dimension sz = getSize();
		sp.setPreferredSize(new Dimension(WIDTH/2,HEIGHT));
		panel.add(splitPane);
		
		rootField = new JTextField(10);
		
		setRootBtn = new JButton("Root »ý¼º");
		setRootBtn.addActionListener((e) -> {
			if (rootField.getText() == null) return;
			model.setRoot(rootField.getText());
			tree.setModel(model);
			
			rootField.setEditable(false);
			setRootBtn.setEnabled(false);	
		});
		
		JPanel p1 = new JPanel();
		p1.add(rootField);
		p1.add(setRootBtn);
		funcPanel.add(p1);
		
		parentField = new JTextField(5);
		
		parentLabel = new JLabel("Parent");
		
		childField = new JTextField(5);
		
		childLabel = new JLabel("Child");
		
		JPanel p2 = new JPanel();
		p2.add(parentLabel);
		p2.add(parentField);
		p2.add(childLabel);
		p2.add(childField);
		funcPanel.add(p2);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener((e) -> {
			String parent = parentField.getText();
			String child = childField.getText();
			
			TreePath path = model.addNewChild(parent, child);
			tree.expandPath(path);
			System.out.println(path);
			tree.updateUI();
			
			System.out.println(model.getTotalNodesNumber());
		});
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener((e) -> {
			String parent = parentField.getText();
			String child = childField.getText();
			
			model.deleteChild(parent, child);
			tree.updateUI();
		});
		
		JPanel p3 = new JPanel();
		p3.add(addBtn);
		p3.add(deleteBtn);
		funcPanel.add(p3);
		
		MyTreeCellRenderer renderer = new MyTreeCellRenderer();
//		renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
//		renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
//		renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
		tree.setCellRenderer(renderer);
		
		getContentPane().add(splitPane);
		
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void serialLoad() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) return;
		String fileName = chooser.getSelectedFile().getPath();
		if (fileName == null) return;
		if (fileName.length() == 0) return;
		
		model.serialLoad(fileName);
		tree.setModel(model);
		rootField.setEditable(false);
		setRootBtn.setEnabled(false);	
		tree.updateUI();
	}
	
	public void serialSave() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) return;
		String fileName = chooser.getSelectedFile().getPath();
		if (fileName == null) return;
		if (fileName.length() == 0) return;
		
		model.serialSave(fileName);
	}
	
	public void textLoad() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) return;
		String fileName = chooser.getSelectedFile().getPath();
		if (fileName == null) return;
		if (fileName.length() == 0) return;
		
		model.txtLoad(fileName);
		tree.setModel(model);
		rootField.setEditable(false);
		setRootBtn.setEnabled(false);	
		tree.updateUI();
	}
	
	public void textSave() {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) return;
		String fileName = chooser.getSelectedFile().getPath();
		if (fileName == null) return;
		if (fileName.length() == 0) return;
		
		model.txtSave(fileName);
	}
	
}
