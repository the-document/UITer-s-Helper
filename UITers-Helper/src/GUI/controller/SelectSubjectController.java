package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Global;
import GUI.StaticFunctions;
import BLL.MonHocBLL;
import DTO.MonHoc;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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

// </editor-fold>
public class SelectSubjectController implements Initializable {

    // <editor-fold desc="Static variables zone">
    String form;
    Stage window;

    // </editor-fold>
    
    // <editor-fold desc="FXML variabls zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXListView<Label> lv_subject;

    @FXML
    private JFXTextField txt_subject;

    @FXML
    private JFXComboBox<String> cbb_subject;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXChipView<String> cv_subject;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;
    
    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private StackPane stack_pane;

    // </editor-fold>
    
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_addClick(ActionEvent event) {
        String key = txt_subject.getText();
        txt_subject.clear();
        cv_subject.getChips().add(key);
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
    }
    
    @FXML
    void btn_deleteClick(ActionEvent event) {
        cv_subject.getChips().clear();
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
        form = "../view/SelectMethodCreate.fxml";
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
        Platform.runLater(AnchorPaneMain::requestFocus);

        String text = "Xin chào, 17520433";
        init_cbb_user(text);
        init_cbb_subjecT();
        init_lv_subject();
        setKeyEvent();

    }
    
    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/SelectSubject.fxml");
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
        btn_next.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_next.fire();
                    break;

            }
        });
        txt_subject.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_add.fire();
                    break;

            }
        });
    }

    public void init_lv_subject() {
        try
        {
            MonHocBLL mhbll = new MonHocBLL();
            List<MonHoc> lsMonHocs;
            lsMonHocs = mhbll.GetAllLopHoc(Global.MSSV, Global.NGANHHOC, Global.HOCKY);
            if(lsMonHocs!=null)
            for (MonHoc m : lsMonHocs) {
                Label MonHocLabel = new Label(m.getMaMonHoc() + " - " + m.getTenMonHoc());
                lv_subject.getItems().addAll(MonHocLabel);
            }
            else
            {
                Label MonHocLabel = new Label("No connect");
                lv_subject.getItems().addAll(MonHocLabel);      
            }
        }
        catch (SQLException e)
        {
            Label MonHocLabel = new Label("No connect");
            lv_subject.getItems().addAll(MonHocLabel);         
        }
       
        lv_subject.setOnMouseClicked(e -> {
            String id = lv_subject.getSelectionModel().getSelectedItem().getText();
            cv_subject.getChips().add(id);
            switch (id) {
                
                
            }
        });
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

    public void init_cbb_subjecT() {
        ObservableList<String> list = FXCollections.observableArrayList("CNPM", "KTMT", "KHMT");
        cbb_subject.setPromptText("Chọn khoa");
        cbb_subject.getSelectionModel().select(1);
        cbb_subject.getItems().clear();
        cbb_subject.setItems(list);

        cbb_subject.setOnAction(e -> {
            switch (cbb_subject.getValue()) {
                case "CNPM":
                    break;
                case "KTMT":

                    break;
                case "KHMT":

                    break;
                default:
                    break;
            }

        });
    

    }

}
