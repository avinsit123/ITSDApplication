package com.io.itsduser.controller;

import com.io.itsduser.controller.model.CreateCustomerBody;
import com.io.itsduser.controller.model.CreateUserBody;
import com.io.itsduser.model.Responder;
import com.io.itsduser.service.ResponderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ResponderController {

    private ResponderService responderService;
    private static final String RESPONDER_BASE_URL = "/responder";

    @Autowired
    public ResponderController(ResponderService responderService) {
        this.responderService = responderService;
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/create")
    public String createResponder(Model model) {
        model.addAttribute("createResponderBody", new CreateUserBody());
        return "CreateResponder";
    }

    @PostMapping(value = RESPONDER_BASE_URL + "/insert")
    public RedirectView insertCustomer(@ModelAttribute CreateUserBody createUserBody){
        responderService.createResponder(createUserBody);
        return new RedirectView("list");
    }

    @GetMapping(value = RESPONDER_BASE_URL + "/list")
    public String getResponders(Model model) {
       List<Responder> responderList = responderService.getAllResponders();
       model.addAttribute("responderList", responderList);
       return "ViewAllResponders";
    }
}
