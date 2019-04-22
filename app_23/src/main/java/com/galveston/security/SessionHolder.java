package com.galveston.security;

public class SessionHolder {

    private static SessionHolder getInstance = null;
    public boolean session;
    public Long userId;
    public String role;

    private SessionHolder(){
        this.session = false;
        this.userId = null;
        this.role = null;
    }

    public static SessionHolder getSession(){
        if(getInstance==null) getInstance = new SessionHolder();
        return getInstance;
    }


}
