package com.cloudsherpas.fundyourleader.model;

import org.joda.time.DateTime;

/**
 * Created by ben on 1/19/16.
 */
public class Candidate {

    private String firstName;
    private String lastName;
    private DateTime birthday;

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

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }
}
