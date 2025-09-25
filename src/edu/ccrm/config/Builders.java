package edu.ccrm.config;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
//import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

import java.util.ArrayList;
import java.util.List;

public class Builders {

    // Course Builder
    public static class CourseBuilder {
        private String code;
        private String title;
        private int credits;
        private String instructor;
        private String semester;
        private String department;

        public CourseBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public CourseBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public CourseBuilder setCredits(int credits) {
            this.credits = credits;
            return this;
        }

        public CourseBuilder setInstructor(String instructor) {
            this.instructor = instructor;
            return this;
        }

        public CourseBuilder setSemester(String semester) {
            this.semester = semester;
            return this;
        }

        public CourseBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Course build() {
            Instructor instructorObj = new Instructor(instructor);
            Semester semesterEnum = Semester.valueOf(semester.toUpperCase());
            return new Course(code, title, credits, instructorObj, semesterEnum, department);
        }

    }

    // Transcript Builder
    public static class TranscriptBuilder {
        private Student student;
        private final List<String> courseRecords = new ArrayList<>();

        public TranscriptBuilder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public TranscriptBuilder addCourseRecord(String record) {
            this.courseRecords.add(record);
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder();
            sb.append("Transcript for ").append(student.getFullName()).append("\n");
            courseRecords.forEach(record -> sb.append(record).append("\n"));
            return sb.toString();
        }
    }
}
