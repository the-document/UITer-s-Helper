package GUI.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BLL.Global;
import GUI.Main_1;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.hslf.record.RecordTypes;


public class WelcomeController implements Initializable {
    Stage window;
    String form;
    @FXML
    private AnchorPane AnchorPaneMain;
    @FXML
    private JFXButton btn_minimize;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private JFXCheckBox cb_remember;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Global.AnimationShow(AnchorPaneMain);
    }
    ///////// Khối lệnh cho 1 event show cửa sổ ///////////////
    public void madeFadeOut(ActionEvent event) 
    {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished( e -> {
            try {
                LoadNextScene(event);
            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void LoadNextScene(ActionEvent event) throws Exception 
    {
        form = "../view/Login.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(form));      
        Scene tableViewScene = new Scene(root);     
        window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }
    public void btn_launchClick (ActionEvent event) throws Exception
    {
        madeFadeOut(event);    
        Global.stack_link.push("../view/Login.fxml");
    }
     ///////// Khối lệnh cho 1 event show cửa sổ ///////////////
    
    public void btn_exitClick (ActionEvent event) throws Exception
    {
       Global.ExitEvent(AnchorPaneMain);
       
    }
    public void btn_minimizeClick (ActionEvent event) throws Exception
    {
       Global.MinimizeEvent(event, AnchorPaneMain);
    }
    public void btn_exitMouseMove (ActionEvent event) throws Exception
    {
    
    }
    public void btn_minimizeMouseMove (ActionEvent event) throws Exception
    {
        
    }
          
}
