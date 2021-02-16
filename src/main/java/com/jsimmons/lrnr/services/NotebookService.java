package com.jsimmons.lrnr.services;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookService {

    @Autowired
    NotebookRepository repository;

    public List<Notebook> getNotebooks() {
        return (List<Notebook>) repository.findAll();
    }
    public Notebook saveNotebook(Notebook n) {
        return repository.save(n);
    }

    public Notebook findNotebookByName(String name){
        return repository.findByNotebookName(name);
    }

    public Notebook findById(Long id) {
        return repository.findById(id).get();
    }
}
