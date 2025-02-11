public class Student {
    String name;
    int age;
    double gpa;
    boolean isEnrolled;
    static int numOfStudents;

    Student (String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        isEnrolled = true;
        numOfStudents++;
    }

     static void numStudents() {
        System.out.println(numOfStudents);
    }
}