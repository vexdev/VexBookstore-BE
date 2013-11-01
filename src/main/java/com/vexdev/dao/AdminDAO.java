package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AdminDAO extends BaseDAO<Admin> {
    public static final Class CLASS = Admin.class;

    /**
     * Returns an Admin from it's email
     * @param email Admin's email
     * @return Admin
     */
    @Transactional
    public Admin byUser(String email) {
        return getByID(email);
    }

    @Override
    public boolean setField(@NotNull Admin entity, @NotNull String field, @NotNull String value) {
        if(field.equalsIgnoreCase("name")) {
            entity.setDisplayName(value);
        } else if(field.equalsIgnoreCase("password")) {
            entity.setPassword(value);
        } else if(field.equalsIgnoreCase("email")) {
            entity.setEmail(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Class getEntityClass() {
        return CLASS;
    }

    @Override
    public String getIDName() {
        return "email";
    }
}
