package com.cloudsherpas.fundyourleader.model;

import org.joda.time.LocalDate;

/**
 * Created by ben on 1/19/16.
 */
public class Candidate {

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Candidate(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFullName() { return firstName + " " + lastName; }
}
