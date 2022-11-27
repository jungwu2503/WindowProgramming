import java.util.*;

public class TreeNode {

	private String data;
	private LinkedList<TreeNode> children;
	
	TreeNode(String s) {
		data = s;
		children = new LinkedList<TreeNode>();
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
	
	void addChild(String s) {
		TreeNode pNewNode = new TreeNode(s);
		children.addLast(pNewNode);
	}
	
	void deleteChild(int i) {
		children.remove(i);
	}
	
}
