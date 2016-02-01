package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    public Producer testProducer;
    public Consumer testConsumer;
    public Document testDocument;

    @Before
    public void setUp() throws Exception {
        testProducer = new Producer(new Simulation(), "testProducer", "testTaste");
        testConsumer = new Consumer(new Simulation(), "testConsumer", "testTaste");
        testDocument = new Document("testDocument", "testTag", testProducer);
    }

    @Test
    public void testFollowUser() throws Exception {
        assert testConsumer.getFollowers().size() == 0;
        testProducer.followUser(testConsumer);
        assert testConsumer.getFollowers().size() == 1;
    }

    @Test
    public void testLikeDocument() throws Exception {
        assert testConsumer.getLikedDocuments().size() == 0;
        testConsumer.likeDocument(testDocument);
        assert testConsumer.getLikedDocuments().size() == 1;
    }

    @Test
    public void testGetSim() throws Exception {
        assert testProducer.getSim() != null;
    }

    @Test
    public void testGetTaste() throws Exception {
        assert testProducer.getTaste().equals("testTaste");
    }

    @Test
    public void testSetTaste() throws Exception {
        assert testProducer.getTaste().equals("testTaste");
        testProducer.setTaste("newTaste");
        assert testProducer.getTaste().equals("newTaste");
    }

    @Test
    public void testGetName() throws Exception {
        assert testProducer.getName().equals("testProducer");
    }

    @Test
    public void testSetName() throws Exception {
        assert testProducer.getName().equals("testProducer");
        testProducer.setName("newName");
        assert testProducer.getName().equals("newName");
    }

    @Test
    public void testGetFollowers() throws Exception {
        assert testProducer.getFollowers().size() == 0;
        testConsumer.followUser(testProducer);
        assert testProducer.getFollowers().size() == 1;
    }

    @Test
    public void testGetPayoff() throws Exception {
        assert testConsumer.getPayoffs().size() == 0;
    }
}