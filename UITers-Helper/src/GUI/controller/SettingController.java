/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SettingController implements Initializable {
    String form;
    Stage window;
    @FXML
    private AnchorPane AnchorPaneMain;
    @FXML
    private JFXButton btn_minimize;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private StackPane stack_pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        Global.AnimationMouseDragButton(btn_exit, "red");
    }    
    public void madeFadeOut(ActionEvent event) {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished(e -> {
            try {
                LoadNextScene(event);
            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void LoadNextScene(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(form));
        Scene tableViewScene = new Scene(root);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }
    public void btn_createClick (ActionEvent event) throws Exception
    {
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);
        
    }
    public void btn_backClick (ActionEvent event) throws Exception
    {
        form = "../view/Home.fxml";
        madeFadeOut(event);
        
    }
    public void btn_showdateClick (ActionEvent event) throws Exception
    {
    
    }
     public void btn_exitClick (ActionEvent event) throws Exception
    {
       Global.ExitEvent(AnchorPaneMain);
       
    }
    public void btn_minimizeClick (ActionEvent event) throws Exception
    {
       Global.MinimizeEvent(event, AnchorPaneMain);
    }
}
