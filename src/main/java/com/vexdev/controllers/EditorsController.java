package com.vexdev.controllers;

import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/editors")
public class EditorsController extends BaseController<Editor> {

    @Autowired
    BaseDAO<Editor> editorDAO;
    Editor editor = new Editor();

    @Override
    protected BaseDAO<Editor> getDAO() {
        return editorDAO;
    }

    @Override
    protected Editor getMock() {
        return editor;
    }

    @Override
    protected String getViewName() {
        return "editors";
    }
}
