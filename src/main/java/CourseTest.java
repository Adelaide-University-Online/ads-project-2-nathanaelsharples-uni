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
}
