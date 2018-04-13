import java.util.*;
import java.io.*;
import java.text.*;

public class DFS
{
	//recursive DFS to find all paths between src and dst
	public static void search (int [][] adjmatrix, int[] visited, int src, int dst, ArrayList<Integer> path, ArrayList<Double> averages)
	{
		visited[src] = 1;
		path.add(src);

		//if the src passed into the recursive call == dst, then the function has reached the end of the path
		if (src==dst)
		{
			//print path
			for (int i = 0; i < path.size(); i++) 
			{
            	System.out.print(path.get(i) + " ");
        	}
        	//find the weight between each edge in the path and add it to the arraylist weight
        	ArrayList<Integer> weight = new ArrayList<Integer>();
        	for (int i=0; i<path.size()-1; i++)
        	{
        		int x = path.get(i);
        		int y = path.get(i+1);
        		weight.add(adjmatrix[x][y]);
        	}

        	//calculate total of all the edges
        	int total = 0;
        	for (int x = 0; x<weight.size(); x++)
        	{
        		total+= weight.get(x);
        	}
    
        	double average = 0;
        	//find the average of the path and format to two decimal places, and print
        	DecimalFormat df = new DecimalFormat(".##");
        	average = (double)total/(weight.size());
        	System.out.println("Average: " + df.format(average));
        	averages.add(average);
		}
		else
		{
			//find an adjacent node and pass that in as the next parameter, so the path moves from that node onwards
			for (int node=0; node<adjmatrix[src].length; node++)
			{
				if (adjmatrix[src][node] !=11)
				{
					if (visited[node]==0)
					{
                		search(adjmatrix, visited, node, dst, path, averages);
            		}
				}
			}
		}
		
		path.remove(path.size()-1);
		visited[src] = 0;

		//System.out.println("Total of averages: " + averages.size());
	}
}