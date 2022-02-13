package com.io.request.controller;

import com.io.itsduser.service.UserService;
import com.io.request.controller.data.CreateRequestBody;
import com.io.request.model.Request;
import com.io.request.services.RequestService;
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
    private UserService userService;

    private static final String REQUEST_BASE_URL = "/request";
    private static final Logger logger = LogManager.getLogger(RequestController.class);

    @Autowired
    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping(REQUEST_BASE_URL + "/viewAll")
    public String viewAllRequests(Model model) {
        List<Request> requestList = requestService.getAllRequests();
        model.addAttribute("requestList", requestList);
        return "ViewAllRequests";
    }

    @GetMapping(REQUEST_BASE_URL + "/view/{id}")
    public String viewRequest(Model model, @PathVariable String id) {
        Request request = requestService.getRequestById(id);
        model.addAttribute("request", request);
        return "ViewRequest";
    }

    @GetMapping(REQUEST_BASE_URL + "/view/user/{userId}")
    public String getAllRequestsForUser(Model model, @PathVariable String userId) {
        List<Request> requestList = userService.getAllRequestsForUser(userId);
        model.addAttribute("requestList", requestList);
        model.addAttribute("userId", userId);
        return "ViewAllRequests";
    }

    @GetMapping(REQUEST_BASE_URL + "/{userId}/create")
    public String displayCreateRequestForm(Model model, @PathVariable String userId) {
        CreateRequestBody createRequestBody = new CreateRequestBody().setUserId(userId);
        model.addAttribute("createRequestBody", createRequestBody);
        return "CreateRequest";
    }

    @PostMapping(REQUEST_BASE_URL + "/create")
    public RedirectView addNewRequestForUser(@ModelAttribute CreateRequestBody createRequestBody) {
        userService.createRequestForUser(createRequestBody);
        String userId = createRequestBody.getUserId();
        return new RedirectView("/request/view/user/" + userId);
    }


}
