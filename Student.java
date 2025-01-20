public class Student extends Person {
    private String course;

    // Constructor
    public Student(String name, int age, String course) {
        super(name, age); // Call the constructor of the superclass
        this.course = course;
    }

    // Getter og setter
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    //Overrider displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Course: " + course);
    }
}
