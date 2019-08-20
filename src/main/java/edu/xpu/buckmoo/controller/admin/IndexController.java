package edu.xpu.buckmoo.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class IndexController {
    @GetMapping(value = {"", "/", "/index", "/index.html"})
    public String index(){
        return "index";
    }
}
