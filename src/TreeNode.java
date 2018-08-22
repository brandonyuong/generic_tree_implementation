public class TreeNode<E>
{
    // use protected access so the tree, in the same package,
    // or derived classes can access members
    protected TreeNode<E> firstChild, sibling, previous;
    protected E data;
    protected TreeNode<E> myRoot;  // needed to test for certain error

    public TreeNode(E dat, TreeNode<E> sib, TreeNode<E> child, TreeNode<E> prev)
    {
        firstChild = child;
        sibling = sib;
        previous = prev;
        data = dat;
        myRoot = null;
    }

    public TreeNode()
    {
        this(null, null, null, null);
    }

    public E getData() {return data;}

    // for use only by Tree (default access)
    protected TreeNode( E dat, TreeNode<E> sib, TreeNode<E> child, TreeNode<E> prev,
                        TreeNode<E> root )
    {
        this(dat, sib, child, prev);
        myRoot = root;
    }
}

// traverser interface ---------------------------------------------------------
interface Traverser<E>
{
    public void visit(E x);
}
