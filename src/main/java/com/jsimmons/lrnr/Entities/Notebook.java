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

import javax.persistence.Entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;



@Entity
@Table(name = "notebooks")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String notebookName;
    //@OneToMany(targetEntity = ReviewNote.class, mappedBy = "notebookName")
    //private List<ReviewNote> notesForNotebook;

    public Notebook() {
    }

    public Notebook(String notebookName) {
        this.notebookName = notebookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }
}
