package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.Entities.Notebook;

import org.springframework.data.repository.CrudRepository;

public interface NotebookRepository extends CrudRepository<Notebook, Long> {
    
    Notebook findByNotebookName(String notebookName);
    
}
