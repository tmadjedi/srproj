import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {
		//int matrix[][] = {{0,1,1,1,0},{0,0,0,1,0},{0,0,0,1,0},{0,0,0,0,1},{0,0,0,0,0}};
		
		// undirected graph
		//int matrix2[][] = {{0,1,1,1,0},{1,0,0,1,0},{1,0,0,1,0},{1,1,1,0,1},{0,0,0,1,0}};
		
		// matrix from sergey's paper that is proven to not admit any semi-transitive orientations (my code agrees)
		int matrix2[][]={{0,1,1,1,1,0,0},{1,0,1,0,0,0,1},{1,1,0,1,0,1,0},{1,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,0,1,1,0,0,1},{0,1,0,0,1,1,0}};
		
		//PathMatrix pathMatrix = new PathMatrix(matrix);
		//pathMatrix.printPowers();
		
		//if (Tools.testPowers(pathMatrix)) {
		//	System.out.println("The graph is semi-transitive");
		//} else {
		//	System.out.println("The graph is not semi transitive");
		//}
		
		
		ArrayList<ArrayList<Integer>> indices = Tools.getIndicesOfOnes(matrix2);
		ArrayList<ArrayList<Integer>> subsets = Tools.getSubsets(indices.size());
		int orientations[][][] = Tools.getAllOrientations(indices, subsets, indices.size());
		
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
		boolean semiTransitive = false;
		ArrayList<Integer> semiTransIndices = new ArrayList<Integer>();
		for (int i = 0; i < subsets.size(); i++) {
			if (Tools.testPowers(new PathMatrix(orientations[i]))) {
				semiTransitive = true;
				semiTransIndices.add(i);
			}
		}
		
		// result
		if (semiTransitive) {
			System.out.println("The graph admits a semi-transitive orientation");
			System.out.println(semiTransIndices.toString());
		} else {
			System.out.println("The graph does not admit a semi-transitive orientation");
		}
	}
}
