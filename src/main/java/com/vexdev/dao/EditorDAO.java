package com.vexdev.dao;

import com.vexdev.models.Editor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EditorDAO {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of editors
     * @return Every editor in Database.
     */
    @Transactional
    public List<Editor> list() {
        return sessionFactory.getCurrentSession().createCriteria(Editor.class).list();
    }
}
