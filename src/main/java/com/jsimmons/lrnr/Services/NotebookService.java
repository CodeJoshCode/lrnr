package com.jsimmons.lrnr.Services;

import com.jsimmons.lrnr.Entities.Notebook;
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
    public void saveNotebook(Notebook n) {
        repository.save(n);
    }

    public Notebook getNotebook(String name){
        return repository.findByNotebookName(name);
    }
}
