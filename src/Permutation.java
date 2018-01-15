import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
	
		RandomizedQueue<String> rqueue = new RandomizedQueue<String>();
		
		int numberOfValues = Integer.valueOf(args[0]);
		
		while (!StdIn.isEmpty()) {
			rqueue.enqueue(StdIn.readString());
		}
		
		int counter = 0;
		while (counter < numberOfValues) {
			StdOut.println(rqueue.dequeue());
			counter++;
		}

	}

}
