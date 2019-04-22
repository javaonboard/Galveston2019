package com.galveston.controller;

import com.galveston.dao.Registration;
import com.galveston.entities.Family;
import javafx.scene.control.TextField;

public class FamilyController extends Registration {

    public String createUser(TextField fName, TextField mName, TextField lName, TextField user, TextField pass, TextField cPass, TextField address, TextField city, TextField state, TextField zip, TextField phone, TextField email) {
       try {
            Family newUser = new Family(fName.getText(), mName.getText(), lName.getText(), user.getText(), pass.getText(),cPass.getText(), address.getText(), city.getText(), state.getText(), zip.getText(), phone.getText(), email.getText());
            newUser.isValid(newUser);
            persistUserInFileAndMemory(newUser);

       }catch (Exception e){
           System.out.println(e.getMessage()+"\n"+ e.getStackTrace().toString());
           return e.getMessage();
            }
       return "You are all set.";
    }






}
