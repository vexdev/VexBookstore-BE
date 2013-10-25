package com.vexdev.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Category {
    private int cid;
    private String name;
    private List<Book> books;

    @javax.persistence.Column(name = "cid")
    @Id
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (cid != category.cid) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "categories")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
