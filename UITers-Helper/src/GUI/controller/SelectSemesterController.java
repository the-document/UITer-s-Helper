package GUI.controller;

// <editor-fold desc="import zone">
import GUI.StaticFunctions;
import BLL.Global;
import BLL.HocKyBLL;
import DTO.HocKy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class SelectSemesterController implements Initializable {

    // <editor-fold desc="Static variables zone">
    String form;
    Stage window;

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXListView<Label> lv_semester;

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

    // </editor-fold>
    //<editor-fold desc="FXML functions zone">
    @FXML
    void btn_homeClick(ActionEvent event) {
        StaticFunctions.stack_link.push("SelectSemester");
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
        StaticFunctions.stack_link.push("SelectSemester");
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
            madeFadeOut(event);
        } else {

            StaticFunctions.IsDarkMode = false;
        }
        form = "SelectSemester";
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

        StaticFunctions.stack_link.push("SelectSemester");
        form = "SelectSubject";
        madeFadeOut(event);
    }

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        form = "SelectSemester";
         lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
        try {
            init_lv_semester();
        } catch (SQLException ex) {
            Logger.getLogger(SelectSemesterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String name = "Xin chào, " + Global.webCM.getUserName();
        init_cbb_user(name);
        btn_next.setVisible(false);
       
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_next.fire();
                    break;
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
        btn_next.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_next.fire();
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

    public void init_lv_semester() throws SQLException {
        HocKyBLL hkbll = new HocKyBLL();
        List<HocKy> lsHocKy = hkbll.GetAllHocKy();

        for (HocKy hocKy : lsHocKy) {
            Label lb = new Label(hocKy.getTenHK());
            lv_semester.getItems().addAll(lb);
        }

        lv_semester.setOnMouseClicked(e -> {
            String id = lv_semester.getSelectionModel().getSelectedItem().getText();
            int _id = lv_semester.getSelectionModel().getSelectedIndex();
            System.out.println("In selectSemeterController: id semeter = " + _id);

            Global.HOCKY = lsHocKy.get(_id).getMaHK();
            btn_next.setVisible(true);
        });
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
