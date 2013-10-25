package com.vexdev.controllers;

import com.vexdev.dao.AuthorDAO;
import com.vexdev.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/authors")
public class AuthorsController {

    @Autowired
    AuthorDAO authorDAO;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String authors(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        map.addAttribute("userDetails", userDetails);
        List<Author> authors = authorDAO.list();
        map.addAttribute("authors", authors);
        return "authors";
    }
}
