package GUI.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BLL.Global;
import GUI.Main_1;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.hslf.record.RecordTypes;


public class WelcomeController implements Initializable {
    Stage window;
    @FXML
    private AnchorPane AnchorPaneMain;
    private JFXButton btn_minimize;
    private JFXButton btn_exit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void btn_launchClick (ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));      
        Scene tableViewScene = new Scene(root);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        window.show();
        Global.SetStageDrag(root, window, event);
    }
    public void btn_exitClick (ActionEvent event) throws Exception
    {
        System.exit(0);
    }
    public void btn_minimizeClick (ActionEvent event) throws Exception
    {
        Stage stage = (Stage)AnchorPaneMain.getScene().getWindow();
        stage = (Stage)((JFXButton) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    public void btn_exitMouseMove (ActionEvent event) throws Exception
    {
    
    }
    public void btn_minimizeMouseMove (ActionEvent event) throws Exception
    {
        
    }
          
}
