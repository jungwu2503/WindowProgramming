import java.io.*;
import java.util.*;

import javax.swing.tree.*;

public class TreeNode implements Serializable {

	private String data;
	private LinkedList<TreeNode> children;
	private TreeNode parent;
	
	TreeNode(String s) {
		data = s;
		children = new LinkedList<TreeNode>();
		parent = null;
	}
	
	String getData() {
		return data;
	}
	
	public String toString() {
		return data;
	}
	
	public boolean isLeaf() {
		if (children.size() == 0) return true;
		return false;
	}
	
	public int getChildCount() {
		return children.size();
	}
	
	public Object getChildAt(int i) {
		return children.get(i);
	}
	
	public int getIndexOfChild(TreeNode child) {
		return children.indexOf(child);
	}
	
	void depthFirstTraverse() {
		System.out.println(data);
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			child.depthFirstTraverse();
		}
	}
	
	String dfsSave() {
		String s = "";
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			s += data + " " + child.data + "\n";
			child.dfsSave();
		}
		return s;
	}
	
	public int getDepth() {
		int depth = 0;
		int maxDepth = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			depth = child.getDepth();
			if (maxDepth < depth) maxDepth = depth;
		}
		return maxDepth + 1;
	}
	
	public int getNumberOfLeafNodes() {
		if (children.size() == 0) return 1;
		
		int total = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			total += child.getNumberOfLeafNodes();			
		}
		return total;
	}
	
	public int getNoneLeafNodesNumber() {
		if (children.size() == 0) return 0;
		
		int total = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			total += child.getNoneLeafNodesNumber();	
		}
		return total + 1;
	}
	
	public int getTotalNodesNumber() {
		if (children.size() == 0) return 1;
		
		int total = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			total += child.getTotalNodesNumber();
		}
		return total + 1;
	}
	
	void depthFirstEnumeration(LinkedList<String> pEnumeration) {
		pEnumeration.addLast(data);
		ListIterator<TreeNode> i = children.listIterator();
		while(i.hasNext()) {
			TreeNode child = i.next();
			child.depthFirstEnumeration(pEnumeration);
		}
	}
	
	TreeNode find(String s) {
		if (data.equals(s)) return this;
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			TreeNode child = i.next();
			TreeNode foundNode = child.find(s);
			if (foundNode != null) return foundNode;
		}
		return null;
	}
	
	TreeNode addChild(String s) {
		TreeNode pNewNode = new TreeNode(s);
		children.addLast(pNewNode);
		pNewNode.parent = this;		
		return pNewNode;
	}
	
	TreePath constructTreePath() {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode tmp = parent;
		while (tmp != null) {
			list.add(tmp);
			tmp = tmp.parent;
		}
		
		TreeNode nodes[] = new TreeNode[list.size()];
		
		/*for (int i = 0; i < list.size(); i++) {
			nodes[i] = list.get(list.size()-i-1);
		}
		return new TreePath(nodes);*/
		int i = 0;
		ListIterator<TreeNode> li = list.listIterator(list.size()); 
		while (li.hasPrevious()) {
			TreeNode node = li.previous();
			nodes[i++] = node;
		}
		
		return new TreePath(nodes);
		/*if (parent == null) {
			return new TreePath(this);
		} else {
			TreePath path = parent.constructTreePath();
			
			if (isLeaf()) {
				return path;
			} else {
				return path.pathByAddingChild(this);
			}
		}*/
	}
	
	void deleteChild(int i) {
		children.remove(i);
	}	
}
