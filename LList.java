//--------------------------------------------------------------------------------------------------
//	 Programmer: Philip Nicholas					  
//	 Assignment: Programming Project 3: Linked List
//        Date: 03/01/2022
// Description: Refer to Chapter12 LList. Adding nodes to or removing nodes from a linked chain 
//              requires a special case when the operation is at the beginning of the chain. 
//              To eliminate this special case, you can add a dummy node at the beginning of the 
//              chain. the dummy node is always present but does not contain a list entry(data). 
//              The chain then is never empty and so the head reference is never null, even when the 
//              list is empty. Modify the class LList as presented in that chapter, by adding the 
//              dummy node to the chain. Test your implementation.
//--------------------------------------------------------------------------------------------------
import java.io.*;
import java.lang.*;
import java.util.*;
//--------------------------------------------------------------------------------------------------
public class LList<T> implements ListInterface<T>
{
   private Node firstNode;             //reference to 1st node of chain
   private int numberOfEntries;        //number of nodes in list
   
   public LList()                      //linked list class constructor
   {
      initializeDataFields();          //initalize firstNode & numberOfEntries
   }                                   //end linked list class constructor
//--------------------------------------------------------------------------------------------------
   private class Node                  //private inner class
   {
      private T    data;               //node key
      private Node next;               //link to next node
      
      private Node(T dataPortion)      //node constructor with data parameter
      {
         this(dataPortion, null);      //initialize node
      }                                //end constructor
      
      private Node(T dataPortion, Node nextNode)   //node constructor with data & next node reference
      {
         data = dataPortion;           //initialize node data
         next = nextNode;              //initialize node next reference
      }                                //end constructors

      private T getData()              //get node data
      {
         return(data);                 //return data
      }                                //end getData
      
      private void setData(T newData)  //set node data
      {
         data = newData;               //set node data
      }                                //end setData
      
      private Node getNextNode()       //get next node reference
      {
         return(next);                 //return next node reference
      }                                //end getNextNode
      
      private void setNextNode(Node nextNode)   //set next node reference
      {
         next = nextNode;              //set next node reference
      }                                //end setNextNode
   }                                   //end Node
//--------------------------------------------------------------------------------------------------
   private void initializeDataFields() //initialize the node class's data fields to indicate enpty list
   {
      firstNode = new Node(null);;     //initialize root
      numberOfEntries = 0;             //set node count to zero
   }                                   //end of initializeDataFields
//--------------------------------------------------------------------------------------------------
   private Node getNodeAt(int givenPosition)    //return reference to the node at a given position
   {  
      //assertion (firstNode != null) && (1 <= givenPosition) && givenPosition <= numberofNodes)
      Node currentNode = firstNode;
      //traverse the chain to locate the desired node
      for(int counter = 1; counter <= givenPosition; counter++ )   //for loop to advance tmp node pointer
      {
         currentNode = currentNode.getNextNode();  //advance tmp node pointer to next node
      }                                //assertion currentNode != null
      return(currentNode);             //return node at requested position 
   }                                   //end of getNodeAt
//--------------------------------------------------------------------------------------------------
   public void clear()                 //clear all entries from linked list
   {
      initializeDataFields();          //initalize firstNode & numberOfEntries
   }                                   //end of clear
//--------------------------------------------------------------------------------------------------
   public int getLength()              //get number of nodes
   {
      return(numberOfEntries);         //return number of entries
   }                                   //end getLength
//--------------------------------------------------------------------------------------------------
   public void add(T newEntry)         //add node to end of linked list
   {
      Node newNode = new Node(newEntry);  //create node to be added
      
      Node lastNode = getNodeAt(numberOfEntries);  //get reference of last node
      lastNode.setNextNode(newNode);   //set next reference of last node       

      numberOfEntries++;               //
   }                                   //end add
//--------------------------------------------------------------------------------------------------
   public void add(int givenPosition, T newEntry)  //add node at given position
   {
      if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) //validate position value
      {
         Node newNode = new Node(newEntry);  //create node to be added

         Node nodeBefore = getNodeAt(givenPosition - 1); //get reference of previous node
         Node nodeAfter  = nodeBefore.getNextNode();     //get reference of current node
         newNode.setNextNode(nodeAfter);                 //set new node's next to current node's reference 
         nodeBefore.setNextNode(newNode);                //set previous node's next to new node's current reference

         numberOfEntries++;            //increment node counter
      }
      else
      {                                //display position value 'out of range' error message
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
      }                                //end if
   }                                   //end add
//--------------------------------------------------------------------------------------------------
   public boolean isEmpty()            //check if list is empty
   {
      boolean result;                  //
      if (numberOfEntries == 0)        //or getLength() == 0
         result = true;                //if zero, list is empty
      else
         result = false;               //if > 0, list is not empty
      
      return(result);                  //return result
   }                                   //end isEmpty
//--------------------------------------------------------------------------------------------------
   public T[] toArray()                //return list as an array
   {
      //the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")   //
      
      T[] result = (T[])new Object[numberOfEntries];  //create tmp array

      int index = 0;                   //initialize index into array
      Node currentNode = firstNode.getNextNode();  //initialize tmp node reference
      while ((index < numberOfEntries) && (currentNode != null))  //check that index & current node within range
      {
         result[index] = currentNode.getData();    //get data from current node & place in array
         currentNode = currentNode.getNextNode();  //advance current node reference to next node 
         index++;                      //increment index
      }                                //end while
      return(result);                  //return array
   }                                   //end toArray
//--------------------------------------------------------------------------------------------------
   public T getEntry(int givenPosition)   //get data from node referenced by position
   {
      if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) //validate position value
         return(getNodeAt(givenPosition).getData());  //return data from node
      else                             //display position value 'out of range' error message
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   }                                   //end getEntry
//--------------------------------------------------------------------------------------------------
   public T remove(int givenPosition)  //remove node at given position
   {
      T result = null;                 //tmp data value of node removed
      
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))   //validate position value
      {
         Node nodeBefore = getNodeAt(givenPosition - 1); //get reference of previous node
         Node nodeToRemove = nodeBefore.getNextNode();   //get reference of node to remove
         result = nodeToRemove.getData();                //save data value of node to be removed 
         Node nodeAfter = nodeToRemove.getNextNode();    //get reference of node after node to be removed
         nodeBefore.setNextNode(nodeAfter);              //set next reference of previous node

         numberOfEntries--;            //decrement node counter
         return(result);               //return value of removed node
      }
      else                             //display position value 'out of range' error message
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
   }                                   //end remove
//--------------------------------------------------------------------------------------------------
   public T replace(int givenPosition, T newEntry) //replace data value of indicated node
   {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))   //validate position value
      {
         Node desiredNode = getNodeAt(givenPosition); //get reference of requested node
         T originalEntry = desiredNode.getData();     //get data value of requested node
         desiredNode.setData(newEntry);               //set new data value in requested node
         return(originalEntry);                       //return previous data value of requested node
      }    
      else                             //display position value 'out of range' error message
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   }                                   //end replace
//--------------------------------------------------------------------------------------------------
   public boolean contains(T anEntry)  //check if list contains an node with the indicated data value
   {
      boolean found = false;           //initialize t/f indicator
      Node currentNode = firstNode.getNextNode();  //set tmp node reference to 1st node
      
      while(!found && currentNode != null)   //while loop to search list for requested data value
      {
         if(anEntry.equals(currentNode.getData())) //compare requested value to current node value
            found = true;              //set t/f indicator to true
         else
            currentNode = currentNode.getNextNode();  //advance tmp node reference to next node reference
      }                                //end while
      return(found);                   //return t/f indicator
   }                                   //end contains
//--------------------------------------------------------------------------------------------------
public static void main(String[] args)          
{
   System.out.println("--Original 'main' method to test implementation---------------------");

   System.out.println("Create an empty list");   
   ListInterface<String> myList = new LList<>();
 
   System.out.println("List should be empty: isEmpty returns " + 
                      myList.isEmpty() + ".");

   System.out.println("\nTesting add to end:");
   myList.add("15");
   myList.add("25");
   myList.add("35");
   myList.add("45");
   System.out.println("List should contain 15 25 35 45.");

   displayList(myList);

   System.out.println("List should not be empty: isEmpty returns " + 
                      myList.isEmpty() + ".");

   System.out.println("\nTesting clear():");
   myList.clear();
   System.out.println("List should be empty: isEmpty returns " + 
                      myList.isEmpty() + ".");

   System.out.println("\n--Updated 'main' method to test implementation----------------------");
   add_ToEnd_test(myList);                
   add_ToPosition_test(myList);
   getLength_test(myList);
   toArray_test(myList);
   getEntry_test(myList);
   remove_test(myList);
   replace_test(myList);
   contains_test(myList);
   clear_test(myList);
return;
}                                //end main
//--------------------------------------------------------------------------------------------------
public static void displayList(ListInterface<String> list)  //display list information 
{
   int numberOfEntries = list.getLength();   //get number of nodes in list      
   System.out.println("The list contains " + numberOfEntries + " entries, as follows:"); //display # of nodes 

   for(int position=1;position<=numberOfEntries;position++) //traverse list
      System.out.println(list.getEntry(position) + " is entry " + position);  //display entry & position
   
   System.out.println();   
}                                      //end displayList
//--------------------------------------------------------------------------------------------------
public static void add_ToEnd_test(ListInterface<String> myList)    //test add to end method
{
   System.out.printf("\n--test add 110, 120, 130, 140 to end of list\n");                      
   System.out.printf("myList items {Before}: %s\n", Arrays.toString(myList.toArray()));                      
   myList.add("110");
   myList.add("120");
   myList.add("130");
   myList.add("140");
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
}                                      //end add_ToEnd_test
//--------------------------------------------------------------------------------------------------
public static void add_ToPosition_test(ListInterface<String> myList)    //test add to position method
{
   System.out.printf("\n--test add 210, 220, 230, 240 to positions 4, 3, 1, 2 of list\n");                      
   System.out.printf("myList items {Before}: %s\n", Arrays.toString(myList.toArray()));                      
   myList.add(4, "210");
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
   myList.add(3, "220");
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
   myList.add(1, "230");
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
   myList.add(2, "240");
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
//    myList.add(0, "250");  <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
//    myList.add(9, "260");  <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));                      
}                                      //end add_ToEnd_test
//--------------------------------------------------------------------------------------------------
public static void getLength_test(ListInterface<String> myList)    //test getLength method
{
   System.out.printf("\n--test getLength of list\n");                      
   System.out.printf("myList items:  %s\n", Arrays.toString(myList.toArray()));    
   System.out.printf("myList length: [%d]\n", myList.getLength());                  
}                                      //end getLength_test
//--------------------------------------------------------------------------------------------------
public static void toArray_test(ListInterface<String> myList)    //test toArray method
{
   System.out.printf("\n--test toArray for list\n");                      
   System.out.printf("myList items: %s\n", Arrays.toString(myList.toArray()));    
}                                      //end toArray_test
//--------------------------------------------------------------------------------------------------
public static void getEntry_test(ListInterface<String> myList)    //test getEntry method
{
   System.out.printf("\n--test getEntry for list\n");                      
   System.out.printf("myList items: %s\n", Arrays.toString(myList.toArray()));    
   for (int i=1;i<=myList.getLength();i++)
      System.out.printf("myList[%d] = %s\n", i, myList.getEntry(i));
}                                      //end getEntry_test
//--------------------------------------------------------------------------------------------------
public static void remove_test(ListInterface<String> myList)    //test remove method
{
   System.out.printf("\n--test remove for list\n");                      
   System.out.printf("myList items {Before}: %s\n", Arrays.toString(myList.toArray()));    
   for (int i=1;i<=myList.getLength();i++) {
      System.out.printf("myList[%d] = %s\n", i, myList.remove(i));
      System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));    
   }
//    System.out.printf("myList[%d] = %s\n", 0, myList.remove(0));   <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:   %s\n", Arrays.toString(myList.toArray()));    
//    System.out.printf("myList[%d] = %s\n", 9, myList.remove(9));   <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:   %s\n", Arrays.toString(myList.toArray()));    
}                                      //end remove_test
//--------------------------------------------------------------------------------------------------
public static void replace_test(ListInterface<String> myList)    //test replace method
{
   System.out.printf("\n--test replace for list\n");                      
   System.out.printf("myList items {Before}: %s\n", Arrays.toString(myList.toArray()));    
   for (int i=1;i<=myList.getLength();i++) {
      System.out.printf("myList[%d] = %s\n", i, myList.replace(i, String.format("%d", i+300)));
      System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));    
   }
//    System.out.printf("myList[%d] = %s\n", 0, myList.replace(0, "305"));   <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:   %s\n", Arrays.toString(myList.toArray()));    
//    System.out.printf("myList[%d] = %s\n", 9, myList.replace(9, "306"));   <--this generated an 'IndexOutOfBoundsException' error as expected
//    System.out.printf("myList items {After}:   %s\n", Arrays.toString(myList.toArray()));    
}                                      //end replace_test
//--------------------------------------------------------------------------------------------------
public static void contains_test(ListInterface<String> myList)    //test contains method
{
   System.out.printf("\n--test contains for list\n");                      
   System.out.printf("myList items: %s\n", Arrays.toString(myList.toArray()));    
   System.out.printf("myList contains [%s] = %s\n", "301", myList.contains("301"));    
   System.out.printf("myList contains [%s] = %s\n", "304", myList.contains("304"));    
   System.out.printf("myList contains [%s] = %s\n", "309", myList.contains("309"));    
}                                      //end contains_test
//--------------------------------------------------------------------------------------------------
public static void clear_test(ListInterface<String> myList)    //test clear & isEmpty methods
{
   System.out.printf("\n--test clear & isEmpty for list\n");                      
   System.out.printf("myList items {Before}: %s\n", Arrays.toString(myList.toArray()));  
   System.out.printf("myList length{Before}: [%d]\n", myList.getLength());     
   System.out.printf("myList is empty = %s\n", myList.isEmpty());    
             
    myList.clear();            
  
   System.out.printf("myList items {After}:  %s\n", Arrays.toString(myList.toArray()));    
   System.out.printf("myList length{After}:  [%d]\n", myList.getLength());                  
   System.out.printf("myList is empty = %s\n", myList.isEmpty());    
}                                      //end clear_test
//--------------------------------------------------------------------------------------------------
}                                      //end LList
/*
   Implements
public interface ListInterface<T>
{
   public void add(T newEntry);
   public void add(int newPosition, T newEntry);
   public T remove(int givenPosition);
   public void clear();
   public T replace(int givenPosition, T newEntry);
   public T getEntry(int givenPosition);
   public T[] toArray();
   public boolean contains(T anEntry);
   public int getLength();
   public boolean isEmpty();
}                           
   Output
 --Original 'main' method to test implementation---------------------
 Create an empty list
 List should be empty: isEmpty returns true.
 
 Testing add to end:
 List should contain 15 25 35 45.
 The list contains 4 entries, as follows:
 15 is entry 1
 25 is entry 2
 35 is entry 3
 45 is entry 4
 
 List should not be empty: isEmpty returns false.
 
 Testing clear():
 List should be empty: isEmpty returns true.
 
 --Updated 'main' method to test implementation----------------------
 
 --test add 110, 120, 130, 140 to end of list
 myList items {Before}: []
 myList items {After}:  [110, 120, 130, 140]
 
 --test add 210, 220, 230, 240 to positions 4, 3, 1, 2 of list
 myList items {Before}: [110, 120, 130, 140]
 myList items {After}:  [110, 120, 130, 210, 140]
 myList items {After}:  [110, 120, 220, 130, 210, 140]
 myList items {After}:  [230, 110, 120, 220, 130, 210, 140]
 myList items {After}:  [230, 240, 110, 120, 220, 130, 210, 140]
 
 --test getLength of list
 myList items:  [230, 240, 110, 120, 220, 130, 210, 140]
 myList length: [8]
 
 --test toArray for list
 myList items: [230, 240, 110, 120, 220, 130, 210, 140]
 
 --test getEntry for list
 myList items: [230, 240, 110, 120, 220, 130, 210, 140]
 myList[1] = 230
 myList[2] = 240
 myList[3] = 110
 myList[4] = 120
 myList[5] = 220
 myList[6] = 130
 myList[7] = 210
 myList[8] = 140
 
 --test remove for list
 myList items {Before}: [230, 240, 110, 120, 220, 130, 210, 140]
 myList[1] = 230
 myList items {After}:  [240, 110, 120, 220, 130, 210, 140]
 myList[2] = 110
 myList items {After}:  [240, 120, 220, 130, 210, 140]
 myList[3] = 220
 myList items {After}:  [240, 120, 130, 210, 140]
 myList[4] = 210
 myList items {After}:  [240, 120, 130, 140]
 
 --test replace for list
 myList items {Before}: [240, 120, 130, 140]
 myList[1] = 240
 myList items {After}:  [301, 120, 130, 140]
 myList[2] = 120
 myList items {After}:  [301, 302, 130, 140]
 myList[3] = 130
 myList items {After}:  [301, 302, 303, 140]
 myList[4] = 140
 myList items {After}:  [301, 302, 303, 304]
 
 --test contains for list
 myList items: [301, 302, 303, 304]
 myList contains [301] = true
 myList contains [304] = true
 myList contains [309] = false
 
 --test clear & isEmpty for list
 myList items {Before}: [301, 302, 303, 304]
 myList length{Before}: [4]
 myList is empty = false
 myList items {After}:  []
 myList length{After}:  [0]
 myList is empty = true
*/
