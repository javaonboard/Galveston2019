package com.galveston.entities;

import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.util.Checker;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Family extends Checker {

    private Long userId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String cPassword;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private Long points;
    private List<Draw> draws = new ArrayList<>();
    private boolean isAdmin;

    public Family(String fName, String mName, String lName, String user, String pass,
                  String cPass, String address, String city, String state, String zip, String phone, String email){

        this.userId = RunTimeObjectHolder.getInstance().getLastID()+1;
        this.firstName = fName;
        this.middleName = mName==null?"":mName;
        this.lastName = lName;
        this.userName = user;
        this.password = pass;
        this.cPassword = cPass;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
        this.phoneNumber = phone;
        this.email = email;
        this.points = 0L;
        this.draws = new ArrayList<>();
        this.isAdmin = false;
    }

    public void setDraw(Draw d){
        draws.add(d);
    }


}
