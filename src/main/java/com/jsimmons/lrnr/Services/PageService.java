package com.jsimmons.lrnr.Services;

import java.util.List;

import com.jsimmons.lrnr.Entities.Page;
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
}
