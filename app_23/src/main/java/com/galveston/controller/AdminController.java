package com.galveston.controller;

import com.galveston.entities.Draw;
import com.galveston.entities.Family;
import com.galveston.entities.Request;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.util.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdminController {

    public static void adminController(TableView<Request> adminTable, TableColumn<Request, String> adminFamily, TableColumn<Request, String> adminRequest, TableColumn adminAction) {

        adminFamily.setCellValueFactory(new PropertyValueFactory<>("family"));
        adminRequest.setCellValueFactory(new PropertyValueFactory<>("request"));

        adminAction.setCellFactory(ActionButton.forTableColumn("Approve",(Request v)-> {
            List<String> codes = new ArrayList<>();
            RunTimeObjectHolder.getInstance().families.values().stream().forEach(f->
                    f.getDraws().stream().forEach(d->codes.add(d.getDrawCode().toLowerCase()))
            );
            RunTimeObjectHolder.getInstance().draws.stream().forEach(d->codes.add(d.getDrawCode()));
            Random rand = new Random();
            int randomCode = rand.nextInt(codes.size());
            for(Map.Entry<Long, Family> map : RunTimeObjectHolder.getInstance().families.entrySet()){
                if(map.getValue().getLastName().equals(v.getFamily())){
                    boolean flag = true;
                    for(Draw d:map.getValue().getDraws()) {
                        if (codes.get(randomCode).equals(d.getDrawCode())){
                            flag = false;
                            Alert.infoAlert("Winner","We Got Winner Here!");
                            map.getValue().setPoints(-1L);
                        }
                    }
                    if(flag)Alert.infoAlert("Looser","Bad Luck!");
                }
            }

            return v;
        }));
        ObservableList<Request> requests = FXCollections.observableArrayList(RunTimeObjectHolder.getInstance().requests);
        adminTable.setItems(requests);
    }


}
