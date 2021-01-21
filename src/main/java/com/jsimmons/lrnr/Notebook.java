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
    @OneToMany(targetEntity = ReviewNote.class, mappedBy = "notebookName")
    private List<ReviewNote> notesForNotebook;

    public Notebook() {
    }

    public Notebook(Long id, String notebookName, List<ReviewNote> notesForNotebook) {
        this.id = id;
        this.notebookName = notebookName;
        this.notesForNotebook = notesForNotebook;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotebookName() {
        return this.notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public List<ReviewNote> getNotesForNotebook() {
        return this.notesForNotebook;
    }

    public void setNotesForNotebook(List<ReviewNote> notesForNotebook) {
        this.notesForNotebook = notesForNotebook;
    }

    public Notebook id(Long id) {
        this.id = id;
        return this;
    }

    public Notebook notebookName(String notebookName) {
        this.notebookName = notebookName;
        return this;
    }

    public Notebook notesForNotebook(List<ReviewNote> notesForNotebook) {
        this.notesForNotebook = notesForNotebook;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Notebook)) {
            return false;
        }
        Notebook notebook = (Notebook) o;
        return Objects.equals(id, notebook.id) && Objects.equals(notebookName, notebook.notebookName) && Objects.equals(notesForNotebook, notebook.notesForNotebook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notebookName, notesForNotebook);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", notebookName='" + getNotebookName() + "'" +
            ", notesForNotebook='" + getNotesForNotebook() + "'" +
            "}";
    }
	

}
