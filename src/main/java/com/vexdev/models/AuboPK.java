package com.vexdev.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
public class AuboPK implements Serializable {
    private int aid;
    private String isbn;

@Id
@Column(name = "aid")
public int getAid() {
    return aid;
}

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Id@Column(name = "isbn")
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

        AuboPK auboPK = (AuboPK) o;

        if (aid != auboPK.aid) return false;
        if (isbn != null ? !isbn.equals(auboPK.isbn) : auboPK.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
}}
