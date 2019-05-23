package GUI.controller;

// <editor-fold desc="import zone">
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

//</editor-fold>
public class SettingController implements Initializable {

    // <editor-fold desc="Static variables zone">
    String form;
    Stage window;
    int isExpandTerm = 1;
    int isExpandCachSuDung = 1;

    // </editor-fold>
    // <editor-fold desc="import zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXToggleButton tg_start_with_os;

    @FXML
    private JFXToggleButton tg_turn_nofi;

    @FXML
    private Label lb_name_song;

    @FXML
    private JFXButton btn_select_rington;

    @FXML
    private JFXSlider sl_volume;

    @FXML
    private Label lb_term;

    @FXML
    private Label txt_term;

    @FXML
    private Label lb_cachsudung;

    @FXML
    private Label txt_cachsudung;

    @FXML
    private Label lb_source;

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
    private Label lb_about;

    @FXML
    private Label txt_about;

    //</editor-fold>
    //<editor-fold desc="FXML functions zone">
    @FXML
    void btn_homeClick(ActionEvent event) {
        StaticFunctions.stack_link.push("Setting");
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
    void btn_select_ringtonClick(ActionEvent event) {

    }

    @FXML
    void btn_settingClick(ActionEvent event) {

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
        form = "Setting";
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
    void btn_select_imagesClick(ActionEvent event) {

    }

    @FXML
    void lb_cachsudungClick(MouseEvent event) {
        if (isExpandCachSuDung == 1) {
            txt_cachsudung.setMaxHeight(0f);
            txt_cachsudung.setMinHeight(0f);
            isExpandCachSuDung = 0;
        } else {
            if (isExpandTerm == 1) {
                txt_cachsudung.setMaxHeight(150);
                txt_cachsudung.setMinHeight(150);
                txt_term.setMaxHeight(150);
                txt_term.setMinHeight(150);
            } else {

                txt_cachsudung.setMaxHeight(300);
                txt_cachsudung.setMinHeight(300);
            }
            isExpandCachSuDung = 1;
        }
        txt_cachsudung.setVisible(true);
        txt_cachsudung.setText("abc");

    }

    @FXML
    void lb_termClick(MouseEvent event) {
        if (isExpandTerm == 1) {
            txt_term.setVisible(false);
            txt_term.setMaxHeight(0f);
            txt_term.setMinHeight(0f);
            isExpandTerm = 0;
        } else {
            if (isExpandCachSuDung == 1) {
                txt_term.setMaxHeight(150);
                txt_term.setMinHeight(150);
                txt_cachsudung.setMaxHeight(150);
                txt_cachsudung.setMinHeight(150);
            } else {
                txt_term.setMaxHeight(300);
                txt_term.setMinHeight(300);
            }
            txt_term.setVisible(true);
            isExpandTerm = 1;
        }
        txt_term.setText("abc");
    }

    @FXML
    void sl_volumeClick(MouseEvent event) {
        double volume = sl_volume.getValue();
    }

    @FXML
    void tg_start_with_osClick(ActionEvent event) {
        if (tg_start_with_os.isSelected()) {
            tg_start_with_os.setText("Khởi động cùng OS");
        } else {
            tg_start_with_os.setText("Không khởi động cùng OS");
        }

    }

    @FXML
    void tg_turn_nofiClick(ActionEvent event) {
        if (tg_turn_nofi.isSelected()) {
            tg_turn_nofi.setText("Thông báo");
        } else {
            tg_turn_nofi.setText("Tắt thông báo");
        }
    }

    //</editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        String name = "Xin chào, 17520433";
        init_cbb_user(name);
        form = "Setting";
         lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
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
