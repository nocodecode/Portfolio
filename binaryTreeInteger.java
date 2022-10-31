//--------------------------------------------------------------------------------------------------
//	 Programmer: Philip Nicholas					  
//	 Assignment: Programming Project 5: Tree Sort
//        Date: 04/06/2022
// Description: Devise an algorithm that uses a binary search tree to sort (for simplicity don't allow 
//              duplicate data) an array of objects(could be Integers). Such a sort is called tree sort. 
//              Implement and test your algorithm.
//
//              Testing and Submitting:
//              At the end of your source program include a comment about your programs behavior/output  
//              as comment. If your program does not run and gives you any kind of error , include that 
//              message. If I don't see this comment and your program misbehaves you will loose more points.
//--------------------------------------------------------------------------------------------------
import java.io.*;
import java.util.*;
import java.lang.*;
//--------------------------------------------------------------------------------------------------
public class binaryTreeInteger {
   //instances
   Node root;                                   //'root' node object
   Node tmpNode;                                //temporary node object
   int dupNodes;                                //count of duplicate nodes not added to tree
   int nodeCount;                               //count of nodes contained within the tree
   String treeName;                             //name of 'binaryTreeInteger' object for output displays
   String tmpString1;                           //temporary String variables
   String tmpString2;                           //
   String nodeName="";                          //
   String tmpDirection;                         //variable used to indicate ascending/decending listing 
   static long stopTime=0;                      //variables used in 'timer' function
   static long startTime=0;                     //
   static long elapsedTime=0;                   //
//--------------------------------------------------------------------------------------------------
//'binaryTreeInteger' constructor
   private binaryTreeInteger(String name) {
      root = null;                              //initialize variables
      dupNodes = 0;                             //initialize variables
      nodeCount = 0;                            //initialize variables
      treeName = name;                          //initialize variables
   }
//--------------------------------------------------------------------------------------------------
//Node class
   private class Node {
      int key;                                  //node key
      Node left;                                //left node pointer
      Node right;                               //right node pointer 
      //'Node' constructor
      Node(int number) {
         key = number;                          //initialize variables
         left  = null;                          //initialize variables
         right = null;                          //initialize variables
      }
   };
//--------------------------------------------------------------------------------------------------
//'inorderIterator' class 
//step thru binary tree one item at a time:
// beginning at the first item & stepping in order toward the last item or
// beginning at the last item & stepping in reverse order toward the first item
   private class inorderIterator{
      Stack<Node> nodeStack = new Stack<Node>();   //initialize stack for push/pop of tree nodes
      Node currentNode;                         //temporary work node
      //
      public inorderIterator(Node node)         //constructor
      {
         currentNode = node;                    //initialize work node with passes node reference 
      }
      //
      public boolean hasNext()                  //check if stack/tree data available
      {
         return((!nodeStack.isEmpty()) || (currentNode != null)); //return true-false based on data availability
      }
      //
      public int next()                         //return next binarty tree value
      {
         Node nextNode = null;                  //initialize work node
         
         while(currentNode != null)             //loop thru left tree nodes until last leaf{no children nodes}
         {
            nodeStack.push(currentNode);        //push nodes onto stack
            currentNode = currentNode.left;     //advance to next left node reference
         }
         if(!nodeStack.isEmpty())               //check if stack is empty
         {
            nextNode = nodeStack.pop();         // if not, pop last left node
            currentNode = nextNode.right;       //set work node to right node reference
         }
         else                                   //check if stack is empty
            throw new NoSuchElementException(); // if so, throw exception
      
      return(nextNode.key);                     //return key value
      }
      //
      public int previous()                     //return previous binarty tree value
      {
         Node nextNode = null;                  //initialize work node
         
         while(currentNode != null)             //loop thru right tree nodes until last leaf{no children nodes}
         {
            nodeStack.push(currentNode);        //push nodes onto stack
            currentNode = currentNode.right;    //advance to next right node reference
         }
         if(!nodeStack.isEmpty())               //check if stack is empty
         {
            nextNode = nodeStack.pop();         // if not, pop last right node
            currentNode = nextNode.left;        //set work node to left node reference
         }
         else                                   //check if stack is empty
            throw new NoSuchElementException(); // if so, throw exception

      return(nextNode.key);                     //return key value
      }
      //
      public int remove()                       //unsupported method
      {
         throw new UnsupportedOperationException();   //throw exception
      }
   };   
//--------------------------------------------------------------------------------------------------
//step thru binary tree one item at a time:
// beginning at the first item & stepping in order toward the last item or
// beginning at the last item & stepping in reverse order toward the first item
   String iterateTree(String direction, int stepCount)
   {
      tmpString2 = "";                          //initialize return work area
      
      if(direction.equals("Forward"))           //check if iteration is forward
      {
         inorderIterator iT1 = new inorderIterator(root);   //create iterator object
      
         for(int i=0;i<stepCount;i++)           //step forward thru tree a set number of items
         {
            if(iT1.hasNext())                   //check is next item is available
               tmpString2 = tmpString2 + String.format("[%3d]", iT1.next());  // if so, add to return string
         }
      }
      else                                      //if iteration is backward
      {      
         inorderIterator iT2 = new inorderIterator(root);   //create iterator object

         for(int i=nodeCount;i>nodeCount-stepCount;i--)     //step backward thru tree a set number of items
         {
            if(iT2.hasNext())                   //check is next item is available
               tmpString2 = tmpString2 + String.format("[%3d]", iT2.previous()); // if so, add to return string
         }
      }
      if (tmpString2.trim() == "")              //check if return string is empty
         return("empty");                       // if so, return 'empty' for display
      else                                      //
         return(tmpString2.trim());             // otherwise, return items
   }      
//--------------------------------------------------------------------------------------------------
//recusively add binary tree node
//{overloaded method}
   Node addNode(Node node, int key) {
      if (node == null) {                       //check if new node is to be added
         Node newNode = new Node(key);          //create new node with key value
         nodeCount++;                           //increment node count
      return(newNode);                          //return reference to new node to be added
      }
      else {
         if (key == node.key) {                 //check if duplicate node exists
            dupNodes++;                         // if so, increment dup count
            ;                                   //ignore duplicate node keys
         }
         else {                                 //if not dup, check which branch to update
            if (key < node.key) {               //check if passed key is < current node's key
               node.left = addNode(node.left, key);   // if so, add node to left branch
            }
            else {                              //if passed key is > current node's key
               node.right = addNode(node.right, key); // if so, add node to right branch
            }    
         }
         return(node);                          //return passed node reference
      }
   }
//--------------------------------------------------------------------------------------------------
//add binary tree node & return string of updated tree key values
//called by 'formatOutput' routine
//{overloaded method}
   String addNode(int key) {
      root = addNode(root, key);                //start at 'root' to add new tree node 
      return(listNode("Ascending"));            //return string of updated tree key values
   }
//--------------------------------------------------------------------------------------------------
//locate end of left tree branch of passed node
   Node minKeyNode(Node node) {
      Node currentNode = node;                  //save passed node as current
      
      while (currentNode.left != null)          //search to end of left tree branch
         currentNode = currentNode.left;        //save new left node reference
         
   return(currentNode);                         //return node reference of end of branch
   }
//--------------------------------------------------------------------------------------------------
//recursively delete binary tree node
//{overloaded method}
   Node deleteNode(Node node, int key) {
      if (node == null) {                       //check if end of tree branch
         return(node);                          // if so, do nothing & return passed reference
      }
      else if (key < node.key) {                //check if key to be deleted is < node key         
         node.left = deleteNode(node.left, key);   // if so, delete from left tree branch
         return(node);                          //return passed reference
      }
      else if (key > node.key){                 //check if key to be deleted is > node key
         node.right = deleteNode(node.right, key); // if so, delete from right tree branch
         return(node);                          //return passed reference
      }
      else {
         if (node.left == null) {               //check if node has only 1 child
            tmpNode = node.right;               // if so, save right node reference
            node = null;                        //delete node from tree
            nodeCount--;                        //decrement node counter
            return(tmpNode);                    //return node reference of deleted node
         }
         else if (node.right == null) {         //check if node has only 1 child
            tmpNode = node.left;                //if so, save left node reference
            node = null;                        //delete node from tree
            nodeCount--;                        //decrement node counter
            return(tmpNode);                    //return node reference of deleted node
         } 
         else {                                 //node has 2 children
            tmpNode = minKeyNode(node.right);   //get smallest node in right tree branch
            node.key = tmpNode.key;             //copy content to this node
            node.right = deleteNode(node.right, tmpNode.key);  //delete node
         return(node);                          //return updated node reference
         }
      }
   }
//--------------------------------------------------------------------------------------------------
//delete binary tree node
//called by 'formatOutput' routine
//{overloaded method}
   String deleteNode(int key) {
      if(findNode(key))                         //check if key in tree
      {
      root = deleteNode(root, key);             // if so, delete key from tree
      return(listNode("Ascending"));            //return updated list of tree values
      }
      else
         return("Key Not Found");               //if key not in tree, return error message
   }
//--------------------------------------------------------------------------------------------------
//recursively search binary tree nodes for key value
//{overloaded method}
   Node findNode(Node node, int key) {
      if (node == null || node.key == key)      //check if end of tree branch or key found
         return(node);                          // if si, return node reference
      
      if (node.key > key)                       //check if current node key > key
         return(findNode(node.left, key));      // if so, check left tree branch
      else
         return(findNode(node.right, key));     //otherwise check right tree branch
   }
//--------------------------------------------------------------------------------------------------
//return boolean result of binary tree search for key value
//called by 'formatOutput' routine
//{overloaded method}
   boolean findNode(int key) {
      if (findNode(root, key) != null)          //check if key value is in tree
         return(true);                          // if so, return 'true'
      else
         return(false);                         //otherwise return 'false'
   }
//--------------------------------------------------------------------------------------------------
//recursively list binary tree nodes 
//{overloaded method}
   String listNode(Node node) {
      if (node != null) {                       //check if end of tree branch
         if (tmpDirection.equals("Ascending"))  // if not, then check list order {ascending/decending}
            listNode(node.left);                //if ascending then start with left tree branch
         else                                   
            listNode(node.right);               // if not, then start with right tree branch
            
         tmpString1 = tmpString1 + (String.format(node.key + " "));  //format output display string
         
         if (tmpDirection.equals("Ascending"))  //if ascending then continnue with right tree branch
            listNode(node.right);
         else
            listNode(node.left);                // if not, then continue with left tree branch
      }
   return(tmpString1);                          //return formatted listing string
   }
//--------------------------------------------------------------------------------------------------
//list binary tree nodes
//called by 'formatOutput' routine
//{overloaded method}
   String listNode(String direction) {
      tmpString1 = "";                          //initialize formatted list string
      tmpDirection = direction;                 //save list output order
      tmpString1 = listNode(root).trim();       //list tree starting at the 'root'
      if (!tmpString1.equals(""))               //remove leading & trailing spaces if formatted string
         return(tmpString1);                    //return formatted listing string
      else
         return("empty");                       //return 'empty' if tree is empty
   }
//--------------------------------------------------------------------------------------------------
//recursively delete binary tree 
//{overloaded method}
   Node deleteTree(Node node) {
      if (node != null) {                       //check if end of branch
         deleteTree(node.left);                 //delete left branch
         deleteNode(node.key);                  //delete indicated node
         deleteTree(node.right);                //delete right branch
      }
   return(node);                                //return passed node
   }
//--------------------------------------------------------------------------------------------------
//delete binary tree 
//called by 'formatOutput' routine
//{overloaded method}
   String deleteTree() {
      tmpString2 = listNode("Ascending");       //get listing of tree before deletion 
      deleteTree(root);                         //start at 'root' and delete all nodes
   return(tmpString2);                          //return tree list before deletion
   }
//--------------------------------------------------------------------------------------------------
//return count of binary tree nodes
   int treeSize() {
      ;
   return(nodeCount);                           //return tree size
   }
//--------------------------------------------------------------------------------------------------
//map binary tree nodes 
//{overloaded method}
   String mapTree(Node node) {
      if (node != null) {                       //check if end of tree branch
         if ((node.left != null) && (node.right != null))   //format display string for node with 2 children
            tmpString2 = tmpString2 + String.format("%s[%4s] left[%4s] right[%4s]\n",nodeName, node.key, node.left.key, node.right.key);
         else if ((node.left != null) && (node.right == null)) //format display string for node with 1 child on left
            tmpString2 = tmpString2 + String.format("%s[%4s] left[%4s] right[%4s]\n",nodeName, node.key, node.left.key, "null");
         else if ((node.left == null) && (node.right != null)) //format display string for node with 1 child on right
            tmpString2 = tmpString2 + String.format("%s[%4s] left[%4s] right[%4s]\n",nodeName, node.key, "null", node.right.key);
         else                                   //format display string for node with no children
            tmpString2 = tmpString2 + String.format("%s[%4s] left[%4s] right[%4s]\n",nodeName, node.key, "null", "null");


         if (!tmpString2.equals(""))            //check if not 1st time
            tmpString2 = tmpString2 + String.format("%18s", " "); // if so, indent output
      
         nodeName = "lNode";                    //set indicator as left node
         mapTree(node.left);                    //follow left tree branch
            ;
         nodeName = "rNode";                    //set indicator as right node
         mapTree(node.right);                   //follow right tree branch
      }
   return(tmpString2);                          //return formatted tree map
   }
//--------------------------------------------------------------------------------------------------
//map binary tree nodes 
//called by 'formatOutput' routine
//{overloaded method}
   String mapTree() {
      nodeName = "root ";                       //set indicator as root node
      tmpString2 = "";                          //initialize display string
      tmpString2 = mapTree(root).trim();        //remove leading & trailing spaces
      tmpString2 = tmpString2 + String.format("]\n\n                 [Tree Height [%2d]   Node Count [%2d]", this.getHeight(), this.getNumberOfNodes());
   return(tmpString2);                          //return tree map string
   }
//--------------------------------------------------------------------------------------------------
//traverse binary tree from the 'root' counting the nodes to reach the farthest leaf of the tree  
//{overloaded method}
   int getHeight() {
      ;
   return(getHeight(root));                     //return 
   }
//--------------------------------------------------------------------------------------------------
//recursively traverse binary tree from the 'root' counting the nodes to reach the farthest leaf of the tree  
//{overloaded method}
   int getHeight(Node node) {
      int treeHeight=0;
      
      if(node!=null)
      {
         treeHeight = 1 + Math.max(getHeight(node.left),
                               getHeight(node.right));
      } 
   return(treeHeight);
   }
//--------------------------------------------------------------------------------------------------
//read tree to determine the current number of nodes 
//{overloaded method}
  int getNumberOfNodes()
   {
      ;
   return(getNumberOfNodes(root));
   }
//--------------------------------------------------------------------------------------------------
//recursive read of tree to determine the current number of nodes 
//{overloaded method}
   int getNumberOfNodes(Node node)
   {
      int numberOfLeftNodes = 0;
      int numberOfRightNodes = 0;
      
      if (node.left != null)
         numberOfLeftNodes = getNumberOfNodes(node.left);

      if (node.right != null)
         numberOfRightNodes = getNumberOfNodes(node.right);
         
   return(1 + numberOfLeftNodes + numberOfRightNodes);
   }
//--------------------------------------------------------------------------------------------------
   public static void main(String[] args) throws Exception {
      binaryTreeInteger bTree = new binaryTreeInteger("intTree1");
      binaryTreeInteger xTree = new binaryTreeInteger("intTree2");

      try {
         //populate xTree & list nodes
         StartUp(xTree);
         //populate bTree & test all supported binary tree methods
         Process(bTree);
         //delete xTree & list nodes
         Wrap_Up(xTree);
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
//populate xTree & map tree nodes & list tree nodes 
   public static void StartUp(binaryTreeInteger bTree) {
      System.out.println("\nStart of Program\n");
      buildBinaryTree(bTree, 20);
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "mapTree", "");
      //
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "iterateTree", "10");
      
      formatOutput(bTree, "addNode", "0");
      formatOutput(bTree, "addNode", "0");
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "iterateTree", "10");

   return;
   } 
//--------------------------------------------------------------------------------------------------
//populate bTree & test all supported binary tree methods
   public static void Process(binaryTreeInteger bTree) {
      buildBinaryTree(bTree, 10);
      //
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "iterateTree", "20");
      //
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "mapTree", "");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteTree", "");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "addNode", "1000");
      formatOutput(bTree, "addNode", "2000");
      formatOutput(bTree, "addNode", "3000");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "2000");
      formatOutput(bTree, "treeSize", "");
      //
      buildBinaryTree(bTree, 20);
      //
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "mapTree", "");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "addNode", "9999");
      formatOutput(bTree, "findNode", "1000");
      formatOutput(bTree, "findNode", "2000");
      formatOutput(bTree, "listNode", "Decending");
      formatOutput(bTree, "findNode", "9999");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "50");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "1000");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "80");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "0");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteNode", "10");
      formatOutput(bTree, "treeSize", "");
      formatOutput(bTree, "deleteTree", "");
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "iterateTree", "10");
   return;
   } 
//--------------------------------------------------------------------------------------------------
//delete xTree & list nodes
   public static void Wrap_Up(binaryTreeInteger bTree) {
      formatOutput(bTree, "deleteTree", "");
      formatOutput(bTree, "listNode", "Ascending");
      formatOutput(bTree, "iterateTree", "10");
   return;
   } 
//--------------------------------------------------------------------------------------------------
//load passed binary tree with random integers
   public static void buildBinaryTree(binaryTreeInteger bTree, int treeSize) 
   {
      System.out.printf("--Populate '%s' binary tree---------------------------------------\n", bTree.treeName);
      Random randomInteger = new Random();         //create number generator instance
      int randomUpperLimit = treeSize * 10;        //set random number generator upper limit
      bTree.dupNodes = 0;                          //initialize dup node count
      
      timer("Start");                              //start timer
      for(int i=0;i<treeSize;i++)                  //load random integers into a binary tree
      {
         bTree.addNode(randomInteger.nextInt(randomUpperLimit));  //add random integer into binary tree 
      }
      timer("Stop");                               //stop timer & display elasped time in miliseconds
      
      System.out.printf("\nNumber of nodes = %d\n", bTree.nodeCount);            //display number of tree nodes
      System.out.printf("Number of duplicate nodes = %d\n\n", bTree.dupNodes);   //display dup tree nodes skipped

   return;
   }
//--------------------------------------------------------------------------------------------------
//test binary tree functions & format output for display 
   public static void formatOutput(binaryTreeInteger bTree, String command, String arg) {
      int tmp_integer;

      switch (command) {
         case "addNode":
            tmp_integer = Integer.valueOf(arg);
            System.out.printf("--Add node to '%s' binary tree------------------------------------\n" + 
                              "   'addNode' [%d]->[" + bTree.addNode(tmp_integer) + "][%d]\n\n", bTree.treeName, tmp_integer, bTree.treeSize());
         break;
         case "deleteNode":
            tmp_integer = Integer.valueOf(arg);
            System.out.printf("--Delete node from '%s' binary tree-------------------------------\n" +
                              "'deleteNode' [%d]->[" + bTree.deleteNode(tmp_integer) + "][%d]\n\n", bTree.treeName, tmp_integer, bTree.treeSize());
         break;
         case "findNode":
            tmp_integer = Integer.valueOf(arg);
            System.out.printf("--Find key value in '%s' binary tree------------------------------\n" + 
                              "  'findNode' [%d]->[" + bTree.findNode(tmp_integer) + "]\n\n", bTree.treeName, tmp_integer);
         break;
         case "listNode":
            System.out.printf("--List nodes of '%s' binary tree----------------------------------\n" + 
                              "  'listNode' [%s]->[" + bTree.listNode(arg) + "][%d]\n\n", bTree.treeName, arg, bTree.treeSize());
         break;
         case "treeSize":
            System.out.printf("--Get size of '%s' binary tree------------------------------------\n" + 
                              "  'treeSize' []->[" + bTree.treeSize() + "]\n\n", bTree.treeName);
         break;
         case "deleteTree":
            System.out.printf("--Delete '%s' binary tree-----------------------------------------\n" + 
                              "'deleteTree' []->[" + bTree.deleteTree() + "]\n\n", bTree.treeName);
         break;
         case "mapTree":
            System.out.printf("--Map '%s' binary tree--------------------------------------------\n" + 
                              "   'mapTree' []->[" + bTree.mapTree() + "]\n\n", bTree.treeName);
         break;
         case "iterateTree":
            tmp_integer = Integer.valueOf(arg);
            System.out.printf("--Iterate '%s' binary tree----------------------------------------\n" + 
                              "'iterateTree'[%s/%2d]->[" + bTree.iterateTree("Forward", tmp_integer-5) + "]\n" +
                              "'iterateTree'[%s/%2d]->[" + bTree.iterateTree("Reverse", tmp_integer+5) + "]\n\n", bTree.treeName, "Forward", tmp_integer-5, "Reverse", tmp_integer+5);
         break;
      }

   return;
   } 
//--------------------------------------------------------------------------------------------------
//start/stop timer based on passed argument
// 'Start' saves system clock start time in milliseconds
//  'Stop' saves system clock end time in milliseconds & then calculates the elapsed time in milliseconds
   public static void timer(String function)
   {
      Date currentDate = new Date();
      if(function == "Start")
      {
         startTime = currentDate.getTime();
         System.out.printf("  Start time{ms}: [%d]\n",startTime);
      }
      else if(function == "Stop")
      {
         stopTime =currentDate.getTime();
         elapsedTime = stopTime - startTime;
         System.out.printf("   Stop time{ms}: [%d]\n",stopTime);
         System.out.printf("Elapsed time{ms}: [%d]\n",elapsedTime);
      }
      
   return;
   }
//--------------------------------------------------------------------------------------------------
/*
Note: The values in the 2 binary trees in this program are loaded using a random number  
      generator so the displayed values will change each time the program is executed.
      
  ----jGRASP exec: java binaryTreeInteger
 
 Start of Program
 
 --Populate 'intTree2' binary tree---------------------------------------
   Start time{ms}: [1649523886172]
    Stop time{ms}: [1649523886179]
 Elapsed time{ms}: [7]
 
 Number of nodes = 20
 Number of duplicate nodes = 0
 
 --List nodes of 'intTree2' binary tree----------------------------------
   'listNode' [Ascending]->[3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193][20]
 
 --Map 'intTree2' binary tree--------------------------------------------
    'mapTree' []->[root [   3] left[null] right[ 174]
                   rNode[ 174] left[ 112] right[ 178]
                   lNode[ 112] left[  88] right[ 123]
                   lNode[  88] left[  82] right[  95]
                   lNode[  82] left[  36] right[null]
                   lNode[  36] left[  19] right[  72]
                   lNode[  19] left[   7] right[null]
                   lNode[   7] left[null] right[null]
                   rNode[  72] left[  56] right[  77]
                   lNode[  56] left[null] right[null]
                   rNode[  77] left[null] right[null]
                   rNode[  95] left[null] right[ 111]
                   rNode[ 111] left[null] right[null]
                   rNode[ 123] left[null] right[ 143]
                   rNode[ 143] left[ 129] right[ 168]
                   lNode[ 129] left[null] right[null]
                   rNode[ 168] left[null] right[null]
                   rNode[ 178] left[null] right[ 181]
                   rNode[ 181] left[null] right[ 193]
                   rNode[ 193] left[null] right[null]]
 
                  [Tree Height [ 8]   Node Count [20]]
 
 --List nodes of 'intTree2' binary tree----------------------------------
   'listNode' [Ascending]->[3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193][20]
 
 --Iterate 'intTree2' binary tree----------------------------------------
 'iterateTree'[Forward/ 5]->[[  3][  7][ 19][ 36][ 56]]
 'iterateTree'[Reverse/15]->[[193][181][178][174][168][143][129][123][112][111][ 95][ 88][ 82][ 77][ 72]]
 
 --Add node to 'intTree2' binary tree------------------------------------
    'addNode' [0]->[0 3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193][21]
 
 --Add node to 'intTree2' binary tree------------------------------------
    'addNode' [0]->[0 3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193][21]
 
 --List nodes of 'intTree2' binary tree----------------------------------
   'listNode' [Ascending]->[0 3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193][21]
 
 --Iterate 'intTree2' binary tree----------------------------------------
 'iterateTree'[Forward/ 5]->[[  0][  3][  7][ 19][ 36]]
 'iterateTree'[Reverse/15]->[[193][181][178][174][168][143][129][123][112][111][ 95][ 88][ 82][ 77][ 72]]
 
 --Populate 'intTree1' binary tree---------------------------------------
   Start time{ms}: [1649523886188]
    Stop time{ms}: [1649523886188]
 Elapsed time{ms}: [0]
 
 Number of nodes = 10
 Number of duplicate nodes = 0
 
 --List nodes of 'intTree1' binary tree----------------------------------
   'listNode' [Ascending]->[31 40 43 48 51 58 63 69 76 85][10]
 
 --Iterate 'intTree1' binary tree----------------------------------------
 'iterateTree'[Forward/15]->[[ 31][ 40][ 43][ 48][ 51][ 58][ 63][ 69][ 76][ 85]]
 'iterateTree'[Reverse/25]->[[ 85][ 76][ 69][ 63][ 58][ 51][ 48][ 43][ 40][ 31]]
 
 --List nodes of 'intTree1' binary tree----------------------------------
   'listNode' [Ascending]->[31 40 43 48 51 58 63 69 76 85][10]
 
 --Map 'intTree1' binary tree--------------------------------------------
    'mapTree' []->[root [  40] left[  31] right[  58]
                   lNode[  31] left[null] right[null]
                   rNode[  58] left[  51] right[  85]
                   lNode[  51] left[  43] right[null]
                   lNode[  43] left[null] right[  48]
                   rNode[  48] left[null] right[null]
                   rNode[  85] left[  76] right[null]
                   lNode[  76] left[  63] right[null]
                   lNode[  63] left[null] right[  69]
                   rNode[  69] left[null] right[null]]
 
                  [Tree Height [ 6]   Node Count [10]]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[10]
 
 --Delete 'intTree1' binary tree-----------------------------------------
 'deleteTree' []->[31 40 43 48 51 58 63 69 76 85]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[0]
 
 --Add node to 'intTree1' binary tree------------------------------------
    'addNode' [1000]->[1000][1]
 
 --Add node to 'intTree1' binary tree------------------------------------
    'addNode' [2000]->[1000 2000][2]
 
 --Add node to 'intTree1' binary tree------------------------------------
    'addNode' [3000]->[1000 2000 3000][3]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[3]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [2000]->[1000 3000][2]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[2]
 
 --Populate 'intTree1' binary tree---------------------------------------
   Start time{ms}: [1649523886191]
    Stop time{ms}: [1649523886191]
 Elapsed time{ms}: [0]
 
 Number of nodes = 22
 Number of duplicate nodes = 0
 
 --List nodes of 'intTree1' binary tree----------------------------------
   'listNode' [Ascending]->[15 19 25 30 41 46 61 72 74 79 82 97 114 122 126 152 167 186 192 199 1000 3000][22]
 
 --Map 'intTree1' binary tree--------------------------------------------
    'mapTree' []->[root [1000] left[ 199] right[3000]
                   lNode[ 199] left[  25] right[null]
                   lNode[  25] left[  15] right[  74]
                   lNode[  15] left[null] right[  19]
                   rNode[  19] left[null] right[null]
                   rNode[  74] left[  46] right[ 122]
                   lNode[  46] left[  41] right[  61]
                   lNode[  41] left[  30] right[null]
                   lNode[  30] left[null] right[null]
                   rNode[  61] left[null] right[  72]
                   rNode[  72] left[null] right[null]
                   rNode[ 122] left[ 114] right[ 152]
                   lNode[ 114] left[  97] right[null]
                   lNode[  97] left[  79] right[null]
                   lNode[  79] left[null] right[  82]
                   rNode[  82] left[null] right[null]
                   rNode[ 152] left[ 126] right[ 167]
                   lNode[ 126] left[null] right[null]
                   rNode[ 167] left[null] right[ 192]
                   rNode[ 192] left[ 186] right[null]
                   lNode[ 186] left[null] right[null]
                   rNode[3000] left[null] right[null]]
 
                  [Tree Height [ 9]   Node Count [22]]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[22]
 
 --Add node to 'intTree1' binary tree------------------------------------
    'addNode' [9999]->[15 19 25 30 41 46 61 72 74 79 82 97 114 122 126 152 167 186 192 199 1000 3000 9999][23]
 
 --Find key value in 'intTree1' binary tree------------------------------
   'findNode' [1000]->[true]
 
 --Find key value in 'intTree1' binary tree------------------------------
   'findNode' [2000]->[false]
 
 --List nodes of 'intTree1' binary tree----------------------------------
   'listNode' [Decending]->[9999 3000 1000 199 192 186 167 152 126 122 114 97 82 79 74 72 61 46 41 30 25 19 15][23]
 
 --Find key value in 'intTree1' binary tree------------------------------
   'findNode' [9999]->[true]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[23]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [50]->[Key Not Found][23]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[23]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [1000]->[15 19 25 30 41 46 61 72 74 79 82 97 114 122 126 152 167 186 192 199 3000 9999][22]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[22]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [80]->[Key Not Found][22]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[22]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [0]->[Key Not Found][22]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[22]
 
 --Delete node from 'intTree1' binary tree-------------------------------
 'deleteNode' [10]->[Key Not Found][22]
 
 --Get size of 'intTree1' binary tree------------------------------------
   'treeSize' []->[22]
 
 --Delete 'intTree1' binary tree-----------------------------------------
 'deleteTree' []->[15 19 25 30 41 46 61 72 74 79 82 97 114 122 126 152 167 186 192 199 3000 9999]
 
 --List nodes of 'intTree1' binary tree----------------------------------
   'listNode' [Ascending]->[empty][0]
 
 --Iterate 'intTree1' binary tree----------------------------------------
 'iterateTree'[Forward/ 5]->[empty]
 'iterateTree'[Reverse/15]->[empty]
 
 --Delete 'intTree2' binary tree-----------------------------------------
 'deleteTree' []->[0 3 7 19 36 56 72 77 82 88 95 111 112 123 129 143 168 174 178 181 193]
 
 --List nodes of 'intTree2' binary tree----------------------------------
   'listNode' [Ascending]->[empty][0]
 
 --Iterate 'intTree2' binary tree----------------------------------------
 'iterateTree'[Forward/ 5]->[empty]
 'iterateTree'[Reverse/15]->[empty]
 
 End of Program
 
  ----jGRASP: operation complete.

*/
}
