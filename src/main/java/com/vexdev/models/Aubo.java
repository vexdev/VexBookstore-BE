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
@javax.persistence.IdClass(AuboPK.class)
@Entity(name = "aubo")
public class Aubo {
    private int aid;
    private String isbn;

    @javax.persistence.Column(name = "aid")
    @Id
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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

        Aubo aubo = (Aubo) o;

        if (aid != aubo.aid) return false;
        if (isbn != null ? !isbn.equals(aubo.isbn) : aubo.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }
}
