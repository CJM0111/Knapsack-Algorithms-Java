import java.io.*;
import java.util.*;
/**
 * This program calculates the optimal solution
 * for the 0-1 Knapsack Problem using the dynamic programming algorithm
 * @author Chris McDonald - The Program: Dynamic Programming Algorithm
*/
public class dynamic
{
	public static void main(String [] args)
	throws IOException, FileNotFoundException
	{
		readIn();
		// Reads in the file
		Scanner cin= new Scanner(System.in);
		String input = null;
		input = cin.nextLine();
		Scanner file=new Scanner(new FileReader(input));
		int total = file.nextInt();
		double capacity = file.nextInt();
		// Creates an array for the object "knapsack"
		knapsack [] bag  = new knapsack [total+1];
		double [] price = new double[total+1];
		double [] price2 = new double[total+1];
		double [] W = new double[total+1];
		double [] W2 = new double[total+1];
		for(int i=1; i<total+1; i++) {
			// Stores the item in an array
			bag[i] = new knapsack(file);
			// Creates a separate array to hold the value for "price"
			price[i] = bag[i].getPrice();
			// Creates a seperate array to hold the value for "weight"
			W[i] = bag[i].getWeight();
			// Creates a second array to hold the values for "price" for comparisons
			price2[i] = price[i];
		} // end for loop
		// Sorts the array holding the values for "price" in ascending order
		Arrays.sort(price);
		Arrays.sort(price2);
		// Replaces the first value in the array with last value and
		// continues until every value has been swapped
		// thus sorting in descending order
		for(int i=1, j=total; i<total+1; i++, j--) {
			price[i] = price2[j];
		} // end for loop
		// Reorders the values for "weight" to correctly pair with their 
		// corresponding value for "price" found in the given set of data
		for(int a=1;a<total+1;a++) {
			for(int i=1;i<total+1; i++) {
				if(price[a] == bag[i].getPrice()) {
					W2[a] = W[i];
				} // end if
			} // end for loop
		} // end for loop
		double totalprice = 0;
		double totalW = 0;
		// calculates the optimal price
		for(int n=1; n<total+1; n++) {
			totalW = totalW + W2[n];
			totalprice = totalprice+price[n];
			if(totalW > capacity) {
				totalW = totalW-W2[n];
				totalprice = totalprice-price[n];
			} // end if
			if(totalW == capacity) {
				break;
			} // end if
		} // end for loop
		result(totalprice, totalW);
	}
	// Instructs the user on how to start the program
	public static void readIn()
	{
		System.out.println("Enter the name of your file:");
		System.out.println("(The format should be /Users/name/~)");
	}
	// Outputs the result for the optimal solution
	public static void result(double totalprice, double totalW)
	{
		System.out.println("This is the optimal price: " + totalprice + " dollars.");
		System.out.println("And this is the optimal weight: " + totalW + " pounds.");
	}
}
