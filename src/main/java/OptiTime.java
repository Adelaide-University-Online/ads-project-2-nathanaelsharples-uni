/**
 * File: OptiTime.java
 * Description: The main driver class of the degree planning application.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. ChatGPT was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * OptiTime is the main driver class for the degree planning application.
 *
 * This program reads a course prerequisite file, constructs a directed
 * graph representing course dependencies, generates a valid course
 * ordering using topological sorting, and creates an optimal study plan
 * based on the maximum number of courses that can be completed in each
 * study period.
 *
 **/
public class OptiTime {

    /**
     * Program entry point.
     *
     * Prompts the user for an input file containing course prerequisite
     * information and the maximum number of courses that may be taken
     * concurrently. The method then builds the degree graph, generates
     * a valid course order, and displays an optimal study plan.
     *
     * @param args command-line arguments (not used in this program)
     * @throws FileNotFoundException if the specific input file cannot be found
     **/
    public static void main(String[] args) throws FileNotFoundException {

        //scanner used to read user input from the keyboard
        Scanner keyboard = new Scanner(System.in);

        //prompt the user for the course prerequisite file
        System.out.print("Enter filename: ");
        String filename = keyboard.nextLine();

        //prompt user for the maximum number of concurrent courses
        System.out.print("Maximum concurrent courses: ");
        int maxCourses = keyboard.nextInt();

        //create and populate the degree graph from the input file
        DegreeGraph graph = new DegreeGraph();
        graph.buildGraph(filename);

        //create a planner that uses the graph to generate study schedules
        DegreePlanner planner = new DegreePlanner(graph);

        //display a valid course ordering
        System.out.println("\nCourse Ordering:");

        List<String> order = planner.getCourseOrder();

        for (String course : order) {
            System.out.print(course + " ");
        }

        //display the optimal study plan
        System.out.println("\n\nOptimal study Plan:");

        List<List<String>> plan = planner.createStudyPlan(maxCourses);

        //print the courses assigned to each study period
        for (int i = 0; i < plan.size(); i++) {
            System.out.println("Study Period " + (i+1) + ": ");
            System.out.println(plan.get(i));
        }

        //display the total number of study periods required
        System.out.println("\nTotal Study Periods: " + plan.size());

        //close scanner to release system resources
        keyboard.close();
    }
}
