/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXChipView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
public class SubscribedController implements Initializable {
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXCheckBox cb_daa;

    @FXML
    private JFXCheckBox cb_gm;

    @FXML
    private JFXCheckBox cb_courses;

    @FXML
    private TextField txt_keyword;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXChipView<String> cv_keyword;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_courses.setSelected(true);
        cb_daa.setSelected(true);
        cb_gm.setSelected(true);
        initChipvView();

    }
    public void initChipvView()
    {
        String list_item[] = new String[100]; // Danh sách các item để add vô chipview
        String hint_item[] = new String[100]; // Danh sách các item được hint trong quá trình gõ
        cv_keyword.getChips().addAll("WEF", "WWW", "JD");
        cv_keyword.getSuggestions().addAll("HELLO", "TROLL", "WFEWEF", "WEF");
        
    }
    @FXML
    void btn_addClick(ActionEvent event) {
        String key = txt_keyword.getText();
        txt_keyword.clear();
        cv_keyword.getChips().add(key);
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
       
    }

    @FXML
    void cb_coursesClick(ActionEvent event) {
        boolean status = cb_courses.isSelected();
        
    }

    @FXML
    void cb_daaClick(ActionEvent event) {
        boolean status = cb_daa.isSelected();
        
    }

    @FXML
    void cb_gmClick(ActionEvent event) {
         boolean status = cb_gm.isSelected();
        
    }

}
