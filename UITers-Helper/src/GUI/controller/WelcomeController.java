package GUI.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import GUI.Main_1;
import com.jfoenix.controls.JFXButton;
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

import javafx.stage.Stage;


public class WelcomeController implements Initializable {
    Stage window;
    @FXML
    private AnchorPane AnchorPaneMain;
    private JFXButton btn_minimize;
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void btn_launchClick (ActionEvent event) throws Exception
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));      
        Scene tableViewScene = new Scene(tableViewParent);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        window.show();
     
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
        window.setIconified(true);
    }
//    public void setOnMouseClick(MouseEvent e)
//    {
//         xOffSet = e.getSceneX();
//         yOffSet = e.getSceneY();
//    }
//    public void setDragDone(DragEvent e) 
//    {
//        Main_1.primaryStage.setOpacity(1.0f);
//    }
//    public void setOnMouseReleased(MouseEvent e) 
//    {
//        Main_1.primaryStage.setOpacity(1.0f);
//    }
//    public void setOnMouseDragged(MouseEvent e) 
//    {
//        Main_1.primaryStage.setX(e.getScreenX() - xOffSet);
//        Main_1.primaryStage.setY(e.getScreenY() - yOffSet);
//        Main_1.primaryStage.setOpacity(0.8f);
//    }
          
}
