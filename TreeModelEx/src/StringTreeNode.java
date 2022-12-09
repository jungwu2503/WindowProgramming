import java.io.*;
import java.util.*;

import javax.swing.tree.*;

public class StringTreeNode extends DefaultMutableTreeNode {
	
	StringTreeNode(String s) {
		super(s);
	}
	
	String getData() {
		return (String)userObject;
	}
	
	public String toString() {
		return (String)userObject;
	}
	
	void depthFirstTraverse() {
		System.out.println(userObject);
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			child.depthFirstTraverse();
		}
	}
	
	String dfsSave() {
		String s = "";
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			s += userObject + " " + child.userObject + "\n";
			child.dfsSave();
		}
		return s;
	}
	
	public int getDepth() {
		int depth = 0;
		int maxDepth = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
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
			StringTreeNode child = (StringTreeNode)i.next();
			total += child.getNumberOfLeafNodes();			
		}
		return total;
	}
	
	public int getNoneLeafNodesNumber() {
		if (children.size() == 0) return 0;
		
		int total = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			total += child.getNoneLeafNodesNumber();	
		}
		return total + 1;
	}
	
	public int getTotalNodesNumber() {
		if (children.size() == 0) return 1;
		
		int total = 0;
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			total += child.getTotalNodesNumber();
		}
		return total + 1;
	}
	
	void depthFirstEnumeration(LinkedList<String> pEnumeration) {
		pEnumeration.addLast((String)userObject);
		ListIterator<TreeNode> i = children.listIterator();
		while(i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			child.depthFirstEnumeration(pEnumeration);
		}
	}
	
	TreeNode find(String s) {
		if (userObject.equals(s)) return this;
		
		ListIterator<TreeNode> i = children.listIterator();
		while (i.hasNext()) {
			StringTreeNode child = (StringTreeNode)i.next();
			TreeNode foundNode = child.find(s);
			if (foundNode != null) return foundNode;
		}
		return null;
	}
	
	TreeNode addChild(String s) {
		StringTreeNode pNewNode = new StringTreeNode(s);
		add(pNewNode);
		return pNewNode;
	}
	
	TreePath constructTreePath() {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		StringTreeNode tmp = (StringTreeNode)parent;
		while (tmp != null) {
			list.add(tmp);
			tmp = (StringTreeNode)tmp.parent;
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
