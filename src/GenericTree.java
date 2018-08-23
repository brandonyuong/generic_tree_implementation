public class GenericTree<E> implements Cloneable
{
    protected int mSize;
    protected TreeNode<E> mRoot;

    public GenericTree()
    {
        clear();
    }

    public boolean empty()
    {
        return (mSize == 0);
    }

    public int size()
    {
        return mSize;
    }

    public void clear()
    {
        mSize = 0;
        mRoot = null;
    }

    public TreeNode<E> find(E x)
    {
        return find(mRoot, x, 0);
    }

    public boolean remove(E x)
    {
        return remove(mRoot, x);
    }

    public void display()
    {
        display(mRoot, 0);
    }

    public <F extends Traverser<? super E>>
    void traverse(F func)
    {
        traverse(func, mRoot, 0);
    }

    public TreeNode<E> addChild( TreeNode<E> treeNode,  E x )
    {
        // empty tree? - create a root node if user passes in null
        if (mSize == 0)
        {
            if (treeNode != null)
                return null; // error something's fishy.  treeNode can't right
            mRoot = new TreeNode<E>(x, null, null, null);
            mRoot.myRoot = mRoot;
            mSize = 1;
            return mRoot;
        }
        if (treeNode == null)
            return null; // error inserting into non_null tree with a null parent
        if (treeNode.myRoot != mRoot)
            return null;  // silent error, node does not belong to this tree

        // push this node into the head of the sibling list; adjust prev pointers
        TreeNode<E> newNode = new TreeNode<E>(x,
                treeNode.firstChild, null, treeNode, mRoot);  // sb, chld, prv, rt
        treeNode.firstChild = newNode;
        if (newNode.sib != null)
            newNode.sib.prev = newNode;
        ++mSize;
        return newNode;
    }

    public TreeNode<E> find(TreeNode<E> root, E x, int level)
    {
        TreeNode<E> retval;

        if (mSize == 0 || root == null)
            return null;

        if (root.data.equals(x))
            return root;

        // otherwise, recurse.  don't process sibs if this was the original call
        if ( level > 0 && (retval = find(root.sib, x, level)) != null )
            return retval;
        return find(root.firstChild, x, ++level);
    }


}