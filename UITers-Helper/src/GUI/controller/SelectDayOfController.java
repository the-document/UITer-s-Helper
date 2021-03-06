package GUI.controller;
// <editor-fold desc="import zone">

import BLL.Global;
import DTO.LopHoc;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

//</editor-fold>
public class SelectDayOfController implements Initializable {

    // <editor-fold desc="Static variables zone">
    Stage window;
    String form;
    String style = "-fx-border-color : white";
    String style3 = "-fx-border-color : black";

    String style2 = "-fx-border-color : transparent";

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_home;

    @FXML
    private JFXButton lbl_path;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private JFXButton btn_setting;

    @FXML
    private JFXButton btn_notification;

    @FXML
    private JFXToggleButton toggle_mode;

    @FXML
    private Label lb_t3;

    @FXML
    private JFXButton btn_t3_123;

    @FXML
    private JFXButton btn_t3_45;

    @FXML
    private JFXButton btn_t2_678;

    @FXML
    private JFXButton btn_t2_910;

    @FXML
    private Label lb_t2;

    @FXML
    private JFXButton btn_t2_123;

    @FXML
    private JFXButton btn_t2_45;

    @FXML
    private JFXButton btn_t3_678;

    @FXML
    private JFXButton btn_t3_910;

    @FXML
    private Label lb_t4;

    @FXML
    private JFXButton btn_t4_123;

    @FXML
    private JFXButton btn_t4_45;

    @FXML
    private JFXButton btn_t4_678;

    @FXML
    private JFXButton btn_t4_910;

    @FXML
    private Label lb_t5;

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
    private Label lb_t6;

    @FXML
    private JFXButton btn_t6_123;

    @FXML
    private JFXButton btn_t6_45;

    @FXML
    private Label lb_t7;

    @FXML
    private JFXButton btn_t7_123;

    @FXML
    private JFXButton btn_t7_45;

    @FXML
    private JFXButton btn_t7_678;

    @FXML
    private JFXButton btn_t7_910;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_homeClick(ActionEvent event) {
        StaticFunctions.stack_link.push("SelectDayOff");
        form = "Home";
        madeFadeOut(event);
    }

    @FXML
    void lbl_pathClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
    }
    
    @FXML
    void btn_notification_Click(ActionEvent event) {

    }

    @FXML
    void btn_settingClick(ActionEvent event) {
        StaticFunctions.stack_link.push("SelectDayOff");
        form = "Setting";
        madeFadeOut(event);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {
            StaticFunctions.IsDarkMode = true;
        } else {
            StaticFunctions.IsDarkMode = false;
        }
        form = "SelectDayOff";
        madeFadeOut(event);
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        StaticFunctions.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        StaticFunctions.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_nextClick(ActionEvent event) {
        StaticFunctions.stack_link.push("SelectDayOff");
        form = "Loader";
        madeFadeOut(event);
    }

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);

        initArrButton();
        setKeyEvent();
         String name = "Xin chào, " + Global.webCM.getUserName();
        init_cbb_user(name);
        init_label();
        form = "SelectDayOff";
        lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
        Global.lsDayOff.clear();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_minimize.fire();
                    break;
                case LEFT:
                    btn_home.fire();
                    break;
                case ENTER:
                    btn_next.fire();
                    break;
                default:
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
        Parent root = FXMLLoader.load(getClass().getResource(StaticFunctions.switcher.Switch(form)));
        Scene tableViewScene = new Scene(root);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        StaticFunctions.SetStageDrag(root, window, event);
        window.show();

    }

    public void initButtonClick(JFXButton btn) {
        String fullname = btn.getId();
        String thu = fullname.split("_")[1].substring(1, 2);
        String tiet = fullname.split("_")[2];

        btn.setOnAction(e -> {
            if (btn.getStyle().compareTo(style) == 0) {
                btn.setStyle(style2);
                LopHoc l = new LopHoc("DAY-OFF", "DAY-OFF", "DAY-OFF", "1", "1", tiet, thu, "DAY-OFF", "DAY-OFF", "DAY-OFF");
                for (LopHoc lopHoc : Global.lsDayOff) {
                    if (lopHoc.getThu().equals(thu) && lopHoc.getTiet().equals(tiet)) {
                        Global.lsDayOff.remove(lopHoc);
                        break;

                    }
                }

                System.out.println("remove -" + thu + "-" + tiet + " -size: " + Global.lsDayOff.size());

            } else {
                if (StaticFunctions.IsDarkMode == true) {
                    btn.setStyle(style);

                } else {
                    btn.setStyle(style3);

                }
                LopHoc l = new LopHoc("DAY-OFF", "DAY-OFF", "DAY-OFF", "1", "1", tiet, thu, "DAY-OFF", "DAY-OFF", "DAY-OFF");
                Global.lsDayOff.add(l);

                System.out.println("add -" + thu + "-" + tiet + " -size: " + Global.lsDayOff.size());

            }

        });

    }

    public void init_label() {
        lb_t2.setOnMouseClicked(e -> {
            btn_t2_123.setStyle(style2);
            btn_t2_45.setStyle(style2);
            btn_t2_678.setStyle(style2);
            btn_t2_910.setStyle(style2);
        });

        lb_t3.setOnMouseClicked(e -> {
            btn_t3_123.setStyle(style2);
            btn_t3_45.setStyle(style2);
            btn_t3_678.setStyle(style2);
            btn_t3_910.setStyle(style2);
        });
        lb_t4.setOnMouseClicked(e -> {
            btn_t4_123.setStyle(style2);
            btn_t4_45.setStyle(style2);
            btn_t4_678.setStyle(style2);
            btn_t4_910.setStyle(style2);
        });
        lb_t5.setOnMouseClicked(e -> {
            btn_t5_123.setStyle(style2);
            btn_t5_45.setStyle(style2);
            btn_t5_678.setStyle(style2);
            btn_t5_910.setStyle(style2);
        });
        lb_t6.setOnMouseClicked(e -> {
            btn_t6_123.setStyle(style2);
            btn_t6_45.setStyle(style2);
            btn_t6_678.setStyle(style2);
            btn_t6_910.setStyle(style2);
        });
        lb_t7.setOnMouseClicked(e -> {
            btn_t7_123.setStyle(style2);
            btn_t7_45.setStyle(style2);
            btn_t7_678.setStyle(style2);
            btn_t7_910.setStyle(style2);
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

    public void init_cbb_user(String text) {
            ObservableList<String> list = FXCollections.observableArrayList("Trang chủ", "Thời khóa biểu", "Cài đặt", "Đăng xuất");
        cbb_user.setPromptText(text);
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            StaticFunctions.stack_link.push("CreateTimetableNow");
            switch (cbb_user.getValue()) {
                case "Trang chủ":
                    form = "Home";
                    madeFadeOut(e);
                    break;
                case "Thời khóa biểu":
                    form = "CreateTimetableNow";
                    madeFadeOut(e);
                    break;
                case "Cài đặt":
                    form = "Setting";
                    madeFadeOut(e);
                    break;
                case "Đăng xuất":
                    form = "Login";
                    madeFadeOut(e);
                    break;
                default:
                    break;
            }

        });
    }

}
