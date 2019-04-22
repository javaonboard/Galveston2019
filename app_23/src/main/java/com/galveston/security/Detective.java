package com.galveston.security;

import com.galveston.entities.Family;
import com.galveston.error.GenericException;
import javafx.scene.control.Tab;

public interface Detective {

    boolean isOnline();
    String whatRole();
    Family whoIsIt(Long id);
    String nameAndPoint(Long id) throws GenericException;
    void lockTab(Tab... tabs);
    void unlockTab(Tab... tabs);
}
