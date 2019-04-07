/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LocationController implements Initializable {

    LocalDate date;
    LocalTime time;

      @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_ok;

    @FXML
    private JFXComboBox<String> cbb_building;

    @FXML
    private JFXComboBox<String> cbb_room;

    @FXML
    private JFXTextField txt_other;


    @FXML
    void btn_okClick(ActionEvent event) { 
       
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("A", "B", "C");
        cbb_building.getItems().clear();
      
        cbb_building.setItems(list);

        cbb_building.setOnAction(e -> {
           
        });
    }

}
