package a3;

/*
 * Mateo Garzon Velasco 40277001
 * Assignment 3 COMP249
 * Due Date: 2024-04-14
 * Purpose: This DoubleLinkedList is made to store Vocabulary lists organized by topic
 */

/**
 * Represents a doubly linked list of vocabulary topics.
 * @author Mateo Garzon
 */
public class DoublyLinkedList {
	
	/**
     * Represents a node in the doubly linked list.
     * @author Mateo Garzon
     */
    private class DNode {
        Vocab data;
        DNode prev;
        DNode next;
        
        
        /**
         * Constructs a new node with the given data.
         *
         * @param data the data to store in the node
         */
        public DNode(Vocab data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        
        /**
         * Sets the next node in the doubly linked list.
         *
         * @param nextNode the node to set as the next node
         */
        public void setNext(DNode nextNode) {
            this.next = nextNode;
        }
        
        /**
         * Returns the next node in the doubly linked list.
         *
         * @return the next node in the list
         */
        public DNode getNext() {
        	return this.next;
        }
        
        /**
         * Sets the previous node in the doubly linked list.
         *
         * @param prevNode the previous node to be set
         */
        public void setPrev(DNode prevNode) {
            this.prev = prevNode;
        }

        /**
         * Returns the Vocab object stored in this node.
         * 
         * @return the Vocab object stored in this node
         */
		public Vocab getData() {
			return data;
		}
		
		/**
		 * Returns the previous node in the linked list.
		 * 
		 * @return the previous node in the linked list
		 */
		public DNode getPrev() {
			return prev;
		}
		
    }

    /**
     * Represents the head of the doubly linked list.
     */
    private DNode head;

    /**
     * Represents the tail of the doubly linked list.
     */
    private DNode tail;

    /**
     * Represents the size of the doubly linked list.
     */
    private int size;

    /**
     * Constructs a new empty doubly linked list.
     * Initializes the head, tail, and size to null and 0, respectively.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Increases the size of the doubly linked list by 1.
     */
    public void incrementSize() {
        size++;
    }
    
    /**
     * Retrieves the node at the specified index in the doubly linked list.
     *
     * @param index The index of the node to retrieve.
     * @return The node at the specified index, or null if the index is out of bounds.
     */
    public DNode getNode(int index) {
        if (index < 0) {
            return null;
        }

        DNode current = head;
        int currentIndex = 0;

        // Traverse the list until reaching the node at the specified index
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        return current;
    }
    
    /**
     * Inserts a new node containing the provided data at the end of the doubly linked list.
     *
     * @param data The data to be stored in the new node.
     */
    public void insert(Vocab data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    
    /**
	 * Retrieves the head node of the doubly linked list.
	 *
	 * @return The head node of the doubly linked list.
	 */
    public DNode getHead() {
        return head;
    }
    
    /**
     * Retrieves the size of the doubly linked list.
     *
     * @return The size of the doubly linked list.
     */
	public int size() {
		return size;
	}
	
	/**
	 * Retrieves the vocabulary data at the specified index in the doubly linked list.
	 *
	 * @param index The index of the vocabulary data to retrieve.
	 * @return The vocabulary data at the specified index.
	 * @throws IndexOutOfBoundsException If the index is out of range .
	 */
    public Vocab get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

	
    /**
     * Removes all elements from the doubly linked list, making it empty.
     */
	 public void clear() {
	        head = null;
	        tail = null;
	        size = 0;
	    }

	 /**
	  * Returns an array containing all of the elements in this doubly linked list in proper sequence.
	  *
	  * @return an array containing all of the elements in this list in proper sequence
	  */
	public Vocab[] toArray() {
        Vocab[] array = new Vocab[size];
        DNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
	
	/**
	 * Inserts the specified element at the end of this doubly linked list.
	 *
	 * @param data the element to be inserted
	 */
	public void insertLast(Vocab data) {
        DNode newNode = new DNode(data); // Create a new node with the provided data
        if (head == null) {
            // If the list is empty, make the new node the head and tail
            head = tail = newNode;
        } else {
            // Otherwise, append the new node after the current tail
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

	}

	
	/**
	 * Returns the next topic in the doubly linked list after the specified current topic.
	 *
	 * @param currentTopic the current topic
	 * @return the next topic after the current topic, or {@code null} if the current topic is {@code null}
	 *         or if the next topic is not found
	 */
	 public Vocab getNextTopic(Vocab currentTopic) {
	        if (currentTopic != null) {
	            DNode currentNode = findNode(currentTopic);
	            if (currentNode != null && currentNode.getNext() != null) {
	                return currentNode.getNext().getData();
	            }
	        }
	        return null;
	    }
	 
	 /**
	  * Finds the node containing the specified topic in the doubly linked list.
	  *
	  * @param topic the topic to find
	  * @return the node containing the specified topic, or {@code null} if the topic is not found
	  */
	 public	DNode findNode(Vocab topic) {
	        DNode currentNode = head;
	        while (currentNode != null) {
	            if (currentNode.getData() == topic) {
	                return currentNode;
	            }
	            currentNode = currentNode.getNext();
	        }
	        return null;
	    }
	 
	 /**
	  * Retrieves the first topic in the doubly linked list.
	  *
	  * @return the first topic in the list, or {@code null} if the list is empty
	  */
	 public Vocab getFirstTopic() {
	        if (head != null) {
	            return head.getData();
	        }
	        return null;
	    }
	    
	 /**
	  * Displays the words associated with the topic at the specified index.
	  *
	  * @param index the index of the topic whose words are to be displayed
	  */
	    public void displayWordsOfTopic(int  index) {
	        
	        Vocab vocab = findVocabByIndex(index);
	        
	        if (vocab != null) {
	            // Access the words associated with the topic
	            SinglyLinkedList words = vocab.getWords();
	            
	            // Display each word
	            for (int i = 0; i < words.size(); i++) {
	                System.out.println(words.get(i));
	            }
	        } else {
	            System.out.println("Topic not found at index : " + index);
	        }
	    }

	    
	    /**
	     * Finds and returns the vocabulary (topic and associated words) at the specified index.
	     *
	     * @param index the index of the vocabulary to find
	     * @return the vocabulary at the specified index, or null if the index is out of bounds or the vocabulary is not found
	     */
	    public Vocab findVocabByIndex(int index) {
	        if (index < 0 || index >= size) {
	            return null; // Index out of bounds
	        }
	        
	        DNode current = head;
	        int currentIndex = 0;
	        
	        while (current != null && currentIndex < index) {
	            current = current.getNext();
	            currentIndex++;
	        }
	        
	        if (current != null) {
	            return current.getData();
	        } else {
	            return null; // Index not found
	        }
	    }

	    
	    
	    /**
	     * Inserts a new topic before the existing topic at the specified index.
	     *
	     * @param newTopic           the name of the new topic to insert
	     * @param existingTopicIndex the index of the existing topic before which the new topic will be inserted
	     */
	    public void insertTopicBefore(String newTopic, int existingTopicIndex) {
	       
	        Vocab newVocab = new Vocab(newTopic, new SinglyLinkedList());

	        // Find the node at the specified index
	        DNode existingNode = getNode(existingTopicIndex-1);

	        // If the node at the specified index is found, insert the new topic before it
	        if (existingNode != null) {
	            // Create a new node for the new topic
	            DNode newNode = new DNode(newVocab);

	            // Adjust links to insert the new node before the existing node
	            newNode.setNext(existingNode);
	            newNode.setPrev(existingNode.getPrev());
	            existingNode.setPrev(newNode);

	            if (newNode.getPrev() == null) {
	                // If the new node is inserted at the beginning of the list, update the head
	                head = newNode;
	            } else {
	                // Otherwise, adjust the next reference of the previous node
	                newNode.getPrev().setNext(newNode);
	            }
	        } else {
	            System.out.println("Node not found at index: " + existingTopicIndex);
	        }
	        incrementSize();
	    }
	    
	    /**
	     * Inserts a new topic after the existing topic at the specified index.
	     *
	     * @param newTopic           the name of the new topic to insert
	     * @param existingTopicIndex the index of the existing topic after which the new topic will be inserted
	     */
	    public void insertTopicAfter(String newTopic, int existingTopicIndex) {
	       
	        Vocab newVocab = new Vocab(newTopic, new SinglyLinkedList());

	        // Find the node at the specified index
	        DNode existingNode = getNode(existingTopicIndex-1);

	        // If the node at the specified index is found, insert the new topic after it
	        if (existingNode != null) {
	            // Create a new node for the new topic
	            DNode newNode = new DNode(newVocab);

	            // Adjust links to insert the new node after the existing node
	            newNode.setPrev(existingNode);
	            newNode.setNext(existingNode.getNext());
	            existingNode.setNext(newNode);

	            if (newNode.getNext() == null) {
	                // If the new node is inserted at the end of the list, update the tail
	                tail = newNode;
	            } else {
	                // Otherwise, adjust the previous reference of the next node
	                newNode.getNext().setPrev(newNode);
	            }
	        } else {
	            System.out.println("Node not found at index: " + existingTopicIndex);
	        }
	        incrementSize();
	    }


	    /**
	     * Removes the topic at the specified index from the doubly linked list.
	     *
	     * @param index the index of the topic to be removed
	     */
	    public void removeTopic(int index) {
	        // Find the node at the specified index
	        DNode nodeToRemove = getNode(index);

	        // If the node is found, remove it from the list
	        if (nodeToRemove != null) {
	            // Adjust links to remove the node
	            if (nodeToRemove.getPrev() != null) {
	                nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
	            } else {
	                // If the node to remove is the head, update the head
	                head = nodeToRemove.getNext();
	            }

	            if (nodeToRemove.getNext() != null) {
	                nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
	            } else {
	                // If the node to remove is the tail, update the tail
	                tail = nodeToRemove.getPrev();
	            }
	        } else {
	            System.out.println("Node not found at index: " + index);
	        }
	    }

	    /**
	     * Adds a new word to the specified topic in the doubly linked list.
	     *
	     * @param topic   the topic to which the word will be added
	     * @param newWord the new word to be added
	     */
	    public void addWordToTopic(String topic, String newWord) {
	        // Find the node corresponding to the topic
	        DNode topicNode = findNodeByTopic(topic);

	        // If the topic node is found, add the new word to its associated SinglyLinkedList
	        if (topicNode != null) {
	            Vocab vocab = topicNode.getData();
	            vocab.getWords().insertLast(newWord);
	        } else {
	            System.out.println("Topic not found: " + topic);
	        }
	    }

	    /**
	     * Removes a specified word from the given topic in the doubly linked list.
	     *
	     * @param topic        the topic from which the word will be removed
	     * @param wordToRemove the word to be removed from the topic
	     */
	    public void removeWordFromTopic(String topic, String wordToRemove) {
	        // Find the node corresponding to the topic
	        DNode topicNode = findNodeByTopic(topic);

	        // If the topic node is found, remove the word from its associated SinglyLinkedList
	        if (topicNode != null) {
	            Vocab vocab = topicNode.getData();
	            SinglyLinkedList words = vocab.getWords();
	            boolean removed = words.remove(wordToRemove);
	            if (!removed) {
	                System.out.println("Word not found in topic: " + wordToRemove);
	            }
	        } else {
	            System.out.println("Topic not found: " + topic);
	        }
	    }
	    
	    /**
	     * Finds the node containing the specified topic in the doubly linked list.
	     *
	     * @param topic the topic to search for
	     * @return the node containing the specified topic, or null if the topic is not found
	     */
	    public DNode findNodeByTopic(String topic) {
	        DNode current = head;
	        while (current != null) {
	            Vocab vocab = current.getData();
	            if (vocab.getTopic().equals(topic)) {
	                return current;
	            }
	            current = current.getNext();
	        }
	        return null;
	    }
	    
	    /**
	     * Replaces a word in the specified topic with a new word.
	     *
	     * @param topic    the topic in which the word should be replaced
	     * @param oldWord  the word to be replaced
	     * @param newWord  the new word to replace the old word
	     */
	    public void replaceWordInTopic(String topic, String oldWord, String newWord) {
	        // Find the node corresponding to the topic
	        DNode topicNode = findNodeByTopic(topic);

	        // If the topic node is found, replace the word in its associated SinglyLinkedList
	        if (topicNode != null) {
	            Vocab vocab = topicNode.getData();
	            SinglyLinkedList words = vocab.getWords();
	            words.replace(oldWord, newWord);
	        } else {
	            System.out.println("Topic not found: " + topic);
	        }
	    }
	 
	    /**
	     * Finds the topic in which the specified word is found.
	     *
	     * @param word the word to search for
	     * @return the name of the topic where the word is found, or an empty string if the word is not found in any topic
	     */
	    public String WordFound(String word) {
	        DNode current = head;
	        while (current != null) {
	            Vocab vocab = current.getData();
	            SinglyLinkedList words = vocab.getWords();
	            if (words.contains(word)) {
	                return vocab.getTopic(); // Return the name of the topic
	            }
	            current = current.getNext();
	        }
	        return ""; // Word not found in any topic
	    }
	    
	    /**
	     * Checks if the specified word is contained in any of the topics.
	     *
	     * @param word the word to search for
	     * @return true if the word is found in any topic, false otherwise
	     */
	    public boolean containsWord(String word) {
	        DNode current = head;
	        while (current != null) {
	            Vocab vocab = current.getData();
	            SinglyLinkedList words = vocab.getWords();
	            if (words.contains(word)) {
	                return true; // Found the word in this topic
	            }
	            current = current.getNext();
	        }
	        return false; // Word not found in any topic
	    }



	 
	    
	    
	
}


   

