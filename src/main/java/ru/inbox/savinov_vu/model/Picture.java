package ru.inbox.savinov_vu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "pictures")

public class Picture {

    @Id
    @SequenceGenerator(name = "PICT_SEQ", sequenceName = "PICT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PICT_SEQ")
    @JsonProperty("id")
    private int id;


    @Column(name = "url")
    @JsonProperty("url")
    private String url;

    @Column(name = "loadpath")
    @JsonProperty("loadpath")
    private String loadpath;


    @Column(name = "countLike")
    @JsonProperty("countLike")
    private int countLike;


    public Picture(String url, String loadpath, int countLike) {
        this.url = url;
        this.loadpath = loadpath;
        this.countLike = countLike;
    }

    public Picture() {
    }


    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", loadpath='" + loadpath + '\'' +
                ", countLike=" + countLike +
                '}';
    }


}
