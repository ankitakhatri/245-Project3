import java.io.*;
import java.util.*;
import java.text.*;

public class Driver 
{
	public static void main(String[] args) 
	{
		//read file name from command line argument
		File input = null;
        if (0 < args.length) 
        {
            input = new File(args[0]);
        }

        //read the number of lines in the csv file to get n = number of lines
        int n = 0;
        try
        {
        	BufferedReader br = new BufferedReader(new FileReader(input));
        	String line = null;
        	String delimiter = ",";

        	while (br.readLine() != null)
        		{
        			n++;
        		}
        	//br.reset();
     		//br.close();
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
     	}
     	//read source and target nodes into one array
     	//int [] nodes = new int[n*2];
        int [] data = new int[n*3];
        int max=0;
     	//int [] ratings = new int[n];
        //read numbers into an array using "," as delimiter
        try
        {
        	BufferedReader br = new BufferedReader(new FileReader(input));
       		String line = null;
        	String delimiter = ",";
        	//int i = 0;
            int d = 0;
        	
            while ((line=br.readLine()) != null)
        	{
        		String [] values = line.split(delimiter);
                //read all the data into data[] to fill the 2d array later
                data[d] = Integer.parseInt(values[0]);
                data[d+1] = Integer.parseInt(values[1]);
                data[d+2] = Integer.parseInt(values[2]);
                if (data[d]>max)
                {
                    max = data[d];
                }
                if (data[d+1]>max)
                {
                    max=data[d+1];
                }
                d+=3;
        	}
            //System.out.println("max" + max);
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
     	}

     	//create 2d array with max dimensions
     	int [][] adjmatrix = new int[max+1][max+1];
        //fill whole array with -11 to symbolize no connection
        for (int row = 0; row<adjmatrix.length; row++) 
        {
            for (int col = 0; col<adjmatrix[row].length; col++) 
            {
                adjmatrix[row][col] = 11;
            }
        }

        int a=0;
        int b=0;
        int c=0;

     	//fill the 2d array
        for (int index=0; index<data.length; index+=3)
        {     
            a = data[index];
            b = data[index+1];
            c = data[index+2];
            adjmatrix[a][b] = c;
        }
        //test code print matrix
        // for (int[] x : adjmatrix)
        // {
        //     for (int y : x)
        // {
        //     System.out.print(y + " ");
        //}
        //     System.out.println();
//}
        //System.out.println (max);
        //System.out.println(adjmatrix[1128][13]);

        //now my adjacency matrix is filled, get user input and perform BFS to find all the nodes with acceptable ratings
        //get user input for source and min acceptable rating

        //code to run the BFS, can just comment this out to make that work

        // Scanner sc = new Scanner(System.in);
        // System.out.println("Enter the User ID: ");
        // int source = sc.nextInt();
        // //input validation if source is not an existed node, or rating is below -10 or greater than 10
        // if (source > max || source < 0)
        // {
        //     System.out.println("User id is not valid.");
        //     System.exit(0);
        // }
        // System.out.println("Enter the minimum acceptable rating: ");
        // int minrating = sc.nextInt();
        // if (minrating < -11 || minrating > 11)
        // {
        //     System.out.println("Rating is not valid");
        //     System.exit(0);
        // }
        // else
        // {
        //     BFS bfs = new BFS();
        //     bfs.search(adjmatrix, source, minrating, max+1);
        // }

        //code to run DFS

        //get input for source and destination, input validation
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the source node: ");
        int src = sc.nextInt();
        if (src > max || src < 0)
        {
            System.out.println("User id is not valid.");
            System.exit(0);
        }
        System.out.println("Enter the destination node: ");
        int dst = sc.nextInt();
        if (dst > max || dst < 0)
        {
            System.out.println("User id is not valid.");
            System.exit(0);
        }

        //create instance of DFS and run it with the necessary parameters
        DFS dfs = new DFS();
        int[] visited = new int[max+1];
        ArrayList<Integer> path = new ArrayList();
        ArrayList<Double> averages = new ArrayList();
        dfs.search(adjmatrix, visited, src, dst, path, averages);

        //print average of all the averages
        int total = 0;
        for (int j = 0; j<averages.size(); j++)
        {
            total += averages.get(j);
        }
        //format to 2 decimal places
        DecimalFormat df = new DecimalFormat(".##");
        double average = (double)total/(averages.size());
        System.out.println("Total Average: " + df.format(average));

	}
}