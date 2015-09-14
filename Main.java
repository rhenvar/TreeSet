import java.util.*;

public class Main {
	public static void main(String[] args) {
		TreeSet<Integer> treeTest = new TreeSet<Integer>();
		List<Integer> arrayTest = new ArrayList<Integer>();
		Random r = new Random();
		int n = 10000000;
		long startTimeAdd = System.nanoTime();
		for (int i = 0; i < n; i++) {
			treeTest.add(r.nextInt(n));
		}
		long endTimeAdd = (System.nanoTime() - startTimeAdd);
		
		long startTimeIterate = System.nanoTime();
		
		for (Integer i : treeTest) {
			//System.out.println(i);
		}
		
		long endTimeIterate = (System.nanoTime() - startTimeIterate);
		
		System.out.println("Time to add " + n + " random items: " + endTimeAdd / 1000000 + "ms");
		System.out.println("Time to iterate over list: " + endTimeIterate / 1000000 + "ms");
		
		int testContains = r.nextInt(n);
		long startTimeContains = System.nanoTime();

		boolean isPresent = treeTest.contains(testContains);
		
		long endTimeContains = (System.nanoTime() - startTimeContains);
		
		System.out.println("Time to check if " + testContains + " is in the set " + endTimeContains / 1000000 + "ms " + isPresent);
	}
}
