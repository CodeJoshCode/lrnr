/*
Author:
Date:
Purpose: A topic is a chunk of specific information or skills that has practicable
outcomes defined at the time it is learned. A topic should be sized such that
it can be practiced in a top limit of two hours per session or less. A topic has
ReviewItems that make up its view as well as a ReviewSchedule that determines
when a topic will be added to the daily review.
*/
package com.jsimmons.lrnr;

import javax.persistence.Entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;



@Entity
public class Topic {
    private @Id @GeneratedValue Long id;
    private String topicName;
    @OneToMany(targetEntity=ReviewNote.class, mappedBy="topicName")
    private List<ReviewNote> notesForTopic;


    public Topic() {
    }

    public Topic(Long id, String topicName, List<ReviewNote> notesForTopic) {
        this.id = id;
        this.topicName = topicName;
        this.notesForTopic = notesForTopic;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<ReviewNote> getNotesForTopic() {
        return this.notesForTopic;
    }

    public void setNotesForTopic(List<ReviewNote> notesForTopic) {
        this.notesForTopic = notesForTopic;
    }

    public Topic id(Long id) {
        this.id = id;
        return this;
    }

    public Topic topicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public Topic notesForTopic(List<ReviewNote> notesForTopic) {
        this.notesForTopic = notesForTopic;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) && Objects.equals(topicName, topic.topicName) && Objects.equals(notesForTopic, topic.notesForTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, notesForTopic);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", topicName='" + getTopicName() + "'" +
            ", notesForTopic='" + getNotesForTopic() + "'" +
            "}";
    }
	

}
