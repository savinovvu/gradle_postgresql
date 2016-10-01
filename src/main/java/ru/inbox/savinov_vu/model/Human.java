package ru.inbox.savinov_vu.model;

import java.io.Serializable;

public class Human implements Serializable {

    private int id;
    private String name;
    private String phoneNumber;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Human() {
    }


    public Human(int id) {
        this.id = id;
    }

    public Human(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Human(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id: " + id + " name: " + name + " phonenumber: " + phoneNumber + ")";
    }
}
