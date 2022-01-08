package com.io.itsd.controller;

import com.io.itsd.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello World";
    }

    @GetMapping("/getAllRequests")
    public String getAllRequests() {
        return requestService.getAllRequests().toString();
    }

}
