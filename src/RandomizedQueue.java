import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int size;
	private Node head;
	private Node tail;
	
	public RandomizedQueue() {
	  head = tail = null;
	  size = 0;
	}
	
	public boolean isEmpty() {
	  return size == 0;
	}
	
	public int size() {
	  return size;
	}
	
	public void enqueue(Item item) {
	  if (item == null) {
	    throw new IllegalArgumentException("Null value argument sent.");
	  }
				
	  Node newNode = new Node(item);
				
	  if (size == 0) {
	    head = tail = newNode;
	  } else {
	    Node currHead = head;
		newNode.next = currHead;	
		currHead.prev = newNode;
		head = newNode;			
	  }
			  
	  size++;		
	}
	
	public Item dequeue() {

	  if (size == 0) {
		  throw new NoSuchElementException("Queue is currently empty.");
	  }
	  
	  int indexToRemove = StdRandom.uniform(size);
	  
	  int halfWayPoint = size / 2;
	  
	  if (indexToRemove > halfWayPoint) {
	    int counter = size - 1;
	    Node currTail = tail;
	    while (currTail != null) {
	    	  if (counter == indexToRemove) {
	    		Node prevNode = currTail.prev;
	    		if (indexToRemove != (size-1)) {
		    	  prevNode.next = currTail.next;	
		    	  currTail.next.prev = prevNode;
	    		} else {
	    		  prevNode.next = null;
	    		  currTail.prev = null;
	    		  tail = prevNode;
	    		}
	    		size--;
	    		return (Item) currTail.getData(); 
	    	  } else {
	    	    currTail = currTail.prev;
	    	    counter--;
	    	  }
	    }
	  } else {
		int counter = 0; 
	    Node currHead = head;
	    while (currHead != null) {
	    	  if (counter == indexToRemove) {
	    		if (indexToRemove == 0) {
	    		  head = currHead.next;
	    		  currHead.next = null;
	    		} else {
		      Node prevNode = currHead.prev;
		      if (currHead.next == null) {
		    	    prevNode.next = null;
		      } else {
		    	    prevNode.next = currHead.next;
		    	    currHead.next.prev = prevNode;
		      }		    	  
		    	  currHead.next = null;
		    	  currHead.prev = null;	    			
	    		}
	    		size--;
	    		return (Item) currHead.getData(); 
	    	  } else {
	    	    currHead = currHead.next;
	    	    counter++;
	    	  }
	    }
	  }
	  
	  return null;
	}
	
	public Item sample() {		
	  if (size == 0) {
	    throw new NoSuchElementException("Queue is currently empty.");
      }
		  
	  int randomItem = StdRandom.uniform(size);
	  
	  int halfWayPoint = size / 2;
	  
	  if (randomItem > halfWayPoint) {
	    int counter = size - 1;
	    Node currTail = tail;
	    while (currTail != null) {
	    	  if (counter == randomItem) {
	    		return (Item) currTail.getData(); 
	    	  } else {
	    	    currTail = currTail.prev;
	    	    counter--;
	    	  }
	    }
	  } else {
		int counter = 0; 
	    Node currHead = head;
	    while (currHead != null) {
	    	  if (counter == randomItem) {
	    		return (Item) currHead.getData(); 
	    	  } else {
	    	    currHead = currHead.next;
	    	    counter++;
	    	  }
	    }
	  }
	  
	  return null;
	}
	
	private class Node<Item> {
	  private Item data;
	  private Node next;
	  private Node prev;
			
	  public Node(Item data) {
	    this.data = data;
		this.next = null;
		this.prev = null;
	  }	
		  
      public Item getData() {
        return data;
      }
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandQueueIterator<Item>();
	}
	
	private class RandQueueIterator<Item> implements Iterator<Item> {

		private Object[] randomArray;
		private int iterCounter;
		
		public RandQueueIterator() {
			randomArray = new Object[size];
			iterCounter = 0;
			Node currentNode = head;
			int counter = 0;
			while (currentNode != null) {
		      randomArray[counter] = currentNode.getData();
		      counter++;
			  currentNode = currentNode.next;
			}
			StdRandom.shuffle(randomArray);
		}
		
		@Override
		public boolean hasNext() { 
	      return iterCounter < randomArray.length;      
		}

		@Override
		public Item next() {
		   if (!hasNext()) {
		     throw new NoSuchElementException();
		   }
		   return (Item) randomArray[iterCounter++];
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}		
	}

	public static void main(String[] args) {
		
		RandomizedQueue<Integer> rQueue = new RandomizedQueue<Integer>();
		
		rQueue.enqueue(22);
		rQueue.enqueue(10);
		rQueue.enqueue(34);
		System.out.println("SAMPLE: " + rQueue.sample());
		rQueue.dequeue();
		System.out.println("SIZE: " + rQueue.size());
		rQueue.dequeue();
		rQueue.dequeue();
		System.out.println("SIZE: " + rQueue.size());
		rQueue.enqueue(10);
		rQueue.enqueue(34);
		System.out.println(rQueue.sample());
		rQueue.dequeue();
		rQueue.dequeue();
		System.out.println("SIZE: " + rQueue.size());
		rQueue.enqueue(7);
		rQueue.enqueue(5);
		System.out.println("SAMPLE: " + rQueue.sample());
		rQueue.enqueue(99);
		rQueue.enqueue(2);
		System.out.println("SAMPLE: " + rQueue.sample());
		rQueue.dequeue();	
		System.out.println("SAMPLE: " + rQueue.sample());
		Iterator<Integer> rqueueIterator = rQueue.iterator();
		while(rqueueIterator.hasNext()) {
			Integer item = rqueueIterator.next();
			System.out.println(item);
		}		
	}
}
