package com.jsimmons.lrnr.Controllers;

import com.jsimmons.lrnr.Entities.Notebook;
import com.jsimmons.lrnr.Services.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
    public String postNotebook(@RequestParam(name="notebook") String notebook, Model model) {
        //add new notebook to Model
        model.addAttribute("notebook", notebook);
        //add new notebook to Database
        service.saveNotebook(new Notebook(notebook));
        return "PostNotebook";
    }


}
