package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.Notebook;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotebookRepository extends CrudRepository<Notebook, Long> {
    
    Notebook findByNotebookName(String notebookName);

}
