import java.util.*;
import java.io.*;

public class BFS
{
	public static void search (int[][] adjmatrix, int source, int minrating, int max)
	{
		//data
		//queue to keep track of the nodes visited
		Queue<Integer> queue;
		queue = new LinkedList<Integer>();
		//the max parameter is the dimensions of the matrix that we found in the driver, based on the file
		int nodenum = max;
		//visited is the int[] array that keeps track of if we've visited a node or not
		int[] visited = new int[nodenum];
		//i is the node we are visiting-- traversing through the columns in the row, and source is the row we are in
		int i, elem;
 
        visited[source] = 1;
        queue.add(source);

        while (!queue.isEmpty())
        {
            elem = queue.remove();
            i = elem;
            //System.out.println(i);
            while (i < nodenum)
            {
                // if (adjmatrix[elem][i] != 0)
                // {
                // 	System.out.println (adjmatrix[elem][i]);
                // }
    
                if (adjmatrix[elem][i] >= minrating && visited[i] == 0)
                {
                    queue.add(i);
                    visited[i] = 1;
                    //System.out.println(i);
                }
                i++;
            }
        }
	}
}