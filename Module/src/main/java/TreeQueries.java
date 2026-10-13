/**
 * Recursive questions you can ask about any tree of IntNodes.
 * These methods take a node (possibly null) and do not change the tree.
 * You may add private helper methods.
 */
public class TreeQueries {

    /**
     * The height of a tree is the number of edges on its longest path
     * from root to a leaf
     * return the height of t
     */
    public static int maxor;

    public static int height(IntNode t) {
        if (t == null) {
            return -1;
        }

        return 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * return the number of leaves (nodes with no children) in t
     */
    public static int countLeaves(IntNode t) {
        if (t == null) {
            return 0;
        }

        if (t.right == null && t.left == null) {
            return 1;
        }

        return countLeaves(t.left) + countLeaves(t.right);
    }

    /**
     * A tree is a valid BST if, for every node, every value in its left
     * subtree is smaller than the node's value and every value in its right
     * subtree is larger. (No duplicates.) The empty tree is a valid BST.
     * return true if t is a valid BST, false otherwise
     */
    public static boolean isValidBST(IntNode t)
    {
        return validHelper(t, null, null);
    }

    /* same as isValidBST but takes in max and min to ensure the value falls within range*/

    private static boolean validHelper(IntNode t, Integer min, Integer max)
    {
        if (t == null)
        {
            return true;
        }

        if (min != null && t.value <= min)
        {
            return false;
        }

        if (max != null && t.value >= max)
        {
            return false;
        }

        return validHelper(t.left, min, t.value) && validHelper(t.right, t.value, max);
    }
}