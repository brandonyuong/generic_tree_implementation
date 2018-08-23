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

    public boolean remove(TreeNode<E> root, E x)
    {
        TreeNode<E> tn = null;

        if (mSize == 0 || root == null)
            return false;

        if ( (tn = find(root, x, 0)) != null )
        {
            removeNode(tn);
            mSize--;
            return true;
        }
        return false;
    }

    private void removeNode(TreeNode<E> nodeToDelete )
    {
        if (nodeToDelete == null || mRoot == null)
            return;
        if (nodeToDelete.myRoot != mRoot)
            return;  // silent error, node does not belong to this tree

        // remove all the children of this node
        while (nodeToDelete.firstChild != null)
            removeNode(nodeToDelete.firstChild);

        if (nodeToDelete.prev == null)
            mRoot = null;  // last node in tree
        else if (nodeToDelete.prev.sib == nodeToDelete)
            nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
        else
            nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

        // adjust the successor sib's prev pointer
        if (nodeToDelete.sib != null)
            nodeToDelete.sib.prev = nodeToDelete.prev;
    }

    public Object clone() throws CloneNotSupportedException
    {
        GenericTree<E> newObject = (GenericTree<E>)super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubGenericTree(mRoot);
        newObject.mSize = mSize;
        newObject.setMyRoots(newObject.mRoot);

        return newObject;
    }

    private TreeNode<E> cloneSubGenericTree(TreeNode<E> root)
    {
        TreeNode<E> newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new TreeNode<E>
                (
                        root.data,
                        cloneSubGenericTree(root.sib), cloneSubGenericTree(root.firstChild),
                        null
                );

        // the prev pointer is set by parent recursive call ... this is the code:
        if (newNode.sib != null)
            newNode.sib.prev = newNode;
        if (newNode.firstChild != null)
            newNode.firstChild.prev = newNode;
        return newNode;
    }

    // recursively sets all myRoots to mRoot
    private void setMyRoots(TreeNode<E> treeNode)
    {
        if (treeNode == null)
            return;

        treeNode.myRoot = mRoot;
        setMyRoots(treeNode.sib);
        setMyRoots(treeNode.firstChild);
    }

}