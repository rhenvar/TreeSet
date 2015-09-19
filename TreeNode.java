public class TreeNode<T extends Comparable<T>> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T field;

    public TreeNode(T t) {
	this.field = t;
    }

    public TreeNode(T t, TreeNode<T> left) {
	this.field = t;
	this.left = left;
    }

    public TreeNode(T t, TreeNode<T> left, TreeNode<T> right) {
	this.field = t;
	this.left = left;
	this.right = right;
    }

    public boolean isLeaf() {
	return left == null && right == null;
    }
}
