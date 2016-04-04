package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	
	ArrayList<Integer> smallIntArray;
	PriorityQueue<Integer> pQ_Int;
	PriorityQueue<String> pQ_String;
	
	@Before
	public void generateSmallIntArrayListForTesting() {
		smallIntArray = new ArrayList<>();
		for(int i = 0; i < 100; i++)
		{		
			smallIntArray.add(i);
		}
		Collections.shuffle(smallIntArray);
		
	}
	
	@Before
	public void constructEmptyIntPriorityQueueNoComparator(){
		pQ_Int = new PriorityQueue<Integer>();
	}
	
	@Before
	public void constructEmptyStringPriorityQueueNoComparator(){
		pQ_String = new PriorityQueue<String>();
	}
	
	
	@Test
	public void testSmallIntAddToPQFindMin(){
		
		//add 0-99 to our new priorityQueue
		for(int element : smallIntArray)
		{
			pQ_Int.add(element);
		}
		
		
		//finds min after adding 0-99
		assertEquals(0, (int)pQ_Int.findMin());
		
		//deletes min, which returns min
		assertEquals(0, (int)pQ_Int.deleteMin());
		
		//is the next value the new min?
		assertEquals(1, (int)pQ_Int.findMin());
		
		//delete it, which returns it x 3
		assertEquals(1, (int)pQ_Int.deleteMin());
		assertEquals(2, (int)pQ_Int.deleteMin());
		assertEquals(3, (int)pQ_Int.deleteMin());
		
		//is 4 our new min?
		assertEquals(4, (int)pQ_Int.findMin());
		
		//add 0 again, after we've deleted 0-3
		pQ_Int.add(0);
		
		//is 0 our new min? delete it. are we back to 4?
		assertEquals(0, (int)pQ_Int.findMin());
		assertEquals(0, (int)pQ_Int.deleteMin());
		assertEquals(4, (int)pQ_Int.findMin());
		
		
		
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinWhenNothingsBeenAddedInts(){
		
		pQ_Int.findMin();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteMinWhenNothingsBeenAddedInts(){
		
		pQ_Int.deleteMin();
	}
	
	@Test 
	public void addStringPriorityQueueFindMin(){
		
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
	public void addStringPriorityQueueDeleteMin(){
		
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
	
	@Test (expected = NoSuchElementException.class)
	public void findMinWhenNothingsBeenAddedStrings(){
		
		pQ_String.findMin();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteMinWhenNothingsBeenAddedStrings(){
		
		pQ_String.deleteMin();
	}

}
