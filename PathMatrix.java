import java.util.ArrayList;

public class PathMatrix {
	
//	private ArrayList<String>[][] paths;
	private MatrixEntry[][] paths;
	
	public PathMatrix(int size) {
//		paths = new ArrayList[size][size];
		paths = new MatrixEntry[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				paths[i][j] = new MatrixEntry();
			}
		}
	}
	
	public PathMatrix(int matrix[][]) {	
		if (matrix.length != matrix[0].length) {
			throw new IllegalArgumentException("Not a square matrix");
		}
		
		paths = new MatrixEntry[matrix.length][matrix.length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				paths[i][j] = new MatrixEntry();
				
				if (matrix[i][j] == 1) {
					if (matrix[j][i] == 1) {
						throw new IllegalArgumentException("Not a directed graph");
					}
					
					paths[i][j].add(i + "," + j);
				}
			}
		}
	}
	
	public ArrayList<String> getPaths(int i, int j) {
		return this.paths[i][j].getPaths();
	}
	
	
	// this function multiplies paths*pmat.paths (in that order)
	public PathMatrix multiply(PathMatrix pmat) {
		PathMatrix newMat = new PathMatrix(paths.length);
		
		// big loop. i,j takes us through the indices of newMat
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				
				// k iterates through the ith row of paths and the jth column 
				// of pmat.paths, and performs the "matrix multiplication"
				for (int k = 0; k < paths.length; k++) {
					if (paths[i][k].size() > 0 && pmat.paths[k][j].size() > 0) {
						for (String string : pmat.paths[k][j]) {
							newMat.paths[i][j].add(i + "," + string);
						}
					}
				}
			}
		}
		
		return newMat;
	}
	
	public PathMatrix[] getPowers() {
		PathMatrix[] powers = new PathMatrix[paths.length];
		powers[0] = this;
		
		for (int i = 1; i < paths.length; i++) {
			powers[i] = this.multiply(powers[i-1]);
		}
		
		return powers;
	}
	
	public void print() {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				System.out.print(paths[i][j].getPaths().toString() + " ");
			}
			
			System.out.println();
		}
	}
	
	public void printPowers() {
		PathMatrix[] powers = this.getPowers();
		for (int i = 0; i < powers.length; i++) {
			System.out.println("Power: " + (i+1));
			powers[i].print();
			System.out.println();
		}
	}
	
}
