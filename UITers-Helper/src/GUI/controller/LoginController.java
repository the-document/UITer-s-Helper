/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {
    String form;
    Stage window;
    @FXML
    private AnchorPane AnchorPaneMain;
    @FXML
    private JFXButton btn_minimize;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private JFXCheckBox cb_remember;
    @FXML
    private StackPane stack_pane;
    @FXML
    private JFXTextField txt_user;
    @FXML
    private JFXTextField txt_password;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
    }    
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
        Parent root = FXMLLoader.load(getClass().getResource(form));      
        Scene tableViewScene = new Scene(root);     
        window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }
    
   
  
    public void btn_exitClick (ActionEvent event) throws Exception
    {
        Global.ExitEvent(AnchorPaneMain);
    }
    public void btn_minimizeClick (ActionEvent event) throws Exception
    {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }
    
    
    public void btn_settingClick (ActionEvent event) throws Exception
    {
        form = "../view/Setting.fxml";
        madeFadeOut(event);
        Global.stack_link.push("../view/Setting.fxml");
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
        if (cb_remember.isSelected())
        {
            cb_remember.setText("Đã ghi nhớ");
        }
        else
        {
            cb_remember.setText("Nhớ mật khẩu");
        }
    }
    
    // Sự kiện quên mật khẩu
    public void btn_loseClick (ActionEvent event) throws Exception
    {

    }
    
    public void btn_loginClick (ActionEvent event) throws Exception
    {   
        String user_name = txt_user.getText();
        String password = txt_password.getText();
        /////////////////////////////
        ///// Xử lý here ////////////
        /////////////////////////////
        
        stack_pane.setDisable(false);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thông báo"));
        content.setBody(new Text("Đăng nhập thành công"));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("OK");
        btn.setOnAction(e -> {
           
            dialog.close();
            Parent root = null;
            form = "../view/Home.fxml";
            madeFadeOut(event);
            Global.stack_link.push("../view/Home.fxml");
           
        });
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        dialog.show();
        
    }
    
    /////////////////////////////
    ////// code here ////////////
    ////////////////////////////
}
