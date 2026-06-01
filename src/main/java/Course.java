import java.util.ArrayList;
import java.util.List;

public class Course {

    private String code;
    private List<Course> dependents;

    public Course(String code) {
        this.code = code;
        this.dependents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<Course> getDependents() {
        return dependents;
    }

    public void addDependent(Course course) {
        dependents.add(course);
    }

    @Override
    public String toString() {
        return code;
    }
}
