import java.io.*;

class ListNode<Type> implements Serializable
{
	private static final long serialVersionUID = 7482385526893942031L;
	Type data;
	ListNode<Type> pNext;
	ListNode(Type x) {
		data = x;
		pNext = null;
	}
	ListNode(Type x,ListNode next) {
		data = x;
		pNext = next;
	}
}

class LinkedList<Type> implements Serializable
{
	private static final long serialVersionUID = -2557784595318644263L;
	private ListNode<Type> pHead;
	private int nCount;
	private boolean flag;
	LinkedList()
	{
		pHead = null;
		nCount = 0;
	}
	int count() {
		return nCount;
	}
	boolean isEmpty() {
		if (pHead == null) return true;
		else return false;
	}
	int size() {
		return nCount;
	}
	void addFirst(Type data)
	{
		ListNode<Type> pNode = new ListNode<Type>(data,pHead);
		nCount++;
		pHead = pNode;
	}
	void addLast(Type data)
	{
		ListNode<Type> pNode = new ListNode<Type>(data);
		nCount++;
		if (pHead == null) {
			pHead = pNode;
			return;
		}
		ListNode<Type> pTraverse = pHead;
		while(pTraverse.pNext != null) {
			pTraverse = pTraverse.pNext;
		}
		pTraverse.pNext = pNode;
	}
	void add(int index,Type data)
	{
		if (index < 0 || index > nCount) {
			System.err.println("index out of bound error - add(index,data) failed.");
			return;
		}
		if (index == 0) {
			addFirst(data);
			return;
		}
		int count = 1;
		ListNode<Type> pFollow = pHead;
		ListNode<Type> pTraverse = pHead.pNext;
		while(pTraverse != null) {
			if (index == count) break;
			count++;
			pFollow = pTraverse;
			pTraverse = pTraverse.pNext;
		}
		ListNode<Type> pNode = new ListNode<Type>(data,pTraverse);
		nCount++;
		pFollow.pNext = pNode;
	}
	boolean remove(Type data)
	{
		if (isEmpty() == true) {
			System.err.println("The list is empty. No data removed.");
			return false;
		}
		if (pHead != null && pHead.data.equals(data)) {
			ListNode<Type> pNextNode = pHead.pNext;
			pHead = pNextNode;
			nCount--;
			return true;
		}
		ListNode<Type> pFollow = pHead;
		ListNode<Type> pTraverse = pHead.pNext;
		while(pTraverse != null) {
			if (pTraverse.data.equals(data)) {
				ListNode<Type> pNextNode = pTraverse.pNext;
				pFollow.pNext = pNextNode;
				nCount--;
				return true;
			}
			pFollow = pTraverse;
			pTraverse = pTraverse.pNext;
		}
		System.err.println(data + " is not found. No data removed.");
		return false;
	}
	ListIterator listIterator() {
		return new ListIterator<Type>(pHead);
	}
	public String toString()
	{
		if (isEmpty() == true) {
			return "<>";
		}
		String tmp = "< ";
		ListNode<Type> pNode = pHead;
		while (pNode != null) {
			tmp = tmp + pNode.data;
			if (pNode.pNext != null) {
				tmp = tmp + ", ";
			} else {
				tmp = tmp + " >";
			}	
			pNode = pNode.pNext;
		}
		return tmp;
	}
}

class ListIterator<Type> 
{
	ListNode<Type> ptr;
	ListIterator(ListNode<Type> pHead) {
		ptr = pHead;
	}
	boolean hasNext() {
		if (ptr == null)
			return false;
		else
			return true;
	}
	Type next() {
		Type data = ptr.data;
		ptr = ptr.pNext;
		return data;
	}
};
