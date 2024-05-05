package a3;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/*
 * Mateo Garzon Velasco 40277001
 * Assignment 3 COMP249
 * Due Date: 2024-04-14
 * Purpose: This Driver is meant to create a user interface
 * to interact with vocabulary lists
 * It has the option of saving lists to and from a file
 */

/**
 * Represents the way to access our data structure, has main method
 * @author Mateo Garzon
 *
 */
public class Driver {
	 private static DoublyLinkedList topics; // Assuming topics is a DoublyLinkedList of Vocab objects

	 /**
	  * A string representing the menu options for the Vocabulary Control Center.
	  */
 	private static String menu = 
 			  "\n---------------------------------"
			+ "\n\tVocabulary Control Center"
			+ "\n---------------------------------"
			+ "\n1\tbrowse a topic"
			+ "\n2\tinsert a new topic before another one"
			+ "\n3\tinsert a new topic after another one"
			+ "\n4\tremove a topic"
			+ "\n5\tmodify a topic"
			+ "\n6\tsearch topics for a word"
			+ "\n7\tload from a file"
			+ "\n8\tshow all words starting with a given letter"
			+ "\n9\tsave to file"
			+ "\n0\texit"
			+ "\n---------------------------------"
			+ "\nEnter Your Choice: ";
 	
 	/**
 	 * Generates a menu for selecting a topic from the provided list of topics.
 	 * 
 	 * @param topics A string representing the list of topics to display in the menu.
 	 * @return A string representing the menu for selecting a topic.
 	 */
 	public static String topicmenu(String topics){
 		return 	  "\n------------------------------"
 				+ "\n\tPick a Topic"
 				+ "\n------------------------------"
 				+ "\n"+topics
 				+ " 0\tExit"
 				+ "\n------------------------------"
 				+ "\nEnter Your Choice: ";
 	}
 		
 	/**
 	 * A string representing the menu options for modifying topics.
 	 */
 	public static String editmenu =
 				  "\n------------------------------"
 				+ "\n\tModify Topics Menu"
 			    + "\n------------------------------"
 				+ "\n a\tadd a word"
 				+ "\n r\tremove a word"
 				+ "\n c\tchange a word"
 				+ "\n 0\tExit"
 				+ "\n------------------------------"
 				+ "\nEnter Your Choice: ";

	   
 	/**
 	 * Generates a string representation of all topics in the linked list.
 	 * 
 	 * This method iterates over each topic in the linked list and constructs a string representation
 	 * of the topics along with their corresponding numbers.
 	 * 
 	 * @return A string containing the representation of all topics in the linked list.
 	 */
	    public static String displayTopics() {
	        // Get the first topic in the linked list
	        Vocab currentTopic = topics.getFirstTopic();
	        
	        int topicNumber =1;
	        String topicdisplay="";

	        // Iterate over each topic until reaching the end of the list
	        while (currentTopic != null) {
	            // Print the topic name
	            topicdisplay+=" "+topicNumber +"\t"+ currentTopic.getTopic()+"\n";

	            
	            currentTopic = topics.getNextTopic(currentTopic);

	            // Print a separator between topics
	            topicNumber++;
	        }
	        return topicdisplay;
	    }
	    
	    
	    /**
	     * Saves the topics and their associated words to a text file.
	     * 
	     * @param fileName The name of the file to which topics and words will be saved.
	     */
	    public static void saveToFile(String fileName) {
	    	 if (!fileName.endsWith(".txt")) {
	    	        fileName += ".txt";
	    	    }
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
	            Vocab[] topicArray = topics.toArray();
	            for (Vocab topicword : topicArray) {
	                writer.write("#"+topicword.getTopic());
	                writer.newLine();
	                for(String bro: (topicword.getWords()).toArray() ) {
	                	writer.write(bro);
	                	writer.newLine();
	                }
	            }
	            System.out.println("Words saved to file: " + fileName);
	        } catch (IOException e) {
	            System.err.println("Error writing to file: " + e.getMessage());
	        }
	    }

	    /**
	     * Loads topics and their associated words from a text file and constructs a DoublyLinkedList of topics.
	     * @param fileName The name of the file from which topics and words will be loaded.
	     * @return A DoublyLinkedList containing the loaded topics.
	     */
	    public static DoublyLinkedList loadFromFile(String fileName) {
	        DoublyLinkedList topics = new DoublyLinkedList();
	        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line;
	            Vocab currentVocab = null;
	            while ((line = reader.readLine()) != null) {
	                line = line.trim(); // Remove leading and trailing whitespace
	                if (!line.isEmpty()) { // Check if the line is not empty
	                    if (line.startsWith("#")) {
	                        // Start of a new topic
	                        if (currentVocab != null) {
	                            // Insert the current vocab into the linked list
	                            topics.insertLast(currentVocab);
	                            topics.incrementSize(); // Increment the size
	                        }
	                        // Create a new Vocab object with the topic name
	                        String topicName = line.substring(1).trim();
	                        // Extract topic name
	                        currentVocab = new Vocab(topicName,new SinglyLinkedList());
	                    } else {
	                        // Word associated with the current topic
	                        if (currentVocab != null) {
	                            currentVocab.addWords(line);
	                            
	                        } else {
	                            System.err.println("Words found without a topic: " + line);
	                        }
	                    }
	                }
	            }
	            // Insert the last vocab object into the linked list
	            if (currentVocab != null) {
	                topics.insertLast(currentVocab);
	                topics.incrementSize(); // Increment the size
	            }
	            System.out.println("-------------------------------------------------------"
	            				 + "\nTopics loaded from file " + fileName +" succesfully\n"
	            				 + "-------------------------------------------------------");
	        } catch (IOException e) {
	            System.err.println("Error reading from file: " + e.getMessage());
	        }
	        return topics;
	    }




	    
	    /**
	     * Retrieves and formats words starting with a specified letter from all topics.
	     * @param letter The letter that the words should start with.
	     * @return A formatted string containing words starting with the specified letter.
	     */
	    public static String showWordsStartingWithLetter(String letter) {
	        ArrayList<String> extractedWords = new ArrayList<>();

	        // Get the first topic in the linked list
	        Vocab currentTopic = topics.getFirstTopic();

	        // Iterate over each topic until reaching the end of the list
	        while (currentTopic != null) {
	            // Access the words associated with the current topic
	            SinglyLinkedList words = currentTopic.getWords();

	            // Iterate over each word in the words list
	            int wordIndex = 0;
	            while (wordIndex < words.size()) {
	                String word = words.get(wordIndex);

	                // Check if the word starts with the specified letter
	                if (word.charAt(0) == letter.charAt(0)) {
	                    extractedWords.add(word);
	                }

	                wordIndex++;
	            }

	            // Move to the next topic
	            currentTopic = topics.getNextTopic(currentTopic);
	        }

	        // Sort the extracted words manually
	        sortArrayList(extractedWords);
	        
	        String wordstream ="";
	        // Print the sorted list
	        int wordnumber = 1;
	        for (String word : extractedWords) {
	            
	            wordstream+=wordnumber+": "+word+"\t\t";
	            
	            if(wordnumber%4==0)
	            	wordstream+="\n\n";
	            else
	            	wordstream+="";
	            wordnumber++;
	        }
	        return wordstream;
	    }

	    /**
	     * Sorts an ArrayList of strings in ascending order.
	     * @param list The ArrayList of strings to be sorted.
	     */
	    private static void sortArrayList(ArrayList<String> list) {
	        for (int i = 0; i < list.size() - 1; i++) {
	            for (int j = i + 1; j < list.size(); j++) {
	                if (list.get(i).compareTo(list.get(j)) > 0) {
	                    String temp = list.get(i);
	                    list.set(i, list.get(j));
	                    list.set(j, temp);
	                }
	            }
	        }
	    }
	    
	    /**
	     * Retrieves and displays words associated with a topic at the specified index.
	     *
	     * @param index The index of the topic for which words are to be displayed.
	     * @return A string containing the words associated with the topic, formatted with numbers and tabs.
	     *         Newlines are inserted every fourth word for readability.
	     */
	    public static String displayWordsOfTopic(int index) {
	        int numberofwords=1;
	        Vocab vocab = topics.get(index - 1);
	        String wordsoftopic ="";
	        if (vocab != null) {
	            // Access the words associated with the topic
	            SinglyLinkedList words = vocab.getWords();
	            
	            // Print the size of the words list
	            System.out.println("Size of words associated with topic at index " + index + ": " + words.size());

	            // Display each word
	            if (words != null) {
	            	
	            	for (int i = 0; i < words.size(); i++) {
	            	    // Append word to the string
	            	    wordsoftopic += numberofwords + ": " + words.get(i)+"\t\t";
	            	     

	            	    // Add a newline every fourth word
	            	    if (numberofwords % 4 == 0) {
	            	        wordsoftopic += "\n\n";
	            	    } else {
	            	        wordsoftopic += "";
	            	    }
	            	    numberofwords++;
	            	}
	            } 
	        } else {
	            System.out.println("Topic not found at index: " + index);
	        }
	        return wordsoftopic;
	    }


	    
	    /**
	     * The entry point of the program.
	     * @param args The command-line arguments passed to the program.
	     */
	    public static void main(String[] args) {
	    	System.out.println("------------------------------------------\n"
	    					 + "\tWelcome to the Center\n"
	    					 + "------------------------------------------");
	    	Scanner sc = new Scanner(System.in);
	    	int choice;
	    	do {
	    		
	    		System.out.println(menu);
	    		choice = sc.nextInt();
	    		switch(choice) {
	    		case 1:
	    			System.out.println(topicmenu(displayTopics()));
	    			int tpc1 =sc.nextInt();
	    			System.out.println(displayWordsOfTopic(tpc1));
	    			break;
	    		case 2:
	    			System.out.println(topicmenu(displayTopics()));
	    			int tpc2 =sc.nextInt();
	    			System.out.println("Enter a topic name:");
	    			String newtopic1 = sc.next();
	    			topics.insertTopicBefore(newtopic1, tpc2);
	    			System.out.println("Press Enter on an empty line to submit\n"
	    							 + "Add words to your topic:\n");
	    			String words1;
	    			sc.nextLine();
	    			while (!(words1 = sc.nextLine()).isEmpty()) {
	    			    topics.findVocabByIndex(tpc2 - 1).getWords().insert(words1);
	    			}
	    			break;
	    		case 3:
	    			System.out.println(topicmenu(displayTopics()));
	    			int tpc3 =sc.nextInt();
	    			System.out.println("Enter a topic name:");
	    			String newtopic2 = sc.next();
	    			topics.insertTopicAfter(newtopic2, tpc3);
	    			System.out.println("Press Enter on an empty line to submit\n"
	    							 + "Add words to your topic:\n");
	    			sc.nextLine();
	    			String words2;
	    			while (!(words2 = sc.nextLine()).isEmpty()) {
	    			    topics.findVocabByIndex(tpc3).getWords().insert(words2);
	    			}
		    		break;
	    		case 4:
	    			System.out.println(topicmenu(displayTopics()));
	    			int tpc4 =sc.nextInt();
	    			topics.removeTopic(tpc4-1);
		    		break;
	    		case 5:
	    			System.out.println(topicmenu(displayTopics()));
	    			int tpc5 = sc.nextInt();
	    			System.out.println(editmenu);
	    			String word1 = sc.next();
	    			switch(word1) {
	    			case "a":
	    				System.out.println("Enter word chosen: ");
	    				String aword = sc.next();
	    				topics.findVocabByIndex(tpc5-1).getWords().insert(aword);
	    				System.out.println(aword + " added succesfully");
	    				break;
	    			case "r":
	    				System.out.println("Enter word chosen: ");
	    				String rword = sc.next();
	    				topics.findVocabByIndex(tpc5-1).getWords().remove(rword);
	    				System.out.println(rword + " removed successfully");
	    				break;
	    			case "c":
	    				System.out.println("Enter word chosen: ");
	    				String cword = sc.next();
	    				System.out.println("Enter your new word: ");
	    				String newword = sc.next();
	    				topics.findVocabByIndex(tpc5-1).getWords().replace(cword, newword);
	    				System.out.println(cword +" replaced succesfully by " + newword);
	    				break;
	    			case "0":
	    				break;
	    			default:
	    				System.out.println("----------------------------\n"
	    								 + "Valid entries: a, r, c and 0\n"
	    								 + "----------------------------");
	    				break;
	    			}
		    		break;
	    		case 6:
	    			System.out.println("--------------\n"
	    							 + "Enter a word: ");
	    			String word2 = sc.next();
	    			if(topics.containsWord(word2))
	    				System.out.println("--------------------------------------\n"
	    								 + "Word found in topic: "+topics.WordFound(word2));
	    			else
	    				System.out.println("------------------------------\n"
	    								 + "\tWord not found\n"
	    								 + "------------------------------");
		    		break;
	    		case 7:
	    			System.out.print("Enter the name of the input file:");
	    			String file1 = sc.next();
	    			topics = loadFromFile(file1);
		    		break;
	    		case 8:
	    			System.out.println("Enter letter chosen: ");
	    			String letter = sc.next();
	    			System.out.println(showWordsStartingWithLetter(letter));
	    			break;
	    		case 9:
	    			System.out.println("Enter the file name: ");
	    			String file2 = sc.next();
	    			saveToFile(file2);
	    			System.out.println("------------------------------------------\n"
	    							 + "Vocabulary saved Succesfully in "+ file2
	    							 + "\n------------------------------------------");
	    			break;
	    		default:
	    			System.out.println("Options range: 0-9");
	    			break;
	    		}
	    		
	    	}while(choice !=0);
	    	System.out.println("-------------------------------------------------\n"
	    					 + "Thank you for using the Vocabulary Control Center");
	       sc.close();
	    }
	        
	       
	    

	    
	    
}
