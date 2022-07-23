package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.Responder;
import com.io.itsduser.model.User;
import com.io.itsduser.service.ResponderService;
import com.io.login.LoginUtils;
import com.io.request.model.Request;
import com.io.request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static com.io.BaseURLsKt.CUSTOMER_BASE_URL;
import static com.io.BaseURLsKt.RESPONDER_BASE_URL;

@Controller
public class ResponderController {

    private ResponderService responderService;
    private RequestService requestService;

    @Autowired
    public ResponderController(ResponderService responderService, RequestService requestService) {
        this.responderService = responderService;
        this.requestService = requestService;
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/" +  RESPONDER_BASE_URL + "/create")
    public String createResponder(Model model) {
        model.addAttribute("createResponderBody", new CreateUserBody());
        return "CreateResponder";
    }

    @PostMapping(value = CUSTOMER_BASE_URL + "/" +  RESPONDER_BASE_URL + "/insert")
    public String insertResponder(@ModelAttribute CreateUserBody createUserBody){
        responderService.createResponder(createUserBody);
        return "redirect:/homepage";
    }

    @GetMapping(value = CUSTOMER_BASE_URL + "/" + RESPONDER_BASE_URL + "/list")
    public String getResponders(Model model) {
       List<User> responderList = responderService.getAllResponders();
       model.addAttribute("responderList", responderList);
       return "ViewAllResponders";
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/triage/all")
    public String triageRequests(Model model) {
        List<Request> requestList = requestService.getAllRequests();
        model.addAttribute("requestList", requestList);
        return "TriageAllRequests";
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/triage/assigned")
    public String triageRequestsForLoggedInResponder(Model model) {
        UserDetails loggedInUser = LoginUtils.getLoggedInUser();
        List<Request> requestListForLoggedInAssignee = requestService.getAllRequests()
                .stream()
                .filter(request -> loggedInUser.getUsername().equals(request.getAssigneeName()))
                .collect(Collectors.toList());
        model.addAttribute("requestList", requestListForLoggedInAssignee);
        return "TriageAllRequests" ;
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/{requestId}/{state}")
    public String changeRequestStatus(@PathVariable String requestId, @PathVariable String state) {
        requestService.modifyRequestState(requestId, state);
        return "redirect:/responder/triage/all";
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/{requestId}/selfAssign")
    public String selfAssignRequests(@PathVariable String requestId) {
        requestService.selfAssignRequest(requestId);
        return "redirect:/responder/triage/all";
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/{requestId}/unAssign")
    public String unAssignRequests(@PathVariable String requestId) {
        requestService.unAssignRequest(requestId);
        return "redirect:/responder/triage/all";
    }

}
