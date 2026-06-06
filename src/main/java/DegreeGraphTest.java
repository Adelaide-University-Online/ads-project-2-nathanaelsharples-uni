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

    @Test
    public void testGraphBuildsCorrectNumberOfCourses() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(3, graph.getCourse().size());
    }

    @Test
    public void testCourseAExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("A"));
    }

    @Test
    public void testCourseBExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("B"));
    }

    @Test
    public void testCourseCExists() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNotNull(graph.getCourse("C"));
    }

    @Test
    public void testIndegreeOfA() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(1, graph.getIndegree("A"));
    }

    @Test
    public void testIndegreeOfB() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(1, graph.getIndegree("B"));
    }

    @Test
    public void testIndegreeOfC() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertEquals(0, graph.getIndegree("C"));
    }

    @Test
    public void testEdgeCreatedBetweenBAndA() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        Course b = graph.getCourse("B");

        assertEquals ("A", b.getDependents().get(0).getCode());
    }

    @Test
    public void testCopyIndegreesCreatesSeparateMap() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        Map<String, Integer> copy = graph.copyIndegrees();

        copy.put("A", 999);

        assertEquals(1, graph.getIndegree("A"));
    }

    @Test
    public void testGetCourseReturnsNullForUnknownCourse() throws Exception {

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(createTestFile());

        assertNull(graph.getCourse("COMP2627"));
    }

    @Test
    public void testMissingPrerequisiteThrowsException() {

        DegreeGraph graph = new DegreeGraph();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            graph.buildGraph("InvalidPrereq.txt");
        });

        assertEquals("Prerequisite course 'COMP9999' does not exist in the course list.", exception.getMessage());
    }
}
