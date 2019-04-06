/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SelectDayOfController implements Initializable {

    Stage window;
    String form;
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_t3_123;

    @FXML
    private JFXButton btn_t3_45;

    @FXML
    private JFXButton btn_t2_678;

    @FXML
    private JFXButton btn_t2_910;

    @FXML
    private JFXButton btn_t2_123;

    @FXML
    private JFXButton btn_t2_45;

    @FXML
    private JFXButton btn_t3_678;

    @FXML
    private JFXButton btn_t3_910;

    @FXML
    private JFXButton btn_t4_123;

    @FXML
    private JFXButton btn_t4_45;

    @FXML
    private JFXButton btn_t4_678;

    @FXML
    private JFXButton btn_t4_910;

    @FXML
    private JFXButton btn_t5_123;

    @FXML
    private JFXButton btn_t5_45;

    @FXML
    private JFXButton btn_t5_678;

    @FXML
    private JFXButton btn_t5_910;

    @FXML
    private JFXButton btn_t6_678;

    @FXML
    private JFXButton btn_t6_910;

    @FXML
    private JFXButton btn_t6_123;

    @FXML
    private JFXButton btn_t6_45;

    @FXML
    private JFXButton btn_t7_123;

    @FXML
    private JFXButton btn_t7_45;

    @FXML
    private JFXButton btn_t7_678;

    @FXML
    private JFXButton btn_t7_910;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initArrButton();
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

    @FXML
    void btn_backClick(ActionEvent event) {
        form = "../view/SelectMethodCreate.fxml";
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
        form = "../view/Home.fxml";
        madeFadeOut(event);
    }

    public void initButtonClick(JFXButton btn) {
        String style = "-fx-border-color : white";
        String style2 = "-fx-border-color : transparent";

        btn.setOnAction(e -> {
            if (btn.getStyle().compareTo(style) == 0) {
                btn.setStyle(style2);

            } else {

                btn.setStyle(style);

            }

        });

    }

    public void initArrButton() {
        initButtonClick(btn_t2_123);
        initButtonClick(btn_t2_45);
        initButtonClick(btn_t2_678);
        initButtonClick(btn_t2_910);
        initButtonClick(btn_t3_123);
        initButtonClick(btn_t3_45);
        initButtonClick(btn_t3_678);
        initButtonClick(btn_t3_910);
        initButtonClick(btn_t4_123);
        initButtonClick(btn_t4_45);
        initButtonClick(btn_t4_678);
        initButtonClick(btn_t4_910);
        initButtonClick(btn_t5_123);
        initButtonClick(btn_t5_45);
        initButtonClick(btn_t5_678);
        initButtonClick(btn_t5_910);
        initButtonClick(btn_t6_123);
        initButtonClick(btn_t6_45);
        initButtonClick(btn_t6_678);
        initButtonClick(btn_t6_910);
        initButtonClick(btn_t7_123);
        initButtonClick(btn_t7_45);
        initButtonClick(btn_t7_678);
        initButtonClick(btn_t7_910);
    }
    

}
