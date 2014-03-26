import java.util.ArrayList;
import java.util.Iterator;

public class MatrixEntry implements Iterable<String> {
	private ArrayList<String> paths;
	
	public MatrixEntry() {
		paths = new ArrayList<String>();
	}
	
	public MatrixEntry(String s) {
		paths = new ArrayList<String>();
		paths.add(s);
	}
	
	public void add(String s) {
		paths.add(s);
	}
	
	public ArrayList<String> getPaths() {
		return paths;
	}
	
	public int size() {
		return paths.size();
	}

	public Iterator<String> iterator() {
		return paths.iterator();
	}
}
