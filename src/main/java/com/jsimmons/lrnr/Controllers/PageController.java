package com.jsimmons.lrnr.Controllers;

import com.jsimmons.lrnr.Entities.Notebook;
import com.jsimmons.lrnr.Entities.Page;
import com.jsimmons.lrnr.Services.NotebookService;
import com.jsimmons.lrnr.Services.PageService;
import com.jsimmons.lrnr.repositories.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    PageService service;

    @Autowired
    NotebookService notebookService;

    @GetMapping("page")
    public String getPage() {
        return "GetPage";
    }

    @PostMapping("page")
    public String postPage(@RequestParam(name = "page") String pagePath, Model model) {
        model.addAttribute("page", pagePath);
        Notebook newbook = new Notebook("examplebook1");
        notebookService.saveNotebook(newbook);
        Page newpage = new Page(pagePath, newbook);
        service.savePage(newpage);
        return "PostPage";
    }
}
