package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    NotebookService notebookService;

    //main page

    @GetMapping("/")
    public String getIndex(Model model, @PathVariable(value = "notebookId", required = false) String notebookId) {
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "index";
    }
    @PostMapping("/")
    public String postIndex(@RequestParam(name="notebook") String notebookPath, Model model) {
        notebookService.saveNotebook(new Notebook(notebookPath));
        model.addAttribute("notebook", notebookPath);
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "index";
    }

    // pages for individual notebooks

    @GetMapping("/usernotebook/{notebookidentifier}")
    public String getUserNotebook(@PathVariable(value = "notebookidentifier") String notebookIdentifier, Model model) throws Exception {
        return "user_notebook";
    }
}
