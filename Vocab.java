package a3;

/*
 * Mateo Garzon Velasco 40277001
 * Assignment 3 COMP249
 * Due Date: 2024-04-14
 * Purpose: Vocab represents a vocabulary list meant to store topics
 * and lists of words in each topic
 */
/**
 * Represents a vocabulary topic with associated words.
 * @author Mateo Garzon
 */
public class Vocab{
    private String topic;
    private SinglyLinkedList words;
    
    /**
     * Constructs a new Vocab object with the given topic and words.
     *
     * @param topic the topic of the vocabulary
     * @param words the list of words associated with the topic
     */
    public Vocab(String topic,SinglyLinkedList words) {
        this.topic = topic;
        this.words = words;
    }
    
    /**
     * Returns the list of words associated with this vocabulary topic.
     *
     * @return the list of words
     */
    public SinglyLinkedList getWords() {
    	return words;
    }
    
    /**
     * Returns the topic of this vocabulary.
     *
     * @return the topic
     */
    public String getTopic() {
    	return topic;
    }
    
    /**
     * Adds a word to the list of words associated with this vocabulary topic.
     *
     * @param word the word to add
     */
    public void addWords(String word) {
        this.words.insertLast(word);
    }
    
    /**
     * Checks if this Vocab object is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vocab other = (Vocab) obj;
        return topic.equals(other.topic);
    }

	
}
