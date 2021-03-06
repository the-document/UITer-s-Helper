package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Global;
import BLL.MakeSchelude;
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

public class SelectMethodCreateController implements Initializable {

    // <editor-fold desc="Static variables zone">
    Stage window;
    String form;
    String state = null;

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
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

    // </editor-fold>
    // <editor-fold desc="FXML function zone">
    @FXML
    void btn_homeClick(ActionEvent event) {
        StaticFunctions.stack_link.push("SelectMethodCreate");
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
        StaticFunctions.stack_link.push("SelectMethodCreate");
        form = "Setting";
        madeFadeOut(event);
    }

    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {

            StaticFunctions.IsDarkMode = true;
        } else {

            StaticFunctions.IsDarkMode = false;

        }
        form = "SelectMethodCreate";
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
    void btn_select_advancedClick(ActionEvent event) {
        Global.MeThodCreateSchedule = Global.MeThodCreate.ADVANCE;
        //load data from database in new thread.
        MakeSchelude schedule = new MakeSchelude();
        schedule.start();
        state = "select_advanced";
        StaticFunctions.stack_link.push("SelectMethodCreate");
        form = "SelectAdvanced";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_day_offClick(ActionEvent event) {
        Global.MeThodCreateSchedule = Global.MeThodCreate.DAYOF;
        state = "select_day_off";
        StaticFunctions.stack_link.push("SelectMethodCreate");
        form = "SelectDayOff";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_randomClick(ActionEvent event) {
        Global.MeThodCreateSchedule = Global.MeThodCreate.RANDOM;
        state = "select_random";
        StaticFunctions.stack_link.push("SelectMethodCreate");
        form = "Loader";
        madeFadeOut(event);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(btn_select_advanced::requestFocus);
        form = "SelectMethodCreate";
        lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
        setKeyEvent();
         String name = "Xin chào, " + Global.webCM.getUserName();
        init_cbb_user(name);
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

                default:
                    break;
            }
        });
        btn_select_advanced.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_advanced.fire();
                    break;
                case TAB:
                    btn_select_day_off.fire();
                    break;
                default:
                    break;

            }
        });
        btn_select_day_off.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_day_off.fire();
                    break;
                case TAB:
                    btn_select_random.fire();
                    break;
                default:
                    break;
            }
        });
        btn_select_random.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_random.fire();
                    break;
                case TAB:
                    btn_select_advanced.fire();
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
