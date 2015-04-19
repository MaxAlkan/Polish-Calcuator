/* Max Garcia */

/* masc0877   */







import java.util.Iterator;



public class Stack<E> implements Iterable<E> {



	private LinearListADT<E> list;

    

	//Stack constructor

	public Stack() {

		list = new LinearList<>();	

	}      

	

	//Adds an element to the top of the stack                

	public void push(E obj) {

		list.addLast(obj);

	}



	//Removes and returns the last item in the stack

	public E pop() {

		return list.removeLast();

	}    



	//Returns the number of objects in the stack    

	public int size() {

		return list.size();

	}



	//Returns true if empty, false otherwise

	public boolean isEmpty() {

		return list.isEmpty();

	}



	//Returns, but does not remove the last item in the stack

	public E peek() {

		return list.get(list.size());

	}



	//Returns true if object is in the stack, false otherwise

	public boolean contains(E obj) {

		return list.contains(obj);

	}   



	//Clears the stack

	public void makeEmpty() {

		list.clear();

	}



	//Returns an iterator for the stack

	public Iterator<E> iterator() {

		return list.iterator();	

	}  

	
}    







