package com.jsimmons.lrnr.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class NotebookController {

    @GetMapping("notebook")
    public String getNotebook() {
        return "GetNotebook";
    }

    @PostMapping("notebook")
    public String postNotebook(@RequestParam(name="notebook") String notebook, Model model) {
        model.addAttribute("notebook", notebook);
        return "PostNotebook";
    }


}
