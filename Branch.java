import java.io.*;
import java.util.*;
// Branch and Bound Algorithm
// @author Chris McDonald
public class Branch
{
        private double maxValue;
        private double K; 
        private double [ ] s; 
        private double [ ] v; 
        private Vector bestList; 
        private int numItems;
        private Queue q;
        class Node 
        {
                int level;
                double size, value, bound; List contains;
                protected Node() {
                        level = 0;
                        size = 0.0; value = 0.0; bound = 0.0; contains = null;
                }
                protected void copyList(Vector v) {
                        if( v == null || v.isEmpty( )) {
                                contains = new Vector();
                        }
                        else {
                                contains = new Vector(v);
                        }
                }
                protected void add(int index) {
                        contains.add(new Integer(index) );
                }
        }
        public static void main(String [] args){}
        public Branch (double capacity, double [ ] size, double [ ] value, int num) {
                maxValue = 0.0; K = capacity;
                s = size;
                v = value; numItems = num; bestList = null;
                q = new QueueLL(); 
        }
        private void knapsack() {
                while(!q.isEmpty()) {
                        Node temp = (Node) q.dequeue( ); 
                        if(temp.bound > maxValue) {
                                Node u = new Node();
                                u.level = temp.level + 1;
                                u.size = temp.size + s[temp.level+1]; u.value = temp.value + v[temp.level+1]; u.copyList((Vector) temp.contains); u.add(temp.level+1);
                                if(u.size < K && u.value > maxValue) {
                                        maxValue = u.value;
                                        bestList = new Vector (u.contains); 
                                } // end if
                                u.bound = bound(u.level, u.size, u.value); 
                                if(u.bound > maxValue) {
                                     q.enqueue(u);
                                } // end if
                                Node w = new Node();
                                w.level = temp.level + 1;
                                w.size = temp.size;
                                w.value = temp.value;
                                w.copyList((Vector)temp.contains);
                                w.add(temp.level+1);
                                w.bound = bound(w.level, w.size, w.value);
                                if(w.bound > maxValue) {
                                     q.enqueue(w);
                                } // end if
                        } // end if
                } // end while loop 
        }
        private double bound (int item, double size, double value) { 
        double bound = value;
        double totalSize = size;
        int k = item + 1;
        if (size > K) {
             return 0.0;
        } // end if
        while(k < numItems && totalSize + s[k] <= K) {
             bound += v[k]; totalSize += s[k]; k++;
        } // end while loop
        if (k < numItems)
             bound += (K - totalSize) * (v[k]/s[k]); 
             return bound;
        }
        public void findSolution() {
                Node root = new Node( ); root.level = 0;
                root.size = 0.0;
                root.value = 0.0;
                root.bound = bound(0,0,0);
                root.copyList(null);
                q.enqueue(root);
                knapsack();
                System.out.print("The solution set is: ");
                for (int i = 0; i < bestList.size(); i++) {
                        System.out.print(" " + bestList.get(i));
                        System.out.println();
                        System.out.println("The value contained in the knapsack is: $" +
                        maxValue);
                } // end for loop 
        }
}
