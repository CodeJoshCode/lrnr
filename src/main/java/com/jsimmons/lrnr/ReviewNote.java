package com.jsimmons.lrnr;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ReviewNote extends ReviewItem{
    private @Id @GeneratedValue Long id;
    private String fileLocation;
    private String topicName;

    public ReviewNote() {
    }

    public ReviewNote(Long id, String fileLocation, String topicName) {
        this.id = id;
        this.fileLocation = fileLocation;
        this.topicName = topicName;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileLocation() {
        return this.fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ReviewNote id(Long id) {
        this.id = id;
        return this;
    }

    public ReviewNote fileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public ReviewNote topicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReviewNote)) {
            return false;
        }
        ReviewNote reviewNote = (ReviewNote) o;
        return Objects.equals(id, reviewNote.id) && Objects.equals(fileLocation, reviewNote.fileLocation) && Objects.equals(topicName, reviewNote.topicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileLocation, topicName);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fileLocation='" + getFileLocation() + "'" +
            ", topicName='" + getTopicName() + "'" +
            "}";
    }

    
}
