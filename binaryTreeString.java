//--------------------------------------------------------------------------------------------------
//	 Programmer: Philip Nicholas					  
//	 Assignment: 
//        Date: 04/01/2022
// Description: 
//--------------------------------------------------------------------------------------------------
import java.io.*;
import java.util.*;
import java.lang.*;

public class binaryTreeString {
   //instances
    int nodeCount;
    Node root;
    Node tmpNode;
    String tmpString1;
    String tmpString2;
    String objectName;
    String tmpDirection;
   //constructor
   private binaryTreeString() {
      root = null;
      nodeCount = 0;
   }
   //Node class
    class Node {
      String key;
        int count;
        Node left;
        Node right;
      //constructor
      Node(String string) {
         key = string;
         count = 1;
         left = null;
         right = null;
      }
   };
   //add binary tree node
   //{overloaded method}
   Node addNode(Node node, String key) {
      if (node == null) {
         Node newNode = new Node(key);
         nodeCount++;
         if (root == null)
            root = newNode;
      return(newNode);
      }
      else {
         if (key.compareTo(node.key) == 0) {
            node.count++;
            nodeCount++;
         }
         else {
            if (key.compareTo(node.key) < 0) {
               node.left = addNode(node.left, key);
            }
            else {
               node.right = addNode(node.right, key);
            }    
         }
         return(node);
      }
   }
   //add binary tree node & return string of key added
   //{overloaded method}
   String addNode(String key) {
      root = addNode(root, key);
      return(listNode("Ascending"));
   }
   //
   Node minKeyNode(Node node) {
      Node currentNode = node;
      
      while (currentNode.left != null) 
         currentNode = currentNode.left;
         
   return(currentNode);
   }
   //delete binary tree node
   //{overloaded method}
   Node deleteNode(Node node, String key) {
      if (node == null) {
         return(node);
      }
      else if (key.compareTo(node.key) < 0) {
         //if key to be deleted is < node
         //delete from left sub-tree
         node.left = deleteNode(node.left, key);
         return(node);
      }
      else if (key.compareTo(node.key) > 0){
         //if key to be deleted is > node
         //delete from right sub-tree
         node.right = deleteNode(node.right, key);
         return(node);
      }
      else {
         if (node.count > 1) {
            //if key is equal & count > 1 decrease count
            node.count--;
            nodeCount--;
            return(node);   
         } else if (node.left == null) {
            //delete node with only 1 child
            tmpNode = node.right;
            node = null;
            nodeCount--;
            return(tmpNode);
         }
         else if (node.right == null) {
         //delete node with only 1 child
            tmpNode = node.left;
            node = null;
            nodeCount--;
            return(tmpNode);
         } else { 
            //delete node with 2 children, 
            //get smallest in right sub-tree
            tmpNode = minKeyNode(node.right);
            //copy content to this node
            node.key = tmpNode.key;
            node.count = tmpNode.count;
            node.right.count = 1;
            //delete node
            node.right = deleteNode(node.right, tmpNode.key);
         return(node);
         }
      }
   }
   //delete binary tree node
   //{overloaded method}
   String deleteNode(String key) {
      root = deleteNode(root, key);
   return(listNode("Ascending"));   
   }
   //search binary tree nodes 
   //{overloaded method}
   Node findNode(Node node, String key) {
      if (node == null || key.compareTo(node.key) == 0)
         return(node);
      
      if (key.compareTo(node.key) < 0)
         return(findNode(node.left, key));
      else
         return(findNode(node.right, key));
   }
   //return boolean result of binary tree search 
   //{overloaded method}
   boolean findNode(String key) {
      if (findNode(root, key) != null)
         return(true);
      else
         return(false);
   }
   //list binary tree nodes 
   //{overloaded method}
   String listNode(Node node) {
      if (node != null) {
         if (tmpDirection.equals("Ascending"))
            listNode(node.left);
         else
            listNode(node.right);
         tmpString2 = tmpString2 + (String.format(node.key + "(" + node.count + ") "));
         if (tmpDirection.equals("Ascending"))
            listNode(node.right);
         else
            listNode(node.left);
      }
   return(tmpString2);
   }
   //list binary tree nodes
   //{overloaded method}
   String listNode(String direction) {
      tmpString2 = "";
      tmpDirection = direction;
      tmpString2 = listNode(root).trim();
      if (!tmpString2.equals(""))
         return(tmpString2);
      else
         return("empty");
   }
   //delete binary tree 
   //{overloaded method}
   Node deleteTree(Node node) {
      if (node != null) {
         deleteTree(node.left);
         //force deletion of duplicate nodes
         for (int i=node.count;i>=2;i--) {
            nodeCount--;
            node.count--; 
         }
         deleteNode(node.key);   
         //force deletion of duplicate nodes
         deleteTree(node.right);
      }
   return(node);
   }
   //delete binary tree 
   //{overloaded method}
   String deleteTree() {
      tmpString1 = listNode("Ascending");
      deleteTree(root);   
   return(tmpString1);
   }
   //return count of binary tree nodes
   int treeSize() {
      ;
   return(nodeCount);
   }
   //copy binary tree 
   //{overloaded method}
   Node copyTree(Node srceNode) {
      if (srceNode != null) {
         copyTree(srceNode.left);
         //
         for (int i=srceNode.count;i>0;i--) {
            addNode(srceNode.key);   
         }
         //
         copyTree(srceNode.right);
      }
   return(srceNode);
   }
   //copy binary tree 
   //{overloaded method}
   String copyTree(binaryTreeString xTree) {
      copyTree(xTree.root);   
   return(listNode("Ascending"));
   }
   //move {copy/delete} binary tree 
   String moveTree(binaryTreeString xTree) {
      copyTree(xTree.root);        //copy nodes from xTree {srce} using 'this' {dest} method
      xTree.deleteTree();          //delete nodes from xTree {srce} using 'xTree' {srce} method
   return(listNode("Ascending"));
   }
   //map binary tree nodes 
   //{overloaded method}
   String mapTree(Node node) {
      if (node != null) {
         if ((node.left != null) && (node.right != null))
            tmpString1 = tmpString1 + String.format("root[%s] left[%s] right[%s]\n",node.key, node.left.key, node.right.key);
         else if ((node.left != null) && (node.right == null))
            tmpString1 = tmpString1 + String.format("root[%s] left[%s] right[%s]\n",node.key, node.left.key, "null");
         else if ((node.left == null) && (node.right != null))
            tmpString1 = tmpString1 + String.format("root[%s] left[%s] right[%s]\n",node.key, "null", node.right.key);
         else
            tmpString1 = tmpString1 + String.format("root[%s] left[%s] right[%s]\n",node.key, "null", "null");

         if (!tmpString1.equals(""))
            tmpString1 = tmpString1 + String.format("%18s", " ");
      
         mapTree(node.left);
            ;
         mapTree(node.right);
      }
   return(tmpString1);
   }
   //map binary tree nodes 
   //{overloaded method}
   String mapTree() {
      tmpString1 = "";
      tmpString1 = mapTree(root).trim();   
   return(tmpString1);
   }
//--------------------------------------------------------------------------------------------------
   public static void main(String[] args) throws Exception {
      binaryTreeString bTree = new binaryTreeString();   //create bTree object
      bTree.objectName = "bTree";                        //set name of bTree object
      binaryTreeString xTree = new binaryTreeString();   //create xTree object
      xTree.objectName = "xTree";                        //set name of xTree object

      try {
         //populate xTree & list nodes
         StartUp(bTree, xTree);
         //populate bTree & test all supported binary tree methods
         //{proving bTree & xTree are separate instances of a binary tree}
         Process(bTree, xTree);
         //copy, move xTree nodes & list bTree/xTree nodes
         Wrap_Up(bTree, xTree);
      }
      catch (Exception e) {
         System.out.println("Unknown Exception Error");
      }
      finally {
         System.out.println("End of Program");
      }
      
   return;
   }
 //--------------------------------------------------------------------------------------------------
   public static void StartUp(binaryTreeString bTree, binaryTreeString xTree) {
      System.out.println("Start of Program\n");
      System.out.printf("--Populate '%s' tree-----------------------------------------\n", xTree.objectName);
      xTree.addNode("Georgia");
      xTree.addNode("Georgia");
      xTree.addNode("Georgia");
      xTree.addNode("Alabama");
      xTree.addNode("Alabama");
      xTree.addNode("Alabama");
      xTree.addNode("Alabama");
      xTree.addNode("North Carolina");
      xTree.addNode("South Carolina");
      xTree.addNode("Virginia");
      xTree.addNode("Ohio");
      xTree.addNode("Nevada");
      formatOutput(xTree,"mapTree","");
      buildBinaryTree(bTree);
      formatOutput(bTree,"mapTree","");
  return;
   } 
//--------------------------------------------------------------------------------------------------
   public static void Process(binaryTreeString bTree, binaryTreeString xTree) {
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteTree", "");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "addNode", "Gwinnett");
      formatOutput(bTree, "addNode", "Fulton");
      formatOutput(bTree, "addNode", "Dekalb");
      formatOutput(bTree, "addNode", "Cobb");
      formatOutput(bTree, "addNode", "Clayton");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "Dekalb");
      formatOutput(bTree, "treeSize", "");
      buildBinaryTree(bTree);
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "findNode", "Gwinnett");
      formatOutput(bTree, "findNode", "Clayton");
      formatOutput(bTree, "findNode", "Dekalb");
      formatOutput(bTree, "listNode", "Decending");
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "Cobb");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "Bartow");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "Fulton");
      formatOutput(bTree, "treeSize", "");
   return;
   } 
//--------------------------------------------------------------------------------------------------
   public static void Wrap_Up(binaryTreeString bTree, binaryTreeString xTree) {
      formatOutput(bTree, "copyTree", xTree);
      formatOutput(bTree, "moveTree", xTree);
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(xTree, "listNode", "Ascending");
   return;
   } 
//--------------------------------------------------------------------------------------------------
   public static void buildBinaryTree(binaryTreeString bTree) {
      System.out.printf("--Populate '%s' tree-----------------------------------------\n", bTree.objectName);
      bTree.addNode("Ware");
      bTree.addNode("Ware");
      bTree.addNode("Ware");
      bTree.addNode("Burke");
      bTree.addNode("Laurens");
      bTree.addNode("Clinch");
      bTree.addNode("Washington");
      bTree.addNode("Mitchell");
      bTree.addNode("Brooke");
      bTree.addNode("Grady");
      bTree.addNode("Bartow");
      bTree.addNode("Grady");
      bTree.addNode("Bartow");
      bTree.addNode("Grady");
      bTree.addNode("Bartow");
      bTree.addNode("Stewart");
      bTree.addNode("Rabun");
      bTree.addNode("Jasper");
      bTree.addNode("Toombs");
      bTree.addNode("Greene");
      bTree.addNode("Monroe");
      bTree.addNode("Marion");
      bTree.addNode("Jasper");
      bTree.addNode("Toombs");
      bTree.addNode("Greene");
   return;
   } 
//--------------------------------------------------------------------------------------------------
   //copy formatOutput
   //{overloaded method}
   public static void formatOutput(binaryTreeString bTree, String command, String arg) {
      switch (command) {
         case "findNode":
            System.out.printf("--Find key value in '%s' binary tree-------------------------\n" + 
                              "  'findNode' [%s]->[" + bTree.findNode(arg) + "]\n\n", bTree.objectName, arg);
         break;
         case "listNode":
            System.out.printf("--List nodes of '%s' binary tree-----------------------------\n" + 
                              "  'listNode' [%s]->[" + bTree.listNode(arg) + "]\n\n", bTree.objectName, arg);
         break;
         case "treeSize":
            System.out.printf("--Get size of '%s' binary tree-------------------------------\n" + 
                              "  'treeSize' []->[" + bTree.treeSize() + "]\n\n", bTree.objectName);
         break;
         case "addNode":
            System.out.printf("--Add node to '%s' binary tree-------------------------------\n" + 
                              "   'addNode' [%s]->[" + bTree.addNode(arg) + "]\n\n", bTree.objectName, arg);
         break;
         case "deleteNode":
            System.out.printf("--Delete node from '%s' binary tree--------------------------\n" +
                              "'deleteNode' [%s]->[" + bTree.deleteNode(arg) + "]\n\n", bTree.objectName, arg);
         break;
         case "deleteTree":
            System.out.printf("--Delete '%s' binary tree------------------------------------\n" + 
                              "'deleteTree' []->[" + bTree.deleteTree() + "]\n\n", bTree.objectName);
         break;
         case "mapTree":
            System.out.printf("--Map '%s' binary tree---------------------------------------\n" + 
                              "   'mapTree' []->[" + bTree.mapTree() + "]\n\n", bTree.objectName);
         break;
      }
   return;
   } 
//--------------------------------------------------------------------------------------------------
   //copy formatOutput
   //{overloaded method}
   public static void formatOutput(binaryTreeString bTree, String command, binaryTreeString xTree) {
      switch (command) {
         case "copyTree":
            System.out.printf("--Copy '%s' binary tree to '%s' binary tree---------------\n" + 
                              "  'copyTree' []->[" + bTree.copyTree(xTree) + "]\n\n", xTree.objectName, bTree.objectName);
         break;
         case "moveTree":
            System.out.printf("--Move '%s' binary tree to '%s' binary tree---------------\n" + 
                              "  'moveTree' []->[" + bTree.moveTree(xTree) + "]\n\n", xTree.objectName, bTree.objectName);
         break;
      }
   return;
   } 
//--------------------------------------------------------------------------------------------------
}
