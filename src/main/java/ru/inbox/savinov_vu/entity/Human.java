package ru.inbox.savinov_vu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "human")
@NamedQuery(name = "Human.getAll", query = "SELECT c from Human c")
public class Human implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @Column(name = "phonenumber")
    @JsonProperty("phonenumber")
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
