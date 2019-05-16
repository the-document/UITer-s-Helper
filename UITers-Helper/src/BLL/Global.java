/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.LopHoc;
import DTO.MonHoc;
import GUI.controller.WelcomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nguyen Hong Phuc
 */
enum EducationProgram {
    CQUI,
    CTTT,
    CLC,
    CNTN,
    KSTN
}


public class Global {

    private static double xOffSet = 0;
    private static double yOffSet = 0;
    
    public static WebCommunicate webCM;
    
    public static String IEDriverPath = "D:\\UIT Stuffs\\UIT Stuffs\\Documents\\Tai_Lieu\\Mon_hoc\\HK4\\Java\\Library\\WebDriver\\IEDriverServer.exe";
    public static String FirefoxDriverPath = "D:\\UIT Stuffs\\UIT Stuffs\\Documents\\Tai_Lieu\\Mon_hoc\\HK4\\Java\\Library\\WebDriver\\geckodriver.exe";

    //==========METHOD SCHEDULE===================
    public static enum MeThodCreate{
        RANDOM,
        DAYOF,
        ADVANCE
    }
    
    //==========USER INFOR===================
    public static  String MSSV="18520951";
    public static  String NGANHHOC="";
    public static  int HOCKY=-1;
    //public static List<MonHoc> lsMonHocSelected=new ArrayList<>();
    public static HashMap<String,MonHoc> lsMonHocSelected=new HashMap<>();
    public static MeThodCreate MeThodCreateSchedule;
    
    public static List<LopHoc> lsDayOff=new ArrayList();
    //======================================
    
    public static Stack<String> stack_link;
    
    private String URL_form;

    public Global(String URL_form) {
        this.URL_form = URL_form;
    }

    static public void SetStageDrag(Parent root, Stage window, ActionEvent event) {
        root.setOnMousePressed(e -> {
            xOffSet = e.getSceneX();
            yOffSet = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() - xOffSet);
            window.setY(e.getScreenY() - yOffSet);
            window.setOpacity(1.0f);
        });
        root.setOnMouseReleased(e -> {
            window.setOpacity(1.0f);
        });

        window.initStyle(StageStyle.UNDECORATED);
        window.setResizable(false);
    }

    static public void AnimationShow(AnchorPane AnchorPaneMain) {

        AnchorPaneMain.setOpacity(0);
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(0);
        fade_trands.setToValue(1);
        fade_trands.play();
    }

    static public void ExitEvent(AnchorPane AnchorPaneMain) {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(800));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished(e -> {
            try {
                System.exit(0);

            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    static public void MinimizeEvent(ActionEvent event, AnchorPane AnchorPaneMain) {
        @SuppressWarnings("UnusedAssignment")
        Stage stage = (Stage) AnchorPaneMain.getScene().getWindow();
        stage = (Stage) ((JFXButton) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void madeFadeOut(ActionEvent event, AnchorPane AnchorPaneMain) {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished(e -> {
            try {
                Stage window;
                Parent root = FXMLLoader.load(getClass().getResource("../view/" + this.URL_form));
                Scene tableViewScene = new Scene(root);
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                Global.SetStageDrag(root, window, event);
                window.show();
            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    static public void AnimationMouseDragButton(JFXButton btn, String color) {
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color : " + color);
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-color : transparent");
        });
    }

    static public void AnimationMouseSelectedButton(JFXButton btn, String color) {

        btn.setStyle("-fx-background-color : " + color);

    }

    static public void AnimationMouseDropButton(JFXButton btn1, JFXButton btn2) {

        btn1.setStyle("-fx-background-color : transparent");
        btn2.setStyle("-fx-background-color : transparent");

    }

    static public JFXDialog getDialogChange(StackPane stack_pane, String heading, String body, JFXButton btn) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        return dialog;
    }
    static public JFXDialog getDialog(StackPane stack_pane, String heading, String body) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("OK");
            btn.setOnAction(e -> {
                dialog.close();
               
            });
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        return dialog;
    }
  
}
