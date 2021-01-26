package com.jsimmons.lrnr.Controllers;

import com.jsimmons.lrnr.Services.PageService;
import com.jsimmons.lrnr.repositories.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    PageService service;

    @GetMapping("page")
    public String getPage() {
        return "GetPage";
    }
}
