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
public class CaboPK implements Serializable {
    private int cid;
    private String isbn;

@Id
@Column(name = "cid")
public int getCid() {
    return cid;
}

    public void setCid(int cid) {
        this.cid = cid;
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

        CaboPK caboPK = (CaboPK) o;

        if (cid != caboPK.cid) return false;
        if (isbn != null ? !isbn.equals(caboPK.isbn) : caboPK.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
}}
