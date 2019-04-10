package GUI.controller;

// <editor-fold desc="import zone">
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXComboBox<String> cbb_user;

    // </editor-fold>
    
    // <editor-fold desc="FXML function zone">
    @FXML
    void btn_backClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
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
        state = "select_advanced";
        form = "../view/SelectAdvanced.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_day_offClick(ActionEvent event) {
        state = "select_day_off";
        form = "../view/SelectDayOf.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_select_randomClick(ActionEvent event) {
        state = "select_random";
        form = "../view/CreateTimetableNow.fxml";
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

        setKeyEvent();
        String text = "Xin chào, 17520433";
        init_cbb_user(text);
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_minimize.fire();
                    break;
                case LEFT:
                    btn_back.fire();
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

            }
        });
        btn_select_day_off.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_day_off.fire();
                    break;

            }
        });
        btn_select_random.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_select_random.fire();
                    break;

            }
        });
    }

    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/SelectMethodCreate.fxml");
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
            switch (cbb_user.getValue()) {
                case "Trang chủ":
                    form = "../view/Home.fxml";
                    madeFadeOut(e);
                    break;
                case "Thời khóa biểu":
                    form = "../view/CreateTimetableNow.fxml";
                    madeFadeOut(e);
                    break;
                case "Cài đặt":
                    form = "../view/Setting.fxml";
                    madeFadeOut(e);
                    break;
                case "Đăng xuất":
                    form = "../view/Login.fxml";
                    madeFadeOut(e);
                    break;
                default:
                    break;
            }

        });
    }

}
