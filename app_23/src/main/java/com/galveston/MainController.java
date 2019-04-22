package com.galveston;

import com.galveston.controller.AdminController;
import com.galveston.controller.EventController;
import com.galveston.controller.PrizeController;
import com.galveston.controller.FamilyController;
import com.galveston.dao.Login;
import com.galveston.entities.*;
import com.galveston.error.GenericException;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.repository.FamilyRepo;
import com.galveston.security.SessionHolder;
import com.galveston.util.Alert;
import com.galveston.util.Checker;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;


public class MainController extends Checker{

    @FXML TextField fName;
    @FXML TextField mName;
    @FXML TextField lName;
    @FXML TextField user;
    @FXML TextField pass;
    @FXML TextField cPass;
    @FXML TextField address;
    @FXML TextField city;
    @FXML TextField state;
    @FXML TextField zip;
    @FXML TextField phone;
    @FXML TextField email;
    @FXML Button create;
    @FXML Label createOutPut;
    @FXML Label successOutPut;
    @FXML TextField loginUser;
    @FXML TextField loginPass;
    @FXML Label loginLabel;
    @FXML Tab eventTab;
    @FXML Tab rewardTab;
    @FXML Button logout;
    @FXML Label status;
    @FXML TabPane mainTab;

    @FXML TableView<Event> evTableView;
    @FXML TableColumn<Event, Long> evTableId;
    @FXML TableColumn<Event, String> evTableName;
    @FXML TableColumn evAction;

    @FXML Tab adminTab;
    @FXML TableView<Request> adminTable;
    @FXML TableColumn<Request,String> adminFamily;
    @FXML TableColumn<Request,String> adminRequest;
    @FXML TableColumn adminAction;

    @FXML Label drawLabel;
    @FXML TextField drawCode;

    @FXML TableView<Prize> prizeTable;
    @FXML TableColumn<Prize,String> prizeName;
    @FXML TableColumn prizeAction;


    @FXML
    public void createUser(){
        createOutPut.setText(null);successOutPut.setText(null);
        FamilyController familyController = new FamilyController();
        String response = familyController.createUser(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
        if(!response.equals("You are all set."))createOutPut.setText(response);
        else{
            setNull(fName,mName,lName,user,pass,cPass,address,city,state,zip,phone,email);
            successOutPut.setText(response);
        }
    }

    @FXML
    public void login() throws GenericException {
        if(SessionHolder.getSession().session)loginLabel.setText("Please First logout!");
        else {
            Login login = new Login();
            if (!login.userAuthentication(loginUser.getText(), loginPass.getText()))
                loginLabel.setText("Invalid Family or Password!");
            else {
                Alert.infoAlert("SignIn","Welcome "+whoIsIt(SessionHolder.getSession().userId).getFirstName()+", "+ whoIsIt(SessionHolder.getSession().userId).getLastName());
                setNull(loginPass,loginUser);

                loginLabel.setText(null);createOutPut.setText(null);
                logout.setDisable(false);

                if(SessionHolder.getSession().role.equals("admin")){
                    status.setText(nameAndPoint(SessionHolder.getSession().userId));
                    unlockTab(adminTab);
                    AdminController.adminController(adminTable,adminFamily,adminRequest,adminAction);
                    adminTab.getTabPane().getSelectionModel().selectLast();

                }else {
                    status.setText(nameAndPoint(SessionHolder.getSession().userId));
                    unlockTab(rewardTab, eventTab);
                    eventTab.getTabPane().getSelectionModel().selectNext();
                    EventController.eventViewController(evTableView, evTableId,evTableName, evAction);
                    PrizeController.prizeViewController(prizeTable,prizeName,prizeAction);
                    if(RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId).getPoints()<0){
                        Alert.infoAlert("WoW","You Won!");
                    }
                }
            }
        }
    }


    @FXML
    public void getDraw() throws GenericException {
        String code  = drawCode.getText().toLowerCase();
        boolean find = false;
        for(Draw s : RunTimeObjectHolder.getInstance().draws)
            if(code.equals(s.getDrawCode().toLowerCase())) find = true;
            if(find==true){

                RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId).setPoints(
                        RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId).getPoints()+1
                );
                List<Family> families = new ArrayList<>();
                RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId).setDraw(new Draw(code));
                RunTimeObjectHolder.getInstance().families.values().forEach(f->families.add(f));
                Alert.infoAlert("Correct","Nice Job!");
                drawLabel.setText("Go For Prize!");
                status.setText(nameAndPoint(SessionHolder.getSession().userId));
                FamilyRepo.updateDb(families);
            }else{
                Alert.warningAlert("Oops","Wrong Code!");
            }
    }

    @FXML
    public void logout(){
        loginLabel.setText(null);createOutPut.setText(null);successOutPut.setText(null);
        if(isOnline()){
            kicOut();
            lockTab(rewardTab,eventTab,adminTab);
            eventTab.getTabPane().getSelectionModel().selectFirst();
            logout.setDisable(true);
            status.setText(null);
        }

    }

    @FXML
    public void closeWindow(){
        System.exit(0);
    }


}
