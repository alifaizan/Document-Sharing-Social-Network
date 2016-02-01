package main.java;
//Name: Zameer Masjedee
//Student Number: 100888893
//Date: November 5th,2015
//Version #: 1

import org.junit.Before;
import org.junit.Test;

public class DocumentTest {

    public Producer testProducer;
    public Document testDocument;

    @Before
    public void setUp() throws Exception {
        testProducer = new Producer(new Simulation(), "testName", "testTaste");
        testDocument = new Document("testDocument", "testTag", testProducer);
    }

    @Test
    public void testLikeDocument() throws Exception {

        //Make sure user isn't originally in liked list
        assert testDocument.getLikedBy().size() == 0;
        testDocument.likeDocument(testProducer);
        //Make sure user is in liked list after they like the document
        assert testDocument.getLikedBy().contains(testProducer);
    }

    @Test
    public void testCompareTo() throws Exception {
        Document testDocument2 = new Document("testDocument2", "testTag", testProducer);
        assert testDocument.compareTo(testDocument2) == 0;
    }

    @Test
    public void testGetTag() throws Exception {
        assert testDocument.getTag().equals("testTag");
    }

    @Test
    public void testSetTag() throws Exception {
        assert testDocument.getTag().equals("testTag");
        testDocument.setTag("newTest");
        assert testDocument.getTag().equals("newTest");
    }

    @Test
    public void testGetProducer() throws Exception {
        assert testDocument.getProducer() == testProducer;
    }

    @Test
    public void testSetProducer() throws Exception {
        Producer testProducer2 = new Producer(new Simulation(), "testName2", "testTaste");
        assert testDocument.getProducer() == testProducer;
        testDocument.setProducer(testProducer2);
        assert testDocument.getProducer() == testProducer2;
    }

    @Test
    public void testGetName() throws Exception {
        assert testDocument.getName().equals("testDocument");
    }

    @Test
    public void testSetName() throws Exception {
        assert testDocument.getName().equals("testDocument");
        testDocument.setName("newTestName");
        assert testDocument.getName().equals("newTestName");
    }

    @Test
    public void testGetLikedBy() throws Exception {
        assert testDocument.getLikedBy().size() == 0;
        testDocument.likeDocument(testProducer);
        assert testDocument.getLikedBy().size() == 1;
    }


    @Test
    public void testGetScore() throws Exception {
        assert testDocument.getScore() == 0;
    }
}