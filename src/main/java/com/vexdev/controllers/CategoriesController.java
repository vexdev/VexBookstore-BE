package com.vexdev.controllers;

import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController extends BaseController<Category> {

    @Autowired
    BaseDAO<Category> categoryDAO;
    Category category = new Category();

    @Override
    protected BaseDAO<Category> getDAO() {
        return categoryDAO;
    }

    @Override
    protected Category getMock() {
        return category;
    }

    @Override
    protected String getViewName() {
        return "categories";
    }
}
