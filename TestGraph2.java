import java.util.Arrays;
import java.util.Iterator;
import java.io.*;
//read graph provided to us in phase 1
class ColEdge
			{
			int u;
			int v;
			}


public class TestGraph2 {

	public final static boolean DEBUG = true;
		
	public final static String COMMENT = "//";

	public static void main(String[] args) {

		long startTimer = System.nanoTime(); //starts the timer at the beginning of the main method.

			if (args.length < 1)
				{
				System.out.println("Error! No filename specified.");
				System.exit(0);
				}

				
			String inputfile = args[0];
			
			boolean seen[] = null;
			
			//! n is the number of vertices in the graph
			int n = -1;
			
			//! m is the number of edges in the graph
			int m = -1;
			
			//! e will contain the edges of the graph
			ColEdge e[] = null;

			// tree part
			//////////////////////////
			//GraphTree four = new GraphTree();
			//////////////////////////

			
			Coloring hope = null; 


			try 	{
			    	FileReader fr = new FileReader(inputfile);
			        BufferedReader br = new BufferedReader(fr);

			        String record = new String();
					
					//! THe first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
			        while ((record = br.readLine()) != null)
						{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
						}
	
					if( record.startsWith("VERTICES = ") )
						{
						n = Integer.parseInt( record.substring(11) );					
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
						}
					
						hope = new Coloring(n);

						// tree part
						////////////////
						//four.addVertices(n);
						////////////////

					seen = new boolean[n+1];	
						
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
						{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
						}

					e = new ColEdge[m];	


												
					for( int d=0; d<m; d++)
						{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )
								{
								System.out.println("Error! Malformed edge line: "+record);
								System.exit(0);
								}
						e[d] = new ColEdge();
						
						e[d].u = Integer.parseInt(data[0]);
						e[d].v = Integer.parseInt(data[1]);

						seen[ e[d].u ] = true;
						seen[ e[d].v ] = true;

						

						if(DEBUG){
							System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
							//g2.addEdge(e[d].u, e[d].v); 
						
						}
				
					}
									
					String surplus = br.readLine();
					if( surplus != null )
						{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
						}
					
					}
				catch (IOException ex)
				{ 
		        // catch possible io errors from readLine()
			    System.out.println("Error! Problem reading file "+inputfile);
				System.exit(0);
				}

			for( int x=1; x<=n; x++ )
				{
				if( seen[x] == false )
					{
					if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
					}
				}

			//! At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
			//! e[1] will be the second edge...
			//! (and so on)
			//! e[m-1] will be the last edge
			//! 
			//! there will be n vertices in the graph, numbered 1 to n

			//! INSERT YOUR CODE HERE!
			
			//! vvvvvvv STUDENTS CODE vvvvvvv

			int[][] vertices = new int[n][n]; //initialising the graph wherein we will be able to see whether a vertex has an edge.

										//e contains all the relations between given values(the: "8 11" part of the txt files).
			for (ColEdge edge: e) { 	//For every value in 
				//System.out.println(edge.u + " " + edge.v);
				vertices[edge.u-1][edge.v-1] = 1;			//establish symmetry by just flipping edge.v with edge.u
				vertices[edge.v-1][edge.u-1] = 1;			//because if 1 is related to 3, 3 is also related to 1.
			}


			System.out.println(hope.graphColoring(vertices, 3));

}
}
