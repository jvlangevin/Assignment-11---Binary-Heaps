package assignment11;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

	ArrayList<Integer> smallIntArray;
	PriorityQueue<Integer> pQ_Int;
	PriorityQueue<String> pQ_String;
	PriorityQueue<Integer> intPQ_WithComparator;
	PriorityQueue<String> stringPQ_WithComparator;
	Comparator<String> comparator = Comparator.naturalOrder();

	@Before
	public void generateSmallIntArrayListForTesting() {
		smallIntArray = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			smallIntArray.add(i);
		}
		Collections.shuffle(smallIntArray);

	}

	@Before
	public void constructEmptyIntPriorityQueueNoComparator() {
		pQ_Int = new PriorityQueue<Integer>();
	}

	@Before
	public void constructEmptyStringPriorityQueueNoComparator() {
		pQ_String = new PriorityQueue<String>();
	}
	
	@Before
	public void constructStringPriorityQueueWithComparator(){
		stringPQ_WithComparator = new PriorityQueue<>(comparator);
	}

	@Test
	public void testSmallIntAddToPQFindMin() {

		// add 0-99 to our new priorityQueue
		for (int element : smallIntArray) {
			pQ_Int.add(element);
		}

		// finds min after adding 0-99
		assertEquals(0, (int) pQ_Int.findMin());

		// deletes min, which returns min
		assertEquals(0, (int) pQ_Int.deleteMin());

		// is the next value the new min?
		assertEquals(1, (int) pQ_Int.findMin());

		// delete it, which returns it x 3
		assertEquals(1, (int) pQ_Int.deleteMin());
		assertEquals(2, (int) pQ_Int.deleteMin());
		assertEquals(3, (int) pQ_Int.deleteMin());

		// is 4 our new min?
		assertEquals(4, (int) pQ_Int.findMin());

		// add 0 again, after we've deleted 0-3
		pQ_Int.add(0);

		// is 0 our new min? delete it. are we back to 4?
		assertEquals(0, (int) pQ_Int.findMin());
		assertEquals(0, (int) pQ_Int.deleteMin());
		assertEquals(4, (int) pQ_Int.findMin());

	}

	@Test(expected = NoSuchElementException.class)
	public void findMinWhenNothingsBeenAddedInts() {

		pQ_Int.findMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteMinWhenNothingsBeenAddedInts() {

		pQ_Int.deleteMin();
	}

	@Test
	public void addStringPriorityQueueFindMin() {

		pQ_String.add("k");
		pQ_String.add("l");
		pQ_String.add("c");
		pQ_String.add("d");
		pQ_String.add("b");
		pQ_String.add("j");
		pQ_String.add("e");
		pQ_String.add("f");
		pQ_String.add("g");
		pQ_String.add("h");
		pQ_String.add("i");
		pQ_String.add("a");

		assertEquals("a", pQ_String.findMin());

	}

	@Test
	public void addStringPriorityQueueDeleteMin() {

		pQ_String.add("k");
		pQ_String.add("l");
		pQ_String.add("c");
		pQ_String.add("d");
		pQ_String.add("b");
		pQ_String.add("j");
		pQ_String.add("e");
		pQ_String.add("f");
		pQ_String.add("g");
		pQ_String.add("h");
		pQ_String.add("i");
		pQ_String.add("a");

		assertEquals("a", pQ_String.deleteMin());
		assertEquals("b", pQ_String.deleteMin());
		assertEquals("c", pQ_String.deleteMin());
		assertEquals("d", pQ_String.deleteMin());

	}
	
	@Test
	public void addToPriorityQueueWithComparator() {

		stringPQ_WithComparator.add("k");
		stringPQ_WithComparator.add("l");
		stringPQ_WithComparator.add("c");
		stringPQ_WithComparator.add("d");
		stringPQ_WithComparator.add("b");
		stringPQ_WithComparator.add("j");
		stringPQ_WithComparator.add("e");
		stringPQ_WithComparator.add("f");
		stringPQ_WithComparator.add("g");
		stringPQ_WithComparator.add("h");
		stringPQ_WithComparator.add("i");
		stringPQ_WithComparator.add("a");

		assertEquals("a", stringPQ_WithComparator.toArray()[0]);
		assertEquals("c", stringPQ_WithComparator.toArray()[1]);
		assertEquals("b", stringPQ_WithComparator.toArray()[2]);
		assertEquals("f", stringPQ_WithComparator.toArray()[3]);
		assertEquals("d", stringPQ_WithComparator.toArray()[4]);
		assertEquals("e", stringPQ_WithComparator.toArray()[5]);
		assertEquals("j", stringPQ_WithComparator.toArray()[6]);
		assertEquals("l", stringPQ_WithComparator.toArray()[7]);
		assertEquals("g", stringPQ_WithComparator.toArray()[8]);
		assertEquals("h", stringPQ_WithComparator.toArray()[9]);
		assertEquals("i", stringPQ_WithComparator.toArray()[10]);
		assertEquals("k", stringPQ_WithComparator.toArray()[11]);
	}
	
	@Test
	public void findMinInPriorityQueueWithComparator() {

		stringPQ_WithComparator.add("k");
		assertEquals("k", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("l");
		assertEquals("k", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("c");
		assertEquals("c", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("d");
		assertEquals("c", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("b");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("j");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("e");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("f");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("g");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("h");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("i");
		assertEquals("b", stringPQ_WithComparator.findMin());
		
		stringPQ_WithComparator.add("a");
		assertEquals("a", stringPQ_WithComparator.findMin());

	}
	
	@Test
	public void deleteMinFromPriorityQueueWithComparator() {

		stringPQ_WithComparator.add("k");
		stringPQ_WithComparator.add("l");
		stringPQ_WithComparator.add("c");
		stringPQ_WithComparator.add("d");
		stringPQ_WithComparator.add("b");
		stringPQ_WithComparator.add("j");
		stringPQ_WithComparator.add("e");
		stringPQ_WithComparator.add("f");
		stringPQ_WithComparator.add("g");
		stringPQ_WithComparator.add("h");
		stringPQ_WithComparator.add("i");
		stringPQ_WithComparator.add("a");
		
		assertEquals("a", stringPQ_WithComparator.deleteMin());
		assertEquals("b", stringPQ_WithComparator.deleteMin());
		assertEquals("c", stringPQ_WithComparator.deleteMin());
		assertEquals("d", stringPQ_WithComparator.deleteMin());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void findMinWhenNothingsBeenAddedStrings() {

		pQ_String.findMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteMinWhenNothingsBeenAddedStrings() {

		pQ_String.deleteMin();
	}

}
