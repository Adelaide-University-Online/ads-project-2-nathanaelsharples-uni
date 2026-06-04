import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
