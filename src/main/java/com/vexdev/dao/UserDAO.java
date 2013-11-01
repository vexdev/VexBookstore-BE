package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.User;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAO extends BaseDAO<User> {
    public static final Class CLASS = User.class;

    @Override
    public boolean setField(@NotNull User entity, @NotNull String field, @NotNull String value) {
        if(field.equalsIgnoreCase("name")) {
            entity.setName(value);
        } else if(field.equalsIgnoreCase("surname")) {
            entity.setSurname(value);
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
