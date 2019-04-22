package com.galveston;

import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.objectFactory.RunTimeObjectLoader;
import com.galveston.services.LoaderImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * @implNote
 * @version 0.0.1
 */
public class Main extends Application implements RunTimeObjectLoader {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Adding the Address of fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application_ui.fxml"));
        Parent root = loader.load();
        //URL url = new File("src/main/resources/application_ui.fxml").toURL();
        //Parent root = FXMLLoader.load(url);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root,750,500));
        primaryStage.show();
        // Load the Object In ruTime Memory from Files
        fireUp();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @implNote
     * @param
     * @return
     * @exception
     */

    @Override
    public void fireUp() {
        RunTimeObjectHolder.getInstance();
        LoaderImpl li = new LoaderImpl();
        try {
            li.eventsFireUpTimeLoader();
            li.userFireUpTimeLoader();
            li.whoIsBoss();
            li.loadPrizes();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
