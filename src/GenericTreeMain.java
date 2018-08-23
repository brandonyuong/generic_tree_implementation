/* ---------------------------------------------------------------------------------------
Author:  Brandon Yuong
Description:  The purpose of this program is to implement soft deletion in a
generic tree ADT.
--------------------------------------------------------------------------------------- */

public class GenericTreeMain
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("***** Test GenericTree *****");
        GenericTree<String> sceneryTree = new GenericTree<String>();
        TreeNode<String> treeNode;

        // create a scene in a room
        treeNode = sceneryTree.addChild(null, "scenery");

        // add three objects to the scene tree
        sceneryTree.addChild(treeNode, "Bobby the dire wolf");
        sceneryTree.addChild(treeNode, "Mike the hero");
        sceneryTree.addChild(treeNode, "castle");
        // add some parts to Miguel
        treeNode = sceneryTree.find("Mike the hero");

        // Miguel's left arm
        treeNode = sceneryTree.addChild(treeNode, "abdomen");
        treeNode = sceneryTree.addChild(treeNode, "left arm");
        treeNode = sceneryTree.addChild(treeNode, "left hand");
        sceneryTree.addChild(treeNode, "thumb");
        sceneryTree.addChild(treeNode, "index finger");
        sceneryTree.addChild(treeNode, "middle finger");
        sceneryTree.addChild(treeNode, "ring finger");
        sceneryTree.addChild(treeNode, "pinky");

        // Miguel's right arm
        treeNode = sceneryTree.find("Mike the hero");
        treeNode = sceneryTree.find(treeNode, "abdomen", 0);
        treeNode = sceneryTree.addChild(treeNode, "right arm");
        treeNode = sceneryTree.addChild(treeNode, "right hand");
        sceneryTree.addChild(treeNode, "thumb");
        sceneryTree.addChild(treeNode, "index finger");
        sceneryTree.addChild(treeNode, "middle finger");
        sceneryTree.addChild(treeNode, "ring finger");
        sceneryTree.addChild(treeNode, "pinky");

        // add some parts to Lily
        treeNode = sceneryTree.find("Bobby the dire wolf");
        treeNode = sceneryTree.addChild(treeNode, "abdomen");
        sceneryTree.addChild(treeNode, "right front paw");
        sceneryTree.addChild(treeNode, "left front paw");
        sceneryTree.addChild(treeNode, "right rear paw");
        sceneryTree.addChild(treeNode, "left rear paw");
        sceneryTree.addChild(treeNode, "spare mutant paw");
        sceneryTree.addChild(treeNode, "wagging tail");

        // add some parts to table
        treeNode = sceneryTree.find("castle");
        sceneryTree.addChild(treeNode, "north east wing");
        sceneryTree.addChild(treeNode, "north west wing");
        sceneryTree.addChild(treeNode, "south east wing");
        sceneryTree.addChild(treeNode, "south west wing");

        sceneryTree.display();
        System.out.println("\nSize: " + sceneryTree.size() + "\n");

        // clone
        GenericTree<String> ClonedTree = (GenericTree<String>) sceneryTree.clone();

        // remove some nodes
        sceneryTree.remove("spare mutant paw");
        sceneryTree.remove("Mike the hero");
        sceneryTree.remove("an imagined sword");

        sceneryTree.display();
        System.out.println("\nOriginal's Size: " + sceneryTree.size());

        // see if the clone worked
        System.out.println("\nClone display:");
        ClonedTree.display();
        System.out.println("\nClone's Size: " + ClonedTree.size() + "\n");
    }
}
