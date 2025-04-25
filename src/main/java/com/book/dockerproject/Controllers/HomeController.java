package com.book.dockerproject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex(){
        return "redirect:/students";
    }

    @GetMapping("/allLectures")
    public String getLectures(){
        return "redirect:/lectures";
    }
}
