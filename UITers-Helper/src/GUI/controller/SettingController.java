/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.InputStream;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private JFXButton btn_select_images;
    @FXML
    private JFXButton btn_select_ringtin;
    @FXML
    private JFXSlider sl_volume;
    @FXML
    private JFXToggleButton tg_language;
    @FXML
    private JFXToggleButton tg_start_with_os;
    @FXML
    private JFXToggleButton tg_turn_nofi;
    @FXML
    private ImageView img_background;
    @FXML
    private Label lb_name_picture;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        Global.AnimationMouseDragButton(btn_exit, "red");
        tg_languageClick();
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

    public void btn_createClick(ActionEvent event) throws Exception {
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);

    }

    public void btn_backClick(ActionEvent event) throws Exception {
        form = "../view/Home.fxml";
        madeFadeOut(event);

    }

    public void btn_showdateClick(ActionEvent event) throws Exception {

    }

    public void btn_exitClick(ActionEvent event) throws Exception {
        Global.ExitEvent(AnchorPaneMain);

    }

    public void btn_minimizeClick(ActionEvent event) throws Exception {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    public void btn_select_imagesClick(ActionEvent event) throws Exception {
        //// open file doalog
        /// truyền vào link cho đối tượng file
        //lb_name_picture.setText;
    }

    public void tg_languageClick() {
        tg_language.setOnMouseClicked(e -> {
            if (tg_language.isSelected()) {
                //tg_language.setText("true");
            } else {
                //tg_language.setText("false");
            }
        });

    }
    public void tg_start_with_osClick() {
        tg_start_with_os.setOnMouseClicked(e -> {
            if (tg_start_with_os.isSelected()) {
                //tg_language.setText("true");
            } else {
                //tg_language.setText("false");
            }
        });

    }
     public void tg_turn_nofiClick() {
        tg_turn_nofi.setOnMouseClicked(e -> {
            if (tg_turn_nofi.isSelected()) {
                //tg_language.setText("true");
            } else {
                //tg_language.setText("false");
            }
        });

    }
    public void btn_select_ringtonClick(ActionEvent event) throws Exception {
        //// open file doalog
        /// truyền vào link cho đối tượng file
        //lb_name_picture.setText;
    }
    public void sl_volumeClick(MouseEvent event) throws Exception{
       
        //tg_language.setText(String.valueOf(sl_volume.getValue()));
    }

}
