package com.vexdev.models;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 25/10/13
 * Time: 01:41
 * To change this template use File | Settings | File Templates.
 */
public class JeditablePOJO {
    private String id;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JeditablePOJO{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
