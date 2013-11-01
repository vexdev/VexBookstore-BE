package com.vexdev.controllers;

import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/administrators")
public class AdminsController extends BaseController<Admin> {

    @Autowired
    BaseDAO<Admin> adminDAO;
    Admin admin = new Admin();

    @Override
    protected BaseDAO<Admin> getDAO() {
        return adminDAO;
    }

    @Override
    protected Admin getMock() {
        return admin;
    }

    @Override
    protected String getViewName() {
        return "admins";
    }
}
