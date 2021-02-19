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

    //TODO : Tried out @ModelAttribute annotation on /postHome below, will explain when we go over code again.
    //
    @GetMapping("/home")
    public String getHome(@PathVariable(value = "notebookId", required = false) String notebookId, Model model) {
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "home";
    }
    @PostMapping("/home")
    public String postHome(
//            @RequestParam(name="notebook") String notebookPath,
            @ModelAttribute(name = "notebook") Notebook notebook,
            Model model) {
        notebookService.saveNotebook(notebook);

//        notebookService.saveNotebook(new Notebook(notebookPath));
//        model.addAttribute("notebook", notebookPath);
        model.addAttribute("notebooks", notebookService.getNotebooks());
        return "home";
    }

    // pages for individual notebooks

    @GetMapping("/user_notebook/{notebook_identifier}")
    public String getUserNotebook(@PathVariable(value = "notebook_identifier") Long notebookIdentifier, Model model) throws Exception {
        Notebook notebook = notebookService.findById(notebookIdentifier);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }

    @PostMapping("/user_notebook/{notebook_identifier}")
    public String postUserNotebook(@PathVariable(value = "notebook_identifier") Long notebookIdentifier,
                                   @RequestParam(name = "page_name") String pageName,
                                   Model model) throws Exception{
        Notebook notebook = notebookService.findById(notebookIdentifier);
        //TODO : this looks like it should be abstracted to a constructor below
        Page new_page = new Page();
        new_page.setName(pageName);
        new_page.setNotebook(notebook);
        new_page.setTextContents("");
        ArrayList<Page> pageList = new ArrayList<Page>();
        pageList.addAll(notebook.getPages());
        pageList.add(new_page);
        notebook.setPages(pageList);
        notebookService.saveNotebook(notebook);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }

    @GetMapping("/user_page/{page_identifier}")
    public String getUserPage(@PathVariable(value = "page_identifier") Long pageIdentifier, Model model ) {
        Page page =  pageService.findById(pageIdentifier);
        model.addAttribute("user_page", page);

        return "user_page";
    }

//    // TODO: change parameters to a map
//    @PostMapping("/user_page/{page_identifier}")
//    public String postUserPage(@PathVariable(value = "page_identifier") Long pageIdentifier,
//                               @RequestParam(name = "id") Long page_id,
//                               @RequestParam(name = "page_name") String page_name,
//                               @RequestParam(name = "page_text_content") String page_contents,
//                               Model model){
//        Page page =  pageService.findById(pageIdentifier);
//        page.setTextContents(page_contents);
//        pageService.savePage(page);
//        model.addAttribute("user_page", page);
//        return "user_page";
//    }
    @PostMapping("/user_page/{page_identifier}")
    public String postUserPage(@PathVariable(value = "page_identifier") Long pageIdentifier,
                               //@RequestParam(name = "page_name") String pageName,
                               @RequestParam(name = "page_text_content") String page_contents,
                               Model model) {
        Page page = pageService.findById(pageIdentifier);
        System.out.println(page);
        page.setTextContents(page_contents);
        pageService.savePage(page);
        model.addAttribute("user_page", page);
        System.out.println(page);
        return "user_page";
    }

    // edit pages

    @GetMapping("/user_page/edit/{page_identifier}")
    public String getUserPageEdit(@PathVariable(value = "page_identifier") Long pageIdentifier, Model model) {
        Page page = pageService.findById(pageIdentifier);
        model.addAttribute("user_page", page);

        return "edit_user_page";
    }

    @PostMapping("/user_page/edit/{page_identifier}")
    public String postUserPageEdit(@PathVariable(value = "page_identifier") Long pageIdentifier,
                                   @RequestParam(name = "page_name", required = false) String pageName,
                                   @RequestParam(name = "page_text_content", required = false) String pageContents,
                                   Model model) {
        //TODO : pretty sure theres specific spring validation for null checking these requestparams
        Page page = pageService.findById(pageIdentifier);
        if (pageName != null) {
            page.setName(pageName);
        }
        if (pageContents != null) {
            page.setTextContents(pageContents);
        }
        pageService.savePage(page);
        model.addAttribute("user_page", page);
        return "user_page";
    }


}