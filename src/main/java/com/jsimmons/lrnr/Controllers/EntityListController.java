package com.jsimmons.lrnr.Controllers;

import com.jsimmons.lrnr.Entities.Notebook;
import com.jsimmons.lrnr.Services.NotebookService;
import com.jsimmons.lrnr.Services.PageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EntityListController {

    @Autowired
    NotebookService notebookService;

    @Autowired
    PageService pageService;

    @GetMapping("entity-list")
    public List<Object> getEntities() {
        List<Object> allEntities = new ArrayList<Object>();
        allEntities.addAll(notebookService.getNotebooks());
        allEntities.addAll(pageService.getPages());
        return allEntities;
    }
}
