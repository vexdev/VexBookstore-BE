package com.vexdev.dao.interfaces;

import com.sun.istack.internal.NotNull;
import com.vexdev.models.interfaces.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Base DAO interface should be implemented by all DAOs
 * @param <T>
 */
@Repository
public abstract class BaseDAO<T extends BaseEntity> {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Returns a list of entities
     * @return List of any entities.
     */
    @Transactional
    public List<T> list() {
        return getSession().createCriteria(getEntityClass()).list();
    }

    /**
     * Method used to set parameters of current entity.
     * @param id Current entity's ID
     * @param field Field to set
     * @param value Value to set
     * @return Result
     */
    @Transactional
    public boolean setField(@NotNull String id, @NotNull String field, @NotNull String value) {
        T entity = getByID(id);
        return setField(entity, field, value);
    }

    /**
     * Method used to set parameters of current entity.
     * @param entity Current entity
     * @param field Field to set
     * @param value Value to set
     * @return Result
     */
    @Transactional
    public abstract boolean setField(@NotNull T entity, @NotNull String field, @NotNull String value);

    /**
     * Returns an entity by it's ID
     * @param id Element's ID
     * @return Element
     */
    @Transactional
    public T getByID(String id) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq(getIDName(), id));
        T entity = (T) criteria.uniqueResult();
        return entity;
    }

    /**
     * Drops an element by it's ID
     * @param id Element's ID
     */
    @Transactional
    public void dropByID(String id) {
        getSession().delete(getByID(id));
    }

    /**
     * Stores an entity in the database
     * @param entity Entity
     */
    @Transactional
    public void persist(T entity) {
        getSession().persist(entity);
        getSession().flush();
    }

    /**
     * Returns the class of the associated entity
     * @return a Class.
     */
    public abstract Class getEntityClass();

    /**
     * Returns the name of ID column in entity
     * @return String ID name
     */
    public abstract String getIDName();

    /**
     * Returns current session
     * @return Session
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
