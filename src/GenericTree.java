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
}