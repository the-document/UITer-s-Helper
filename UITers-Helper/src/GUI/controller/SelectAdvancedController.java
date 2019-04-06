/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectAdvancedController implements Initializable {
    String form;
    Stage window;
    
    
    
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXChipView<JFXButton> cv_subject;

    @FXML
    private JFXButton btn_nexxt;

    @FXML
    private Label lb_t3_123;

    @FXML
    private Label lb_t3_45;

    @FXML
    private Label lb_t2_678;

    @FXML
    private Label lb_t2_910;

    @FXML
    private Label lb_t2_123;

    @FXML
    private Label lb_t2_45;

    @FXML
    private Label lb_t3_678;

    @FXML
    private Label lb_t3_910;

    @FXML
    private Label lb_t4_123;

    @FXML
    private Label lb_t4_45;

    @FXML
    private Label lb_t4_678;

    @FXML
    private Label lb_t4_910;

    @FXML
    private Label lb_t5_123;

    @FXML
    private Label lb_t5_45;

    @FXML
    private Label lb_t5_678;

    @FXML
    private Label lb_t5_910;

    @FXML
    private Label lb_t6_123;

    @FXML
    private Label lb_t6_45;

    @FXML
    private Label lb_t6_678;

    @FXML
    private Label lb_t6_910;

    @FXML
    private Label lb_t7_123;

    @FXML
    private Label lb_t7_45;

    @FXML
    private Label lb_t7_678;

    @FXML
    private Label lb_t7_910;
    
    @FXML
    private JFXButton btn_test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        Global.AnimationMouseDragButton(btn_exit, "red");
        initChipvView();
        initbtn_test();
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

    public void btn_nexxtClick(ActionEvent event) throws IOException{
        form = "../view/SelectDayOf.fxml";
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
  
    public void initChipvView()
    {
        JFXButton btn = new JFXButton("hai");
        
        cv_subject.getChips().add(btn);
        
        btn.setOnAction( e -> {
            lb_t2_45.setStyle("-fx-background-color : BLUE");
        });
        cv_subject.setOnMousePressed(e -> {
            lb_t2_123.setStyle("-fx-background-color : RED");
        });
        cv_subject.setOnMouseReleased( e -> {
            
            lb_t2_123.setStyle("-fx-background-color : transperant");
        });
       
        
        
    }
    public void initbtn_test()
    {
        btn_test.setOnMouseReleased(e -> {
               lb_t2_123.setStyle("-fx-background-color : transperant");
                 btn_test.setVisible(false);
        });
        btn_test.setOnMousePressed(e -> {
            lb_t2_123.setStyle("-fx-background-color : RED");
           
        });
       
    }
    
}
