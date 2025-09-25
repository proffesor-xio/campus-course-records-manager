package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private boolean isActive;
    private List<Course> enrolledCourses;

    public Student(String id, String fullName, String email, LocalDate dateOfBirth, String regNo) {
        super(id, fullName, email, dateOfBirth);
        this.regNo = regNo;
        this.isActive = true;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void unenrollCourse(Course course) {
        enrolledCourses.remove(course);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", RegNo: " + regNo +
                ", Active: " + isActive +
                ", EnrolledCourses: " + enrolledCourses.size();
    }
}
