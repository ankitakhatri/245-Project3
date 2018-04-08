import java.util.*;
import java.io.*;

public class BFS
{
	public static void search (int[][] adjmatrix, int source, int minrating, int max)
	{
		//data
		Queue<Integer> queue;
		queue = new LinkedList<Integer>();
		int nodenum = max;
		int[] visited = new int[nodenum];
		int i, element;
 
        visited[source] = 1;
        queue.add(source);

        while (!queue.isEmpty())
        {
            element = queue.remove();
            i = element;
            //System.out.println(i);
            while (i < nodenum)
            {
                //System.out.println (adjmatrix[element][i]);
                if (adjmatrix[element][i] >= minrating && visited[i] == 0)
                {
                    queue.add(i);
                    visited[i] = 1;
                    System.out.println(i);
                }
                i++;
            }
        }
	}
}