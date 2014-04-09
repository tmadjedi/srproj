import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

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
		
		// check for cycles (entries on the diagonal)
		// this could be smooshed into the other loop at the price of readability
		for (int i = 0; i < powers.length; i++) {
			for (int j = 0; j < powers.length; j++) {
				if (powers[i].getPaths(j,j).size() > 0) {
					return false;
				}
			}
		}
		
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
	public static boolean placeholder(int[][] adj) {
		ArrayList<ArrayList<Integer>> indices = Tools.getIndicesOfOnes(adj);
		ArrayList<Integer> subset;
		int[][] orientation = new int[adj.length][adj.length];;
		
		//add the empty set

				
		// 2^n ways, so shift 1 over n times
		long allMasks = ((long)1 << indices.size());
		System.out.println(allMasks);
				
		// this pretty much just counts in binary, adds index to the subset
		// if it appears in the binary number its on
		for (long i = 1; i < allMasks; i++) {
			subset = new ArrayList<Integer>();
					
		    for (int j = 0; j < indices.size(); j++) {
		        if ((i & (1 << j)) > 0) { //The j-th element is used
		           subset.add(j);
		        }
		    }
		    
		    orientation = new int[adj.length][adj.length];
		    
			for (int k = 0; k < indices.size(); k++) {
				if (subset.contains(k)) {
					orientation[indices.get(k).get(0)][indices.get(k).get(1)] = 1;
				} else {
					orientation[indices.get(k).get(1)][indices.get(k).get(0)] = 1;
				}
			}
			
			if (Tools.testPowers(new PathMatrix(orientation))) {
				for (int q = 0; q < orientation.length; q++) {
					for (int r = 0; r < orientation.length; r++) {
						System.out.print(orientation[q][r] + " ");
					}
					System.out.println();
				}
				return true;
			}
		    
		}
		
		return false;
	}
	
	/*public static boolean semiTransitiveCheck(int[][] adj) {
		ArrayList<ArrayList<Integer>> indices = Tools.getIndicesOfOnes(adj);
		ArrayList<ArrayList<Integer>> subsets = Tools.getSubsets(indices.size());
		int[][] orientation = new int[adj.length][adj.length];;
		
		for (int i = 0; i < subsets.size(); i++) {
			orientation = null;
			orientation = new int[adj.length][adj.length];
			
			for (int k = 0; k < indices.size(); k++) {
				if (subsets.get(i).contains(k)) {
					orientation[indices.get(k).get(0)][indices.get(k).get(1)] = 1;
				} else {
					orientation[indices.get(k).get(1)][indices.get(k).get(0)] = 1;
				}
			}
			
			if (Tools.testPowers(new PathMatrix(orientation))) {
				return true;
			}
		}
		
				
				/*
		//ArrayList<Integer> semiTransIndices = new ArrayList<Integer>();
		for (int i = 0; i < subsets.size(); i++) {
			if (Tools.testPowers(new PathMatrix(orientations[i]))) {
				return true;
				//semiTransIndices.add(i);
			}
		}*/
		
		/*return false;
	}*/
	
	// iterative solution, can be optimized
	// nxn matrix, s is M
	public static int[][] generatePathScheme(int n, HashSet<Integer> s) {
		
		int[][] adj = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (s.contains(Math.abs(i-j))) {
					adj[i][j] = 1;
				}
			}
		}
		
		return adj;
	}
	
	public static Point2D[] getPoints(int n) {
		Point2D[] points = new Point2D[n];
		
		double theta = (2 * Math.PI) / n; 
		
		for (int i = 0; i < n; i++) {
			points[i] = new Point2D.Double(Math.cos(theta * i), Math.sin(theta * i));
		}
		
		return points;
	}
	
	public static ArrayList<Point> getEdges(int[][] adj) {
		ArrayList<Point> edges = new ArrayList<Point>();
		
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				if (adj[i][j] != 0) {
					edges.add(new Point(i,j));
				}
			}
		}
		
		return edges;
	}
	
}
