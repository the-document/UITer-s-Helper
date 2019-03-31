/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane AnchorPaneMain;
    private JFXButton btn_minimize;
    private JFXButton btn_exit;
    private JFXCheckBox cb_remember;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void btn_loginClick (ActionEvent event) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();     

        window.setScene(tableViewScene);
        window.show();
        Global.SetStageDrag(root, window, event);
    }
    public void btn_backClick (ActionEvent event) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Welcome.fxml"));
        Scene tableViewScene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    
    public void btn_settingClick (ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Setting.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();    
        Global.SetStageDrag(root, window, event);     
    }
    public void btn_exitMouseMove (ActionEvent event) throws Exception
    {
    
    }
    public void btn_minimizeMouseMove (ActionEvent event) throws Exception
    {
        
    }
    /////////////////////////////
    ////// code here ////////////
    ////////////////////////////
    
    // Sự kiện check vào ô nhớ đăng nhập
    public void cb_rememberCheck (ActionEvent event) throws Exception
    {
        
        
    }
    
    // Sự kiện quên mật khẩu
    public void btn_loseClick (ActionEvent event) throws Exception
    {
        
        
    }
    
    
    /////////////////////////////
    ////// code here ////////////
    ////////////////////////////
}
