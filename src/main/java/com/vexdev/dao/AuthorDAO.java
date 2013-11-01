package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Author;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AuthorDAO extends BaseDAO<Author> {
    public static final Class CLASS = Author.class;

    @Override
    public boolean setField(@NotNull Author entity, @NotNull String field, @NotNull String value) {
        if(field.equalsIgnoreCase("name")) {
            entity.setName(value);
        } else if(field.equalsIgnoreCase("surname")) {
            entity.setSurname(value);
        } else if(field.equalsIgnoreCase("aid")) {
            entity.setAid(Integer.valueOf(value));
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
        return "aid";
    }
}
