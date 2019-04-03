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
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HomeController implements Initializable {

    String form;
    Stage window;
    ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
    @FXML
    private JFXComboBox<String> cbb_user;
    @FXML
    private AnchorPane AnchorPaneMain;
    @FXML
    private JFXButton btn_minimize;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private JFXCheckBox cb_remember;
    @FXML
    private JFXButton btn_create;
    @FXML
    private JFXListView<Label> lv_location;
    @FXML
    private JFXListView<Label> lv_news;
    @FXML
    private StackPane stack_pane;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        cb_userClick();
        lv_locationClick();
        lv_newsClick();
        Global.AnimationMouseDragButton(btn_exit, "red");
        stack_pane.setDisable(true);
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

    public void cbb_userClick(ActionEvent event) {

    }

    public void btn_createClick(ActionEvent event) throws Exception {
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);

    }

    public void btn_backClick(ActionEvent event) throws Exception {
        form = "../view/Login.fxml";
        madeFadeOut(event);

    }

    public void btn_exitClick(ActionEvent event) throws Exception {
        Global.ExitEvent(AnchorPaneMain);
    }

    public void btn_minimizeClick(ActionEvent event) throws Exception {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    public void btn_showdateClick(ActionEvent event) throws Exception {

    }

    public void cb_userClick() {
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        //cbb_user.getItems().addAll(list);
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            btn_create.setText(cbb_user.getValue());

        });
    }

    public void lv_locationClick() {
        // Sự kiện thêm item
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            lv_location.getItems().addAll(lb);
        }
    }

    public void Dialog(Label arg_1) {
        stack_pane.setDisable(false);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thông báo"));
        content.setBody(new Text(arg_1.getText()));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        HBox hb = new HBox();
        JFXButton btn_ok = new JFXButton("OK");
        JFXButton btn_save = new JFXButton("Lưu");
        hb.getChildren().addAll(btn_ok, btn_save);
        btn_ok.setOnAction(e -> {

            dialog.close();

        });
        content.setActions(btn_ok);
        dialog.setOnDialogClosed(e -> {
            stack_pane.setDisable(true);

        });

        dialog.show();
    }

    public void lv_newsClick() {
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            // Xử lý sự kiện tại đây
            lv_news.getItems().addAll(lb);
        }
        lv_news.setOnMouseClicked(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/Subscribed.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 400, 500);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        });
    }
}
