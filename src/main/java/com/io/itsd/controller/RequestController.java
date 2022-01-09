package com.io.itsd.controller;

import com.io.itsd.services.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    private static final Logger logger = LogManager.getLogger(RequestController.class);

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
        logger.info("Received a get All requests request");
        return requestService.getAllRequests().toString();
    }

}
