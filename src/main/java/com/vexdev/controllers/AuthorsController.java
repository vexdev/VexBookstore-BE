package com.vexdev.controllers;

import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authors")
public class AuthorsController extends BaseController<Author>{
    @Autowired
    BaseDAO<Author> authorDAO;
    Author author = new Author();

    @Override
    protected BaseDAO<Author> getDAO() {
        return authorDAO;
    }

    @Override
    protected Author getMock() {
        return author;
    }

    @Override
    protected String getViewName() {
        return "authors";
    }
}
