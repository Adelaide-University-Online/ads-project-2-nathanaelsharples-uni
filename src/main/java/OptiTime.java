import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class OptiTime {

    public static void main(String[] Args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter filename: ");

        String filename = keyboard.nextLine();

        System.out.print("Maximum concurrent courses: ");

        int maxCourses = keyboard.nextInt();

        DegreeGraph graph = new DegreeGraph();

        graph.buildGraph(filename);

        DegreePlanner planner = new DegreePlanner(graph);

        System.out.println("\nCourse Ordering:");

        List<String> order = planner.getCourseOrder();

        for (String course : order) {
            System.out.print(course + " ");
        }

        System.out.println("\n\nOptimal study Plan:");

        List<List<String>> plan = planner.createStudyPlan(maxCourses);

        for (int i = 0; i < plan.size(); i++) {
            System.out.println("Study Period " + (i+1) + ": " + plan.get(i));
        }

        System.out.println("\nTotal Study Periods: " + plan.size());
    }
}
