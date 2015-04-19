/* Max B Garcia */

/* masc0877     */





import java.util.Iterator;



public class Queue<E> implements Iterable<E> {



	private LinearListADT<E> list;



	//Queue Constructor 

	public Queue() {

		list = new LinearList<>();

	}



	//Adds element to the queue

   	public void enqueue(E obj) {

		list.addLast(obj);	

	} 



	//Returns and removes the first element from queue  

    	public E dequeue() {

		return list.removeFirst();	

	}

	

	//gives how many elements in queue   

    	public int size() {

		return list.size();

	}

	

	//Returns true if empty, false otherwise

    	public boolean isEmpty() {

		return list.isEmpty();

	}



	//returns the first element in the queue, but does not remove it

    	public E peek() {

		return list.get(1);

	}



	//Checks to see if the queue contains an object E

    	public boolean contains(E obj) {

		return list.contains(obj);

	}



	//Clears the queue

    	public void makeEmpty() {

		list.clear();

	} 



	//Creates an iterator for the queue

    	public Iterator<E> iterator() {

		return list.iterator();

	}

}

