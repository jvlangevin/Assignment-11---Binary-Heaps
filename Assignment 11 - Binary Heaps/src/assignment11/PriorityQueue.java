package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is implemented as a min heap. The min heap is
 * implemented implicitly as an array.
 * 
 * @author
 */
public class PriorityQueue<AnyType> {

	private int currentSize;
	private AnyType[] array;
	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their natural ordering (i.e., AnyType is
	 * expected to be Comparable) AnyType is not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(){

		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders elements according to the input Comparator
	 * (i.e., AnyType need not be Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super AnyType> c){
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {

		if(this.size() == 0){
			throw new NoSuchElementException();
		}
		
		return this.array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {
		
		// if the heap is empty, throw a NoSuchElementException
		if(this.size() == 0){
			throw new NoSuchElementException();
		}

		// store the minimum item so that it may be returned at the end
		AnyType minValue = this.findMin();

		// replace the item at minIndex with the last item in the tree
		AnyType lastValue = this.array[this.size()-1];
		this.array[0] = lastValue;
		this.array[this.size() - 1] = null;
		
		// update size
		this.currentSize--;

		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!
		this.percolateDown(0);
		
		// return the minimum item that was stored
		return minValue;
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x
	 *            -- the item to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(AnyType x) {

		// if the array is full, double its capacity
		if (currentSize == array.length) {

			AnyType[] tempArray = (AnyType[]) new Object[array.length * 2];

			for (int i = 0; i < array.length; i++) {
				tempArray[i] = array[i];
			}
			array = tempArray;
		}

		// If the heap is empty, add the item to the first spot
		if (currentSize == 0) {
			array[0] = x;
			currentSize++;
			return;
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;

		// update size
		currentSize++;

		// percolate the new item up the levels of the tree until heap order is restored
		percolateUp(currentSize - 1);

	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");
			}

			out.println("}");
			out.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the user at construction time, or
	 * Comparable, if no Comparator was provided.
	 */
	@SuppressWarnings("unchecked")
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}

	/**
	 * Percolates the item with the specified index up the heap until it reaches it appropriate position (i.e. when the
	 * item is greater than its parent node).
	 * 
	 * @param index
	 *            -- the index of the item that will be percolated up the heap
	 */
	private void percolateUp(int index) {

		while (compare(array[index], array[parentIndex(index)]) < 0) {
			AnyType temp = array[parentIndex(index)];
			array[parentIndex(index)] = array[index];
			array[index] = temp;
			index = parentIndex(index);
		}

	}
	
	/**
	 * Percolates the item downward with the specified index until it reaches the appropriate position
	 * @param index
	 */
	private void percolateDown(int index) {

		while(numChildren(index) != 0){
			
			// If the node has one child
			if(numChildren(index) == 1){
				// If the node is greater than its child, swap them
				if(compare(array[index], array[leftChildIndex(index)]) > 0){
					AnyType temp = array[leftChildIndex(index)];
					array[leftChildIndex(index)] = array[index];
					array[index] = temp;
					return;
				}
				// If the node is less than its child, it is at its correct position
				else return;
			}
			else{
				// If the node is less than both its children, it is at its correct position
				if(compare(array[index], array[leftChildIndex(index)]) < 0 && compare(array[index], array[rightChildIndex(index)]) < 0){
					return;
				}
				// If the node's right child is less than its left child, swap the node with the right child
				// and continue percolating downwards
				if(compare(array[rightChildIndex(index)], array[leftChildIndex(index)]) < 0){
					AnyType temp = array[rightChildIndex(index)];
					array[rightChildIndex(index)] = array[index];
					array[index] = temp;
					index = rightChildIndex(index);
				}
				// If the node's left child is less than its right child, swap the node with the left child
				// and continue percolating downwards 
				else if(compare(array[leftChildIndex(index)], array[rightChildIndex(index)]) < 0){
					AnyType temp = array[leftChildIndex(index)];
					array[leftChildIndex(index)] = array[index];
					array[index] = temp;
					index = leftChildIndex(index);
				}
			}
		}
	}
	
	/**
	 * Returns the number of children of the node with the specified index.
	 * @param index -- index of a node
	 */
	private int numChildren(int index){
		
		int children = 0;
		
		if((index*2) + 1 <= this.currentSize){
			if(array[(index * 2) + 1] != null){
				children++;
			}
		}
		if((index*2) + 1 <= this.currentSize){
			if(array[(index * 2) + 2] != null){
				children++;
			}
		}
		return children;
	}

	/**
	 * Returns the index of the parent node of the node with the specified index.
	 * 
	 * @param index
	 *            -- the index of a child node
	 */
	private static int parentIndex(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Returns the index of the left child of the node with the specified index.
	 * 
	 * @param index
	 *            -- the index of a parent node
	 */
	private static int leftChildIndex(int index) {
		return (index * 2) + 1;
	}

	/**
	 * Returns the index of the right child of the node with the specified index.
	 * 
	 * @param index
	 *            -- the index of a parent node
	 */
	private static int rightChildIndex(int index) {
		return (index * 2) + 2;
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}

}
