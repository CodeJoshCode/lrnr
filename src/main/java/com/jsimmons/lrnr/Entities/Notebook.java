/*
Author:
Date:
Purpose: A notebook is a chunk of specific information or skills that has practicable
outcomes defined at the time it is learned. A notebook should be sized such that
it can be practiced in a top limit of two hours per session or less. A notebook has
ReviewItems that make up its view as well as a ReviewSchedule that determines
when a notebook will be added to the daily review.
*/
package com.jsimmons.lrnr.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "notebooks")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String notebookName;
    //@OneToMany(targetEntity = Page.class, mappedBy = "id")
    //private List<Page> pagesForNotebook;

    @OneToMany(mappedBy = "notebook", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Page> pages;

    public Notebook() {
    }

    public Notebook(String notebookName) {
        this.notebookName = notebookName;
    }
}
