package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Global;
import BLL.WebCommunicate;
import BLL.WebDriverMode;
import Exception.CannotBrowseCourseException;
import Exception.CannotLoginException;
import GUI.PopUp_Notification;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
// </editor-fold>

public class LoginController implements Initializable {

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
    private JFXButton btn_home;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_setting2;

    @FXML
    private JFXToggleButton toggle_mode;

    @FXML
    private Button btn_setting1;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private Button btn_setting11;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXCheckBox cb_remember;

    @FXML
    private JFXButton btn_login;

    @FXML
    private Hyperlink btn_lose;

    @FXML
    private JFXButton btn_more;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_exitClick(ActionEvent event) {
        StaticFunctions.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_homeClick(ActionEvent event) {

    }

    @FXML
    void btn_moreClick(ActionEvent event) {

    }

    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {
            StaticFunctions.IsDarkMode = true;
        } else {
            StaticFunctions.IsDarkMode = false;

        }
        form = "Login";
        madeFadeOut(event);
    }

    @FXML
    void btn_loginClick(ActionEvent event) {
        BLL.Global.username = txt_user.getText();
        BLL.Global.password = txt_password.getText();
        
        stack_pane.setDisable(false);

        Global.webCM = new WebCommunicate(WebDriverMode.HtmlUnitDriver,BLL.Global.username, BLL.Global.password);
        
        boolean loginSuccess = false;
        
        try {
            Global.webCM.ExecuteLogin();
            loginSuccess = true;
        } catch (CannotBrowseCourseException ex) {
            System.out.println("Cannot go to courses. Check your internet connection.");
        } catch (CannotLoginException ex) {
            System.out.println("Cannot login. Check username and password.");
        }
        
        if (loginSuccess) {

            JFXButton btn = new JFXButton("OK");
            btn.setOnAction(e -> {                
                System.out.println(StaticFunctions.IsDarkMode);
                form = "Home";
                madeFadeOut(event);

            });

            //JFXDialog dialog = StaticFunctions.getDialogStatic(stack_pane, "Thông báo", "Đăng nhập thành công", btn);
            //dialog.show();
            PopUp_Notification.run("Thông báo", "Đăng nhập thành công", "SUCCESS");
            System.out.println(StaticFunctions.IsDarkMode);
            form = "Home";
            madeFadeOut(event);

        } else {

            //JFXDialog dialog = StaticFunctions.getDialog(stack_pane, "Thông báo", "Đăng nhập thất bại");
            // dialog.show();
            PopUp_Notification.run("Thông báo", "Đăng nhập thất bại", "FAIL");

        }
    }

    @FXML
    void btn_loseClick(ActionEvent event) {
        // quên mật khẩu
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        StaticFunctions.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_settingClick(ActionEvent event) {
        StaticFunctions.stack_link.push("Login");
        form = "Setting";
        madeFadeOut(event);
    }

    @FXML
    void cb_rememberCheck(ActionEvent event) {
        // cần code sự kiện quên mật khẩu

        if (cb_remember.isSelected()) {
            cb_remember.setText("    Đã ghi nhớ");
        } else {
            cb_remember.setText("    Nhớ mật khẩu");
        }
    }

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_minimize.fire();
                    break;
                default:
                    break;
            }
        });
        txt_user.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case TAB:
                    txt_password.focusedProperty();
                    break;
                case ENTER:
                    txt_password.focusedProperty();
                default:
                    break;
            }
        });

        txt_password.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_login.fire();
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
}
