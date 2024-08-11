package prj5;

import java.util.*;

// -------------------------------------------------------------------------
/**
 * Our chosen data structure for this project.
 * 
 * @author jdevi
 * @version Nov 15, 2023
 * @param <T>
 *            placeholder for the object type
 */
public class DLList<T> {
    private static class Node<T> {
        /**
         * previous node
         */
        private Node<T> prev;
        /**
         * next node
         */
        private Node<T> next;
        /**
         * data in current node
         */
        private T data;

        // ----------------------------------------------------------
        /**
         * Create a new Node object.
         * 
         * @param data
         *            init data field
         */
        public Node(T data) {
            this.data = data;
        }


        // ----------------------------------------------------------
        /**
         * return prev node.
         * 
         * @return prev node
         */
        public Node<T> prev() {
            return prev;
        }


        // ----------------------------------------------------------
        /**
         * getter for next node.
         * 
         * @return next node
         */
        public Node<T> next() {
            return next;
        }


        // ----------------------------------------------------------
        /**
         * getter for data in current node.
         * 
         * @return data field
         */
        public T data() {
            return data;
        }


        // ----------------------------------------------------------
        /**
         * setter for prev node.
         * 
         * @param node
         *            new prev node
         */
        public void setPrev(Node<T> node) {
            prev = node;
        }


        // ----------------------------------------------------------
        /*
         * setter for next node.
         * 
         * @param node new next node
         */
        public void setNext(Node<T> node) {
            next = node;
        }

    }

    // ~ Fields ................................................................
    /**
     * number of entries in data structure excluding sentinels
     */
    private int size;
    /**
     * front sentinel
     */
    private Node<T> head;
    /**
     * back sentinel
     */
    private Node<T> tail;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new DLList object.
     */
    public DLList() {
        init();
    }


    /**
     * sets up the fields with default values, creates sentinels
     */
    private void init() {
        head = new DLList.Node<T>(null);
        tail = new DLList.Node<T>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Returns whether the DLList is empty or not
     * 
     * @return Whether the list is empty as a boolean
     */
    // ~Public Methods ........................................................
    public boolean isEmpty() {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * Returns the size of the list
     * 
     * @return the size of the list as an integer
     */
    public int size() {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Determines whether the DLList contains a certain value
     * 
     * @param value
     *            The value being scanned for in the list
     * @return Whether the value is in the list represented as a boolean
     */
    public boolean contains(T value) {
        return lastIndexOf(value) != -1;

    }


    // ----------------------------------------------------------
    /**
     * Returns a certain Node's data at a given index
     * 
     * @param index
     *            The index in which we are retrieving the data from
     * @return The data at the given index or null if nothing is found
     */
    public T get(int index) {
        return getNodeAtIndex(index).data;
    }


    // ----------------------------------------------------------
    /**
     * Adding a Node to the DLList with the given value
     * 
     * @param value
     *            The value being added to the DLList
     */
    public void add(T value) {
        add(size(), value);
    }


    // ----------------------------------------------------------
    /**
     * add a new node to the list at a given index. throws exceptions if bad
     * value/index
     * 
     * @param index
     *            desired index to place new node
     * @param value
     *            data within new node
     */
    public void add(int index, T value) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (value == null) {
            throw new IllegalArgumentException("Cannot add a null value");
        }
        Node<T> nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        }
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<T> addition = new Node<T>(value);
        addition.setPrev(nodeAfter.prev());
        addition.setNext(nodeAfter);
        nodeAfter.prev().setNext(addition);
        nodeAfter.setPrev(addition);
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Gets the node at a specific index
     * 
     * @param index
     *            The index at which we are accessing the node
     * @return The node at the specific index or null if nothing is found
     */
    public Node<T> getNodeAtIndex(int index) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException("The given index is not in the "
                + "list");
        }
        Node<T> place = head.next();
        for (int i = 0; i < index; i++) {
            place = place.next();
        }
        return place;
    }


    // ----------------------------------------------------------
    /**
     * returns the last occurence of a given entry in the list.
     * 
     * @param object
     *            the entry we are searching for
     * @return index of last occurence; else if not found, return -1
     */
    public int lastIndexOf(T object) {

        Node<T> current = tail.prev();
        for (int i = size() - 1; i >= 0; i--) {
            if (current.data.equals(object)) {
                return i;
            }
            current = current.prev();
        }
        return -1;
    }


    // ----------------------------------------------------------
    /**
     * removes a node at a given index.
     * 
     * @param index
     *            the given index of desired removal
     * @return true if successful, false if unsuccessful
     */
    public boolean remove(int index) {
        Node<T> removeNode = getNodeAtIndex(index);
        removeNode.next().setPrev(removeNode.prev());
        removeNode.prev().setNext(removeNode.next());
        size--;
        return true;
    }


    // ----------------------------------------------------------
    /**
     * removes the node of a given data entry.
     * 
     * @param obj
     *            entry that the node to be removed contains
     * @return true if successful removal, false if not
     */
    public boolean remove(T obj) {
        Node<T> curr = head.next();
        int index = 0;
        while (!curr.equals(tail)) {
            if (curr.data.equals(obj)) {
                remove(index);
                return true;
            }
            curr = curr.next();
            index++;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * sends list info to string form
     * 
     * @return final string product
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        Node<T> place = head.next();
        while (place.next() != null) {
            builder.append(place.data());
            if (place.next().next() != null) {
                builder.append(", ");
            }
            place = place.next();
        }
        builder.append("}");
        return builder.toString();
    }


    // ----------------------------------------------------------
    /**
     * Converts the DLList into an array
     * 
     * @return An array representation of the DLList
     */
    public Object[] toArray() {
        Node<T> place = head.next;
        Object[] newArr = new Object[size];
        for (int i = 0; i < size; i++) {
            newArr[i] = place.data();
            place = place.next();
        }
        return newArr;
    }


    // ----------------------------------------------------------
    /**
     * sorts the list using insertion technique given a comparator.
     * 
     * @param c
     *            selected comparator
     */
    public void insertionSort(Comparator<T> c) {
        if (size > 1) {
            Node<T> sorted = head.next;
            Node<T> unsorted = sorted.next;
            sorted.setNext(null);
            Node<T> nodeToInsert = unsorted;
            while (unsorted.next != null) {
                nodeToInsert = unsorted;
                unsorted = unsorted.next;
                insertInOrder(nodeToInsert, c);
            }
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.setNext(tail);
            tail.setPrev(curr);

        }
    }


    // ----------------------------------------------------------
    /**
     * helper function. determines where a specific node goes in the already
     * sorted nodes.
     * 
     * @param nodeToInsert
     *            the node in question to be insert
     * @param c
     *            selected comparator
     */
    private void insertInOrder(Node<T> nodeToInsert, Comparator<T> c) {
        T data = nodeToInsert.data;
        Node<T> curr = head.next;
        Node<T> prev = head;

        // locate insert point
        while (curr != null) {
            if (c.compare(data, curr.data) > 0) {
                prev = curr;
                curr = curr.next;
            }
            else {
                break;
            }
        }

        // insert found
        if (prev.data != null) {
            prev.setNext(nodeToInsert);
            nodeToInsert.setPrev(prev);
            nodeToInsert.setNext(curr);
        }
        else {
            // add at front of list
            Node<T> oldFront = head.next;
            nodeToInsert.setPrev(head);
            nodeToInsert.setNext(oldFront);
            oldFront.setPrev(nodeToInsert);
            head.setNext(nodeToInsert);
        }
    }

    // -------------------------------------------------------------------------
    /**
     * can iterate through the nodes if that's something you want
     * 
     * @param <A>
     *            placeholder generic type
     * @author jdevi
     * @version Nov 19, 2023
     */
    private class DLListIterator<A> implements Iterator<T> {
        /**
         * next node
         */
        private Node<T> next;
        /**
         * true if the next() method has been called
         */
        private boolean hasCalledNext;

        /**
         * Creates a new DLListIterator
         */
        public DLListIterator() {
            hasCalledNext = false;
            next = head.next();
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return next != tail;
        }


        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("List has no more elements");
            }
            T value = next.data;
            next = next.next();
            hasCalledNext = true;
            return value;
        }

    }

    /**
     * Uses the private method and iterates through the doubly linked list
     * 
     * @return the iterator that lets us use this outside the class is
     *         returned
     */
    public Iterator<T> iterator() {
        return new DLListIterator<T>();
    }
}
