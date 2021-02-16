package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.entities.Page;
import com.jsimmons.lrnr.services.NotebookService;
import com.jsimmons.lrnr.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class homeController {

    @Autowired
    NotebookService notebookService;

    @Autowired
    PageService pageService;

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

    @GetMapping("/user_notebook/{notebook_identifier}")
    public String getUserNotebook(@PathVariable(value = "notebook_identifier") String notebookIdentifier, Model model) throws Exception {
        Notebook notebook = notebookService.findNotebookByName(notebookIdentifier);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }

    @PostMapping("/user_notebook/{notebook_identifier}")
    public String postUserNotebook(@PathVariable(value = "notebook_identifier") String notebookIdentifier,@RequestParam(name = "page_name") String pageName, Model model) throws Exception{
        Notebook notebook = notebookService.findNotebookByName(notebookIdentifier);
        Page new_page = new Page();
        new_page.setName(pageName);
        new_page.setNotebook(notebook);
        ArrayList<Page> pageList = new ArrayList<Page>();
        pageList.addAll(notebook.getPages());
        pageList.add(new_page);
        notebook.setPages(pageList);
        notebookService.saveNotebook(notebook);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }
}