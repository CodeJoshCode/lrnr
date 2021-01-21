package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.Notebook;

import org.springframework.data.repository.CrudRepository;

public interface NotebookRepository extends CrudRepository<Notebook, Long> {
    
}
