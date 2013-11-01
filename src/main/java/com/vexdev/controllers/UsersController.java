package com.vexdev.controllers;

import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends BaseController<User> {

    @Autowired
    BaseDAO<User> userDAO;
    User user = new User();

    @Override
    protected BaseDAO<User> getDAO() {
        return userDAO;
    }

    @Override
    protected User getMock() {
        return user;
    }

    @Override
    protected String getViewName() {
        return "users";
    }
}
