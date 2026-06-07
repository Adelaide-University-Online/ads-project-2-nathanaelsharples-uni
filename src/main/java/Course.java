/**
 * File: Course.java
 * Description: Representation of a single course in the degree graph.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. ChatGPT was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *Represents a single course within the degree graph.
 *
 * Each course stores its course code and a list of courses that
 * directly depend on it as a prerequisite. This class is used by
 * the graph structure to model prerequisite relationships between
 * courses when generating an optimal study plan.
 **/

public class Course {

    //stores the unique course code (eg. COMP2627)
    private String code;

    /**
     * Stores all courses that depend on this course.
     * These represent outgoing edges in the prerequisite graph.
     **/
    private List<Course> dependents;

    /**
     * Creates a new course object with the specified course code.
     * An empty list of dependent courses is also created.
     *
     * @param code the course code for this course.
     **/
    public Course(String code) {
        this.code = code;
        this.dependents = new ArrayList<>();
    }

    /**
     * Returns the course code.
     *
     * @return the course code.
     **/
    public String getCode() {
        return code;
    }

    /**
     * Returns the list of courses that depend on this course.
     *
     * @return list of dependent courses.
     **/
    public List<Course> getDependents() {
        return dependents;
    }

    /**
     * Adds a dependent course to this course.
     *
     * This method is used when constructing the graph to create
     * prerequisite relationships between courses.
     *
     * @param course the course that depends on this course.
     **/
    public void addDependent(Course course) {
        dependents.add(course);
    }

    /**
     * Returns the course code as a string representation.
     * This makes graph output and debugging easier to read.
     *
     * @return the course code.
     **/
    @Override
    public String toString() {
        return code;
    }

    /**
     * Compares this course with another object for equality.
     *
     * Two courses objects are considered equal if they have the
     * same course code, regardless of their dependent courses.
     *
     * @param obj the object being compared.
     * @return true if the courses have the same code, otherwise false.
     **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Course other = (Course) obj;

        return Objects.equals(code, other.code);
    }

    /**
     * Generates a hash code based on the course code.
     *
     * This method is overridden to remain consistent with the
     * equals() method and allows Course objects to be used
     * correctly in hash-based collections such as HashMap
     * and HashSet.
     *
     * @return hash code for this course.
     **/
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
