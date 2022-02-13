package com.io.request.controller;

import com.io.request.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private RequestDao requestDao;

    @GetMapping("/asd")
    private String asd() {
        return "fragments/SidebarNav";
    }
}
