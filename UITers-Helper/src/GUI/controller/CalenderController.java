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
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.Size;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CalenderController implements Initializable {

    String form;
    Stage window;
    ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_time;

    @FXML
    private JFXButton btn_loca;

    @FXML
    private JFXTextField txt_decription;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_previous_month;

    @FXML
    private JFXButton btn_next_month;

    @FXML
    private JFXButton btn_day00;

    @FXML
    private JFXButton btn_day10;

    @FXML
    private JFXButton btn_day20;

    @FXML
    private JFXButton btn_day30;

    @FXML
    private JFXButton btn_day40;

    @FXML
    private JFXButton btn_day50;

    @FXML
    private JFXButton btn_day01;

    @FXML
    private JFXButton btn_day11;

    @FXML
    private JFXButton btn_day21;

    @FXML
    private JFXButton btn_day31;

    @FXML
    private JFXButton btn_day41;

    @FXML
    private JFXButton btn_day51;

    @FXML
    private JFXButton btn_day02;

    @FXML
    private JFXButton btn_day12;

    @FXML
    private JFXButton btn_day22;

    @FXML
    private JFXButton btn_day32;

    @FXML
    private JFXButton btn_day42;

    @FXML
    private JFXButton btn_day52;

    @FXML
    private JFXButton btn_day03;

    @FXML
    private JFXButton btn_day13;

    @FXML
    private JFXButton btn_day23;

    @FXML
    private JFXButton btn_day33;

    @FXML
    private JFXButton btn_day43;

    @FXML
    private JFXButton btn_day53;

    @FXML
    private JFXButton btn_day04;

    @FXML
    private JFXButton btn_day14;

    @FXML
    private JFXButton btn_day24;

    @FXML
    private JFXButton btn_day34;

    @FXML
    private JFXButton btn_day44;

    @FXML
    private JFXButton btn_day54;

    @FXML
    private JFXButton btn_day05;

    @FXML
    private JFXButton btn_day15;

    @FXML
    private JFXButton btn_day25;

    @FXML
    private JFXButton btn_day35;

    @FXML
    private JFXButton btn_day45;

    @FXML
    private JFXButton btn_day55;

    @FXML
    private JFXButton btn_day06;

    @FXML
    private JFXButton btn_day16;

    @FXML
    private JFXButton btn_day26;

    @FXML
    private JFXButton btn_day36;

    @FXML
    private JFXButton btn_day46;

    @FXML
    private JFXButton btn_day56;

    @FXML
    private JFXListView<Label> lv_location;

    @FXML
    private JFXListView<Label> lv_time;

    @FXML
    private JFXListView<Label> lv_decription;

    @FXML
    private ScrollPane pane_lichtrinh;

    @FXML
    private ContextMenu cm_menu;
    
    @FXML
    void btn_backClick(ActionEvent event) {
        form = "../view/Login.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_exitClick(ActionEvent event) {

        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void btn_timeClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Time.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 200, 220);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_locaClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Location.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 220, 230);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        lv_locationClick();
        Global.AnimationMouseDragButton(btn_exit, "red");
        stack_pane.setDisable(true);
       
        
        // context menu
        MenuItem lb_add = new MenuItem();
        lb_add.setText("Thêm");
        MenuItem lb_del = new MenuItem();
        lb_del.setText("Xóa");
        cm_menu.getItems().addAll(lb_add, lb_del);
        
        btn_add.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY)
                {
                    cm_menu.show(btn_add, event.getX(), event.getY());
                }
            }
        
        
        });
        btn_add.setContextMenu(cm_menu);
        
        
        
        
        
    }

    @FXML
    void btn_addClick(ActionEvent event) {
        
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

    public void init_pane_lichtrinh(Schedule arrSchedule[]) {
        // Xóa hết nội dung cũ
        pane_lichtrinh.setContent(null);
        VBox root = new VBox();
        for (int i = 0; i < arrSchedule.length; i++) {
            Label lb_time = new Label(arrSchedule[i].getTime());
            lb_time.setStyle("-fx-font-color : white;");
            lb_time.setMaxSize(80, 40);
            lb_time.setMinSize(80, 40);

            Label lb_location = new Label(arrSchedule[i].getLocation());
            lb_location.setStyle("-fx-font-color : white;");
            lb_location.setMaxSize(80, 40);
            lb_location.setMinSize(80, 40);

            Label lb_description = new Label(arrSchedule[i].getDescription());
            lb_location.setStyle("-fx-font-color : white;");
            lb_location.setMaxSize(200, 80);
            lb_location.setMinSize(200, 80);

            HBox hb_parent = new HBox();
            hb_parent.getChildren().addAll(lb_time, new Line(0, 0, 0, 40), lb_location);

            VBox vb_parent = new VBox();
            vb_parent.getChildren().addAll(hb_parent, lb_description);
            root.getChildren().add(vb_parent);

        }
        pane_lichtrinh.setContent(root);
    }

}
