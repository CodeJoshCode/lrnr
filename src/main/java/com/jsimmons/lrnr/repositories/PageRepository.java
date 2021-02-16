package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.Page;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Long>{
    
}
