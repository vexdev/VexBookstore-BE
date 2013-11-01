package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Category;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDAO extends BaseDAO<Category> {
    public static final Class CLASS = Category.class;

    @Override
    public boolean setField(@NotNull Category entity, @NotNull String field, @NotNull String value) {
        if(field.equalsIgnoreCase("name")) {
            entity.setName(value);
        } else if(field.equalsIgnoreCase("cid")) {
            entity.setCid(Integer.valueOf(value));
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
        return "cid";
    }
}
