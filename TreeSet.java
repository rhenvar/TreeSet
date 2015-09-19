import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
 * TreeSet data structure for O(logn) complexity
 */

public class TreeSet<T extends Comparable<T>> implements Iterable<T>, Comparable<TreeSet<T>> {
    private TreeNode<T> root;
    private int size;

    public TreeSet() {
	this.root = null;
	this.size = 0;
    }

    public void add(T t) {
	if (root == null) {
	    this.root = new TreeNode<>(t);
	    this.size++;
	} else {
	    this.root = add(root, t);
	}
    }

    private TreeNode<T> add(TreeNode<T> current, T t) {
	if (current != null) {
	    if (current.field.compareTo(t) > 0) {
		current.left = add(current.left, t);
	    } else if (current.field.compareTo(t) < 0) {
		current.right = add(current.right, t);
	    }
	} else {
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
		} else if (current.left == null) {
		    return current.right;
		} else {
		    TreeNode<T> minimum = minimumNode(current.right);
		    minimum.right = remove(current.right, minimum.field);
		    minimum.left = current.left;
		    return minimum;
		}
	    } else if (current.field.compareTo(t) > 0) {
		current.left = remove(current.left, t);
	    } else {
		current.right = remove(current.right, t);
	    }
	}
	return current;
    }

    public boolean contains(T t) {
	return this.contains(root, t);
    }

    private boolean contains(TreeNode<T> current, T t) {
	if (current != null) {
	    if (current.field.compareTo(t) > 0) {
		return contains(current.left, t);
	    } else if (current.field.compareTo(t) < 0) {
		return contains(current.right, t);
	    } else {
		return true;
	    }
	}
	return false;
    }

    public int size() {
	return this.size;
    }

    public T minimum() {
	return this.minimumNode().field;
    }

    public T maximum() {
	return this.maximumNode().field;
    }

    private TreeNode<T> minimumNode() {
	return this.minimumNode(root);
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
	return this.toArrayList().toString();
    }

    @Override
    public Iterator<T> iterator() {
	return new TreeSetIterator();
    }

    @Override
    public int compareTo(TreeSet<T> other) {
	return this.size() - other.size();
    }

    private class TreeSetIterator implements Iterator<T> {
	private Stack<TreeNode<T>> storedNodes = new Stack<>();
	private TreeNode<T> next;

	private TreeSetIterator() {
	    TreeNode<T> temp = TreeSet.this.root;
	    while (temp != null) {
		this.storedNodes.add(temp);
		temp = temp.left;
	    }
	    this.next = storedNodes.peek();
	}

	@Override
	public boolean hasNext() {
	    return !storedNodes.isEmpty();
	}

	@Override
	public T next() {
	    if (!this.hasNext()) {
		throw new IllegalStateException();
	    }
	    TreeNode<T> node = storedNodes.pop();
	    if (node.right != null) {
		TreeNode<T> currentRight = node.right;
		TreeNode<T> currentLeft = currentRight.left;
		this.storedNodes.add(currentRight);
		while (currentLeft != null) {
		    this.storedNodes.add(currentLeft);
		    currentLeft = currentLeft.left;
		}
	    }
	    this.next = node;
	    return node.field;
	}

	@Override
	public void remove() {
	    if (this.next == null) {
		throw new IllegalStateException();
	    }
	    TreeSet.this.remove(next.field);
	}
    }
}
