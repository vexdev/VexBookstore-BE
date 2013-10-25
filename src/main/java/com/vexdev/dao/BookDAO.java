package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.models.Book;
import org.hibernate.Criteria;
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
public class BookDAO {
    private static final Class CLASS = Book.class;

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of books
     * @return Every book in Database.
     */
    @Transactional
    public List<Book> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CLASS);
        return criteria.list();
    }

    /**
     * Updates / Sets a field in an existing Book.
     * @param id ID
     * @param field Field to set
     * @param value Value to set
     * @return Boolean true if no errors.
     */
    @Transactional
    public boolean setField(@NotNull String id, @NotNull String field, @NotNull String value) {
        try {
            Book book = getByID(id);
            if(field.equalsIgnoreCase("name")) {
                book.setName(value);
            } else if(field.equalsIgnoreCase("desc")) {
                book.setDescription(value);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    /**
     * Gets an element from it's ID
     * @param id ID
     * @return Element
     */
    @Transactional
    public Book getByID(@NotNull String id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CLASS);
        criteria.add(Restrictions.eq("isbn", id));
        Book book = (Book) criteria.uniqueResult();
        return book;
    }
}
