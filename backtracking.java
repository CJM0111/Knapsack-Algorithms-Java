import java.io.*;
import java.util.Scanner;

/**
 * This program calculates the optimal solution
 * for the 0-1 Knapsack problem using the backtracking algorithm
 * @author Chris McDonald - The Program: Backtracking Algorithm
*/

public class backtracking
{
   // sumPrice[n] holds the sum of all values from price[n] on up.
   static int [] sumPrice;
   
   public static void main(String [] args)
   throws IOException, FileNotFoundException
   {  
      boolean [] max;
      int [] weight, price;
      int n, maxWeight;
      String readIn = null;
      Scanner cin = new Scanner(System.in);
      readIn();
      readIn = cin.nextLine();
      Scanner input = new Scanner(new File(readIn));
      n = input.nextInt();
      maxWeight = input.nextInt();
      weight=new int[n];
      price =new int[n];
      max=new boolean[n];
      for(int i=0; i<n; i++ ) { 
      	   price[i] = input.nextInt();
           weight[i]  = input.nextInt();
      } // end for
      knapSack(maxWeight, max, weight, price);
      report(max, weight, price);
    }
    
    // This method will call the recursive backtracking method
    // The recursive method needs to receive and update information
    // on the current state and the optimal solution. This is
    // handled through passing arrays;
    // current[] will hold [0] current weight, [1] current price,
    // and [2] current maximum value;
    // max[] is the boolean vector corresponding to that
    // current maximum value;
    // used[] is the boolean vector used to generate the
    // possible knapsacks;
    
    public static void knapSack(int maxWt, boolean [] max, int [] weight, int [] price)
    {
    	// Places the first possible solution into max[]
    	int [] current = { 0, 0, Integer.MIN_VALUE };
    	boolean [] used = new boolean[max.length];
    	int i;
    	// Calculates the bound for a particular node
    	// The bound is based on the sum of values from the current node
    	// and all of the nodes on the path to the top
    	sumPrice = new int[price.length];
    	i = price.length - 1;
    	sumPrice[i] = price[i];
    	while( --i >= 0 ) { 
        	sumPrice[i] = price[i] + sumPrice[i+1]; 
    	} // end while loop
    	knapSack(maxWt, used, 0, weight, price, max, current);
    }
    
    // Parameters:
    // used[] - current knapsack contents
    // item - position being considered
    // max[] - retains the optimal boolean vector
    // current[] - [0] current weight, 
    // [1] current price, and [2] current max value
    
    static void knapSack(int maxW, boolean [] used, int item, int [] weight, int [] price, boolean [] max, int [] current)
    { 
      // Checks whether all positions have been considered
      if(item == max.length) {
         if(current[1] > current[2]) { 
         	if(current[2] < 0) {
            		System.out.println("The first possible solution is: " + current[1]);
                } // end if
         	else {
   	 		System.out.println(current[1] + " replaces " + current[2]);
   	 	}
        	// Stores the value of the optimal node
         	current[2] = current[1];
         	System.arraycopy(used, 0, max, 0, used.length);
      	 } // end if
      	 else {
         	System.out.println(current[1] + " isn't valid.");
      	 } // end else
      } // end if
      else {
         // Checks if an item exceeds the weight limit on the knapsack
         if(current[0] + weight[item] <= maxW) {
            // Creates a bound based on the possible outcome from a promising node
            if(current[1] + sumPrice[item] > current[2]) {
               used[item] = true;
               current[0] += weight[item];
               current[1] += price[item];
               knapSack(maxW,used,item+1,weight,price,max,current);
            // Resets the array to the values before the item was used
               used[item] = false;
               current[0] -= weight[item];
               current[1] -= price[item];
            } // end if
            else {  
            	System.out.println("The node at position " + item + " with a value of " + current[1] + " is nonpromising.");
            	return;  // Using the values above the current node will not work
            } // end else
         } // end if
         knapSack(maxW, used, item+1, weight, price, max, current);
      } // end else
   }
   
   static void report (boolean [] max, int [] weight, int [] price )
   {  
    	int totalW = 0, totalprice = 0;
    	for (int i=0; i<max.length; i++ ) {
        	if(max[i]) {  
        		totalW += weight[i];
            		totalprice  += price[i];
         	} // end if
        } // end for loop
        result(totalprice, totalW);
    }
    
    public static void readIn()
    {
	System.out.println("Enter the name of your file:");
	System.out.println("(The format should be /Users/name/~)");
    }
	
    public static void result(double totalprice, double totalW)
    {
	System.out.println("This is the optimal price: " + totalprice + " dollars.");
	System.out.println("And this is the optimal weight: " + totalW + " pounds.");
    }
}
