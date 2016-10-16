package ru.inbox.savinov_vu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "files")
@NamedQuery(name = "Files.getAll", query = "SELECT c from SavedFile c")
public class SavedFile implements Serializable, Comparable<SavedFile> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;


    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @Column(name = "loadpath")
    @JsonIgnore
    private String loadpath;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoadpath(String loadpath) {
        this.loadpath = loadpath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoadpath() {
        return loadpath;
    }

    public SavedFile() {
    }


    public SavedFile(int id) {
        this.id = id;
    }

    public SavedFile(String name, String loadpath) {
        this.name = name;
        this.loadpath = loadpath;
    }

    public SavedFile(int id, String name, String loadpath) {
        this.id = id;
        this.name = name;
        this.loadpath = loadpath;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id: " + id + " name: " + name + " phonenumber: " + loadpath + ")";
    }


    @Override
    public int compareTo(SavedFile o) {
        return this.id - o.id;
    }
}
