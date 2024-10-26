package com.luismolina.quipux.database.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Table(name = "lists")
@Entity
@Builder
@Getter
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    public PlayList(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PlayList(){
        name = "name";
    }
}
