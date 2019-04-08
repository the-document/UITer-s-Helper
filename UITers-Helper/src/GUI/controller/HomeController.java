package GUI.controller;
// <editor-fold desc="import zone">

import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
// </editor-fold>

public class HomeController implements Initializable {

    // <editor-fold desc="import zone">
    String form;
    Stage window;
    int isExpandChung = 1;
    int isExpandDeadline = 1;
    int isExpandDangKy = 1;

    ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
    // </editor-fold>

    // <editor-fold desc="FXML variables zone">
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
    private JFXButton btn_expandDeadline;

    @FXML
    private JFXListView<Label> lv_deadline;

    @FXML
    private JFXButton btn_expandChung;

    @FXML
    private JFXListView<Label> lv_news;

    @FXML
    private JFXButton btn_expandDangKy;

    @FXML
    private JFXListView<Label> lv_dangky;

    @FXML
    private StackPane stack_pane;

    // </editor-fold>
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        //cbb_user.fireEvent(null);
        lv_locationClick();
        lv_newsClick();
        Global.AnimationMouseDragButton(btn_exit, "red");
        stack_pane.setDisable(true);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            // Xử lý sự kiện tại đây
            lv_dangky.getItems().addAll(lb);
        }
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            // Xử lý sự kiện tại đây
            lv_deadline.getItems().addAll(lb);
        }

    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    //btn_login.fire();
                    break;
                case ESCAPE:
                    btn_minimize.fire();
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

        Parent root = FXMLLoader.load(getClass().getResource(form));
        Scene tableViewScene = new Scene(root);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }

    @FXML
    void btn_expandChungClick(ActionEvent event) {
        if (isExpandChung == 1) {
            lv_news.setVisible(false);
            lv_news.setMaxHeight(0f);
            lv_news.setMinHeight(0f);
            isExpandChung = 0;
        } else {
            lv_news.setVisible(true);
            lv_news.setMinHeight(300 / (1 + (isExpandDangKy) + (isExpandDeadline)));
            lv_news.setMaxHeight(300 / (1 + (isExpandDangKy) + (isExpandDeadline)));
            isExpandChung = 1;
            if (isExpandDangKy == 1) {
                lv_dangky.setMinHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_dangky.setMaxHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandDeadline == 1) {
                lv_deadline.setMinHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_deadline.setMaxHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
            }

        }

    }

    @FXML
    void btn_expandDangKyClick(ActionEvent event) {
        if (isExpandDangKy == 1) {
            lv_dangky.setVisible(false);
            lv_dangky.setMaxHeight(0f);
            lv_dangky.setMinHeight(0f);
            isExpandDangKy = 0;
        } else {
            lv_dangky.setVisible(true);
            lv_dangky.setMinHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
            lv_dangky.setMaxHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
            isExpandDangKy = 1;
            if (isExpandChung == 1) {
                lv_news.setMinHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_news.setMaxHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandDeadline == 1) {
                lv_deadline.setMinHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_deadline.setMaxHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
            }

        }
    }

    @FXML
    void btn_expandDeadlineClick(ActionEvent event) {
        if (isExpandDeadline == 1) {
            lv_deadline.setVisible(false);
            lv_deadline.setMinHeight(0f);
            lv_deadline.setMaxHeight(0f);
            isExpandDeadline = 0;
        } else {
            lv_deadline.setVisible(true);
            lv_deadline.setMinHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
            lv_deadline.setMaxHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
            isExpandDeadline = 1;
            if (isExpandDangKy == 1) {
                lv_dangky.setMinHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_dangky.setMaxHeight(300 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandChung == 1) {
                lv_news.setMinHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_news.setMaxHeight(300 / (1 + (isExpandDangKy) + (isExpandChung)));
            }

        }
    }

    public void lv_locationClick() {
        // Sự kiện thêm item
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            lv_location.getItems().addAll(lb);
        }
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

    @FXML
    void btn_backClick(ActionEvent event) {

    }

    @FXML
    void btn_createClick(ActionEvent event) {
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);

    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {

        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_nofiClick(ActionEvent event) {

    }

    @FXML
    void btn_showdateClick(ActionEvent event) {

    }

    @FXML
    void cbb_userClick(ActionEvent event) {
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        //cbb_user.getItems().addAll(list);
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            btn_create.setText(cbb_user.getValue());

        });
    }

}
