package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.Page;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PageRepository extends CrudRepository<Page, Long>{
    Page findByUuid(UUID uuid);
}
