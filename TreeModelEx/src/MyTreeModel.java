import java.util.*;

import javax.swing.event.*;
import javax.swing.tree.*;

public class MyTreeModel implements TreeModel {

	private TreeNode root;
	
	MyTreeModel() {
		root = null;
	}
	
	void setRoot(String s) {
		root = new TreeNode(s);
	}

	boolean addNewChild(String parent, String child) {
		if (root == null) return false;
		TreeNode pNode = root.find(parent);
		if (pNode == null) return false;
		pNode.addChild(child);
		return true;
	}
	
	boolean deleteChild(String parent, String child) {
		if (root == null) return false;
		TreeNode pNode = root.find(parent);
		if (pNode == null) return false;
		TreeNode cNode = root.find(child);
		if (cNode == null) return false;
		
		pNode.deleteChild(pNode.getIndexOfChild(cNode));
		return true;
	}
	
	public boolean isLeaf(Object obj) {
		TreeNode node = (TreeNode)obj;
		
		return node.isLeaf();
	}
	
	public int getChildCount(Object obj) {
		TreeNode node = (TreeNode)obj;
		
		return node.getChildCount();
	}
	
	public Object getChild(Object obj, int index) {
		TreeNode node = (TreeNode)obj;
		
		return node.getChildAt(index);
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		TreeNode node = (TreeNode)parent;
		
		return node.getIndexOfChild((TreeNode)child);
	}
	
	public Object getRoot() {
		return root;
	}
	
	public void addTreeModelListener(TreeModelListener l) {
		
	}
	
	public void removeTreeModelListener(TreeModelListener l) {
		
	}
	
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
	
}
