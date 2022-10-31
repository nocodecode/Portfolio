//--------------------------------------------------------------------------------------------------
//	 Programmer: Philip Nicholas					  
//	 Assignment: Programming Project 4: Sorting
//        Date: 03/24/2022
// Description: Complete the implementation of selection sort and quick sort from your book. 
//              Use your implementations to compare the run times of the sorts on various 
//              arrays of 50000 random integers. See chapter 4, page 150 for a description
//              of how to time a block of Java code. Show the output data as comment at the
//              end  of your program.
//--------------------------------------------------------------------------------------------------
import java.io.*;
import java.lang.*;
import java.util.*;
//--------------------------------------------------------------------------------------------------
public class integerSort {
   static int MIN_SIZE = 4;                     //minimum array size to use insertionSort in quickSort 
   static long stopTime=0;                      //variables used in 'timer' function
   static long startTime=0;                     //
   static long elapsedTime=0;                   //
   static boolean testMode=true;                //true-false indicator which controls integerArray display
//--------------------------------------------------------------------------------------------------
   public static void main(String[] args) throws Exception {
      try {                                     //general exception handler
         int[] integerArray = new int[50000];   //integer array
                  
         StartUp(integerArray);                 //start up functions
         Process(integerArray);                 //processing functions
         Wrap_Up(integerArray);                 //wrap up functions
      }
      catch (Exception e) {                     //
         System.out.println("Unknown Exception Error");
      }
      finally {                                 //
         System.out.println("End of Program");
      }                                         //
      
   return;                                      //
   }
//--------------------------------------------------------------------------------------------------
   public static void StartUp(int[] integerArray){
      System.out.println("\nStart of Program\n");
   return;
   } 
//--------------------------------------------------------------------------------------------------
   public static void Process(int[] integerArray){

      BuildRandomIntArray(integerArray);        //populate integerArray with ramdom integers

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray BEFORE 'selectionSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }

      System.out.printf("Start 'selectionSort'\n");
      timer("Start");                           //start timer
      selectionSort(integerArray);              //sort integerArray using selectionSort
      timer("Stop");                            //stop timer & display elasped sort execution time
      System.out.printf("  End 'selectionSort'\n\n");

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray AFTER  'selectionSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }
      
      BuildRandomIntArray(integerArray);        //populate integerArray with ramdom integers

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray BEFORE 'insertionSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }
      
      System.out.printf("Start 'insertionSort'\n");
      timer("Start");                           //start timer
      insertionSort(integerArray);              //sort integerArray using insertionSort
      timer("Stop");                            //stop timer & display elasped sort execution time
      System.out.printf("  End 'insertionSort'\n\n");

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray AFTER  'insertionSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }
      
      BuildRandomIntArray(integerArray);        //populate integerArray with ramdom integers

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray BEFORE 'quickSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }
      
      System.out.printf("Start 'quickSort'\n");
      timer("Start");                           //start timer
      quickSort(integerArray);                  //sort integerArray using quickSort
      timer("Stop");                            //stop timer & display elasped sort execution time
      System.out.printf("  End 'quickSort'\n\n");

      if(testMode)                              //check if integerArray to be displayed
      {
         System.out.printf("intergerArray AFTER  'quickSort':\n");
         printIntegerArray(integerArray);          //display first 100 & last 100 integerArray entries
      }
      
   return;
   } 
//--------------------------------------------------------------------------------------------------
   public static void Wrap_Up(int[] integerArray){
      ;   
   return;
   } 
//--------------------------------------------------------------------------------------------------
//insertionSort of integerArray used in 'Process' routine which sorts the entire array
//overloaded routine
   public static void insertionSort(int[] integerArray)
   {
      insertionSort(integerArray, 0, integerArray.length-1);   //sort entire integerArray
   return;
   }
//--------------------------------------------------------------------------------------------------
//insertionSort of integerArray used in 'quickSort' routine which sorts which sorts small array segments
//overloaded routine
   public static void insertionSort(int[] integerArray, int firstIndex, int lastIndex)
   {
      for(int index=firstIndex+1;index<lastIndex;index++)   //loop thru integerArray starting at position 1
      {
         int nextToInsert = integerArray[index];   //get next int from unsorted partition to be inserted into sorted partition 
         insertInOrder(nextToInsert, integerArray, firstIndex, index-1);   //insert unsorted int into integerArray
      }                                         //
    
   return;
   }
//--------------------------------------------------------------------------------------------------
//shift unsorted integerArray items to right & then insert new item in sorted partion 
   public static void insertInOrder(int nextToInsert, int[] integerArray, int firstIndex, int lastIndex)
   {
      int index = lastIndex;                    //set index to last position
      while((index >= firstIndex) && (nextToInsert < integerArray[index])) //step thru integerArray from right to left shifting
      {                                                                    //items right until insertion position is located
         integerArray[index+1] = integerArray[index]; //shift integerArray items to right
         index--;                               //decrement position index
      }
      integerArray[index+1] = nextToInsert;     //insert item into sorted partition
   }
//--------------------------------------------------------------------------------------------------
//selectionSort of integerArray used in 'Process' routine which sorts the entire array
//overloaded routine
   public static void selectionSort(int[] integerArray)
   {
      selectionSort(integerArray, 0, integerArray.length-1);   //sort entire integerArray
   return;
   }
//--------------------------------------------------------------------------------------------------
//selectionSort of integerArray used in 'quickSort' routine which sorts small array segments
//{this is not used in 'quickSort' because 'insertionSort' is faster}
//overloaded routine
   public static void selectionSort(int[] integerArray, int firstIndex, int lastIndex)
   {
      for(int index=0;index<lastIndex;index++)  //loop thru integerArray
      {
         int indexOfNextSmallest = getIndexOfSmallest(integerArray, index, lastIndex); //get index of next smallest int
         integerSwap(integerArray, index, indexOfNextSmallest);   //swap integerArray entries
      }                                         //
      
   return;
   }
//--------------------------------------------------------------------------------------------------
   public static int getIndexOfSmallest(int[] integerArray, int firstIndex, int lastIndex)
   {
      int minimumInt = integerArray[firstIndex];   //save int to compare
      int indexOfMinimum = firstIndex;             //save index of int to compare
      
      for(int index=firstIndex+1;index<=lastIndex;index++)  //step thru integerArray segment
      {
         if(integerArray[index] < minimumInt)      //check if intergerArray  item < minimum int
         {
            minimumInt = integerArray[index];      //if so, save new minimum int
            indexOfMinimum = index;                //save  new minimum int index
         }
      }
      
   return(indexOfMinimum);                         //return index of next smallest int
   }
//--------------------------------------------------------------------------------------------------
//swap integerArray items based on index position
   public static void integerSwap(int[] integerArray, int i, int j)
   {
      int tmpInt=integerArray[i];                  //save item 1 which will be replaced
      integerArray[i]=integerArray[j];             //replace item 1 with item 2 
      integerArray[j]=tmpInt;                      //move saved item 1 to location previously occupied by item 2
   
   return;
   }
//--------------------------------------------------------------------------------------------------
//quickSort of integerArray used in 'Process' routine which sorts the entire array
//overloaded routine
   public static void quickSort(int[] integerArray)
   {
      quickSort(integerArray, 0, integerArray.length-1); //sort entire integerArray
   return;
   }
//--------------------------------------------------------------------------------------------------
//quickSort of integerArray used in 'quickSort' routine which recursively sorts the array partitions
//overloaded routine
   public static void quickSort(int[] integerArray, int firstIntIndex, int lastIntIndex)
   {
      if (lastIntIndex - firstIntIndex + 1 < MIN_SIZE)   //check if small array segment to be sorted w/insertionSort
      {
        insertionSort(integerArray, firstIntIndex, lastIntIndex); //sort small segment based on MIN_SIZE
      }
      else                                               //use quickSort for large array segments
      {
         int pivotIndex = partition(integerArray, firstIntIndex, lastIntIndex);  //determine pivot index
         quickSort(integerArray, firstIntIndex, pivotIndex-1);    //recursively sort partition to left of pivot
         quickSort(integerArray, pivotIndex+1, lastIntIndex);     //recursively sort partition to right of pivot
      }
      
   return;
   }
//--------------------------------------------------------------------------------------------------
//create the partition: smaller | pivot | larger and return pivot index
   public static int partition(int[] integerArray, int firstIntIndex, int lastIntIndex)
   {
      int middleIntIndex = (firstIntIndex + lastIntIndex)/2;   //calculate middle index in integerArray
      sortFirstMiddleLast(integerArray, firstIntIndex, middleIntIndex, lastIntIndex); //

      integerSwap(integerArray, middleIntIndex, lastIntIndex-1);  //move middle item to item before last item
      int pivotIndex = lastIntIndex-1;                   //save pivot index
      int pivotValue = integerArray[pivotIndex];         //save pivot value

      int indexFromLeft  = firstIntIndex + 1;            //initialize smaller partition index
      int indexFromRight = lastIntIndex - 2;             //initialize larger partition index

      boolean done = false;                              //initialize loop control
      
      while(!done)                                       //loop thru integerArray
      {
         while(integerArray[indexFromLeft] < (pivotValue))  //check smaller value to pivot
            indexFromLeft++;                             //while smaller < pivot then increment index
 
         while(integerArray[indexFromRight] > (pivotValue)) //check larger value to pivot
            indexFromRight--;                            //while larger > pivot then decrement index

         if(indexFromLeft < indexFromRight)              //check if smaller index < larger index
         {
            integerSwap(integerArray, indexFromLeft, indexFromRight);   //if so then swap smaller & larger values
            indexFromLeft++;                             //increment smaller index
            indexFromRight--;                            //decrement larger index
         }            
         else
            done = true;                                 //if indexes >= then end loop
      }
      integerSwap(integerArray, pivotIndex,indexFromLeft);  //swap final items
      pivotIndex = indexFromLeft;                        //save pivot index
      
   return(pivotIndex);                                   //return new pivot index
   }
//--------------------------------------------------------------------------------------------------
//median-of-three pivot selection which sorts first, middle & last integerArray items to place pivot in proper position
   public static void sortFirstMiddleLast(int[] integerArray, int firstIntIndex, int middleIntIndex, int lastIntIndex)
   {
      if (integerArray[firstIntIndex] > (integerArray[middleIntIndex])) //compare first & middle items
         integerSwap(integerArray, firstIntIndex, middleIntIndex);      //if first > middle then swap items

      if (integerArray[middleIntIndex] > (integerArray[lastIntIndex]))  //compare middle & last items
      {
         integerSwap(integerArray, middleIntIndex, lastIntIndex);       //if middle > last then swap items
         if (integerArray[firstIntIndex] > (integerArray[middleIntIndex])) //recheck first & middle items
            integerSwap(integerArray, firstIntIndex, middleIntIndex);   //if first > middle then swap items
      }

   return;
   }
//--------------------------------------------------------------------------------------------------
//populate integerArray with random integers
   public static void BuildRandomIntArray(int[] integerArray)
   {
      Random randomInteger = new Random();         //create number generator instance
      int randomUpperLimit = 500000;               //set random number generator upper limit
      
      for(int i=0;i<integerArray.length;i++)       //step thru IntegerArray
      {
         integerArray[i] = randomInteger.nextInt(randomUpperLimit);  //insert random integer into integerArray item
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
//print 1st 100 and last 100 items in integerArray {integerArray size must be > 100 items}
   public static void printIntegerArray(int[] integerArray)
   {
      if(integerArray.length > 100)             //check for mimimum integerArray length
      {
         for(int i=0;i<100;i+=10)               //loop thru 1st 100 items of integerArray
            System.out.printf("--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--\n", i, integerArray[i], i+1, integerArray[i+1], i+2, integerArray[i+2], i+3, integerArray[i+3], i+4, integerArray[i+4], i+5, integerArray[i+5], i+6, integerArray[i+6], i+7, integerArray[i+7], i+8, integerArray[i+8], i+9, integerArray[i+9]);
         System.out.printf("\n");               //
      
         for(int i=integerArray.length-100;i<integerArray.length;i+=10) //loop thru last 100 items of integerArray
            System.out.printf("--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--integerArray[%5d]=%6d--\n", i, integerArray[i], i+1, integerArray[i+1], i+2, integerArray[i+2], i+3, integerArray[i+3], i+4, integerArray[i+4], i+5, integerArray[i+5], i+6, integerArray[i+6], i+7, integerArray[i+7], i+8, integerArray[i+8], i+9, integerArray[i+9]);
         System.out.printf("\n");               //
      }
   return;
   }
//--------------------------------------------------------------------------------------------------
}
/*
testMode=false
  ----jGRASP exec: java integerSort
 
 Start of Program
 
 Start 'selectionSort'
   Start time{ms}: [1648311623048]
    Stop time{ms}: [1648311623279]
 Elapsed time{ms}: [231]
   End 'selectionSort'
 
 Start 'insertionSort'
   Start time{ms}: [1648311623280]
    Stop time{ms}: [1648311623466]
 Elapsed time{ms}: [186]
   End 'insertionSort'
 
 Start 'quickSort'
   Start time{ms}: [1648311623467]
    Stop time{ms}: [1648311623474]
 Elapsed time{ms}: [7]
   End 'quickSort'
 
 End of Program
 
  ----jGRASP: operation complete.

testMode=true    
  ----jGRASP exec: java integerSort
 
 Start of Program
 
 intergerArray BEFORE 'selectionSort':
 --integerArray[    0]= 35176--integerArray[    1]=  5148--integerArray[    2]=292675--integerArray[    3]=367072--integerArray[    4]=156483--integerArray[    5]= 91699--integerArray[    6]=335048--integerArray[    7]=385440--integerArray[    8]=452987--integerArray[    9]=   860--
 --integerArray[   10]=262226--integerArray[   11]=107304--integerArray[   12]=117600--integerArray[   13]= 16287--integerArray[   14]=109930--integerArray[   15]=295973--integerArray[   16]=220611--integerArray[   17]=367969--integerArray[   18]=332643--integerArray[   19]=168839--
 --integerArray[   20]=390541--integerArray[   21]=388023--integerArray[   22]= 40459--integerArray[   23]=310665--integerArray[   24]=422284--integerArray[   25]=110277--integerArray[   26]=404510--integerArray[   27]= 53023--integerArray[   28]=189999--integerArray[   29]= 39415--
 --integerArray[   30]=321493--integerArray[   31]=112316--integerArray[   32]=267046--integerArray[   33]=218874--integerArray[   34]=437883--integerArray[   35]=219037--integerArray[   36]=236428--integerArray[   37]=159128--integerArray[   38]=328160--integerArray[   39]=172246--
 --integerArray[   40]=117377--integerArray[   41]= 54063--integerArray[   42]= 41543--integerArray[   43]= 58358--integerArray[   44]=469200--integerArray[   45]=255757--integerArray[   46]=383098--integerArray[   47]=250809--integerArray[   48]=295823--integerArray[   49]=140907--
 --integerArray[   50]=422237--integerArray[   51]=336714--integerArray[   52]=442469--integerArray[   53]=153474--integerArray[   54]=343616--integerArray[   55]=230355--integerArray[   56]=260742--integerArray[   57]= 69358--integerArray[   58]=459694--integerArray[   59]=288355--
 --integerArray[   60]=492441--integerArray[   61]=366685--integerArray[   62]=388098--integerArray[   63]= 11130--integerArray[   64]=290810--integerArray[   65]=107796--integerArray[   66]=219641--integerArray[   67]=370836--integerArray[   68]=493276--integerArray[   69]=154175--
 --integerArray[   70]= 69666--integerArray[   71]=331375--integerArray[   72]=225798--integerArray[   73]=  2899--integerArray[   74]=463666--integerArray[   75]= 86235--integerArray[   76]= 68094--integerArray[   77]= 91426--integerArray[   78]=354645--integerArray[   79]=412862--
 --integerArray[   80]=499012--integerArray[   81]=118265--integerArray[   82]=233662--integerArray[   83]= 85153--integerArray[   84]= 48053--integerArray[   85]=305945--integerArray[   86]=295616--integerArray[   87]=310682--integerArray[   88]=106384--integerArray[   89]=245873--
 --integerArray[   90]=125237--integerArray[   91]=479158--integerArray[   92]= 46612--integerArray[   93]=  7028--integerArray[   94]=255451--integerArray[   95]=373187--integerArray[   96]=311560--integerArray[   97]= 94460--integerArray[   98]= 12447--integerArray[   99]=496183--
 
 --integerArray[49900]= 47686--integerArray[49901]=482861--integerArray[49902]=339708--integerArray[49903]=478155--integerArray[49904]= 42917--integerArray[49905]=233357--integerArray[49906]=348627--integerArray[49907]=195724--integerArray[49908]= 72774--integerArray[49909]=116192--
 --integerArray[49910]=372429--integerArray[49911]=465626--integerArray[49912]=137409--integerArray[49913]=359932--integerArray[49914]=141767--integerArray[49915]= 88220--integerArray[49916]=146612--integerArray[49917]=301523--integerArray[49918]=332161--integerArray[49919]= 27421--
 --integerArray[49920]=277674--integerArray[49921]=346445--integerArray[49922]=398975--integerArray[49923]=374685--integerArray[49924]=173642--integerArray[49925]=163825--integerArray[49926]=320665--integerArray[49927]=368648--integerArray[49928]=498138--integerArray[49929]=480131--
 --integerArray[49930]=176511--integerArray[49931]=336323--integerArray[49932]=397389--integerArray[49933]=427655--integerArray[49934]=477997--integerArray[49935]=379650--integerArray[49936]=386736--integerArray[49937]=288643--integerArray[49938]= 41575--integerArray[49939]=387703--
 --integerArray[49940]= 24013--integerArray[49941]=  1763--integerArray[49942]=406705--integerArray[49943]=481030--integerArray[49944]=429722--integerArray[49945]=424534--integerArray[49946]=185966--integerArray[49947]= 28052--integerArray[49948]=185346--integerArray[49949]=228342--
 --integerArray[49950]= 68627--integerArray[49951]=333725--integerArray[49952]=120973--integerArray[49953]=304005--integerArray[49954]=123334--integerArray[49955]=470404--integerArray[49956]=   398--integerArray[49957]=253459--integerArray[49958]=456664--integerArray[49959]=168709--
 --integerArray[49960]= 79433--integerArray[49961]=133691--integerArray[49962]= 78045--integerArray[49963]=400870--integerArray[49964]=441759--integerArray[49965]=210925--integerArray[49966]=164036--integerArray[49967]=134157--integerArray[49968]=499383--integerArray[49969]=488551--
 --integerArray[49970]= 20759--integerArray[49971]=317451--integerArray[49972]=497707--integerArray[49973]=345916--integerArray[49974]=356145--integerArray[49975]=349718--integerArray[49976]=387916--integerArray[49977]=124310--integerArray[49978]=394390--integerArray[49979]= 96020--
 --integerArray[49980]=209459--integerArray[49981]=341400--integerArray[49982]=409300--integerArray[49983]= 31763--integerArray[49984]=249861--integerArray[49985]=423196--integerArray[49986]=117332--integerArray[49987]=234677--integerArray[49988]=467138--integerArray[49989]=307836--
 --integerArray[49990]=405277--integerArray[49991]=488137--integerArray[49992]=258795--integerArray[49993]=321420--integerArray[49994]=153861--integerArray[49995]=496405--integerArray[49996]=210509--integerArray[49997]=287236--integerArray[49998]=466836--integerArray[49999]=418191--
 
 Start 'selectionSort'
   Start time{ms}: [1648311741156]
    Stop time{ms}: [1648311741388]
 Elapsed time{ms}: [232]
   End 'selectionSort'
 
 intergerArray AFTER  'selectionSort':
 --integerArray[    0]=     8--integerArray[    1]=    46--integerArray[    2]=    79--integerArray[    3]=    87--integerArray[    4]=   105--integerArray[    5]=   107--integerArray[    6]=   135--integerArray[    7]=   156--integerArray[    8]=   175--integerArray[    9]=   180--
 --integerArray[   10]=   183--integerArray[   11]=   183--integerArray[   12]=   199--integerArray[   13]=   208--integerArray[   14]=   208--integerArray[   15]=   221--integerArray[   16]=   232--integerArray[   17]=   249--integerArray[   18]=   258--integerArray[   19]=   265--
 --integerArray[   20]=   281--integerArray[   21]=   289--integerArray[   22]=   303--integerArray[   23]=   305--integerArray[   24]=   306--integerArray[   25]=   309--integerArray[   26]=   312--integerArray[   27]=   320--integerArray[   28]=   335--integerArray[   29]=   343--
 --integerArray[   30]=   349--integerArray[   31]=   360--integerArray[   32]=   373--integerArray[   33]=   374--integerArray[   34]=   377--integerArray[   35]=   398--integerArray[   36]=   430--integerArray[   37]=   431--integerArray[   38]=   442--integerArray[   39]=   445--
 --integerArray[   40]=   448--integerArray[   41]=   448--integerArray[   42]=   452--integerArray[   43]=   453--integerArray[   44]=   470--integerArray[   45]=   477--integerArray[   46]=   501--integerArray[   47]=   520--integerArray[   48]=   536--integerArray[   49]=   549--
 --integerArray[   50]=   554--integerArray[   51]=   556--integerArray[   52]=   560--integerArray[   53]=   570--integerArray[   54]=   582--integerArray[   55]=   590--integerArray[   56]=   598--integerArray[   57]=   625--integerArray[   58]=   625--integerArray[   59]=   629--
 --integerArray[   60]=   633--integerArray[   61]=   635--integerArray[   62]=   646--integerArray[   63]=   664--integerArray[   64]=   677--integerArray[   65]=   698--integerArray[   66]=   708--integerArray[   67]=   710--integerArray[   68]=   719--integerArray[   69]=   731--
 --integerArray[   70]=   746--integerArray[   71]=   753--integerArray[   72]=   758--integerArray[   73]=   765--integerArray[   74]=   766--integerArray[   75]=   773--integerArray[   76]=   779--integerArray[   77]=   804--integerArray[   78]=   805--integerArray[   79]=   811--
 --integerArray[   80]=   839--integerArray[   81]=   860--integerArray[   82]=   882--integerArray[   83]=   892--integerArray[   84]=   899--integerArray[   85]=   906--integerArray[   86]=   926--integerArray[   87]=   936--integerArray[   88]=   937--integerArray[   89]=   963--
 --integerArray[   90]=   969--integerArray[   91]=   971--integerArray[   92]=   996--integerArray[   93]=  1002--integerArray[   94]=  1011--integerArray[   95]=  1013--integerArray[   96]=  1016--integerArray[   97]=  1023--integerArray[   98]=  1045--integerArray[   99]=  1046--
 
 --integerArray[49900]=499073--integerArray[49901]=499075--integerArray[49902]=499080--integerArray[49903]=499081--integerArray[49904]=499087--integerArray[49905]=499110--integerArray[49906]=499128--integerArray[49907]=499151--integerArray[49908]=499163--integerArray[49909]=499165--
 --integerArray[49910]=499169--integerArray[49911]=499170--integerArray[49912]=499172--integerArray[49913]=499191--integerArray[49914]=499192--integerArray[49915]=499194--integerArray[49916]=499196--integerArray[49917]=499198--integerArray[49918]=499201--integerArray[49919]=499215--
 --integerArray[49920]=499216--integerArray[49921]=499227--integerArray[49922]=499243--integerArray[49923]=499250--integerArray[49924]=499251--integerArray[49925]=499265--integerArray[49926]=499267--integerArray[49927]=499271--integerArray[49928]=499276--integerArray[49929]=499283--
 --integerArray[49930]=499290--integerArray[49931]=499295--integerArray[49932]=499353--integerArray[49933]=499360--integerArray[49934]=499365--integerArray[49935]=499365--integerArray[49936]=499377--integerArray[49937]=499383--integerArray[49938]=499396--integerArray[49939]=499413--
 --integerArray[49940]=499427--integerArray[49941]=499430--integerArray[49942]=499433--integerArray[49943]=499438--integerArray[49944]=499441--integerArray[49945]=499455--integerArray[49946]=499466--integerArray[49947]=499485--integerArray[49948]=499491--integerArray[49949]=499492--
 --integerArray[49950]=499493--integerArray[49951]=499500--integerArray[49952]=499511--integerArray[49953]=499514--integerArray[49954]=499536--integerArray[49955]=499540--integerArray[49956]=499562--integerArray[49957]=499571--integerArray[49958]=499574--integerArray[49959]=499586--
 --integerArray[49960]=499592--integerArray[49961]=499602--integerArray[49962]=499611--integerArray[49963]=499613--integerArray[49964]=499616--integerArray[49965]=499631--integerArray[49966]=499632--integerArray[49967]=499642--integerArray[49968]=499666--integerArray[49969]=499682--
 --integerArray[49970]=499689--integerArray[49971]=499716--integerArray[49972]=499723--integerArray[49973]=499732--integerArray[49974]=499745--integerArray[49975]=499748--integerArray[49976]=499754--integerArray[49977]=499762--integerArray[49978]=499776--integerArray[49979]=499793--
 --integerArray[49980]=499800--integerArray[49981]=499801--integerArray[49982]=499807--integerArray[49983]=499812--integerArray[49984]=499822--integerArray[49985]=499827--integerArray[49986]=499848--integerArray[49987]=499855--integerArray[49988]=499866--integerArray[49989]=499870--
 --integerArray[49990]=499897--integerArray[49991]=499899--integerArray[49992]=499909--integerArray[49993]=499910--integerArray[49994]=499923--integerArray[49995]=499934--integerArray[49996]=499944--integerArray[49997]=499951--integerArray[49998]=499968--integerArray[49999]=499980--
 
 intergerArray BEFORE 'insertionSort':
 --integerArray[    0]=319315--integerArray[    1]=348873--integerArray[    2]=159765--integerArray[    3]=232384--integerArray[    4]=178001--integerArray[    5]=322956--integerArray[    6]=447316--integerArray[    7]=288046--integerArray[    8]= 56243--integerArray[    9]=418562--
 --integerArray[   10]=289106--integerArray[   11]=375662--integerArray[   12]=210270--integerArray[   13]=230750--integerArray[   14]= 38549--integerArray[   15]= 82726--integerArray[   16]= 99361--integerArray[   17]=295235--integerArray[   18]=292461--integerArray[   19]=414212--
 --integerArray[   20]=482551--integerArray[   21]=291292--integerArray[   22]= 70498--integerArray[   23]=337191--integerArray[   24]=430313--integerArray[   25]=212537--integerArray[   26]=101734--integerArray[   27]=482765--integerArray[   28]= 76561--integerArray[   29]=118662--
 --integerArray[   30]=203913--integerArray[   31]=478841--integerArray[   32]= 23002--integerArray[   33]=232234--integerArray[   34]=367787--integerArray[   35]=279328--integerArray[   36]=217795--integerArray[   37]=135577--integerArray[   38]= 20653--integerArray[   39]=312369--
 --integerArray[   40]=110274--integerArray[   41]=250870--integerArray[   42]=150944--integerArray[   43]=275103--integerArray[   44]= 93093--integerArray[   45]= 94921--integerArray[   46]= 39455--integerArray[   47]=364012--integerArray[   48]=499507--integerArray[   49]=241337--
 --integerArray[   50]=175289--integerArray[   51]=203731--integerArray[   52]=342097--integerArray[   53]=   710--integerArray[   54]=213318--integerArray[   55]=262514--integerArray[   56]=324097--integerArray[   57]= 68901--integerArray[   58]=166011--integerArray[   59]=462771--
 --integerArray[   60]=103589--integerArray[   61]=428825--integerArray[   62]= 11000--integerArray[   63]=184181--integerArray[   64]= 99905--integerArray[   65]=262890--integerArray[   66]=293054--integerArray[   67]= 41755--integerArray[   68]=337951--integerArray[   69]=304357--
 --integerArray[   70]=180030--integerArray[   71]=360817--integerArray[   72]=204972--integerArray[   73]= 86320--integerArray[   74]= 84557--integerArray[   75]=147991--integerArray[   76]=306975--integerArray[   77]=279368--integerArray[   78]=130675--integerArray[   79]=204524--
 --integerArray[   80]=440505--integerArray[   81]= 84249--integerArray[   82]=467651--integerArray[   83]=130135--integerArray[   84]=291399--integerArray[   85]=339001--integerArray[   86]= 50757--integerArray[   87]=437908--integerArray[   88]= 79589--integerArray[   89]=240737--
 --integerArray[   90]= 56559--integerArray[   91]=373658--integerArray[   92]=248606--integerArray[   93]=175337--integerArray[   94]=224786--integerArray[   95]=412709--integerArray[   96]=122510--integerArray[   97]=154239--integerArray[   98]=268145--integerArray[   99]=372709--
 
 --integerArray[49900]=  2133--integerArray[49901]=489316--integerArray[49902]= 29025--integerArray[49903]=295826--integerArray[49904]=100375--integerArray[49905]= 39039--integerArray[49906]=159768--integerArray[49907]=149003--integerArray[49908]=429437--integerArray[49909]=495762--
 --integerArray[49910]= 64541--integerArray[49911]= 92267--integerArray[49912]=260928--integerArray[49913]=459667--integerArray[49914]=399120--integerArray[49915]=424672--integerArray[49916]=185018--integerArray[49917]=377161--integerArray[49918]=143705--integerArray[49919]= 77440--
 --integerArray[49920]= 25886--integerArray[49921]=117696--integerArray[49922]=496089--integerArray[49923]=149189--integerArray[49924]= 90136--integerArray[49925]= 21526--integerArray[49926]=395500--integerArray[49927]=448569--integerArray[49928]=358914--integerArray[49929]=292722--
 --integerArray[49930]= 91384--integerArray[49931]=164077--integerArray[49932]=319872--integerArray[49933]= 64980--integerArray[49934]=395124--integerArray[49935]= 56995--integerArray[49936]=103386--integerArray[49937]=362151--integerArray[49938]=306200--integerArray[49939]=143659--
 --integerArray[49940]=107603--integerArray[49941]=207507--integerArray[49942]=106685--integerArray[49943]=176682--integerArray[49944]=199530--integerArray[49945]= 73040--integerArray[49946]=446128--integerArray[49947]=275575--integerArray[49948]=281517--integerArray[49949]=306741--
 --integerArray[49950]=437144--integerArray[49951]= 69920--integerArray[49952]=499508--integerArray[49953]=150029--integerArray[49954]=  2017--integerArray[49955]= 51913--integerArray[49956]=185025--integerArray[49957]=174155--integerArray[49958]= 81765--integerArray[49959]=250836--
 --integerArray[49960]=129010--integerArray[49961]= 98709--integerArray[49962]=219735--integerArray[49963]=109276--integerArray[49964]= 92000--integerArray[49965]=127963--integerArray[49966]=433131--integerArray[49967]=431419--integerArray[49968]=340376--integerArray[49969]=139691--
 --integerArray[49970]=377317--integerArray[49971]=148940--integerArray[49972]=480788--integerArray[49973]=266269--integerArray[49974]=344924--integerArray[49975]= 74603--integerArray[49976]=336949--integerArray[49977]=210217--integerArray[49978]=454891--integerArray[49979]= 14200--
 --integerArray[49980]=260414--integerArray[49981]=395609--integerArray[49982]=104212--integerArray[49983]=204986--integerArray[49984]= 42192--integerArray[49985]=188920--integerArray[49986]=203665--integerArray[49987]=299680--integerArray[49988]= 73406--integerArray[49989]=242410--
 --integerArray[49990]= 44473--integerArray[49991]=354243--integerArray[49992]=181548--integerArray[49993]=277141--integerArray[49994]=357387--integerArray[49995]= 52743--integerArray[49996]= 59209--integerArray[49997]=231485--integerArray[49998]=337209--integerArray[49999]=498144--
 
 Start 'insertionSort'
   Start time{ms}: [1648311741418]
    Stop time{ms}: [1648311741612]
 Elapsed time{ms}: [194]
   End 'insertionSort'
 
 intergerArray AFTER  'insertionSort':
 --integerArray[    0]=     0--integerArray[    1]=    43--integerArray[    2]=    46--integerArray[    3]=    68--integerArray[    4]=    82--integerArray[    5]=    84--integerArray[    6]=    93--integerArray[    7]=    93--integerArray[    8]=   113--integerArray[    9]=   129--
 --integerArray[   10]=   130--integerArray[   11]=   134--integerArray[   12]=   141--integerArray[   13]=   160--integerArray[   14]=   164--integerArray[   15]=   168--integerArray[   16]=   169--integerArray[   17]=   175--integerArray[   18]=   181--integerArray[   19]=   208--
 --integerArray[   20]=   211--integerArray[   21]=   220--integerArray[   22]=   222--integerArray[   23]=   233--integerArray[   24]=   248--integerArray[   25]=   264--integerArray[   26]=   276--integerArray[   27]=   282--integerArray[   28]=   297--integerArray[   29]=   312--
 --integerArray[   30]=   315--integerArray[   31]=   322--integerArray[   32]=   326--integerArray[   33]=   327--integerArray[   34]=   328--integerArray[   35]=   336--integerArray[   36]=   350--integerArray[   37]=   359--integerArray[   38]=   365--integerArray[   39]=   378--
 --integerArray[   40]=   412--integerArray[   41]=   419--integerArray[   42]=   423--integerArray[   43]=   434--integerArray[   44]=   436--integerArray[   45]=   437--integerArray[   46]=   440--integerArray[   47]=   443--integerArray[   48]=   455--integerArray[   49]=   468--
 --integerArray[   50]=   471--integerArray[   51]=   473--integerArray[   52]=   494--integerArray[   53]=   536--integerArray[   54]=   546--integerArray[   55]=   548--integerArray[   56]=   558--integerArray[   57]=   562--integerArray[   58]=   562--integerArray[   59]=   567--
 --integerArray[   60]=   580--integerArray[   61]=   590--integerArray[   62]=   605--integerArray[   63]=   614--integerArray[   64]=   623--integerArray[   65]=   624--integerArray[   66]=   631--integerArray[   67]=   639--integerArray[   68]=   686--integerArray[   69]=   704--
 --integerArray[   70]=   710--integerArray[   71]=   710--integerArray[   72]=   716--integerArray[   73]=   719--integerArray[   74]=   729--integerArray[   75]=   736--integerArray[   76]=   750--integerArray[   77]=   767--integerArray[   78]=   781--integerArray[   79]=   781--
 --integerArray[   80]=   792--integerArray[   81]=   847--integerArray[   82]=   849--integerArray[   83]=   853--integerArray[   84]=   863--integerArray[   85]=   863--integerArray[   86]=   868--integerArray[   87]=   873--integerArray[   88]=   883--integerArray[   89]=   893--
 --integerArray[   90]=   894--integerArray[   91]=   897--integerArray[   92]=   898--integerArray[   93]=   925--integerArray[   94]=   953--integerArray[   95]=   962--integerArray[   96]=   965--integerArray[   97]=  1002--integerArray[   98]=  1007--integerArray[   99]=  1008--
 
 --integerArray[49900]=498939--integerArray[49901]=498946--integerArray[49902]=498947--integerArray[49903]=498959--integerArray[49904]=498977--integerArray[49905]=498984--integerArray[49906]=498988--integerArray[49907]=498993--integerArray[49908]=499002--integerArray[49909]=499004--
 --integerArray[49910]=499018--integerArray[49911]=499019--integerArray[49912]=499041--integerArray[49913]=499044--integerArray[49914]=499049--integerArray[49915]=499052--integerArray[49916]=499058--integerArray[49917]=499082--integerArray[49918]=499097--integerArray[49919]=499102--
 --integerArray[49920]=499117--integerArray[49921]=499133--integerArray[49922]=499153--integerArray[49923]=499158--integerArray[49924]=499169--integerArray[49925]=499184--integerArray[49926]=499185--integerArray[49927]=499200--integerArray[49928]=499214--integerArray[49929]=499225--
 --integerArray[49930]=499245--integerArray[49931]=499262--integerArray[49932]=499299--integerArray[49933]=499301--integerArray[49934]=499324--integerArray[49935]=499337--integerArray[49936]=499340--integerArray[49937]=499359--integerArray[49938]=499369--integerArray[49939]=499371--
 --integerArray[49940]=499372--integerArray[49941]=499382--integerArray[49942]=499412--integerArray[49943]=499440--integerArray[49944]=499443--integerArray[49945]=499448--integerArray[49946]=499449--integerArray[49947]=499452--integerArray[49948]=499468--integerArray[49949]=499470--
 --integerArray[49950]=499472--integerArray[49951]=499473--integerArray[49952]=499477--integerArray[49953]=499478--integerArray[49954]=499494--integerArray[49955]=499498--integerArray[49956]=499507--integerArray[49957]=499508--integerArray[49958]=499540--integerArray[49959]=499544--
 --integerArray[49960]=499545--integerArray[49961]=499546--integerArray[49962]=499561--integerArray[49963]=499595--integerArray[49964]=499623--integerArray[49965]=499623--integerArray[49966]=499633--integerArray[49967]=499653--integerArray[49968]=499676--integerArray[49969]=499699--
 --integerArray[49970]=499701--integerArray[49971]=499726--integerArray[49972]=499736--integerArray[49973]=499762--integerArray[49974]=499763--integerArray[49975]=499782--integerArray[49976]=499786--integerArray[49977]=499786--integerArray[49978]=499802--integerArray[49979]=499813--
 --integerArray[49980]=499817--integerArray[49981]=499842--integerArray[49982]=499849--integerArray[49983]=499853--integerArray[49984]=499859--integerArray[49985]=499870--integerArray[49986]=499870--integerArray[49987]=499883--integerArray[49988]=499891--integerArray[49989]=499893--
 --integerArray[49990]=499903--integerArray[49991]=499911--integerArray[49992]=499912--integerArray[49993]=499931--integerArray[49994]=499936--integerArray[49995]=499941--integerArray[49996]=499962--integerArray[49997]=499981--integerArray[49998]=499998--integerArray[49999]=498144--
 
 intergerArray BEFORE 'quickSort':
 --integerArray[    0]=161482--integerArray[    1]=210447--integerArray[    2]=200472--integerArray[    3]=473595--integerArray[    4]= 75547--integerArray[    5]=151779--integerArray[    6]= 92765--integerArray[    7]=272773--integerArray[    8]=446477--integerArray[    9]= 83251--
 --integerArray[   10]=459308--integerArray[   11]=483399--integerArray[   12]=413309--integerArray[   13]=278944--integerArray[   14]=401604--integerArray[   15]=285468--integerArray[   16]=144043--integerArray[   17]=450532--integerArray[   18]=139482--integerArray[   19]=257473--
 --integerArray[   20]=240453--integerArray[   21]=323303--integerArray[   22]=213709--integerArray[   23]= 15320--integerArray[   24]= 50980--integerArray[   25]=174395--integerArray[   26]=483792--integerArray[   27]=407864--integerArray[   28]= 16239--integerArray[   29]=355252--
 --integerArray[   30]=460358--integerArray[   31]=253076--integerArray[   32]=220189--integerArray[   33]=185735--integerArray[   34]= 36301--integerArray[   35]=102470--integerArray[   36]=355673--integerArray[   37]=497022--integerArray[   38]= 32400--integerArray[   39]=267416--
 --integerArray[   40]=120798--integerArray[   41]=376438--integerArray[   42]=290688--integerArray[   43]=333327--integerArray[   44]=281793--integerArray[   45]= 28149--integerArray[   46]=404990--integerArray[   47]=265909--integerArray[   48]=195150--integerArray[   49]=206795--
 --integerArray[   50]=216207--integerArray[   51]=429569--integerArray[   52]=400651--integerArray[   53]=118308--integerArray[   54]=219153--integerArray[   55]=468773--integerArray[   56]=461273--integerArray[   57]=456518--integerArray[   58]= 28237--integerArray[   59]=459668--
 --integerArray[   60]=294648--integerArray[   61]=439630--integerArray[   62]=201602--integerArray[   63]=410606--integerArray[   64]=367850--integerArray[   65]= 76993--integerArray[   66]=403137--integerArray[   67]=240873--integerArray[   68]=214032--integerArray[   69]= 18482--
 --integerArray[   70]=402072--integerArray[   71]=237950--integerArray[   72]=143504--integerArray[   73]=460975--integerArray[   74]=405030--integerArray[   75]=473005--integerArray[   76]=498840--integerArray[   77]=185672--integerArray[   78]=199341--integerArray[   79]=243853--
 --integerArray[   80]=161789--integerArray[   81]=383997--integerArray[   82]=386597--integerArray[   83]=309972--integerArray[   84]=201628--integerArray[   85]=458531--integerArray[   86]= 38892--integerArray[   87]=158080--integerArray[   88]=445153--integerArray[   89]= 24595--
 --integerArray[   90]= 49404--integerArray[   91]=468704--integerArray[   92]= 29830--integerArray[   93]=154574--integerArray[   94]=358549--integerArray[   95]=319137--integerArray[   96]=284787--integerArray[   97]=356913--integerArray[   98]=218010--integerArray[   99]=347129--
 
 --integerArray[49900]=124042--integerArray[49901]=414345--integerArray[49902]= 80765--integerArray[49903]=108680--integerArray[49904]=243999--integerArray[49905]=141247--integerArray[49906]=461435--integerArray[49907]=353274--integerArray[49908]=198995--integerArray[49909]=458673--
 --integerArray[49910]= 74549--integerArray[49911]=173665--integerArray[49912]= 97358--integerArray[49913]= 40823--integerArray[49914]= 20871--integerArray[49915]=311618--integerArray[49916]=403911--integerArray[49917]=329750--integerArray[49918]=406951--integerArray[49919]=154021--
 --integerArray[49920]=175483--integerArray[49921]=292049--integerArray[49922]=310602--integerArray[49923]=307896--integerArray[49924]=387849--integerArray[49925]=143136--integerArray[49926]=410255--integerArray[49927]=466892--integerArray[49928]= 80045--integerArray[49929]= 77081--
 --integerArray[49930]=425068--integerArray[49931]=420925--integerArray[49932]= 90935--integerArray[49933]= 70442--integerArray[49934]=479699--integerArray[49935]= 55247--integerArray[49936]= 15175--integerArray[49937]=262568--integerArray[49938]=264992--integerArray[49939]= 73332--
 --integerArray[49940]=198728--integerArray[49941]=123521--integerArray[49942]=432066--integerArray[49943]=201963--integerArray[49944]=496966--integerArray[49945]=407054--integerArray[49946]= 29059--integerArray[49947]=451554--integerArray[49948]=432397--integerArray[49949]=313445--
 --integerArray[49950]= 22294--integerArray[49951]=185116--integerArray[49952]=282559--integerArray[49953]=121803--integerArray[49954]=228220--integerArray[49955]=194893--integerArray[49956]=272466--integerArray[49957]=372263--integerArray[49958]=128083--integerArray[49959]=450306--
 --integerArray[49960]=423556--integerArray[49961]=193553--integerArray[49962]= 67644--integerArray[49963]=175325--integerArray[49964]=392923--integerArray[49965]=219113--integerArray[49966]=107527--integerArray[49967]=127390--integerArray[49968]=292830--integerArray[49969]=470404--
 --integerArray[49970]=428989--integerArray[49971]= 36030--integerArray[49972]=499561--integerArray[49973]=480480--integerArray[49974]=313002--integerArray[49975]=415797--integerArray[49976]= 66225--integerArray[49977]=331577--integerArray[49978]= 17797--integerArray[49979]=378555--
 --integerArray[49980]= 66773--integerArray[49981]=216501--integerArray[49982]=243005--integerArray[49983]=375596--integerArray[49984]=411964--integerArray[49985]=430745--integerArray[49986]=194754--integerArray[49987]=376132--integerArray[49988]=113009--integerArray[49989]=465631--
 --integerArray[49990]=178284--integerArray[49991]=334185--integerArray[49992]=492440--integerArray[49993]=143007--integerArray[49994]=410540--integerArray[49995]=255784--integerArray[49996]=403852--integerArray[49997]= 50922--integerArray[49998]=339790--integerArray[49999]=254552--
 
 Start 'quickSort'
   Start time{ms}: [1648311741634]
    Stop time{ms}: [1648311741639]
 Elapsed time{ms}: [5]
   End 'quickSort'
 
 intergerArray AFTER  'quickSort':
 --integerArray[    0]=     6--integerArray[    1]=     0--integerArray[    2]=    15--integerArray[    3]=    15--integerArray[    4]=    33--integerArray[    5]=    24--integerArray[    6]=    37--integerArray[    7]=    50--integerArray[    8]=    62--integerArray[    9]=    75--
 --integerArray[   10]=    77--integerArray[   11]=    80--integerArray[   12]=    88--integerArray[   13]=    98--integerArray[   14]=   114--integerArray[   15]=   100--integerArray[   16]=   123--integerArray[   17]=   147--integerArray[   18]=   149--integerArray[   19]=   138--
 --integerArray[   20]=   167--integerArray[   21]=   183--integerArray[   22]=   188--integerArray[   23]=   206--integerArray[   24]=   211--integerArray[   25]=   212--integerArray[   26]=   217--integerArray[   27]=   236--integerArray[   28]=   249--integerArray[   29]=   238--
 --integerArray[   30]=   263--integerArray[   31]=   294--integerArray[   32]=   294--integerArray[   33]=   324--integerArray[   34]=   297--integerArray[   35]=   342--integerArray[   36]=   345--integerArray[   37]=   343--integerArray[   38]=   355--integerArray[   39]=   370--
 --integerArray[   40]=   380--integerArray[   41]=   379--integerArray[   42]=   396--integerArray[   43]=   449--integerArray[   44]=   452--integerArray[   45]=   453--integerArray[   46]=   462--integerArray[   47]=   458--integerArray[   48]=   464--integerArray[   49]=   473--
 --integerArray[   50]=   480--integerArray[   51]=   481--integerArray[   52]=   530--integerArray[   53]=   482--integerArray[   54]=   546--integerArray[   55]=   551--integerArray[   56]=   552--integerArray[   57]=   589--integerArray[   58]=   557--integerArray[   59]=   632--
 --integerArray[   60]=   635--integerArray[   61]=   644--integerArray[   62]=   653--integerArray[   63]=   654--integerArray[   64]=   658--integerArray[   65]=   666--integerArray[   66]=   677--integerArray[   67]=   690--integerArray[   68]=   700--integerArray[   69]=   702--
 --integerArray[   70]=   722--integerArray[   71]=   738--integerArray[   72]=   739--integerArray[   73]=   747--integerArray[   74]=   748--integerArray[   75]=   767--integerArray[   76]=   771--integerArray[   77]=   781--integerArray[   78]=   781--integerArray[   79]=   783--
 --integerArray[   80]=   789--integerArray[   81]=   801--integerArray[   82]=   823--integerArray[   83]=   828--integerArray[   84]=   832--integerArray[   85]=   833--integerArray[   86]=   833--integerArray[   87]=   843--integerArray[   88]=   863--integerArray[   89]=   865--
 --integerArray[   90]=   867--integerArray[   91]=   871--integerArray[   92]=   884--integerArray[   93]=   897--integerArray[   94]=   887--integerArray[   95]=   915--integerArray[   96]=   938--integerArray[   97]=   964--integerArray[   98]=   952--integerArray[   99]=   970--
 
 --integerArray[49900]=499090--integerArray[49901]=499117--integerArray[49902]=499140--integerArray[49903]=499130--integerArray[49904]=499140--integerArray[49905]=499154--integerArray[49906]=499184--integerArray[49907]=499184--integerArray[49908]=499195--integerArray[49909]=499199--
 --integerArray[49910]=499200--integerArray[49911]=499219--integerArray[49912]=499222--integerArray[49913]=499231--integerArray[49914]=499246--integerArray[49915]=499257--integerArray[49916]=499257--integerArray[49917]=499279--integerArray[49918]=499268--integerArray[49919]=499281--
 --integerArray[49920]=499296--integerArray[49921]=499286--integerArray[49922]=499304--integerArray[49923]=499328--integerArray[49924]=499323--integerArray[49925]=499335--integerArray[49926]=499336--integerArray[49927]=499347--integerArray[49928]=499344--integerArray[49929]=499367--
 --integerArray[49930]=499370--integerArray[49931]=499376--integerArray[49932]=499385--integerArray[49933]=499378--integerArray[49934]=499388--integerArray[49935]=499406--integerArray[49936]=499408--integerArray[49937]=499450--integerArray[49938]=499459--integerArray[49939]=499450--
 --integerArray[49940]=499459--integerArray[49941]=499476--integerArray[49942]=499478--integerArray[49943]=499466--integerArray[49944]=499483--integerArray[49945]=499487--integerArray[49946]=499499--integerArray[49947]=499522--integerArray[49948]=499518--integerArray[49949]=499528--
 --integerArray[49950]=499529--integerArray[49951]=499532--integerArray[49952]=499540--integerArray[49953]=499540--integerArray[49954]=499549--integerArray[49955]=499550--integerArray[49956]=499561--integerArray[49957]=499566--integerArray[49958]=499566--integerArray[49959]=499580--
 --integerArray[49960]=499583--integerArray[49961]=499592--integerArray[49962]=499594--integerArray[49963]=499620--integerArray[49964]=499608--integerArray[49965]=499624--integerArray[49966]=499644--integerArray[49967]=499633--integerArray[49968]=499648--integerArray[49969]=499664--
 --integerArray[49970]=499678--integerArray[49971]=499695--integerArray[49972]=499711--integerArray[49973]=499690--integerArray[49974]=499716--integerArray[49975]=499727--integerArray[49976]=499725--integerArray[49977]=499753--integerArray[49978]=499759--integerArray[49979]=499760--
 --integerArray[49980]=499772--integerArray[49981]=499784--integerArray[49982]=499797--integerArray[49983]=499814--integerArray[49984]=499815--integerArray[49985]=499814--integerArray[49986]=499846--integerArray[49987]=499877--integerArray[49988]=499856--integerArray[49989]=499879--
 --integerArray[49990]=499905--integerArray[49991]=499907--integerArray[49992]=499923--integerArray[49993]=499932--integerArray[49994]=499942--integerArray[49995]=499948--integerArray[49996]=499948--integerArray[49997]=499957--integerArray[49998]=499974--integerArray[49999]=499986--
 
 End of Program
 
  ----jGRASP: operation complete.
*/