package com.vexdev.models;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 25/10/13
 * Time: 01:43
 * To change this template use File | Settings | File Templates.
 */
public class JresultPOJO {
    private Boolean done;

    public static JresultPOJO success() {
        JresultPOJO pojo = new JresultPOJO();
        pojo.setDone(true);
        return pojo;
    }

    public static JresultPOJO failure() {
        JresultPOJO pojo = new JresultPOJO();
        pojo.setDone(false);
        return pojo;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
