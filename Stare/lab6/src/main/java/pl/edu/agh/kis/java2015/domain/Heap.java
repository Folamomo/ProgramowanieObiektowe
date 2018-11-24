package pl.edu.agh.kis.java2015.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


public class Heap <T extends Comparable<T>> {

	private int heapSize = 0;
	private ArrayList<T> tab = new ArrayList<>();
    private Comparator<T> compare = Comparator.naturalOrder();

    public Heap() {}

    public Heap(Comparator<T> tComparator){
        compare=tComparator;
    }


	public void insert(T value) {
		int currentIndex = heapSize;
		int parentIndex = parentIndex(currentIndex);
		tab.add(value);
		while( isChildGreaterThanParent(currentIndex, parentIndex) ) {
			swapElements(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex(currentIndex);
		}
		heapSize++;
	}

	public boolean isChildGreaterThanParent(int currentIndex, int parentIndex) {
        return compare.compare(tab.get(currentIndex), tab.get(parentIndex))>0;
	}


	public void swapElements(int index1, int index2) {
		int leftIndex = Math.min(index1, index2);
		int rightIndex = Math.max(index1, index2);
		T rightElement = tab.remove(rightIndex); //order is important, removing left would shift right by one
        T leftElement = tab.remove(leftIndex);
        tab.add(leftIndex, rightElement);
        tab.add(rightIndex, leftElement);
	}

	public T parentValue(int currentIndex) {
        return tab.get(parentIndex(currentIndex));
	}

	public T leftChildValue(int currentIndex) {
        return tab.get(leftChildIndex(currentIndex));
	}

    public T rightChildValue(int currentIndex) {
        return tab.get(rightChildIndex(currentIndex));
	}

	public int parentIndex(int currentIndex) {
		return (currentIndex-1)/2;
	}

    public int leftChildIndex(int currentIndex) {
		return (currentIndex+1)*2-1;
	}

    public int rightChildIndex(int currentIndex) {
		return (currentIndex+1)*2;
	}


	public int size() {
		return heapSize ;
	}

	public T top() {
		return tab.get(0);
	}

    @SuppressWarnings("Duplicates")
    public T extract_max() {
        T top = top();
        T last = tab.remove(--heapSize);
        if (heapSize==0) return top;
        tab.set(0, last);
        int current = 0;
        while(rightChildIndex(current)<heapSize){
            int biggerChild = rightChildIndex(current);
            if (compare.compare(leftChildValue(current), rightChildValue(current))>0){
                biggerChild = leftChildIndex(current);
            }
            if (isChildGreaterThanParent(biggerChild, current)){
                swapElements(current, biggerChild);
                current = biggerChild;
            }
            else return top;
        }
        if (leftChildIndex(current)<heapSize){
            if (isChildGreaterThanParent(leftChildIndex(current), current)) swapElements(current, leftChildIndex(current));
        }
        return top;
    }

    public void delte_max () {
        extract_max();
    }

    @SuppressWarnings("Duplicates")
    public void replace_max(T element){
        tab.set(0, element);
        int current = 0;
        while(rightChildIndex(current)<heapSize){
            int biggerChild = rightChildIndex(current);
            if (compare.compare(leftChildValue(current), rightChildValue(current))>0){
                biggerChild = leftChildIndex(current);
            }
            if (isChildGreaterThanParent(biggerChild, current)){
                swapElements(current, biggerChild);
                current = biggerChild;
            }
            else return;
        }
        if (leftChildIndex(current)<heapSize){
            if (isChildGreaterThanParent(leftChildIndex(current), current)) swapElements(current, leftChildIndex(current));
        }
    }

    public static <E extends Comparable<E>> Heap<E> heapify(Collection<E> collection, Comparator<E> comparator){
        Heap <E> heap = new Heap<>(comparator);
        for (E e : collection){
            heap.insert(e);
        }
        return heap;
    }

    public static <E extends Comparable<E>> Heap<E> heapify(Collection<E> collection){
        Heap <E> heap = new Heap<>();
        for (E e : collection){
            heap.insert(e);
        }
        return heap;
    }

    public void merge (Heap<T> other){
        tab.addAll(other.tab);
        heapSize+=other.heapSize;
    }

    public void meld (Heap<T> other){
        for (T element : other.tab){
            insert(element);
        }
    }
}
