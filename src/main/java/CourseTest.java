import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    @Test
    public void testConstructorStoresCode() {

        Course course = new Course("COMP2627");

        assertEquals("COMP2627", course.getCode());
    }

    @Test
    public void testDependentsInitiallyEmpty() {

        Course course = new Course("COMP2627");

        assertTrue(course.getDependents().isEmpty());
    }

    @Test
    public void testAddSingleDependent() {

        Course course = new Course("COMP2627");
        Course dependent = new Course("COMP1043");

        course.addDependent(dependent);

        assertEquals(1, course.getDependents().size());
    }

    @Test
    public void testAddSingleDependentStoredCorrectly() {

        Course course = new Course("COMP2627");
        Course dependent = new Course("COMP1043");

        course.addDependent(dependent);

        assertEquals("COMP1043", course.getDependents().get(0).getCode());
    }

    @Test
    public void testAddMultipleDependents() {

        Course course = new Course("COMP2627");

        course.addDependent(new Course("COMP1043"));
        course.addDependent(new Course("MATH2032"));
        course.addDependent(new Course("INFS1029"));

        assertEquals(3, course.getDependents().size());
    }

    @Test
    public void testToStringReturnsCourseCode() {

        Course course = new Course("COMP2627");

        assertEquals("COMP2627", course.toString());
    }

    @Test
    public void testEqualsSameCode() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP2627");

        assertEquals(course1, course2);
    }

    @Test
    public void testEqualsDifferentCode() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP1617");

        assertNotEquals(course1, course2);
    }

    @Test
    public void testEqualsSameObject() {

        Course course = new Course ("COMP2627");

        assertEquals(course, course);
    }

    @Test
    public void testEqualsDifferentType() {
        Course course = new Course("COMP2627");

        assertNotEquals(course, "COMP22627");
    }

    @Test
    public void testHashCodeEqualObjects() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP2627");

        assertEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    public void testHashCodeDifferentObjects() {

        Course course1 = new Course("COMP2627");
        Course course2 = new Course("COMP1617");

        assertNotEquals(course1.hashCode(), course2.hashCode());
    }
}
