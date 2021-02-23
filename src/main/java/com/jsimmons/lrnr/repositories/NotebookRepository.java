package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.Notebook;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotebookRepository extends CrudRepository<Notebook, Long> {
    
    Notebook findByNotebookName(String notebookName);
    Notebook findByUuid(UUID uuid);

}
