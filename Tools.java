import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Tools {
	
	// checks all possible indices in the paths passed down against the matrix pathMatrix
	public static boolean vertexCheck(PathMatrix pathMatrix, String[] paths) {
		// do this for each path in the list (now an array of strings)
		for (int i = 0; i < paths.length; i++) {
			List<String> vertices = Arrays.asList(paths[i].split("\\s*,\\s*"));

			// check all possible indexes and make sure they're nonzero by checking if the 
			// path at that index in the original pathmatrix is populated
			for (int j = 0; j < vertices.size(); j++) {
				for (int k = j; k < vertices.size(); k++) {
					if (j != k) {
						int vj = (Integer.parseInt(vertices.get(j)));
						int vk = (Integer.parseInt(vertices.get(k)));
						
						//System.out.println("vj: " + vj + " vk: " + vk);
						if (pathMatrix.getPaths(vj,vk).size() == 0) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	// tests powers 2,...,n against A for shortcuts
	public static boolean testPowers(PathMatrix pathMatrix) {
		PathMatrix[] powers = pathMatrix.getPowers();
		
		for (int i = 1; i < powers.length; i++) {
			for (int j = 0; j < powers.length; j++) {
				for (int k = 0; k < powers.length; k++) {
					// check each entry of powers[i] to make sure its edges are in powers[0]
					if (powers[0].getPaths(j,k).size() > 0 && powers[i].getPaths(j,k).size() > 0) {
						String[] paths = powers[i].getPaths(j,k).toArray(new String[0]);
						if(!Tools.vertexCheck(pathMatrix, paths)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	// this gets the indices of all the 1's in the adjancency matrix passed to it
	// takes in an undirected graph, the indices are treated as sets so it only
	// gets one direction, in other words the ones in the upper half of the adj matrix
	public static ArrayList<ArrayList<Integer>> getIndicesOfOnes(int matrix[][]) {
		HashSet<HashSet<Integer>> indices = new HashSet<HashSet<Integer>>();
		
		// add indices of 1's to the hashset indices
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix.length; j++) {
				if (matrix[i][j] == 1) {
					indices.add(new HashSet<Integer>(Arrays.asList(i,j)));
				}
			}
		}
		
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		
		for (HashSet<Integer> subset : indices) {
			list.add(new ArrayList<Integer>(subset));
		}
		
		return list;
	}
	
	// returns the power set of {0,...,n}
	public static ArrayList<ArrayList<Integer>> getSubsets(int n) {
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
		
		//add the empty set
		subsets.add(new ArrayList<Integer>());
				
		// 2^n ways, so shift 1 over n times
		int allMasks = (1 << n);
				
		// this pretty much just counts in binary, adds index to the subset
		// if it appears in the binary number its on
		for (int i = 1; i < allMasks; i++) {
			ArrayList<Integer> subset = new ArrayList<Integer>();
					
		    for (int j = 0; j < n; j++) {
		        if ((i & (1 << j)) > 0) { //The j-th element is used
		           subset.add(j);
		        }
		    }
		    
		    subsets.add(subset);
		}
		
		return subsets;
	}
	
	public static int[][][] getAllOrientations(ArrayList<ArrayList<Integer>> indices, ArrayList<ArrayList<Integer>> subsets, int n) {
		int[][][] orientations = new int[subsets.size()][n][n];

		for (int i = 0; i < subsets.size(); i++) {
			for (int j = 0; j < n; j++) {
				if (subsets.get(i).contains(j)) {
					orientations[i][indices.get(j).get(0)][indices.get(j).get(1)] = 1;
				} else {
					orientations[i][indices.get(j).get(1)][indices.get(j).get(0)] = 1;
				}
			}
		}
		
		return orientations;
	}
}
