package com.przbetkier.mercury.adapter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class PageController {

    @ApiIgnore
    @GetMapping({"/", "/news/**"})
    public String route() {
        return "forward:/index.html";
    }
}
