package assignment11;

import java.util.ArrayList;
import java.util.Collections;

public class TestDotFile {
	
	public static void main(String[] args){
	
	
	
	ArrayList<Integer> smallIntArray = new ArrayList<>();
	for (int i = 0; i < 100; i++) {
		smallIntArray.add(i);
	}
	Collections.shuffle(smallIntArray);
	
	PriorityQueue<Integer> pQ_Int = new PriorityQueue<Integer>();
	
	for (int element : smallIntArray) {
		pQ_Int.add(element);
	}
	
	pQ_Int.generateDotFile("assignment11DOT.dot");
	pQ_Int.deleteMin();
	pQ_Int.generateDotFile("assignment11DOTDeleteMin.dot");

	}
}
