package main.java;

//Name: Ali Faizan
//Student Number: 10095765
//Date: December 7, 2015
//Version #:4

import java.util.HashSet;
import java.util.Set;

public class Document implements Comparable<Document> {

    //Instance Variables
    private String tag;
    private String name;
    private Set<User> likedBy;
    private Producer producer;

    //Constructor for Document that takes in a String
    //Increments documentCounter and documentIDCount every time a document is created

    /**
     * Default constructor for the Document class
     *
     * @param name Name of the document
     * @param tag  Tag for the document
     * @param p    Producer of document
     */
    public Document(String name, String tag, Producer p) {
        this.tag = tag;
        this.name = name;
        this.producer = p;
        this.likedBy = new HashSet<>();
    }

    /**
     * Adds a user to the list of users that like the document
     *
     * @param user The user that liked the document
     */
    public void likeDocument(User user) {
        this.likedBy.add(user);
        this.getProducer().calculatePayoff();
    }

    public int compareTo(Document document) {
        return document.getScore() - this.getScore();
    }

    //-----Getters and Setters------
    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getLikedBy() {
        return this.likedBy;
    }

    public int getScore() {
        int score = 0;

        //Increase score by number of likes the document has
        score += this.getLikedBy().size();

        //Increase score by popularity of the documents producer
        score += this.getProducer().getFollowers().size();

        return score;
    }

	public int popularity() {
		
		return likedBy.size();
	}
}