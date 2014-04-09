import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

public class Driver {
	public static void main(String[] args) {
		//int matrix[][] = {{0,1,1,1,0},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,0,1},{0,0,0,0,0}};
		
		// undirected graph
		//int matrix2[][] = {{0,1,1,1,0},{1,0,0,1,0},{1,0,0,1,0},{1,1,1,0,1},{0,0,0,1,0}};
		
		// matrix from sergey's paper that is proven to not admit any semi-transitive orientations (my code agrees)
		//int matrix2[][]={{0,1,1,1,1,0,0},{1,0,1,0,0,0,1},{1,1,0,1,0,1,0},{1,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,0,1,1,0,0,1},{0,1,0,0,1,1,0}};
		
		//int matrix2[][] = {{0,1,0,0,0,0,0},{1,0,1,0,0,0,0},{0,1,0,1,0,0,0},{0,0,1,0,1,0,0},{0,0,0,1,0,1,0},{0,0,0,0,1,0,1},{0,0,0,0,0,1,0}};
		
		//PathMatrix pathMatrix = new PathMatrix(matrix);
		//pathMatrix.printPowers();
		
		//if (Tools.testPowers(pathMatrix)) {
		//	System.out.println("The graph is semi-transitive");
		//} else {
		//	System.out.println("The graph is not semi transitive");
		//}
		
		//new MatrixInputFrame();
		
		//ArrayList<ArrayList<Integer>> indices = Tools.getIndicesOfOnes(matrix2);
		//ArrayList<ArrayList<Integer>> subsets = Tools.getSubsets(indices.size());
		//int orientations[][][] = Tools.getAllOrientations(indices, subsets, matrix2.length);
		
		// this prints all the matrices
		/*
		for (int i = 0; i < subsets.size(); i++) {
			System.out.println(i);
			for (int j = 0; j < matrix2.length; j++) {
				for (int k = 0; k < matrix2.length; k++) {
					System.out.print(orientations[i][j][k] + " ");
				}
				
				System.out.println();
			}
			
			System.out.println();
		}*/
		
		// run testPowers on all the matrices
		//boolean semiTransitive = Tools.semiTransitiveCheck(matrix2);
		/*
		// result
		if (semiTransitive) {
			System.out.println("The graph admits a semi-transitive orientation");
			//System.out.println(semiTransIndices.toString());
		} else {
			System.out.println("The graph does not admit a semi-transitive orientation");
		}
		*/
		
		// stuff
		/*
		HashSet<Integer> s = new HashSet<Integer>();
		int[][] scheme;
		s.add(3);
		int n = 100;
		
		
		for (int i = 11; i < n; i++) {
			for (int j = 3; j <= i; j++) {
				s = new HashSet<Integer>();
				s.add(j);
				s.remove(j-1);
				s.add(2);
				
				scheme = Tools.generatePathScheme(i, s);
				
				System.out.println("i: " + i);
				System.out.println("s: " + s.toString());
				if (Tools.semiTransitiveCheck(scheme)) {
					System.out.println("Scheme admits STO");
				} else {
					System.out.println("Scheme does not admit STO");
					throw new IllegalArgumentException();
				}
				System.out.println();
			}
			
		}*/
		//end stuff
		
		/*s.add(3);
		scheme = Tools.generatePathScheme(13,s);
		
		System.out.println(Tools.semiTransitiveCheck(scheme));
		*/
		
		//GraphPicture g = new GraphPicture();
		//g.paint(null);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
