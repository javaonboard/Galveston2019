package com.galveston.security;

import com.galveston.entities.Family;
import com.galveston.error.GenericException;
import com.galveston.objectFactory.RunTimeObjectHolder;
import javafx.scene.control.Tab;

public class DetectiveImpl implements Detective {


    @Override
    public boolean isOnline() {

        return SessionHolder.getSession().session;
    }

    @Override
    public String whatRole() {
        return SessionHolder.getSession().role;
    }

    @Override
    public Family whoIsIt(Long id) {
        return RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId);
    }

    @Override
    public String nameAndPoint(Long id) throws GenericException {
        try {
            if(SessionHolder.getSession().role.equals("admin")){
                return "Hi Admin!";
            }
            Family user = RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId);
            return "Hi " + user.getFirstName() + ", YouGot " + user.getPoints() + "pts";
        }catch (Exception e){
            throw new GenericException("Something went wrong please contact administration.");
        }

    }

    @Override
    public void lockTab(Tab...tabs) {
        for(Tab t : tabs)t.setDisable(true);
    }

    @Override
    public void unlockTab(Tab...tabs) {
        for(Tab t : tabs)t.setDisable(false);
    }
}
