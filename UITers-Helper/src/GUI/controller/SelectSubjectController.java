/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SelectSubjectController implements Initializable {

    String form;
    Stage window;

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXListView<Label> lv_subject;

    @FXML
    private JFXTextField txt_subject;

    @FXML
    private JFXComboBox<?> cbb_subject;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXChipView<String> cv_subject;

    @FXML
    void btn_addClick(ActionEvent event) {
        String key = txt_subject.getText();
        txt_subject.clear();
        String list_item[] = new String[100]; // Danh sách các item để add vô chipview
        String hint_item[] = new String[100]; // Danh sách các item được hint trong quá trình gõ
        cv_subject.getChips().add(key);
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);

    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_nextClick(ActionEvent event) {
        form = "../view/SelectMethodCreate.fxml";
        madeFadeOut(event);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        Global.AnimationMouseDragButton(btn_exit, "red");
        lv_subjectClick();
    }
    public void lv_subjectClick() {
        // Sự kiện thêm item
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("HIT00" + i);
            lv_subject.getItems().addAll(lb);
        }
         lv_subject.setOnMouseClicked(e -> {
            
        });
    }

}
