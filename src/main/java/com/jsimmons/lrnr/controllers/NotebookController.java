package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class NotebookController {

    @Autowired
    NotebookService service;

    @GetMapping("notebook")
    public String getNotebook() {
        return "GetNotebook";
    }

    @PostMapping("notebook")
    public String postNotebook(@RequestParam(name="notebook2") String notebook2, Model model) {
        //add new notebook to Model
        model.addAttribute("notebook2", notebook2);
        //add new notebook to Database
        service.saveNotebook(new Notebook(notebook2));
        return "PostNotebook";
    }

    @GetMapping("findnotebookbyname")
    public String getNotebookTest() {
        return "FindNotebookByName";
    }

    // TODO: add something to protect us if notebook isn't there
    @PostMapping("findnotebookbyname")
    public String getFindNotebookByName(@RequestParam(name="notebookName", required = false) String notebookName, Model model){
        Notebook requestedNotebook = service.findNotebookByName(notebookName);
        model.addAttribute("id", requestedNotebook.getId());

        return "FindNotebookByName";
    }





}
