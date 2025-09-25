package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private Grade grade;

    public Enrollment(Student student, Course course, LocalDate enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = Grade.NOT_GRADED; // Default grade
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "Student=" + student.getFullName() +
                ", Course=" + course.getCode() +
                ", EnrollmentDate=" + enrollmentDate +
                ", Grade=" + grade +
                '}';
    }
}
