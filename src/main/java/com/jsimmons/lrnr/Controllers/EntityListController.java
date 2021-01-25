package com.jsimmons.lrnr.Controllers;

import com.jsimmons.lrnr.Entities.Notebook;
import com.jsimmons.lrnr.Services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntityListController {

    @Autowired
    NotebookService notebookService;

    @GetMapping("entity-list")
    public List<Notebook> getEntities() {
        return notebookService.getNotebooks();
    }
}
