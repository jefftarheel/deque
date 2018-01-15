import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Deque<Item> implements Iterable<Item> {
	
	private int size;
	private Node head;
	private Node tail;
	
	public Deque() {
	  head = null;
	  tail = null;
	  size = 0;
	}
	
	public boolean isEmpty() {
	  return size == 0;
	}
	
	public int size() {
	  return size;
	}
	
	public void addFirst(Item item) {		
	  if (item == null) {
	    throw new IllegalArgumentException("Null value argument sent.");
	  }
		
	  Node newNode = new Node(item);
		
	  if (size == 0) {
	    head = tail = newNode;
	  } else {
		Node currHead = head;
		currHead.prev = newNode;
		newNode.next = currHead;			
		head = newNode;			
      }
	  
	  size++;
	}
	
	public void addLast(Item item) {
	  if (item == null) {
	    throw new IllegalArgumentException("Null value argument sent.");
	  }
	  
	  Node newNode = new Node(item);
	  
	  if (size == 0) {
	    head = tail = newNode;
      } else {
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
      }
	  
	  size++;
	}
	
	public Item removeFirst() {
		
		if (size == 0) {
			throw new NoSuchElementException("Queue is currently empty.");
		}
		
		Node currentHead = head;
		
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			currentHead.next = null;
		}
		
		size--;
		
		return (Item) currentHead.getData();
		
	}
	
	public Item removeLast() {
		
		if (size == 0) {
			throw new NoSuchElementException("Queue is currently empty.");
		}
		
		Node currentTail = tail;
		
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			tail = currentTail.prev;
			tail.next = null;
		}
		
		size--;
		
		return (Item) currentTail.getData();
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
		return new DequeIterator<Item>();
	}
	
	private class DequeIterator<Item> implements Iterator<Item> {

		private Node currentNode;
		
		@Override
		public boolean hasNext() {
          if(currentNode == null){
            currentNode = head;
            return Optional.ofNullable(currentNode).isPresent();
          }else{
            currentNode = currentNode.next;
            return Optional.ofNullable(currentNode).isPresent();
          }
		}

		@Override
		public Item next() {
			
		  if (!Optional.ofNullable(currentNode).isPresent()) {
			  throw new NoSuchElementException("No more elements present in the queue.");
		  }
		  return (Item) currentNode.getData();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) {
		
		Deque<String> deque = new Deque<String>();
		deque.addFirst("A");
		deque.addFirst("B");
		deque.addFirst("C");
		deque.addFirst("D");
		deque.addFirst("E");
		deque.addFirst("F");
		deque.addFirst("G");
		deque.addFirst("H");
		deque.addFirst("I");
		deque.addFirst("J");
		
		Iterator<String> iterator = deque.iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			System.out.println(item);
		}	
	}

}
