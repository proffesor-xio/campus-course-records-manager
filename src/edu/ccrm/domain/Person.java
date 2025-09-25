package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;
    protected LocalDate dateOfBirth;

    public Person(String id, String fullName, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Email: " + email + ", DOB: " + dateOfBirth;
    }
}
