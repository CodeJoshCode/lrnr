/*
Author:
Date:
Purpose: A notebook is a chunk of specific information or skills that has practicable
outcomes defined at the time it is learned. A notebook should be sized such that
it can be practiced in a top limit of two hours per session or less. A notebook has
ReviewItems that make up its view as well as a ReviewSchedule that determines
when a notebook will be added to the daily review.
*/
package com.jsimmons.lrnr.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//todo : should id types be final ?
@Getter
@Setter
@Entity
@Table(name = "notebooks")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    UUID uuid = java.util.UUID.randomUUID();

    //TODO : handle error thrown from @NotBlank on entity creation from user. same for page
    @NotBlank
    @Column
    private String notebookName;
    //@OneToMany(targetEntity = Page.class, mappedBy = "id")
    //private List<Page> pagesForNotebook;

    /*
    possible if needed
    @Column
    private LocalDateTime  time;
     */

    @OneToMany(mappedBy = "notebook", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Page> pages;

    public Notebook() {
    }

    public Notebook(String notebookName) {
        this.notebookName = notebookName;
    }

    public void addPage(Page p) {
        pages.add(p);
    }

    public int getPageCount(){return pages.size();}

    public Page getPageById(Long id){
        return pages.get(Math.toIntExact(id) - 1);
    }
}
