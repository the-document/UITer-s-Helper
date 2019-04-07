/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TimeController implements Initializable {

    LocalDate date;
    LocalTime time;

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXDatePicker pk_date;

    @FXML
    private JFXTimePicker pk_time;

    @FXML
    private JFXButton btn_ok;

    @FXML
    void btn_okClick(ActionEvent event) { 
        date = pk_date.getValue();
        time = pk_time.getValue();
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();
                
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

}
