package a3;
/*
 * Mateo Garzon Velasco 40277001
 * Assignment 3 COMP249
 * Due Date: 2024-04-14
 * Purpose: This SingleLinkedList is made to store words associated with a topic into nodes
 */

/**
 * Represents a singly linked list data structure.
 * @author Mateo Garzon
 */
public class SinglyLinkedList {
	
	/**
	 * Private inner class representing a node in a singly linked list.
	 * @author Mateo Garzon
	 */
    private class SNode {
    	/**
    	 * Saves a word inside SNode
    	 */
        String word;
        /**
         * Points to next SNode
         */
        SNode next;

        /**
         * Constructs a new node with the specified word.
         *
         * @param word the word stored in this node
         */
        public SNode(String word) {
            this.word = word;
            this.next = null;
        }
        
        /**
         * Retrieves the next node.
         *
         * @return the next node
         */
        public SNode getNext() {
        	return next;
        }
        
        /**
         * Retrieves the word stored in this node.
         *
         * @return the word stored in this node
         */
        public String getWord() {
        	return word;
        }
        
        /**
         * Sets the next node.
         *
         * @param newNext the next node to be set
         */
        public void setNext(SNode newNext) {
        	this.next = newNext;
        }
        
        /**
         * Sets the word stored in this node.
         *
         * @param newWord the new word to be set
         */
        public void setWord(String newWord) {
        	this.word=newWord;
        }
        

    }
/**
 * Represents leading SNode
 */
    private SNode head;
    /**
     * Represents size of SinglyLinkedList
     */
    private int size;
    
    
    /**
     * default constructor of a singlylinkedlist sets head to null and size to 0
     */
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    
    /**
     * Inserts a new node containing the specified word at the end of the linked list.
     *
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        SNode newNode = new SNode(word);
        if (head == null) {
            head = newNode;
        } else {
            SNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        increaseSize();
    }

    /**
     * Converts the singly linked list to an array of strings.
     *
     * @return An array containing the elements of the singly linked list.
     */    
    public String[] toArray() {
        
        String[] array = new String[size];
        int index = 0;
        SNode current = head;
        while (current != null) {
            array[index++] = current.word;
            current = current.next;
        }
        return array;
    }

    /**
     * Inserts a new node with the specified data at the end of the singly linked list.
     *
     * @param data The data to be stored in the new node.
     */
    public void insertLast(String data) {
        SNode newNode = new SNode(data);
        if (head == null) {
            head = newNode;
        } else {
            SNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        increaseSize();
    }
    
    
    /**
     * Increases the size by one unit.
     */
    public void increaseSize() {
        size++;
    }

    /**
     * Returns the current size.
     * 
     * @return The current size.
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the element at the specified index.
     * 
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        SNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        
        return current.getWord();
        
    }
    
    /**
     * Decreases the size by one unit.
     */
    public void decreaseSize() {
    	size--;
    }
    
    /**
     * Removes the first occurrence of the specified word from the list, if it exists.
     * 
     * @param wordToRemove The word to be removed from the list.
     * @return true if the word was found and removed from the list, false otherwise.
     */
    public boolean remove(String wordToRemove) {
        if (head == null) {
            return false; // List is empty, word cannot be removed
        }

        if (head.getWord().equals(wordToRemove)) {
            head = head.getNext();
            decreaseSize();
            return true; // Word removed from the head of the list
        }

        SNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getWord().equals(wordToRemove)) {
                current.setNext(current.getNext().getNext());
                decreaseSize();
                return true; // Word removed from the middle of the list
            }
            current = current.getNext();
        }
        
        return false; // Word not found in the list
        
    }

    /**
     * Replaces the first occurrence of the specified old word with the new word.
     * 
     * @param oldWord The word to be replaced.
     * @param newWord The new word to replace the old word.
     * @return true if the old word was found and replaced, false otherwise.
     */
    public boolean replace(String oldWord, String newWord) {
        SNode current = head;
        while (current != null) {
            if (current.getWord().equals(oldWord)) {
                current.setWord(newWord);
                return true;
            }
            current = current.getNext();
        }
        return false; // Word not found in the list
    }
    
    /**
     * Searches for word in Vocab SinglyLinkedList
     * 
     * @param word to search in vocab list
     * @return true if word is in vocab list, false if not
     */
    public boolean contains(String word) {
        SNode current = head;
        while (current != null) {
            if (current.getWord().equals(word)) {
                return true; // Found the word
            }
            current = current.getNext();
        }
        return false; // Word not found
    }

   
    
}

