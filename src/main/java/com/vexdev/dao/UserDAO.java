package com.vexdev.dao;

import com.vexdev.models.Admin;
import com.vexdev.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
public class UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of users
     * @return Every user in Database.
     */
    @Transactional
    public List<User> list() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
