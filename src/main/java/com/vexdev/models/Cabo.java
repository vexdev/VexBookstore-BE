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
@javax.persistence.IdClass(CaboPK.class)
@Entity(name = "cabo")
public class Cabo {
    private int cid;
    private String isbn;

    @javax.persistence.Column(name = "cid")
    @Id
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

        Cabo cabo = (Cabo) o;

        if (cid != cabo.cid) return false;
        if (isbn != null ? !isbn.equals(cabo.isbn) : cabo.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }
}
