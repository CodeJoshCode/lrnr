package com.jsimmons.lrnr.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String pathToPage;

    // README on FetchType being FetchType.LAZY vs FetchType.EAGER:
    /*
        When the parent entity (the "one" in one to many) is loaded we can use the fetch flag to indicate whether or not to load the child (many) type.
        This is useful because it will be unreasonable to load all of the children (especially if there is a large amt) on every parent load.
        lazy waits until something like getPages() is called from Notebook. Eager will load every child on each parent load.
        Also Note default fetch types for jpa relationships:

        * OneToMany ----> fetch = FetchType.LAZY
        * ManyToMany ---> fetch = FetchType.LAZY

        * ManyToOne ----> fetch = FetchType.EAGER
        * OneToOne -----> fetch = FetchType.EAGER (Seems EAGER vs LAZY would be disregardable here but maybe loadtime still matters in some case? hard to say)

        So these default fetch types appear to be optimized ahead of time but prob wouldnt hurt to know. 
     */


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notebook_id", nullable = false)
    private Notebook notebook;

    @Column
    private String name;

    public Page() {
    }

    public Page(String pathToPage, String name, Notebook notebook) {
        this.pathToPage = pathToPage;
        this.name = name;
        this.notebook = notebook;
    }

    public Page(String pathToPage, Notebook notebook){
        this.pathToPage = pathToPage;
        this.notebook = notebook;
    }

    //whats up with these page methods are we even using them?
    public Page id(long id) {
        setId(id);
        return this;
    }

    public Page pathToPage(String pathToPage) {
        setPathToPage(pathToPage);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Page)) {
            return false;
        }
        Page page = (Page) o;
        return id == page.id && Objects.equals(pathToPage, page.pathToPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathToPage);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", pathToPage='" + getPathToPage() + "'" +
            "}";
    }

}
