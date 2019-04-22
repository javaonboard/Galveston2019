package com.galveston.controller;

import com.galveston.entities.Prize;
import com.galveston.entities.Request;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.security.SessionHolder;
import com.galveston.util.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrizeController {

    public static void prizeViewController(TableView<Prize> prizeTable, TableColumn<Prize,String> prizeName, TableColumn prizeAction)
    {

        prizeName.setCellValueFactory(new PropertyValueFactory<>("name"));

        prizeAction.setCellFactory(ActionButton.forTableColumn("Request",(Prize e)-> {
            RunTimeObjectHolder.getInstance().requests.add(new Request(RunTimeObjectHolder.getInstance().families.get(SessionHolder.getSession().userId).getLastName(),e.getName()));
            //RunTimeObjectHolder.getInstance().requests.stream().forEach(System.out::println);
            Alert.infoAlert("Request","your request has been submitted after admin approval you will get the result! ");
            return e;
        }));
        ObservableList<Prize> prizes = FXCollections.observableArrayList(RunTimeObjectHolder.getInstance().prizes);
        prizeTable.setItems(prizes);


    }

}
