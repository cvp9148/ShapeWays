import java.io.IOException;


public class shapeWaysHandler 
{
	public static void main(String[] args) throws IOException
	{
		long startTime = System.currentTimeMillis();
		shapeWaysTest sw1 = new shapeWaysTest("Artist_lists_small.txt","output.txt");
				
		sw1.calculatePairs();	
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Runtime of the program : " +totalTime +"ms");
	}
}
