package main.java;
import java.util.*;

public class DocumentPopularityStrategy implements RankingStrategy {

	    
	    public List<Document> Rank(Simulation sim) {
	    	List<Document> document = sim.getDocuments();
	    	int size = document.size();
	    	document.sort((document1, document2) -> document1.popularity() - document2.popularity()); 
	        
	        if (Simulation.constant < size){
	        	size = Simulation.constant;
	        }
	        return document.subList(0,size); 
	    }
	

}
