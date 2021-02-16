package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    NotebookService notebookService;

    @GetMapping
    public String getIndex() {
        return "index";
    }
    @PostMapping
    public String postIndex(@RequestParam(name="notebook") String notebookPath, Model model) {
        notebookService.saveNotebook(new Notebook(notebookPath));
        model.addAttribute("notebook", notebookPath);
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "index";
    }
}
