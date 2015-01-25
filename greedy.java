import java.io.*;
import java.util.*;
/** 
 * This program calculates the "optimal" solution
 * for the 0-1 Knapsack problem using the greedy algorithm
 * @author Chris McDonald - The Program: Greedy Algorithm
 */
public class greedy
{
    public static void main(String [] args)
    throws IOException
    {
    	String input = null;
    	System.out.println("Enter the name of your file:");
    	System.out.println("(The format should be /Users/name/~)");
    	// reads in the file
    	Scanner cin= new Scanner(System.in);
    	input = cin.nextLine();
        Scanner file=new Scanner(new FileReader(input));
        int total = file.nextInt();
        double capacity = file.nextInt();
        // creates an array for the object "knapsack"
        knapsack [] bag  = new knapsack [total+1];
        double [] values = new double[total+1];
        for(int i=1; i<total+1; i++)
        {
        	// stores the item in an array
            bag[i] = new knapsack(file);
            //System.out.println(bag[i]);
            // calculates the values of the items
            // based on their dollars per pound ratio
            values[i] = bag[i].getPrice()/bag[i].getWeight();
            //System.out.println(values[i]);
        }
        double totalprice = 0;
        double totalW = 0;
        double max = 0;
        double minC = 0;
        int o = 0;
        int n = 1;
        int a = 0;
        // calculates the item with the smallest weight
        for(int i=1; i<total+1;i++)
        {
        	minC = bag[n].getWeight();
        	if(bag[i].getWeight() < minC)
        	{
        		minC = bag[i].getWeight();
        	}
        }
        max = values[n];
        // runs the loop until the optimized solution is complete
        do {
             // Determines the item with the maximum value per pound
             for(int i=1; i<total+1 ;i++) {
        		        if(values[i] > max) {
        			            max = values[i];
        			            o = i;
        		        } // end if
        		        if(values[n] == max) {
        			            o = n;
        		        } // end if
             } // end for loop 
        	    // Sets that item's value to 0 so no items are repeated
        	    values[o] = 0;
        	    // Resets the max to the first item
        	    max = values[n];
        	    // Stops items from being added 
        	    // If they would exceed the capacity
        	    if(totalW + bag[o].getWeight() > capacity) {
        		         values[o] = 0;
          	  } // end if
        	    else {
        		       totalW = totalW + bag[o].getWeight();
        		       totalprice = totalprice + bag[o].getPrice();
        	    } // end else
        	    // If adding the item with the lowest weight
        	    // would exceed the capacity,
        	    // the loop ends
        	    if(totalW + minC > capacity) {
        		       a = 1;
        	    } // end if
        }while(a != 1); // end do-while loop
        System.out.println("This is the optimal price: " + totalprice + " dollars.");
        System.out.println("And this is the optimal weight: " + totalW + " pounds.");
    }
}
