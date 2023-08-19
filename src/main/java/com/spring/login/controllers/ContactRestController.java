package com.spring.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Contact;
import com.spring.login.repository.ContactRepository;


@RestController
@RequestMapping(path = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins="*")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    @PreAuthorize("hasRole('CLIENTE')")
    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status")  String status){
        return contactRepository.findByStatus(status);
    }

}
