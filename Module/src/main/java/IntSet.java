import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A set of ints stored in a binary search tree of mutable IntNodes.
 *
 * The tree is private: no method ever returns an IntNode, so code outside
 * this class cannot reach in and change (or break) the tree. Every change
 * goes through add() and remove().
 *
 * Duplicates are not stored: adding a value that is already present
 * leaves the set unchanged.
 *
 * Do not rename the public methods below - the tests call them by name.
 * You may add private helper methods.
 */
public class IntSet {

    private IntNode root;   // null when the set is empty
    private int size;       // number of values in the set

    /** Creates an empty set. */
    public IntSet() {
        root = null;
        size = 0;
    }

    /**
     * Adds v to the set.
     * return true if v was added, false if it was already present
     */
    public boolean add(int v)
    {
        if(root == null)
        {
            root = new IntNode(v);
            size++;
            return true;
        }
        else if(this.contains(v))
        {
            return false;
        }

        IntNode test = insert(root, v);

        if (test == null)
        {
            return false;
        }
        else
        {
            size++;
            return true;
        }
    }

    /**
     * Inserts v below t and returns the root of that subtree afterward.
     * Mutable style: reassign t.left / t.right instead of building new nodes.
     */
    private static IntNode insert(IntNode t, int v)
    {
        if(t == null)
        {
            return new IntNode(v);
        }
        else if (v < t.value)
        {
            t.left = insert(t.left, v);
        }
        else if (v > t.value)
        {
            t.right = insert(t.right, v);
        }
        return t;
    }

    /**
     * Reports whether v is in the set.
     * return true if v is present
     */
    public boolean contains(int v)
    {
        if (root == null)
        {
            return false;
        }
        IntNode count = root;
        while(count!=null)
        {
            if(v < count.value)
            {
                count = count.left;
            }
            else if(v > count.value)
            {
                count = count.right;
            }
            else
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes v from the set.
     * return true if v was removed, false if it was not present
     */
    public boolean remove(int v)
    {
        if(!this.contains(v))
        {
            return false;
        }

        root = remove(root, v);
        size--;
        return true;
    }

    /**
     * Removes v below t and returns the root of that subtree afterward.
     * Handle all three cases from lecture: leaf, one child, two children
     * (copy the in-order successor up, then remove it from the right subtree).
     */
    private static IntNode remove(IntNode t, int v)
    {
        if(t == null){return null;}
        if (v < t.value)
        {
            t.left = remove(t.left, v);
        }
        else if (v > t.value)
        {
            t.right = remove(t.right, v);
        }
        else
        {
            if(t.left == null && t.right == null)
            {
                return null;
            }
            else if(t.left == null)
            {
                return t.right;
            }
            else if(t.right == null)
            {
                return t.left;
            }
            else
            {
                t.value = min(t.right);
                t.right = remove(t.right, t.value);
            }
        }
        return t;
    }

    /**
     * @return the smallest IntNode in a given set
     * @throws NoSuchElementException if the set is empty
     */
    private static int min(IntNode t)
    {
        if (t == null)
        {
            throw new NoSuchElementException("Set is empty");
        }
        IntNode count = t;
        while(count!=null)
        {
            if(count.left == null)
            {
                break;
            }
            count = count.left;
        }
        return count.value;
    }

    /** @return the number of values in the set */
    public int size()
    {
        return size;
    }

    /** @return true if the set has no values */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * @return the smallest value in the set
     * @throws NoSuchElementException if the set is empty
     */
    public int min()
    {
        if (root == null)
        {
            throw new NoSuchElementException("Set is empty");
        }
        IntNode count = root;
        while(true)
        {
            if(count.left == null)
            {
                break;
            }
            count = count.left;
        }
        return count.value;
    }

    /**
     * @return the largest value in the set
     * @throws NoSuchElementException if the set is empty
     */
    public int max()
    {
        if (root == null)
        {
            throw new NoSuchElementException("Set is empty");
        }
        IntNode count = root;
        while(true)
        {
            if(count.right == null)
            {
                break;
            }
            count = count.right;
        }
        return count.value;
    }

    /**
     * return a new list of every value in the set, in increasing order
     */
    public List<Integer> toList()
    {
        List<Integer> out = new ArrayList<>();
        toList(root, out);
        return out;
    }

    /**
     * recursively adds the lowest to highest value in the set
     */
    private static void toList(IntNode t, List<Integer> out)
    {
        if (t == null)
        {
            return;
        }

        toList(t.left, out);
        out.add(t.value);
        toList(t.right, out);
    }

    /**
     * return the height of the underlying tree (see TreeQueries.height).
     * This one is done for you, but it depends on your Part B code.
     */
    public int height()
    {
        return TreeQueries.height(root);
    }
}
