package com.jsimmons.lrnr.services;

import java.util.List;
import java.util.UUID;

import com.jsimmons.lrnr.entities.Page;
import com.jsimmons.lrnr.repositories.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    PageRepository repository;

    public List<Page> getPages() {
        return (List<Page>) repository.findAll();
    }
    public void savePage(Page p) {
        repository.save(p);
    }
    public Page findById(Long id) {
        return repository.findById(id).get();
    }
    public Page findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }
}
