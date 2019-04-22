package com.galveston.controller;

import com.galveston.entities.Draw;
import com.galveston.entities.Event;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.util.Alert;
import com.galveston.util.DrawGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventController {

    public static void eventViewController(TableView<Event> evTableView, TableColumn<Event,Long> evTableId,
                                           TableColumn<Event, String> evTableName, TableColumn evAction)
            {

                                evTableId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
                                evTableName.setCellValueFactory(new PropertyValueFactory<>("name"));

                                            evAction.setCellFactory(ActionButton.forTableColumn("Play",(Event e)-> {
                                                String code = DrawGenerator.generateDrawCode();
                                                Draw draw = new Draw(code);
                                                RunTimeObjectHolder.getInstance().draws.add(draw);
                                                Alert.infoAlert("DrawCode Dont Lose it!", "Submit Your Draw To get Point\n"+draw.getDrawCode());
                                                return e;
                                            }));
                                            ObservableList<Event> events = FXCollections.observableArrayList(RunTimeObjectHolder.getInstance().events.values());
                                            evTableView.setItems(events);


    }


}
