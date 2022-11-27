import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

public class TreeFrame extends JFrame {

	JTree tree;
	JPanel panel;
	//JPanel btnPanel;
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
	//JPanel setRootPanel; 
	//JPanel setInputPanel;
	JPanel funcPanel;
	
	TreeFrame() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("File");
		menuBar.add(menu);
		
		panel = new JPanel();
		funcPanel = new JPanel();
		
		model = new MyTreeModel();
		tree = new JTree();
		sp = new JScrollPane(tree);
		//sp.setBounds(setPreferredSize(new Dimension(200,500)));
		//getContentPane().add(sp, "West");
		getContentPane().add(panel);
		panel.add(sp);
		panel.add(funcPanel);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, funcPanel);
		
		rootField = new JTextField();
		//rootField.setBounds(320, 50, 130, 50);
		
		setRootBtn = new JButton("Root »ý¼º");
		//setRootBtn.setBounds(450, 50, 100, 50);
		setRootBtn.addActionListener((e) -> {
			if (rootField.getText() == null) return;
			model.setRoot(rootField.getText());
			tree.setModel(model);
			
			rootField.setEditable(false);
			setRootBtn.setEnabled(false);	
		});
		
		//setRootPanel = new JPanel();
		funcPanel.add(rootField);
		funcPanel.add(setRootBtn);
		//panel.add(setRootPanel, "North");
		
		parentField = new JTextField();
		//parentField.setBounds(220, 200, 130, 50);
		//parentField.setSize(new Dimension(130, 50));
		
		parentLabel = new JLabel("Parent");
		//parentLabel.setBounds(350, 200, 100, 50);
		
		childField = new JTextField();
		//childField.setBounds(470, 200, 130, 50);
		
		childLabel = new JLabel("Child");
		//childLabel.setBounds(600, 200, 100, 50);
		
		//setInputPanel = new JPanel();
		funcPanel.add(parentLabel);
		funcPanel.add(parentField);
		funcPanel.add(childLabel);
		funcPanel.add(childField);
		//panel.add(setInputPanel, "Center");
		
		addBtn = new JButton("Add");
		//addBtn.setBounds(480, 350, 100, 50);
		addBtn.addActionListener((e) -> {
			String parent = parentField.getText();
			String child = childField.getText();
			
			model.addNewChild(parent, child);
			tree.updateUI();
		});
		
		deleteBtn = new JButton("Delete");
		//deleteBtn.setBounds(600, 350, 100, 50);
		deleteBtn.addActionListener((e) -> {
			String parent = parentField.getText();
			String child = childField.getText();
			
			model.deleteChild(parent, child);
			tree.updateUI();
		});
				
		//btnPanel = new JPanel();
		//btnPanel.setLayout(null);
		//panel.add(btnPanel);	
		
		funcPanel.add(addBtn);
		funcPanel.add(deleteBtn);
		
		/*panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		tree = new JTree(model = new MyTreeModel());*/
		
		getContentPane().add(splitPane);
		
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
}
