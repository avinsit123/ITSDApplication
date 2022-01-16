package com.io.itsd.controller;

import com.io.itsd.controller.data.CreateRequestBody;
import com.io.itsd.model.Request;
import com.io.itsd.services.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class RequestController {

    private RequestService requestService;

    private static final String REQUEST_BASE_URL = "/request";
    private static final Logger logger = LogManager.getLogger(RequestController.class);

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(value = REQUEST_BASE_URL + "/hello" , method = RequestMethod.GET)
    public String helloWorld() {
        return "hello";
    }

    @GetMapping(REQUEST_BASE_URL + "/createRequestView")
    public String createRequest(Model model) {
        model.addAttribute("createRequestBody", new CreateRequestBody());
        return "CreateRequest";
    }

    @PostMapping(REQUEST_BASE_URL + "/insertRequest")
    public RedirectView insertRequest(@ModelAttribute CreateRequestBody createRequestBody) {
        requestService.createRequest(createRequestBody);
        return new RedirectView("viewAllRequests");
    }

    @GetMapping(REQUEST_BASE_URL + "/viewAllRequests")
    public String viewAllRequests(Model model) {
        List<Request> requestList = requestService.getAllRequests();
        model.addAttribute("requestList", requestList);
        return "ViewAllRequests";
    }

    @GetMapping(REQUEST_BASE_URL + "/viewRequest/{id}")
    public String viewRequest(Model model, @PathVariable String id) {
        Request request = requestService.getRequestById(id);
        model.addAttribute("request", request);
        return "ViewRequest";
    }
}
