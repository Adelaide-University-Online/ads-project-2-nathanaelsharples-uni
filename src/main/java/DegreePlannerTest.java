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

    @Test
    public void testCourseOrderContainsAllCourses() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertEquals(3, order.size());
    }

    @Test
    public void testPrerequisiteCAppearsBeforeB() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertTrue(order.indexOf("C") < order.indexOf("B"));
    }

    @Test
    public void testPrerequisiteBAppearsBeforeA() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertTrue(order.indexOf("B") < order.indexOf("A"));
    }

    @Test
    public void testFirstCourseIsC() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<String> order = planner.getCourseOrder();

        assertEquals("C", order.get(0));
    }

    @Test
    public void testThreeStudyPeriodsWithLimitedOne() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals(3, plan.size());
    }

    @Test
    public void testFirstStudyPeriodContainsC() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("C", plan.get(0).get(0));
    }

    @Test
    public void testSecondStudyPeriodContainsB() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("B", plan.get(1).get(0));
    }

    @Test
    public void testThirdStudyPeriodContainsA() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan = planner.createStudyPlan(1);

        assertEquals("A", plan.get(2).get(0));
    }

    @Test
    public void testConcurrentCoursesScheduledTogether() throws Exception {

        DegreeGraph graph = buildConcurrentGraph();

        DegreePlanner planner = new DegreePlanner(graph);

        List<List<String>> plan = planner.createStudyPlan(2);

        assertEquals(2, plan.get(2).size());
    }

    @Test
    public void testConcurrentLimitNeverExceeded() throws Exception {

        DegreeGraph graph = buildConcurrentGraph();

        DegreePlanner planner = new DegreePlanner(graph);

        List<List<String>> plan = planner.createStudyPlan(2);

        for (List<String> period : plan) {

            assertTrue(period.size() <= 2);
        }
    }

    @Test
    public void testLargeCourseLimit() throws Exception {

        DegreePlanner planner = new DegreePlanner(buildSimpleGraph());

        List<List<String>> plan =  planner.createStudyPlan(10);

        assertEquals(3, plan.size());
    }
}
