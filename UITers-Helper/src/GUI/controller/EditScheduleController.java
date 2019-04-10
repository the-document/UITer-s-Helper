/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditScheduleController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_ok;

    @FXML
    private JFXButton btn_time;

    @FXML
    private JFXButton btn_loca;

    @FXML
    private JFXTextField txt_decription;

    @FXML
    void btn_locaClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Location.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 220, 230);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_timeClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Time.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 220, 200);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_okClick(ActionEvent evnet) {
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setKeyEvent();
    }

    public void setKeyEvent() {

        txt_decription.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_ok.fire();
                    break;
            }
        });
    }
}
