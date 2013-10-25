package com.vexdev.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.IdClass(EdboPK.class)
@Entity
public class Edbo {
    private int eid;
    private String isbn;

    @javax.persistence.Column(name = "eid")
    @Id
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @javax.persistence.Column(name = "isbn")
    @Id
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edbo edbo = (Edbo) o;

        if (eid != edbo.eid) return false;
        if (isbn != null ? !isbn.equals(edbo.isbn) : edbo.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }
}
