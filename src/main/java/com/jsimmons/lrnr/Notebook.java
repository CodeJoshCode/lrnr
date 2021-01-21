/*
Author:
Date:
Purpose: A notebook is a chunk of specific information or skills that has practicable
outcomes defined at the time it is learned. A notebook should be sized such that
it can be practiced in a top limit of two hours per session or less. A notebook has
ReviewItems that make up its view as well as a ReviewSchedule that determines
when a notebook will be added to the daily review.
*/
package com.jsimmons.lrnr;

import javax.persistence.Entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;



@Entity
public class Notebook {
    private @Id @GeneratedValue Long id;
    private String notebookName;
    //@OneToMany(targetEntity = ReviewNote.class, mappedBy = "notebookName")
    //private List<ReviewNote> notesForNotebook;

    public Notebook() {
    }
}
