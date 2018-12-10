package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;

public class HeapTest {

	@Test
	public void testSwap(){
		Heap heap = new Heap<Integer>();
		heap.insert(1);
		heap.insert(2);
		assertEquals(heap.top(), 2);
		heap.swapElements(0, 1);
		assertEquals(heap.top(), 1);
	}

	@Test
	public void insert0intoNewHeap_topIs0() {

		Heap heap = new Heap<Double>();
		heap.insert(0);

		assertEquals("size should be 1",1,heap.size());
		assertEquals(0,heap.top());
		assertEquals(1,heap.size());
	}

	@Test
	public void insert0AndThen2intoNewHeap_topIs2() {

		Heap heap = new Heap();
		heap.insert(0);
		heap.insert(2);

		assertEquals("size should be 2",2,heap.size());
		assertEquals(2,heap.top());
	}

	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs6() {

		Heap heap = new Heap<>();
		heap.insert(0);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);

		assertEquals(6,heap.top());
	}
	@Test
	public void insert0and2ExtractTopTwice() {
		Heap heap = new Heap<Double>();
		heap.insert(0);
		heap.insert(2);
		assertEquals("size should be 2",2,heap.size());
		assertEquals(2,heap.extract_max());
		assertEquals("size should be 1",1,heap.size());
		assertEquals(0,heap.extract_max());
		assertEquals("size should be 0",0,heap.size());
	}
	@Test
	public void insert1and3DelteTopTwice() {
		Heap heap = new Heap<Double>();
		heap.insert(1);
		heap.insert(3);
		assertEquals("size should be 2",2,heap.size());
		heap.delte_max();
		assertEquals("size should be 1",1,heap.size());
		heap.delte_max();
		assertEquals("size should be 0",0,heap.size());
	}
	@Test
	public void makeSomeHeapCheckMaxThenReplaceCheckMaxAgain(){
		Heap heap = new Heap();
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);

		assertEquals(6,heap.top());
		heap.replace_max(7);
		assertEquals(7, heap.top());
		heap.replace_max(3);
		assertEquals(5, heap.top());
	}
	@Test
	public void HeapifySomeCollectionThenChcekHeapProperty(){
		LinkedList list = new LinkedList<Integer>();
		list.add(1);
		list.add(6);
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(4);
		list.add(7);

		Heap heap = Heap.heapify(list);

		assertFalse(heap.isChildGreaterThanParent(heap.leftChildIndex(0), 0));
		assertFalse(heap.isChildGreaterThanParent(heap.leftChildIndex(1), 1));
		assertFalse(heap.isChildGreaterThanParent(heap.leftChildIndex(2), 2));
		assertFalse(heap.isChildGreaterThanParent(heap.rightChildIndex(0), 0));
		assertFalse(heap.isChildGreaterThanParent(heap.rightChildIndex(1), 1));
		assertFalse(heap.isChildGreaterThanParent(heap.rightChildIndex(2), 2));
	}
	@Test
	public void MakeSomeHeapsThenMerge(){
		Heap h1 = new Heap();
		Heap h2 = new Heap();
		h1.insert(1);
		h1.insert(18);
		h2.insert(5);
		h2.insert(8);
		h1.merge(h2);
		assertEquals(18, h1.top());
		assertEquals(4, h1.size());
	}
	@Test
	public void MakeSomeHeapsThenMeld(){
		Heap h1 = new Heap();
		Heap h2 = new Heap();
		h1.insert(1);
		h1.insert(13);
		h1.insert(7);
		h2.insert(5);
		h2.insert(6);
		h2.insert(8);
		h2.insert(11);
		h1.meld(h2);
		assertEquals(7, h1.size());
		assertEquals(13, h1.extract_max());
		assertEquals(11, h1.extract_max());
		assertEquals(8, h1.extract_max());
		assertEquals(7, h1.extract_max());
		assertEquals(6, h1.extract_max());
		assertEquals(5, h1.extract_max());
		assertEquals(1, h1.extract_max());
	}
	@Test
	public void ReverseComparator(){
		Heap h1 = new Heap<Integer>(Comparator.reverseOrder());
		Heap h2 = new Heap();
		h1.insert(1);
		h1.insert(13);
		h1.insert(7);
		h2.insert(5);
		h2.insert(6);
		h2.insert(8);
		h2.insert(11);
		h1.meld(h2);
		assertEquals(7, h1.size());
		assertEquals(1, h1.extract_max());
		assertEquals(5, h1.extract_max());
		assertEquals(6, h1.extract_max());
		assertEquals(7, h1.extract_max());
		assertEquals(8, h1.extract_max());
		assertEquals(11, h1.extract_max());
		assertEquals(13, h1.extract_max());
	}
}
