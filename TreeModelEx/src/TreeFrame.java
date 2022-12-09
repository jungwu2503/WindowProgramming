import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

class MainPanel extends JPanel {
	JTree tree;
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
	JSplitPane splitPane;
	JPanel funcPanel;
	MainPanel() {		
		funcPanel = new JPanel();
		funcPanel.setLayout(new BoxLayout(funcPanel, BoxLayout.LINE_AXIS));
		
		model = new MyTreeModel();
		DefaultTreeModel tmpModel = new DefaultTreeModel(new DefaultMutableTreeNode("Please set the root node"));
		tree = new JTree(tmpModel);
		sp = new JScrollPane(tree);
		sp.setPreferredSize(new Dimension(600/3*2,800/6*5));
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, funcPanel);
		
		rootField = new JTextField(10);
		parentField = new JTextField(5);
		parentLabel = new JLabel("Parent");
		parentField.setEnabled(false);
		childField = new JTextField(5);		
		childLabel = new JLabel("Child");
		childField.setEnabled(false);
		
		setRootBtn = new JButton("Root »ý¼º");
		setRootBtn.addActionListener((e) -> {
			if (rootField.getText() == null) return;
			model.setRoot(rootField.getText());
			tree.setModel(model);
			
			rootField.setEditable(false);
			setRootBtn.setEnabled(false);	
			parentField.setEnabled(true);
			childField.setEnabled(true);
		});
		
		JPanel p1 = new JPanel();
		p1.add(rootField);
		p1.add(setRootBtn);
		funcPanel.add(p1);
				
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
			
			addBtnAction(parent, child);
		});
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener((e) -> {
			TreePath selectedPath = tree.getSelectionPath();
			model.remove(selectedPath);
			//tree.removeSelectionPath(selectedPath);
			/*
			String parent = parentField.getText();
			String child = childField.getText();
			
			model.deleteChild(parent, child);*/
			tree.updateUI();
		});
		
		childField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String parentText = parentField.getText();
				String childText = childField.getText();
				if (parentText.isEmpty() || childText.isEmpty()) return;
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addBtnAction(parentText, childText);
				}
			}
		});
		
		JPanel p3 = new JPanel();
		p3.add(addBtn);
		p3.add(deleteBtn);
		funcPanel.add(p3);
		
		MyTreeCellRenderer renderer = new MyTreeCellRenderer();
		tree.setCellRenderer(renderer);
		
		add(splitPane);
	}
	
	public void addBtnAction(String parent, String child) {
		TreePath path = model.addNewChild(parent,child);
		tree.expandPath(path);
		parentField.setText("");
		childField.setText("");
		tree.updateUI();
	}
	
	public void serialLoad(String fileName) {
		model.serialLoad(fileName);
		tree.setModel(model);
		rootField.setEditable(false);
		setRootBtn.setEnabled(false);	
		tree.updateUI();
	}
	
	public MyTreeModel getModel() {
		return model;
	}
	
	public void textLoad(String fileName) {
		model.txtLoad(fileName);
		tree.setModel(model);
		rootField.setEditable(false);
		setRootBtn.setEnabled(false);	
		tree.updateUI();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(600,800);
	}
}

public class TreeFrame extends JFrame {
	MainPanel mainPanel;
	static int WIDTH = 600;
	static int HEIGHT = 800;
	JMenuBar menuBar;
	JMenu menu;
	JMenu serMenu;
	JMenu txtMenu;
	JMenuItem serSave;
	JMenuItem serLoad;
	JMenuItem txtSave;
	JMenuItem txtLoad;

	TreeFrame() {
		setTitle("Tree Test");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
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
		
		mainPanel = new MainPanel();
		getContentPane().add(mainPanel);
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
		
		mainPanel.serialLoad(fileName);
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
		
		mainPanel.getModel().serialSave(fileName);
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
		
		mainPanel.textLoad(fileName);
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
		
		mainPanel.getModel().txtSave(fileName);
	}
	
}
