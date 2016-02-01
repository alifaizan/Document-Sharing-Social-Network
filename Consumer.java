package main.java;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends User {

    /**
     * Default constructor for Consumer class
     *
     * @param simulation The associated simulation
     * @param name       The name of the producer
     * @param taste      The taste of the producer
     */
    public Consumer(Simulation simulation, String name, String taste) {
        super(simulation, name, taste);
        this.setFollowing(new ArrayList<>());
    }

    /**
     * Analyzes a given list of documents and determines how related it is to the user and whether to like/follow anyone else
     *
     * @param documentList   The list of documents to analyze
     * @param numberToReturn The number of documents to return
     * @return List of documents that have been sorted and analyzed
     */
    public List<Document> act(List<Document> documentList, int numberToReturn) {
        List<Document> relevantDocuments = search(documentList, numberToReturn);

        calculatePayoff(relevantDocuments);

        updateLikesAndFollowers(relevantDocuments, this.getTaste());

        return relevantDocuments;
    }

    /**
     * Calculates payoff based on ranked documents
     *
     * @param documents The documents to analyze
     */
    public void calculatePayoff(List<Document> documents) {
        int payoff = 0;

        for (final Document document : documents) {
            if (document.getTag().equals(this.getTaste()) && !(this.likes(document))) payoff += 2;
            else if (document.getTag().equals(this.getTaste())) payoff++;
        }

        this.updatePayoffs(payoff);
        setToPrint("This search gave " + this.getName() + " a payoff of: " + String.valueOf(payoff));
    }
}