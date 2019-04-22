package com.galveston.dao;

import com.galveston.entities.Draw;
import com.galveston.entities.Family;
import com.galveston.objectFactory.RunTimeObjectHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Registration {

    public static void persistUserInFileAndMemory(Family user){
        RunTimeObjectHolder.getInstance().families.put(user.getUserId(),user);
        List<Family> users = new ArrayList<>();
        RunTimeObjectHolder.getInstance().families.values().stream().forEach(u->users.add(u));
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(Family u:users){
            Map obj1 = new LinkedHashMap();
            obj1.put("userId",u.getUserId());
            obj1.put("firstName",u.getFirstName());
            obj1.put("middleName",u.getMiddleName());
            obj1.put("lastName",u.getLastName());
            obj1.put("userName",u.getUserName());
            obj1.put("password",u.getPassword());
            obj1.put("cPassword",u.getCPassword());
            obj1.put("address",u.getAddress());
            obj1.put("city",u.getCity());
            obj1.put("state",u.getState());
            obj1.put("zipCode",u.getZipCode());
            obj1.put("phoneNumber",u.getPhoneNumber());
            obj1.put("email",u.getEmail());
            obj1.put("points",u.getPoints());
            List<Draw> draws = u.getDraws();
            JSONArray evArray = new JSONArray();
            for(Draw d:draws){
                Map evObj = new LinkedHashMap();
                evObj.put("code",d.getDrawCode());
                evArray.add(evObj);
            }
            obj1.put("draws",evArray);
            obj1.put("isAdmin",u.isAdmin());
            jsonArray.add(obj1);
        }

        obj.put("families",jsonArray);

        //System.out.println(obj.toJSONString());

        try (FileWriter file = new FileWriter("userDB.json",false)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
