package com.thc.engfall.controller.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "user/" + page;
    }

    @GetMapping("/{page}/{uniqueId}")
    public String page2(@PathVariable String page, @PathVariable String uniqueId){
        return "user/" + page;
    }
}
