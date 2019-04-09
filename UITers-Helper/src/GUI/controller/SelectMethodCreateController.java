/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class SelectMethodCreateController implements Initializable {

    Stage window;
    String form;
    String state = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(btn_select_advanced::requestFocus);
        setKeyEvent();
        String text = "Xin chào, 17520433"; 
        init_cbb_user(text);
    }

    
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_select_advanced;

    @FXML
    private JFXButton btn_select_day_off;

    @FXML
    private JFXButton btn_select_random;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    void btn_backClick(ActionEvent event) {
        
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
         switch (state) {
            case "select_advanced":
                form = "../view/SelectAdvanced.fxml";
                madeFadeOut(event);
                break;
            case "select_day_off":
                form = "../view/SelectDayOf.fxml";
                madeFadeOut(event);
                break;
            case "select_random":
                form = "../view/SelectAdvanced.fxml";
                madeFadeOut(event);
                break;
            default:
                stack_pane.setDisable(false);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Thông báo"));
                content.setBody(new Text("Chọn 1 trong 3 phương thức"));
                JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
                JFXButton btn = new JFXButton("OK");
                btn.setOnAction(e -> {

                    dialog.close();
                   

                });
                content.setActions(btn);
                dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
                dialog.show();
                break;
        }

    }

    @FXML
    void btn_select_advancedClick(ActionEvent event) {
        state = "select_advanced";
        form = "../view/SelectAdvanced.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_day_offClick(ActionEvent event) {
        state = "select_day_off";
        form = "../view/SelectDayOf.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_randomClick(ActionEvent event) {
        state = "select_random";
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }
    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {                
                case ESCAPE:
                    btn_minimize.fire();
                    break;
                case LEFT:
                    btn_back.fire();
                    break;

                default:
                    break;
            }
        });
        btn_select_advanced.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_advanced.fire();
                    break;
               
            }
        });
        btn_select_day_off.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_day_off.fire();
                    break;
               
            }
        });
        btn_select_random.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_random.fire();
                    break;
               
            }
        });
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
    
    public void init_cbb_user(String text) {
        ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
        cbb_user.setPromptText(text);
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            switch (cbb_user.getValue()) {
                case "Thời khóa biểu":
                    form = "../view/Home.fxml";
                    madeFadeOut(e);
                    break;
                case "Cài đặt":
                    form = "../view/Setting.fxml";
                    madeFadeOut(e);
                    break;
                case "Đăng xuất":
                    form = "../view/Login.fxml";
                    madeFadeOut(e);
                    break;
                default:
                    break;
            }

        });
    }

}
