package com.jsimmons.lrnr.controllers;

import com.jsimmons.lrnr.entities.Notebook;
import com.jsimmons.lrnr.entities.Page;
import com.jsimmons.lrnr.services.NotebookService;
import com.jsimmons.lrnr.services.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Controller
public class homeController {

    private final NotebookService notebookService;

    private final PageService pageService;


    @Autowired
    public homeController(NotebookService notebookService,
                          PageService pageService) {
        this.notebookService = notebookService;
        this.pageService = pageService;
    }

    Logger logger = LoggerFactory.getLogger(homeController.class);

    //main page

    //TODO : Tried out @ModelAttribute annotation on /postHome below, will explain when we go over code again.
    @GetMapping("/home")
    public String getHome(@PathVariable(value = "notebookId", required = false) String notebookId, Model model) {
        logger.info("Home method accessed");
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
    public String getUserNotebook(@PathVariable(value = "notebook_identifier") UUID notebookIdentifier, Model model) throws Exception {
        Notebook notebook = notebookService.findByUuid(notebookIdentifier);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }

    //TODO : consider not allowing multiple notebooks w/ same name below?
    @PostMapping("/user_notebook/{notebook_identifier}")
    public String postUserNotebook(@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                                   @RequestParam(name = "page_name") String pageName,
                                   Model model) {
        Notebook notebook = notebookService.findByUuid(notebookIdentifier);
        Page new_page = new Page(pageName, notebook);
        notebook.addPage(new_page);
        notebookService.saveNotebook(notebook);
        model.addAttribute("user_notebook", notebook);
        return "user_notebook";
    }

    @GetMapping("/{notebook_identifier}/{page_identifier}")
    public String getUserPage(@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                              @PathVariable(value = "page_identifier") UUID pageIdentifier,
                              Model model ) {
        Page page =  pageService.findByUuid(pageIdentifier);
        model.addAttribute("user_page", page);
        return "user_page";
    }


    //handle textcontent form

    @PostMapping("/{notebook_identifier}/{page_identifier}")
    public String postUserPageTextContents (@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                               @PathVariable(value = "page_identifier") UUID pageIdentifier,
                               @RequestParam(name = "page_text_content") String page_contents,
                               Model model) {
        Page page = pageService.findByUuid(pageIdentifier);
        page.setTextContents(page.getTextContents() + page_contents);
        pageService.savePage(page);
        logger.info("page textContents should have just saved to db");
        model.addAttribute("user_page", page);
        return "user_page";
    }

    //handle MultipartFile form

    @PostMapping("/{notebook_identifier}/{page_identifier}/upload")
    public String postUserPageMultipartFile (@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                                             @PathVariable(value = "page_identifier") UUID pageIdentifier,
                                             @RequestParam(name = "uploaded_document") MultipartFile uploadedDocument,
                                             Model model) {
        Page page = pageService.findByUuid(pageIdentifier);
        page.setFileSize(uploadedDocument.getSize());
        page.setFileType(uploadedDocument.getContentType());
        try {
            page.setDocument(uploadedDocument.getBytes());
        } catch (IOException e) {
            logger.error("File was unable to be read", e);
        }
        pageService.savePage(page);
        model.addAttribute("user_page", page);
        return "user_page";
    }

    // edit pages

    @GetMapping("/{notebook_identifier}/edit/{page_identifier}")
    public String getUserPageEdit(@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                                  @PathVariable(value = "page_identifier") UUID pageIdentifier,
                                  Model model) {
        Page page = pageService.findByUuid(pageIdentifier);
        model.addAttribute("user_page", page);

        return "edit_user_page";
    }

    @PostMapping("/{notebook_identifier}/edit/{page_identifier}")
    public String postUserPageEdit(@PathVariable(value = "notebook_identifier") UUID notebookIdentifier,
                                   @PathVariable(value = "page_identifier") UUID pageIdentifier,
                                   @RequestParam(name = "page_name", required = false) String pageName,
                                   @RequestParam(name = "page_text_content", required = false) String pageContents,
                                   Model model) {
        //TODO : pretty sure theres specific spring validation for null checking these requestparams
        Page page = pageService.findByUuid(pageIdentifier);
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