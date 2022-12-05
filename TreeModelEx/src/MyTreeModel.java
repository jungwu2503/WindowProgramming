import java.io.*;
import java.util.*;

import javax.swing.event.*;
import javax.swing.tree.*;

public class MyTreeModel implements TreeModel, Serializable {

	private TreeNode root;
	
	MyTreeModel() {
		root = null;
	}
	
	void setRoot(String s) {
		root = new TreeNode(s);
	}

	TreePath addNewChild(String parent, String child) {
		if (root == null) return null;
		TreeNode pNode = root.find(parent);
		if (pNode == null) return null;
		TreeNode pChildNode = pNode.addChild(child);
		
		TreePath path = pChildNode.constructTreePath();
		return path;
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
	
	public void serialLoad(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			root = (TreeNode)ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void serialSave(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(root);
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void txtLoad(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			
			String s = br.readLine();
			System.out.println(s);
			setRoot(s);
			while ((s = br.readLine()) != null) {
				if (s.length() == 0) break;
				String[] token = s.split(" ");
				addNewChild(token[0], token[1]);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void txtSave(String fileName) {
		try {
			FileWriter fw = new FileWriter(new File(fileName));
			
			String s = root.getData() + "\n";
			s += root.dfsSave();
			fw.write(s);
			
			fw.flush();			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getDepth() {
		if (root == null) return 0;
		return root.getDepth();
	}
	
	public int getNumberOfLeafNodes() {
		if (root == null) return 0;
		return root.getNumberOfLeafNodes();
	}
	
	public int getNoneLeafNodesNumber() {
		if (root == null) return 0;
		return root.getNoneLeafNodesNumber();
	}
	
	public int getTotalNodesNumber() {
		if (root == null) return 0;
		return root.getTotalNodesNumber();
	}
	
	public void addTreeModelListener(TreeModelListener l) {
		
	}
	
	public void removeTreeModelListener(TreeModelListener l) {
		
	}
	
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
	
}
