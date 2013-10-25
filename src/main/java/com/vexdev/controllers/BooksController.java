package com.vexdev.controllers;

import com.vexdev.dao.BookDAO;
import com.vexdev.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BooksController {

    @Autowired
    BookDAO bookDAO;

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String books(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        map.addAttribute("userDetails", userDetails);
        List<Book> books = bookDAO.list();
        map.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(value="/storeParameter", method=RequestMethod.GET)
    public @ResponseBody Map<String, ? extends Object> storeParameter(@RequestParam String id, @RequestParam String field ,@RequestParam String value) {
        boolean result = bookDAO.setField(id, field, value);
        return Collections.singletonMap("done", result);
    }
}
