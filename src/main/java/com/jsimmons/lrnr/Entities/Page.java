package com.jsimmons.lrnr.Entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String pathToPage;


    public Page() {
    }

    public Page(long id, String pathToPage) {
        this.id = id;
        this.pathToPage = pathToPage;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPathToPage() {
        return this.pathToPage;
    }

    public void setPathToPage(String pathToPage) {
        this.pathToPage = pathToPage;
    }

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
