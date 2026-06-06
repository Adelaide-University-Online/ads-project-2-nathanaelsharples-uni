/**
 * File: CourseTest.java
 * Description: 12 tests for testing the Course class code.
 * Author: Nathanael Sharples
 * Student ID: a2983892
 * Email ID: a2983892@adelaide.edu.au
 * AI Tool Used: Yes. Chat gpt was used for research and help with some code logic.
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    //test course constructor stores code
    @Test
    public void testConstructorStoresCode() {

        Course course = new Course("COMP2627");

        assertEquals("COMP2627", course.getCode());
    }

    //test dependent list is initially empty
    @Test
    public void testDependentsInitiallyEmpty() {

        Course course = new Course("COMP2627");

        assertTrue(course.getDependents().isEmpty());
    }

    //test dependents are added to list
    @Test
    public void testAddSingleDependent() {

        Course course = new Course("COMP2627");
        Course dependent = new Course("COMP1043");

        course.addDependent(dependent);

        assertEquals(1, course.getDependents().size());
    }

    //test dependent is stored correctly
    @Test
    public void testAddSingleDependentStoredCorrectly() {

        Course course = new Course("COMP2627");
        Course dependent = new Course("COMP1043");

        course.addDependent(dependent);

        assertEquals("COMP1043", course.getDependents().get(0).getCode());
    }

    //test dependents are stored correctly
    @Test
    public void testAddMultipleDependents() {

        Course course = new Course("COMP2627");

        course.addDependent(new Course("COMP1043"));
        course.addDependent(new Course("MATH2032"));
        course.addDependent(new Course("INFS1029"));

        assertEquals(3, course.getDependents().size());
    }

    //test toString method
    @Test
    public void testToStringReturnsCourseCode() {

        Course course = new Course("COMP2627");

        assertEquals("COMP2627", course.toString());
    }

    //test equals method with same code
    @Test
    public void testEqualsSameCode() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP2627");

        assertEquals(course1, course2);
    }

    //test equals method with different codes
    @Test
    public void testEqualsDifferentCode() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP1617");

        assertNotEquals(course1, course2);
    }

    //test equals method with same object
    @Test
    public void testEqualsSameObject() {

        Course course = new Course ("COMP2627");

        assertEquals(course, course);
    }

    //test equals method are different types
    @Test
    public void testEqualsDifferentType() {
        Course course = new Course("COMP2627");

        assertNotEquals(course, "COMP2627");
    }

    //test hashcode with same object
    @Test
    public void testHashCodeEqualObjects() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP2627");

        assertEquals(course1.hashCode(), course2.hashCode());
    }

    //test hashcode with different objects
    @Test
    public void testHashCodeDifferentObjects() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP1617");

        assertNotEquals(course1.hashCode(), course2.hashCode());
    }
}
