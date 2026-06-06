/**
 * File: DegreeGraphTest.java
 * Description: 11 tests for testing the methods of the DegreeGraph class.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. Chat gpt was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DegreeGraphTest {

    //helper method to create a temporary test file
    private String createTestFile() throws Exception {

        File file = File.createTempFile("degreeGraphTest", ".txt");

        PrintWriter writer = new PrintWriter(file);

        writer.println("A,B,C");
        writer.println("A,B");
        writer.println("B,C");

        writer.close();

        return file.getAbsolutePath();
    }

    //test that all courses in the input file are added to the graph
    @Test
    public void testGraphBuildsCorrectNumberOfCourses() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(3, graph.getCourse().size());
    }

    //test that course A is created during graph construction
    @Test
    public void testCourseAExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("A"));
    }

    //test that course B is created during graph construction
    @Test
    public void testCourseBExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("B"));
    }

    //test that course C is created during graph construction
    @Test
    public void testCourseCExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("C"));
    }

    //test that course A has the correct indegree value
    @Test
    public void testIndegreeOfA() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(1, graph.getIndegree("A"));
    }

    //test that course B has the correct indegree value
    @Test
    public void testIndegreeOfB() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(1, graph.getIndegree("B"));
    }

    //test that course C has the correct indegree value
    @Test
    public void testIndegreeOfC() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(0, graph.getIndegree("C"));
    }

    //test that the prerequisite relationship B -> A is created correctly
    @Test
    public void testEdgeCreatedBetweenBAndA() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        Course b = graph.getCourse("B");

        assertEquals ("A", b.getDependents().get(0).getCode());
    }

    //test the copyIndegrees() returns an independent copy of the map
    @Test
    public void testCopyIndegreesCreatesSeparateMap() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        Map<String, Integer> copy = graph.copyIndegrees();

        copy.put("A", 999);

        assertEquals(1, graph.getIndegree("A"));
    }

    //test that requesting a non-existent course returns null
    @Test
    public void testGetCourseReturnsNullForUnknownCourse() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNull(graph.getCourse("COMP2627"));
    }

    //test that an exception is thrown when a prerequisite does not exist
    @Test
    public void testMissingPrerequisiteThrowsException() {

        DegreeGraph graph = new DegreeGraph();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            graph.buildGraph("InvalidPrereq.txt");
        });

        assertEquals("Prerequisite course 'COMP9999' does not exist in the course list.", exception.getMessage());
    }
}
