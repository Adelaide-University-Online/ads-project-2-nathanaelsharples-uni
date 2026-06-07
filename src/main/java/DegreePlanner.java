/**
 * File: DegreePlanner.java
 * Description: Responsible for generating valid course sequences and study plans
 *              based on prerequisites stored in DegreeGraph.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. ChatGPT was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.util.*;

/**
 * The DegreePlanner class is responsible for generating valid course
 * sequences and study plans based on the prerequisite relationships
 * stored in a DegreeGraph.
 *
 * This class uses graph algorithms to:
 * 1. Generate a valid topological ordering of courses.
 * 2. Create a study plan that respects prerequisite requirements while
 *    limiting the number of courses taken in each study period.
 **/
public class DegreePlanner {

    /**
     * Graph containing all courses and prerequisite relationships.
     **/
    private DegreeGraph graph;

    /**
     * Constructs a DegreePlanner object using the provided degree graph.
     *
     * @param graph the graph containing course dependency information.
     **/
    public DegreePlanner(DegreeGraph graph) {
        this.graph = graph;
    }

    /**
     * Generates a valid course ordering using a Depth-First Search (DFS)
     * topological sort.
     *
     * Courses are added to a stack after all dependent courses have been
     * visited. The stack is then reversed to produce a valid ordering
     * that satisfies prerequisite constraints.
     *
     * @return a list containing the courses in a valid completion order.
     **/
    public List<String> getCourseOrder() {

        //tracks courses that have already been visited during DFS
        Set<String> visited = new HashSet<>();

        //stores courses in reverse topological order
        Stack<String> stack = new Stack<>();

        //performs DFS for every unvisited course in the graph
        for (Course course : graph.getCourse()) {

            if (!visited.contains(course.getCode())) {

                dfs(course, visited, stack);
            }
        }

        //convert the stack contents into the final ordering
        List<String> order = new ArrayList<>();

        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }

        return order;
    }

    /**
     * Recursive DFS helper method used to perform topological sorting.
     *
     * Each course is visited once, and after all dependent courses have
     * been explored, the course is pushed into the stack.
     *
     * @param course the current course being processed.
     * @param visited set of courses that have already been visited.
     * @param stack stack used to build the topological ordering.
     **/
    private void dfs(Course course, Set<String> visited, Stack<String> stack) {

        //mark the current course as visited
        visited.add(course.getCode());

        //visit all dependent courses that have not yet been processed
        for (Course dependent : course.getDependents()) {

            if (!visited.contains(dependent.getCode())) {

                dfs(dependent, visited, stack);
            }
        }

        //add the course to the stack once all dependents are process
        stack.push(course.getCode());
    }

    /**
     * Creates a study plan by grouping courses into study periods.
     *
     * A course can only be scheduled once all of its prerequisites
     * have been completed. The number of courses in each study period
     * is limited by the maxCourses parameter.
     *
     * This method uses a variation of Kahn's topological sorting
     * algorithm based on indegree values.
     *
     * @param maxCourses maximum number of courses allowed per study period.
     * @return a list of study periods, where each study period contains
     *         a list of course codes.
     **/
    public List<List<String>> createStudyPlan(int maxCourses) {

        //create a copy of the indegree map so the original graph
        //remains unchanged
        Map<String, Integer> indegrees = graph.copyIndegrees();

        //stores the final study plan
        List<List<String>> studyPeriods = new ArrayList<>();

        //holds courses that currently have no unmet prerequisites
        Queue<String> available = new LinkedList<>();

        //add all courses with indegree 0 to the queue
        for (Course course : graph.getCourse()) {

            if (indegrees.get(course.getCode()) == 0) {

                available.offer(course.getCode());
            }
        }

        //continue creating study periods while courses remain unavailable
        while (!available.isEmpty()) {

            //represents a single study period
            List<String> period = new ArrayList<>();

            //determine how many courses can be taken this period
            int count = Math.min(maxCourses, available.size());

            for (int i = 0; i < count; i++) {

                //remove the next available course
                String courseCode = available.poll();

                period.add(courseCode);

                //retrieve the course object from the graph
                Course course = graph.getCourse(courseCode);

                //update indegrees for all dependent courses
                for (Course dependent : course.getDependents()) {

                    String depCode = dependent.getCode();

                        //one prerequisite has now been completed
                        indegrees.put(depCode, indegrees.get(depCode) - 1);

                        //if no prerequisites remain, make the course available
                        if (indegrees.get(depCode) == 0) {

                            available.offer(depCode);
                        }
                    }
                }

                //add the completed study period to the study plan
                studyPeriods.add(period);
            }

            return studyPeriods;
        }
    }
