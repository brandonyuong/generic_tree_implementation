public class TreeNode<E>
{
    // use protected access so the tree, in the same package,
    // or derived classes can access members
    protected TreeNode<E> firstChild, sib, prev;
    protected E data;
    protected TreeNode<E> myRoot;  // needed to test for certain error

    // 4 parameter constructor
    public TreeNode(E dat, TreeNode<E> sibling, TreeNode<E> child, TreeNode<E> previous)
    {
        firstChild = child;
        sib = sibling;
        prev = previous;
        data = dat;
        myRoot = null;
    }

    // default constructor
    public TreeNode()
    {
        this(null, null, null, null);
    }

    // accessor
    public E getData() {return data;}

    // for use only by Tree (default access)
    protected TreeNode( E dat, TreeNode<E> sibling, TreeNode<E> child,
                        TreeNode<E> previous, TreeNode<E> root )
    {
        this(dat, sibling, child, previous);
        myRoot = root;
    }
}

// traverser interface -------------------------------------------------------------------
interface Traverser<E>
{
    public void visit(E x);
}
