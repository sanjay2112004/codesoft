import java.util.*;

class Course {
    String title;
    int capacity;
    String schedule;

    Course(String title, int capacity, String schedule) {
        this.title = title;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String id;
    String name;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class CourseRegistrationSystem {
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Student> students = new HashMap<>();
    private Map<String, List<String>> registrations = new HashMap<>();

    public CourseRegistrationSystem() {
        courses.put("CS101", new Course("Introduction to Programming", 3, "Mon 9-11 AM"));
        courses.put("CS102", new Course("Data Structures", 2, "Wed 11-1 PM"));
        courses.put("CS103", new Course("Databases", 2, "Fri 2-4 PM"));
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();
            System.out.println(entry.getKey() + ": " + course.title +
                               " | Capacity: " + course.capacity +
                               " | Schedule: " + course.schedule);
        }
    }

    public void registerStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();
        if (!students.containsKey(studentId)) {
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();
            students.put(studentId, new Student(studentId, studentName));
        }

        displayCourses();
        System.out.print("\nEnter Course Code to Register: ");
        String courseCode = scanner.nextLine();

        if (courses.containsKey(courseCode) && courses.get(courseCode).capacity > 0) {
            registrations.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseCode);
            courses.get(courseCode).capacity--;
            System.out.println("Student " + students.get(studentId).name +
                               " registered for " + courseCode + ".");
        } else {
            System.out.println("Invalid course code or no slots available.");
        }
    }

    public void dropCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();

        if (registrations.containsKey(studentId)) {
            System.out.println("Registered Courses: " + registrations.get(studentId));
            System.out.print("Enter Course Code to Drop: ");
            String courseCode = scanner.nextLine();

            if (registrations.get(studentId).remove(courseCode)) {
                courses.get(courseCode).capacity++;
                System.out.println("Course " + courseCode + " dropped.");
            } else {
                System.out.println("You are not registered for this course.");
            }
        } else {
            System.out.println("No courses registered for this student.");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Display Courses\n2. Register for a Course\n3. Drop a Course\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.start();
    }
}