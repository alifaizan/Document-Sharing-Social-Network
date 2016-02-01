package main.java;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class Simulation {
	
    private static final int MAX_PRODUCERS = 50;
    private static final int MAX_CONSUMERS = 50;
    private static final int MAX_DOCUMENTS = 10;
    private static final int MAX_TAGS = 10;
    private static final SecureRandom random = new SecureRandom();
    static GUI gui;
    private static int numberOfConsumers, numberOfProducers, numberOfDocuments, numberOfSearchResults, numberOfTags;
    private boolean quit;
    private List<Document> documents;
    private List<User> users;
    public static final int constant = 2;
    
    /**
     * Default constructor for Simulation class
     */
    public Simulation() {
        documents = new ArrayList<Document>();
        users = new ArrayList<User>();
        
    }

    /**
     * Randomly selects a tag from the enumerated class
     *
     * @return A random tag
     */
    public static Simulation.Tags randomTag() {
        int index = random.nextInt(numberOfTags);
        return Tags.class.getEnumConstants()[index];
    }

    
    public void setupSim (){
         userInteraction();
         printToGUI("Beginning Simulation...");
         setupConsumers(numberOfConsumers);
         setupProducers(numberOfProducers);
         setupDocuments(numberOfDocuments);

         printSetup();
         
    }
    
    public void userInteraction(){
    	numberOfConsumers = gui.getConsumers();
       
        if (numberOfConsumers > 0 && numberOfConsumers <= MAX_CONSUMERS) {
        	printToGUI("The simulator will create " + numberOfConsumers + " consumers.");
           
        } else {
            printToGUI("ERROR: Please enter a number in the range 1-" + MAX_CONSUMERS + "!");
            numberOfConsumers = gui.getConsumers();
        }
           
        numberOfProducers = gui.getProducers();
                
        if (numberOfProducers > 0 && numberOfProducers <= MAX_PRODUCERS) {
        	printToGUI("The simulator will create " + numberOfProducers + " producers.");
                   
        } else {
            printToGUI("ERROR: Please enter a number in the range 1-" + MAX_PRODUCERS + "!");
            numberOfProducers = gui.getProducers();
         }
            
         numberOfDocuments = gui.getDocuments();
                
         if (numberOfDocuments > 0 && numberOfDocuments <= MAX_DOCUMENTS) {
        	 printToGUI("The simulator will create " + numberOfDocuments + " documents.");
                   
         } else {
             printToGUI("ERROR: Please enter a number in the range 1-" + MAX_DOCUMENTS + "!");
             numberOfDocuments = gui.getDocuments();
         }
          
         numberOfTags = gui.getTags();
                
         if (numberOfTags > 0 && numberOfTags <= MAX_TAGS) {
          printToGUI("The simulator will use " + numberOfTags + " tags.");                    
         
         } else {
           printToGUI("ERROR: Please enter a number in the range 1-" + MAX_TAGS + "!");
           numberOfTags = gui.getTags();
         }
            
 
    	
    }
    /**
     * Obtains parameter values from users
     */
    

    public static void printToGUI(String s){
    	gui.print(s);
    }
    
    private void printSetup() {
        String toPrintConsumer = "The consumers which will be used are: \n";
        String toPrintProducer = "The producers which will be used are: \n";
        String toPrintDocument = "The documents which will be used are: \n";
        printToGUI("The tags which will be used are: ");
        for (int i = 0; i < numberOfTags; i++) {
            System.out.print(Tags.values()[i].name + " ");
        }
        printToGUI("");
        printToGUI("");

        for (final User user : users) {
            if (user instanceof Consumer) toPrintConsumer += user.getName() + " - " + user.getTaste() + ", ";
            if (user instanceof Producer) toPrintProducer += user.getName() + " - " + user.getTaste() + ", ";
        }

        for (final Document document : documents) {
            toPrintDocument += document.getName() + " - " + document.getTag() + ", ";
        }

        printToGUI(toPrintConsumer.substring(0, toPrintConsumer.length() - 2)); //format to get rid of extra trailing comma
        printToGUI(toPrintProducer.substring(0, toPrintProducer.length() - 2));
        printToGUI(toPrintDocument.substring(0, toPrintDocument.length() - 2));
        printToGUI("");
    }

    /**
     * Creates a specified number of consumers with a random taste
     *
     * @param number The amount of consumers to create
     */
    private void setupConsumers(int number) {
        printToGUI("Setting up Consumers: ");
        for (int i = 1; i < number + 1; i++) {
            Consumer consumer = new Consumer(this, "Consumer #" + String.valueOf(i), randomTag().toString());
            users.add(consumer);
            printToGUI(consumer.getName() + "  created");
        }
        printToGUI("-----------------------------");
    }

    /**
     * Creates a specified number of producers with a random taste
     *
     * @param number The amount of producers to create
     */
    private void setupProducers(int number) {
        printToGUI("Setting up Producers: ");
        for (int i = 1; i < number + 1; i++) {
            Producer producer = new Producer(this, "Producer #" + String.valueOf(i), randomTag().toString());
            users.add(producer);
            printToGUI(producer.getName() + "  created");
        }
        printToGUI("-----------------------------");
    }

    /**
     * Creates a specified number of documents with a random tag
     *
     * @param number The amount of documents to create
     */
    private void setupDocuments(int number) {
        printToGUI("Setting up Documents: ");
        for (int i = 1; i < number + 1; i++) {
            User user = users.get(random.nextInt(users.size()));
            while (user instanceof Consumer)
                user = users.get(random.nextInt(users.size())); //Keep picking a random user until it is a producer
            documents.add(((Producer) user).newDoc("Document #" + String.valueOf(i)));
            printToGUI(user.getToPrint());
        }
        printToGUI("-----------------------------");
    }

    public User runIteration() {
        
        int temp = 0;
        User user = this.users.get(random.nextInt(this.users.size()));

        printToGUI("\n" + user.getName() + " was randomly selected to perform a search.");
        
        	
            	setNumberOfSearchResults(gui.getSearch());
                if (getNumberOfSearchResults() > numberOfDocuments) {
                    printToGUI("You have requested a higher number of search results than there are documents! Using max number of documents instead.");
                    setNumberOfSearchResults(numberOfDocuments);
                }
                if(temp == 0){
                	this.setQuit(true);
                	
                }
                printToGUI("The simulator will show " + getNumberOfSearchResults() + " search results.");
                
        

        return user;
    }

    public int getNumberOfDocuments() {
        return this.getDocuments().size();
    }


    //-----Getters and Setters------
    public List<Document> getDocuments() {
        return this.documents;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public static int getNumberOfSearchResults() {
		return numberOfSearchResults;
	}

	public static void setNumberOfSearchResults(int numberOfSearchResults) {
		Simulation.numberOfSearchResults = numberOfSearchResults;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	//-----Enums------
    public enum Tags {
        Technology("Technology"),
        Sports("Sports"),
        Music("Music"),
        Comedy("Comedy"),
        Cartoons("Cartoons"),
        Movies("Movies"),
        Animals("Animals"),
        Environment("Environment"),
        Food("Food"),
        Drinks("Drinks");

        private final String name;

        private Tags(String name) {
            this.name = name;
        }

        public boolean sameTag(String tag) {
            return (tag != null) && name.equals(tag);
        }

        public String toString() {
            return this.name;
        }

        public Tags getTags() {
            return this;
        }
        

    }
    
    public static void main(String[] args) throws NumberFormatException {
    	gui = new GUI();
    	printToGUI("Welcome to the Project Ali simulation.");
    	printToGUI(" ");
    }
    
   
    
    
}
