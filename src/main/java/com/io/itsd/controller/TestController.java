package com.io.itsd.controller;

import com.io.itsd.dao.RequestDao;
import com.io.itsd.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private RequestDao requestDao;

    @GetMapping("/asd")
    private String asd() {
        List<Request> requestList = requestDao.retrieveRequests("From Request");
        System.out.println(requestList.get(0).toString());
        return "hello";
    }
}
