import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DegreeGraph {

    private Map<String, Course> courses;
    private Map<String, Integer> indegrees;

    public DegreeGraph() {
        courses = new HashMap<>();
        indegrees = new HashMap<>();
    }

    public Collection<Course> getCourse() {
        return courses.values();
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public int getIndegree(String code) {
        return indegrees.getOrDefault(code, 0);
    }

    public void buildGraph(String filename)
        throws FileNotFoundException {

        Scanner file = new Scanner(new File(filename));

        String[] allCourses = file.nextLine().split(",");

        for (String courseCode : allCourses) {

            courseCode = courseCode.trim();

            courses.put(courseCode, new Course(courseCode));

            indegrees.put(courseCode, 0);
        }

        while (file.hasNextLine()) {

            String line = file.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");

            String courseCode = parts[0].trim();

            Course course = courses.get(courseCode);

            for (int i = 1; i < parts.length; i++) {

                String prereqCode = parts[i].trim();

                Course prereq = courses.get(prereqCode);

                prereq.addDependent(course);

                indegrees.put(courseCode, indegrees.get(courseCode) + 1);
            }
        }

        file.close();
    }

    public Map<String, Integer> copyIndegrees() {
        return new HashMap<>(indegrees);
    }
}
