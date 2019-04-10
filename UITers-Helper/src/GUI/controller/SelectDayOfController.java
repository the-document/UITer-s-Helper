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
    String style2 = "-fx-border-color : transparent";

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_t3_123;

    @FXML
    private JFXButton btn_t3_45;

    @FXML
    private JFXButton btn_t2_678;

    @FXML
    private JFXButton btn_t2_910;

    @FXML
    private JFXButton btn_t2_123;

    @FXML
    private JFXButton btn_t2_45;

    @FXML
    private JFXButton btn_t3_678;

    @FXML
    private JFXButton btn_t3_910;

    @FXML
    private JFXButton btn_t4_123;

    @FXML
    private JFXButton btn_t4_45;

    @FXML
    private JFXButton btn_t4_678;

    @FXML
    private JFXButton btn_t4_910;

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
    private JFXButton btn_t6_123;

    @FXML
    private JFXButton btn_t6_45;

    @FXML
    private JFXButton btn_t7_123;

    @FXML
    private JFXButton btn_t7_45;

    @FXML
    private JFXButton btn_t7_678;

    @FXML
    private JFXButton btn_t7_910;

    @FXML
    private Label lb_t2;

    @FXML
    private Label lb_t3;

    @FXML
    private Label lb_t4;

    @FXML
    private Label lb_t5;

    @FXML
    private Label lb_t6;

    @FXML
    private Label lb_t7;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
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
    void btn_nextClick(ActionEvent event) {
        form = "../view/CreateTimetableNow.fxml";
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
        String text = "Xin chào, 17520433";
        init_cbb_user(text);
        init_label();
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

    }

    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/SelectDayOf.fxml");
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

    public void initButtonClick(JFXButton btn) {

        btn.setOnAction(e -> {
            if (btn.getStyle().compareTo(style) == 0) {
                btn.setStyle(style2);

            } else {

                btn.setStyle(style);

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
