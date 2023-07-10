package ru.navope.rento.models;

import org.springframework.stereotype.Component;

public class Person {
    private String fullName;
    private int yearBirth;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearBirth = yearOfBirth;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearBirth = yearOfBirth;
    }
}
