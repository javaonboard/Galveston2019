package com.galveston.objectFactory;

import com.galveston.entities.*;
import com.galveston.services.LoaderImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*This Singelton Class is Factory to hold all created data as objects in Runtime Memory.*/
public class RunTimeObjectHolder extends LoaderImpl {

    private static RunTimeObjectHolder singleton_instance = null;

    public Map<Long, Family> families;
    public Map<Long, Event> events;
    public List<Draw> draws;
    public List<Prize> prizes;
    public List<Request> requests;

    private RunTimeObjectHolder(){
        families = new HashMap<>();
        events = new HashMap<>();
        draws = new ArrayList<>();
        requests = new ArrayList<>();
        prizes = new ArrayList<>();
    }

    public static RunTimeObjectHolder getInstance(){
        if(singleton_instance == null) singleton_instance = new RunTimeObjectHolder();
        return singleton_instance;
    }

    public Long getLastID(){
        List<Family> mapUsers = families.values().stream().collect(Collectors.toList());
        if(mapUsers.size()>0) {
            Family user = mapUsers.get(mapUsers.size() - 1);
            return user.getUserId();
        }
        return 1000L;
    }

}
