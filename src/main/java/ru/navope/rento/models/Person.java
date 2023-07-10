package ru.navope.rento.models;

public class Person {
    private int id;
    private String fullName;
    private int yearBirth;

    public Person(int id, String fullName, int yearBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }
}
