/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HomeController implements Initializable {
    
    ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
    @FXML
    private JFXComboBox<String> cbb_user;
    private AnchorPane AnchorPaneMain;
    private JFXButton btn_minimize;
    private JFXButton btn_exit;
    private JFXCheckBox cb_remember;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        cbb_user.getItems().addAll(list);
        //cbb_user.getItems().clear();
    }   
     

    public void cbb_userClick(ActionEvent event){
        cbb_user.getItems().clear();
        cbb_user.getItems().addAll(list);
        //cbb_user.setPromptText(cbb_user.getValue());
    }
     public void btn_createClick (ActionEvent event) throws Exception
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/SelectSemester.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
  
  
    public void btn_backClick (ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
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
    
   
    
     public void btn_showdateClick (ActionEvent event) throws Exception
    {
    
    }
}
