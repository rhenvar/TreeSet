import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
 * TreeSet data structure for O(logn) complexity
 */

public class TreeSet<T extends Comparable <T>> implements Iterable<T> {
	private TreeNode<T> root;
	private int size;
	
	public TreeSet() {
		this.root = null;
		this.size = 0;
	}
	
	public void add(T t) {
		if (root == null) {
			this.root = new TreeNode<T>(t);
			this.size++;
		}
		else {
			this.root = add(root, t);
		}
	}
	
	private TreeNode<T> add(TreeNode<T> current, T t) {
		if (current != null) {
			if (current.field.compareTo(t) > 0) {
				current.left = add(current.left, t);
			}
			else if (current.field.compareTo(t) < 0) {
				current.right = add(current.right, t);
			}
		}
		else {
			this.size++;
			return new TreeNode<T>(t);
		}
		return current;
	}
	
	public void remove(T t) {
		this.root = remove(root, t);
	}
	
	private TreeNode<T> remove(TreeNode<T> current, T t) {
		if (current != null) {
			if (current.field.compareTo(t) == 0) {
				if (current.right == null) {
					return current.left;
				}
				else if (current.left == null) {
					return current.right;
				}
				else {
					TreeNode<T> minimum = minimumNode(current.right);
					minimum.right = remove(current.right, minimum.field);
					minimum.left = current.left;
					return minimum;
				}
			}
			else if (current.field.compareTo(t) > 0) {
				current.left = remove(current.left, t);
			}
			else {
				current.right = remove(current.right, t);
			}
		}
		return current;
	}
	
	public boolean contains(T t) {
		return contains(root, t);
	}
	
	public T minimum() {
		return minimumNode().field;
	}
	
	public T maximum() {
		return maximumNode().field;
	}
	
	private TreeNode<T> minimumNode() {
		return minimumNode(root);
	}
	
	private TreeNode<T> minimumNode(TreeNode<T> current) {
		if (current != null) {
			if (current.left == null || current.isLeaf()) {
				return current;
			}
			return minimumNode(current.left);
		}
		return null;
	}
	
	private TreeNode<T> maximumNode() {
		return maximumNode(root);
	}
	
	private TreeNode<T> maximumNode(TreeNode<T> current) {
		if (current != null) {
			if (current.right == null || current.isLeaf()) {
				return current;
			}
			return maximumNode(current.right);
		}
		return null;
	}
	
	private boolean contains(TreeNode<T> current, T t) {
		if (current != null) {
			if (current.field.compareTo(t) > 0) {
				return contains(current.left, t);
			}
			else if (current.field.compareTo(t) < 0) {
				return contains(current.right, t);
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public List<T> toArrayList() {
		return toArrayList(root, new ArrayList<T>());
	}
	
	private List<T> toArrayList(TreeNode<T> current, List<T> values) {
		if (current != null) {
			toArrayList(current.left, values);
			values.add(current.field);
			toArrayList(current.right, values);
		}
		return values;
	}
	
	@Override
	public String toString() {
		return toString(root);
	}
	
	private String toString(TreeNode<T> current) {
		if (current != null) {
			return toString(current.left) + " " + current.field.toString() + " " + toString(current.right);
		}
		else {
			return "";
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TreeSetIterator<T>(root);
	}
	
	private class TreeSetIterator<T extends Comparable<T>> implements Iterator<T> {
		private Stack<TreeNode<T>> storedNodes = new Stack<TreeNode<T>>();
		
		private TreeSetIterator(TreeNode<T> root) {
			TreeNode<T> temp = root;
			while (temp != null) {
				storedNodes.add(temp);
				temp = temp.left;
			}
		}
		
		@Override
		public boolean hasNext() {
			return !storedNodes.isEmpty();
		}

		@Override
		public T next() {
			TreeNode<T> left = storedNodes.peek();
			if (left.isLeaf()) {
				storedNodes.pop();
				return left.field;
			}
			else {
				
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
