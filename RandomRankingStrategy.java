package main.java;
import java.util.*;

public class RandomRankingStrategy implements RankingStrategy {

    public List<Document> Rank(Simulation sim) {
       List<Document> documents = sim.getDocuments();
       int size = documents.size();
       Collections.shuffle(documents);
       if (Simulation.constant < size){
    	   size = Simulation.constant;
       }
       return (List<Document>) documents.subList(0,size);
    }

	
}