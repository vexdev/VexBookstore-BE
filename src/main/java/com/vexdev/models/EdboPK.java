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
public class EdboPK implements Serializable {
    private int eid;
    private String isbn;

@Id
@Column(name = "eid")
public int getEid() {
    return eid;
}

    public void setEid(int eid) {
        this.eid = eid;
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

        EdboPK edboPK = (EdboPK) o;

        if (eid != edboPK.eid) return false;
        if (isbn != null ? !isbn.equals(edboPK.isbn) : edboPK.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
}}
