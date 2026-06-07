/**
 * File: DegreeGraph.java
 * Description: Represents a directed graph of university courses and their
 *              prerequisite relationship.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. ChatGPT was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * DegreGraph represents a directed graph of university courses and their
 * prerequisite relationships.
 *
 * Each course is stored as a vertex in the graph, while prerequisite
 * relationships are represented as directed edges between courses.
 *
 * The graph also maintains an indegree count for each course, which
 * represents the number of prerequisites that must be completed before
 * the course can be undertaken.
 *
 * This graph is used by the scheduling algorithm to determine a valid
 * order for completing curses within a degree.
 **/
public class DegreeGraph {

    /**
     * Stores all courses in the graph using teh course code as the key.
     * This allows efficient lookup of Course objects.
     **/
    private Map<String, Course> courses;

    /**
     * Stores the indegree of each course.
     * The indegree represents the number of prerequisite courses required.
     **/
    private Map<String, Integer> indegrees;

    /**
     * Constructs and empty DegreeGraph.
     *
     * Both maps are initialized so courses and indegrees can be added
     * as the graph is built from the input file.
     **/
    public DegreeGraph() {
        courses = new HashMap<>();
        indegrees = new HashMap<>();
    }

    /**
     * Returns all courses currently stored in the graph.
     *
     * @return a collection containing every course in the graph.
     **/
    public Collection<Course> getCourse() {
        return courses.values();
    }

    /**
     * Retrieves a specific course using its course code.
     *
     * @param code the course code to search for.
     * @return the corresponding Course object, or null if not found.
    **/
    public Course getCourse(String code) {
        return courses.get(code);
    }

    /**
     * Returns the indegree value of a course.
     *
     * The indegree indicates how many prerequisite courses must be
     * completed before the course becomes available.
     *
     * @param code the course code.
     * @return the indegree value, or 0 if the course does not exist.
     **/
    public int getIndegree(String code) {
        return indegrees.getOrDefault(code, 0);
    }

    /**
     * Builds the degree graph using information read from an input file.
     *
     * The first line contains all course codes.
     * Each remaining line contains a course followed by its prerequisites.
     *
     * Directed edges are add from prerequisite courses to the courses
     * that depend on them. At the same time, indegree values are updated
     * to reflect the number of prerequisites each course has.
     *
     * @param filename the name of the input file.
     * @throws FileNotFoundException if the file cannot be located.
     * @throws IllegalArgumentException if a prerequisite course does not
     * exist in the course list.
     **/
    public void buildGraph(String filename)
        throws FileNotFoundException {

        //open the input file for reading
        Scanner file = new Scanner(new File(filename));

        //Read the first line containing all course codes
        String[] allCourses = file.nextLine().split(",");

        //create a Course object for each course code
        for (String courseCode : allCourses) {

            courseCode = courseCode.trim();

            courses.put(courseCode, new Course(courseCode));

            //all courses initially have an indegree of zero
            indegrees.put(courseCode, 0);
        }

        //process the remaining lines containing prerequisite relationships
        while (file.hasNextLine()) {

            String line = file.nextLine().trim();

            //skip blank lines in the file
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            //first value is the course being described
            String courseCode = parts[0].trim();

            Course course = courses.get(courseCode);

            //remaining values are prerequisite courses
            for (int i = 1; i < parts.length; i++) {

                String prereqCode = parts[i].trim();

                Course prereq = courses.get(prereqCode);

                //ensure the prerequisite exists in the graph
                if (prereq == null) {
                    throw new IllegalArgumentException ("Prerequisite course '" + prereqCode + "' does not exist in the course list.");
                }

                //add a directed edge from the prerequisite
                //to the course that depends on it
                prereq.addDependent(course);

                //increase the indegree count because the course
                //has one additional prerequisite
                indegrees.put(courseCode, indegrees.get(courseCode) + 1);
            }
        }

        //close the file once processing is complete
        file.close();
    }

    /**
     * Creates a copy of the indegree map.
     *
     * A copy is returned so that scheduling algorithms can modify
     * indegree values without affecting the original graph data.
     *
     * @return a new map containing the current indegree values.
     **/
    public Map<String, Integer> copyIndegrees() {
        return new HashMap<>(indegrees);
    }
}
