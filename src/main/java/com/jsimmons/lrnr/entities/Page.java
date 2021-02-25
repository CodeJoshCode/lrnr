package com.jsimmons.lrnr.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    UUID uuid = UUID.randomUUID();

    //@Column
    //private String pathToPage;

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

    @NotBlank
    @Column
    private String name;

    @Column(length = 8000)
    private String textContents;

    /* Constructors
        textContents set to empty string bc most recent textContents is displayed in input form on UI side
        i.e. avoids displaying null in input form box.
     */
    public Page() {
        textContents = "";
    }

    public Page(String name, Notebook notebook) {
        this.name = name;
        this.notebook = notebook;
        this.textContents = "";
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", notebook=" + notebook +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id) && Objects.equals(notebook, page.notebook) && Objects.equals(name, page.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notebook, name);
    }
}
