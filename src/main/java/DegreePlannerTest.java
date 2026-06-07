/**
 * File: DegreePlannerTest.java
 * Description: 11 tests for testing the DegreePlanner class.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. ChatGPT was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DegreePlannerTest {

    //helper method to create a temporary test file for simple graphs
    private DegreeGraph buildSimpleGraph() throws Exception {

        File file = File.createTempFile("plannerTest", "txt");

        PrintWriter writer = new PrintWriter(file);

        writer.println("A,B,C");
        writer.println("A,B");
        writer.println("B,C");

        writer.close();

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(file.getAbsolutePath());

        return graph;

    }

    //helper method to create a temporary test file for concurrent graphs
    private DegreeGraph buildConcurrentGraph() throws Exception {

        File file = File.createTempFile("plannerTest", "txt");

        PrintWriter writer = new PrintWriter(file);

        writer.println("A,B,C,D");
        writer.println("A,C");
        writer.println("B,C");
        writer.println("C,D");

        writer.close();

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(file.getAbsolutePath());

        return graph;
    }

    //tests that the generated course order contains every course in the graph
    @Test
    public void testCourseOrderContainsAllCourses() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertEquals(3, order.size());
    }

    //tests that prerequisite course C appears before course B in the ordering
    @Test
    public void testPrerequisiteCAppearsBeforeB() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertTrue(order.indexOf("C") < order.indexOf("B"));
    }

    //tests that prerequisite course B appears before course A in the ordering
    @Test
    public void testPrerequisiteBAppearsBeforeA() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertTrue(order.indexOf("B") < order.indexOf("A"));
    }

    //tests that the first course in the ordering is the course with no prerequisites
    @Test
    public void testFirstCourseIsC() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertEquals("C", order.get(0));
    }

    //tests that a study plan with a limit of one course per period creates three periods
    @Test
    public void testThreeStudyPeriodsWithLimitedOne() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals(3, plan.size());
    }

    //tests that course C is scheduled in the first study period
    @Test
    public void testFirstStudyPeriodContainsC() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("C", plan.get(0).get(0));
    }

    //tests that course B is scheduled in the second study period
    @Test
    public void testSecondStudyPeriodContainsB() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("B", plan.get(1).get(0));
    }

    //tests that course A is scheduled in the third study period
    @Test
    public void testThirdStudyPeriodContainsA() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("A", plan.get(2).get(0));
    }

    //tests that independent courses can be scheduled together when concurrency allows
    @Test
    public void testConcurrentCoursesScheduledTogether() throws Exception {

        DegreeGraph graph = buildConcurrentGraph();

        DegreePlanner planner = new DegreePlanner(graph);

        List<List<String>> plan = planner.createStudyPlan(2);

        assertEquals(2, plan.get(2).size());
    }

    //tests that no study period exceeds the maximum concurrent course limit
    @Test
    public void testConcurrentLimitNeverExceeded() throws Exception {

        DegreeGraph graph = buildConcurrentGraph();

        DegreePlanner planner = new DegreePlanner(graph);

        List<List<String>> plan = planner.createStudyPlan(2);

        for (List<String> period : plan) {

            assertTrue(period.size() <= 2);
        }
    }

    //tests that a large concurrency limit does not create extra study periods
    @Test
    public void testLargeCourseLimit() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan =  planner.createStudyPlan(10);

        assertEquals(3, plan.size());
    }
}
