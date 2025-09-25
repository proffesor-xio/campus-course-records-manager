package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String employeeId;
    private String department;
    private List<Course> coursesTaught;

    public Instructor(String id, String fullName, String email, LocalDate dateOfBirth, String employeeId,
            String department) {
        super(id, fullName, email, dateOfBirth);
        this.employeeId = employeeId;
        this.department = department;
        this.coursesTaught = new ArrayList<>();
    }

    public Instructor(String fullName) {
        super("", fullName, "", LocalDate.now());
        this.employeeId = "";
        this.department = "";
        this.coursesTaught = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void assignCourse(Course course) {
        coursesTaught.add(course);
    }

    public void removeCourse(Course course) {
        coursesTaught.remove(course);
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", EmployeeID: " + employeeId +
                ", Department: " + department +
                ", CoursesTaught: " + coursesTaught.size();
    }
}
