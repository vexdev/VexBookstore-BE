package com.vexdev.dao;

import com.vexdev.models.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AdminDAO {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of admins
     * @return Every admin in Database.
     */
    @Transactional
    public List<Admin> list() {
        return sessionFactory.getCurrentSession().createCriteria(Admin.class).list();
    }

    /**
     * Returns an Admin from it's email
     * @param email Admin's email
     * @return Admin
     */
    @Transactional
    public Admin byUser(String email) {
        return (Admin) sessionFactory.getCurrentSession().createCriteria(Admin.class).add(Restrictions.eq("email", email)).uniqueResult();
    }
}
