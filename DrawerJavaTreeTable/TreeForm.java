import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.*;


class FigureTreeModel implements TreeModel
{
	static int POINT = 0;
	static int LINE = 1;
	static int BOX = 2;
	static int CIRCLE = 3;
	static int TV = 4;
	static int Star = 5;
	static int N = Star+1;

	DefaultMutableTreeNode root;
	DefaultMutableTreeNode figNode[];
	
	FigureTreeModel(){
		figNode = new DefaultMutableTreeNode[N];
		root = new DefaultMutableTreeNode("Figures");
		DefaultMutableTreeNode p = new DefaultMutableTreeNode("Point");
		root.add(p);
		figNode[POINT] = p;
		p = new DefaultMutableTreeNode("Line");
		root.add(p);
		figNode[LINE] = p;
		p = new DefaultMutableTreeNode("Box");
		root.add(p);
		figNode[BOX] = p;
		p = new DefaultMutableTreeNode("Circle");
		root.add(p);
		figNode[CIRCLE] = p;
		p = new DefaultMutableTreeNode("TV");
		root.add(p);
		figNode[TV] = p;
		p = new DefaultMutableTreeNode("Star");
		root.add(p);
		figNode[Star] = p;
	}

	public void addFigure(Figure f){

		int index = 0;

		if(f instanceof Point){
			index = POINT;
		}else if(f instanceof Line){
			index = LINE;
		}else if(f instanceof Box){
			index = BOX;
		}else if(f instanceof Circle){
			index = CIRCLE;
		}else if(f instanceof TV){
			index = TV;
		}else if(f instanceof Star){
			index = Star;
		}
		figNode[index].add(new DefaultMutableTreeNode(f.toString()));
	}

	public Object getRoot() {
		return root;
	}
	public Object getChild(Object parent,int index) {
		DefaultMutableTreeNode pNode = (DefaultMutableTreeNode)parent;
		return pNode.getChildAt(index);
	}
	public int getChildCount(Object parent) {
		DefaultMutableTreeNode pNode = (DefaultMutableTreeNode)parent;
		return pNode.getChildCount();
	}
	public boolean isLeaf(Object node) {
		DefaultMutableTreeNode pNode = (DefaultMutableTreeNode)node;
		return pNode.isLeaf();
	}
	public int getIndexOfChild(Object parent,Object child) {
		DefaultMutableTreeNode pNode = (DefaultMutableTreeNode)parent;
		DefaultMutableTreeNode cNode = (DefaultMutableTreeNode)child;
		return pNode.getIndex(cNode);
	}
	public void valueForPathChanged(TreePath path,Object newValue) {
	}
	public void addTreeModelListener(TreeModelListener l) {
	}
	public void removeTreeModelListener(TreeModelListener l) {
	}	
}

/*
class TreeNode
{
	String data;
	Vector<TreeNode> children;
	
	TreeNode(String data){
		this.data = data;
		children = new Vector<TreeNode>();
	}
	public String toString(){
		return data;
	}
	void addChild(TreeNode p){
		children.add(p);
	}
	void addChild(Figure p){
		TreeNode node = new TreeNode(p.toString());
		addChild(node);
	}
}

class FigureTreeModel implements TreeModel
{
	static int POINT = 0;
	static int LINE = 1;
	static int BOX = 2;
	static int CIRCLE = 3;
	static int TV = 4;
	static int N = TV+1;

	TreeNode root;
	TreeNode figNode[];
	
	FigureTreeModel(){
		figNode = new TreeNode[N];
		root = new TreeNode("Figures");
		TreeNode p = new TreeNode("Point");
		root.addChild(p);
		figNode[POINT] = p;
		p = new TreeNode("Line");
		root.addChild(p);
		figNode[LINE] = p;
		p = new TreeNode("Box");
		root.addChild(p);
		figNode[BOX] = p;
		p = new TreeNode("Circle");
		root.addChild(p);
		figNode[CIRCLE] = p;
		p = new TreeNode("TV");
		root.addChild(p);
		figNode[TV] = p;
	}

	public void addFigure(Figure f){

		int index = 0;

		if(f instanceof Point){
			index = POINT;
		}else if(f instanceof Line){
			index = LINE;
		}else if(f instanceof Box){
			index = BOX;
		}else if(f instanceof Circle){
			index = CIRCLE;
		}else if(f instanceof TV){
			index = TV;
		}
		figNode[index].addChild(f);
	}

	public Object getRoot() {
		return root;
	}
	public Object getChild(Object parent,int index) {
		TreeNode pNode = (TreeNode)parent;
		return pNode.children.elementAt(index);
	}
	public int getChildCount(Object parent) {
		TreeNode pNode = (TreeNode)parent;
		return pNode.children.size();
	}
	public boolean isLeaf(Object node) {
		TreeNode pNode = (TreeNode)node;
		if(pNode.children.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	public int getIndexOfChild(Object parent,Object child) {
		TreeNode pNode = (TreeNode)parent;
		TreeNode cNode = (TreeNode)child;
		return pNode.children.indexOf(cNode);
	}
	public void valueForPathChanged(TreePath path,Object newValue) {
	}
	public void addTreeModelListener(TreeModelListener l) {
	}
	public void removeTreeModelListener(TreeModelListener l) {
	}	
}*/

class TreeForm extends JDialog
{
	JPanel treePanel;
	JPanel buttonPanel;
	DrawerView view;
	JTree tree;
	FigureTreeModel figureTree;

	TreeForm(DrawerView view) {
		setSize(300,500);

		this.view = view;
		treePanel = new JPanel();
		buttonPanel = new JPanel();

		figureTree = new FigureTreeModel();
		tree = new JTree(figureTree);
		tree.setPreferredSize(new Dimension(280,400));

		treePanel.add(tree);

		this.add(treePanel,"Center");
		this.add(buttonPanel,"South");
	}
	void showForm() {
		setVisible(true);
	}
	public void addFigure(Figure f){
		figureTree.addFigure(f);
		tree.updateUI();
	}
}
