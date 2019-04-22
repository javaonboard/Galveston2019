package com.galveston.security;

import com.galveston.entities.Family;
import com.galveston.objectFactory.RunTimeObjectHolder;

import java.util.Map;

public class Validator extends DetectiveImpl {

    public boolean userAuthentication(final String user, final String password){

            for (Map.Entry<Long, Family> map : RunTimeObjectHolder.getInstance().families.entrySet()) {
                if (map.getValue().getUserName().equals(user) || map.getValue().getEmail().equals(user)) {

                    if (map.getValue().getPassword().equals(password)) {
                        SessionHolder.getSession().session = true;
                        SessionHolder.getSession().userId = map.getValue().getUserId();
                        SessionHolder.getSession().role = map.getValue().isAdmin()? "admin" : "volunteer";
                    }
                }
            }

        return SessionHolder.getSession().session;
    }


    public void kicOut(){
        SessionHolder.getSession().userId = null;
        SessionHolder.getSession().role = null;
        SessionHolder.getSession().session = false;
    }




}
