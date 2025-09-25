package edu.ccrm.util;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;

import java.util.Comparator;

public class Comparators {

    public static Comparator<Student> studentByName = (s1, s2) ->
            s1.getFullName().compareToIgnoreCase(s2.getFullName());

    public static Comparator<Student> studentByRegNo = Comparator.comparing(Student::getRegNo);

    public static Comparator<Course> courseByCode = Comparator.comparing(Course::getCode);

    public static Comparator<Course> courseByTitle = Comparator.comparing(Course::getTitle);
}
