package com.galveston.util;

import com.galveston.dao.Login;
import com.galveston.entities.Family;
import com.galveston.error.GenericException;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;


public class Checker extends Login {


    public boolean isEmpty(String... s){
        for(String ss : s) {
            if (ss == null || ss.isBlank() || ss.isEmpty()) return true;
        }
        return false;
    }

    public void setNull(TextField... s){
        for(TextField ss:s)ss.setText(null);
    }

    public boolean lengthChecker(String s){
        if(s.length()<6)return true;
        return false;
    }

    public boolean isNumeric(String str){
        try {
            String st = str.replaceAll("\\s+","");
            double d = Double.parseDouble(st);
        } catch (NumberFormatException | NullPointerException nfe) {
            return true;
        }
        return false;

    }

    public static boolean isEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    public void isValid(Family user) throws GenericException {

        if(isEmpty(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getCPassword(), user.getAddress(), user.getCity(), user.getState(), user.getZipCode(), user.getEmail(),
                user.getPhoneNumber())) throw new GenericException("Do not leave any field blank!");

        if(!user.getPassword().equals(user.getCPassword()))throw new GenericException("Passwords were not Match!");

        if(lengthChecker(user.getPassword()) || lengthChecker(user.getCPassword())) throw new GenericException("Password length must be more than 6 character!");

        if(isNumeric(user.getZipCode()) || isNumeric(user.getPhoneNumber())) throw new GenericException("Zip Code or Phone Number is wrong!");

        if(!isEmail(user.getEmail()))throw new GenericException("Email is Wrong!");
    }


}
