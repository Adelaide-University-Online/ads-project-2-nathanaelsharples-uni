import java.util.*;

public class DegreePlanner {

    private DegreeGraph graph;

    public DegreePlanner(DegreeGraph graph) {
        this.graph = graph;
    }

    //DFS topological ordering
    public List<String> getCourseOrder() {

        Set<String> visited = new HashSet<>();

        Stack<String> stack = new Stack<>();

        for (Course course : graph.getCourse()) {

            if (!visited.contains(course.getCode())) {

                dfs(course, visited, stack);
            }
        }

        List<String> order = new ArrayList<>();

        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }

        return order;
    }

    private void dfs(Course course, Set<String> visited, Stack<String> stack) {
        visited.add(course.getCode());

        for (Course dependent : course.getDependents()) {

            if (!visited.contains(dependent.getCode())) {

                dfs(dependent, visited, stack);
            }
        }

        stack.push(course.getCode());
    }

    //create study plan
    public List<List<String>> createStudyPlan(int maxCourses) {

        Map<String, Integer> indegrees = graph.copyIndegrees();

        List<List<String>> studyPeriods = new ArrayList<>();

        Queue<String> available = new LinkedList<>();

        for (Course course : graph.getCourse()) {

            if (indegrees.get(course.getCode()) == 0) {

                available.offer(course.getCode());
            }
        }

        while (!available.isEmpty()) {

            List<String> period = new ArrayList<>();

            int count = Math.min(maxCourses, available.size());

            for (int i = 0; i < count; i++) {

                String courseCode = available.poll();

                period.add(courseCode);

                Course course = graph.getCourse(courseCode);

                for (Course dependent : course.getDependents()) {

                    String depCode = dependent.getCode();

                        indegrees.put(depCode, indegrees.get(depCode) - 1);

                        if (indegrees.get(depCode) == 0) {

                            available.offer(depCode);
                        }
                    }
                }

                studyPeriods.add(period);
            }

            return studyPeriods;
        }
    }
