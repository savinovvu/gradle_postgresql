package ru.inbox.savinov_vu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pictures")

public class Picture implements Serializable, Comparable<Picture> {


    public Picture(String url, String loadpath, int countLike) {
        this.url = url;
        this.loadpath = loadpath;
        this.countLike = countLike;
    }

    @Id
    @SequenceGenerator(name = "PICT_SEQ", sequenceName = "PICT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PICT_SEQ")
    @JsonProperty("id")
    private int id;


    @Column(name = "url")
    @JsonProperty("url")
    private String url;

    @Column(name = "loadpath")
    @JsonIgnore
    private String loadpath;


    @Column(name = "countLike")
    @JsonProperty("countLike")
    private int countLike;



    @Override
    public int compareTo(Picture o) {
        return this.id - o.id;
    }
}
