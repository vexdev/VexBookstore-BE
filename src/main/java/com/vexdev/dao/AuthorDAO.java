package com.vexdev.dao;

import com.vexdev.models.Author;
import com.vexdev.models.Book;
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
public class AuthorDAO {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of books
     * @return Every book in Database.
     */
    @Transactional
    public List<Author> list() {
        return sessionFactory.getCurrentSession().createCriteria(Author.class).list();
    }
}
