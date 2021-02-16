package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class homeController {

    @Autowired
    NotebookService notebookService;

    //main page

    @GetMapping("/home")
    public String getHome(Model model, @PathVariable(value = "notebookId", required = false) String notebookId) {
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "home";
    }
    @PostMapping("/home")
    public String postHome(@RequestParam(name="notebook") String notebookPath, Model model) {
        notebookService.saveNotebook(new Notebook(notebookPath));
        model.addAttribute("notebook", notebookPath);
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "home";
    }

    // pages for individual notebooks

    @GetMapping("/usernotebook/{notebook_identifier}")
    public String getUserNotebook(@PathVariable(value = "notebook_identifier") String notebookIdentifier, Model model) throws Exception {
        Notebook notebook = notebookService.findNotebookByName(notebookIdentifier);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }
}