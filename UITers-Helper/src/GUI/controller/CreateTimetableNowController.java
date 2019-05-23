package GUI.controller;
// <editor-fold desc="import zone">

import BLL.Global;
import BLL.ThuatToanTaoTKB;
import DTO.LopHoc;
import DTO.MonHoc;
import DTO.TimeTable;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.swing.text.StyleConstants.Background;

//</editor-fold>
public class CreateTimetableNowController implements Initializable {

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

    @FXML
    private Label lb_SubjectNotFound;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_homeClick(ActionEvent event) {
        StaticFunctions.stack_link.push("CreateTimetableNow");
        form = "Home";
        madeFadeOut(event);
    }

    @FXML
    void btn_notification_Click(ActionEvent event) {

    }

    @FXML
    void btn_settingClick(ActionEvent event) {
          StaticFunctions.stack_link.push("CreateTimetableNow");
        form = "Setting";
        madeFadeOut(event);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void lbl_pathClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
    }
    
    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {
       
            StaticFunctions.IsDarkMode = true;
          
        } else {
            
            StaticFunctions.IsDarkMode = false;
          
        }
        form = "CreateTimetableNow";
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

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        String text = "Xin chào, 17520433";
        init_cbb_user(text);
        form = "CreateTimeTableNow";
        lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
        //process data to show---------------------------------------
        lb_SubjectNotFound.setText("");
        //MaKeSchedule();
        HideTextOfButton();
        LoadSchedule();
        //-----------------------------------------------------------
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

    //==========================================================================
    private void HideTextOfButton() {
        btn_t2_123.setText("");
        btn_t2_45.setText("");
        btn_t3_123.setText("");
        btn_t3_45.setText("");
        btn_t4_123.setText("");
        btn_t4_45.setText("");
        btn_t5_123.setText("");
        btn_t5_45.setText("");
        btn_t6_123.setText("");
        btn_t6_45.setText("");
        btn_t7_123.setText("");
        btn_t7_45.setText("");

        btn_t2_678.setText("");
        btn_t2_910.setText("");
        btn_t3_678.setText("");
        btn_t3_910.setText("");
        btn_t4_678.setText("");
        btn_t4_910.setText("");
        btn_t5_678.setText("");
        btn_t5_910.setText("");
        btn_t6_678.setText("");
        btn_t6_910.setText("");
        btn_t7_678.setText("");
        btn_t7_910.setText("");
    }

    private void SetTextForButtonThu2(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t2_123.setText(textShow);

                break;

            case "12345":
                btn_t2_123.setText(textShow);
                btn_t2_123.setMaxHeight(170);
                btn_t2_45.setVisible(false);
                break;

            case "1234":
                btn_t2_123.setText(textShow);
                btn_t2_123.setMaxHeight(136);
                break;

            case "45":
                btn_t2_45.setText(textShow);
                break;

            case "678":
                btn_t2_678.setText(textShow);
                break;

            case "67890":
                btn_t2_678.setText(textShow);
                btn_t2_678.setMaxHeight(170);
                btn_t2_910.setVisible(false);
                break;

            case "6789":
                btn_t2_678.setText(textShow);
                btn_t2_678.setMaxHeight(136);
                break;

            case "90":
                btn_t2_910.setText(textShow);
                break;
        }
    }

    private void SetTextForButtonThu3(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t3_123.setText(textShow);
                break;

            case "12345":
                btn_t3_123.setText(textShow);
                btn_t3_123.setMaxHeight(170);
                btn_t3_45.setVisible(false);
                break;

            case "1234":
                btn_t3_123.setText(textShow);
                btn_t3_123.setMaxHeight(136);
                break;

            case "45":
                btn_t3_45.setText(textShow);
                break;

            case "678":
                btn_t3_678.setText(textShow);
                break;

            case "67890":
                btn_t3_678.setText(textShow);
                btn_t3_678.setMaxHeight(170);
                btn_t3_910.setVisible(false);
                break;

            case "6789":
                btn_t3_678.setText(textShow);
                btn_t3_678.setMaxHeight(136);
                break;

            case "90":
                btn_t3_910.setText(textShow);
                break;
        }
    }

    private void SetTextForButtonThu4(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t4_123.setText(textShow);
                break;

            case "12345":
                btn_t4_123.setText(textShow);
                btn_t4_123.setMaxHeight(170);
                btn_t4_45.setVisible(false);
                break;

            case "1234":
                btn_t4_123.setText(textShow);
                btn_t4_123.setMaxHeight(136);
                break;

            case "45":
                btn_t4_45.setText(textShow);
                break;

            case "678":
                btn_t4_678.setText(textShow);
                break;

            case "67890":
                btn_t4_678.setText(textShow);
                btn_t4_678.setMaxHeight(170);
                btn_t4_910.setVisible(false);
                break;

            case "6789":
                btn_t4_678.setText(textShow);
                btn_t4_678.setMaxHeight(136);
                break;

            case "90":
                btn_t4_910.setText(textShow);
                break;
        }
    }

    private void SetTextForButtonThu5(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t5_123.setText(textShow);
                break;

            case "12345":
                btn_t5_123.setText(textShow);
                btn_t5_123.setMaxHeight(170);
                btn_t2_45.setVisible(false);
                break;

            case "1234":
                btn_t5_123.setText(textShow);
                btn_t5_123.setMaxHeight(136);
                break;

            case "45":
                btn_t5_45.setText(textShow);
                break;

            case "678":
                btn_t5_678.setText(textShow);
                break;

            case "67890":
                btn_t5_678.setText(textShow);
                btn_t5_678.setMaxHeight(170);
                btn_t5_910.setVisible(false);
                break;

            case "6789":
                btn_t5_678.setText(textShow);
                btn_t5_678.setMaxHeight(136);
                break;

            case "90":
                btn_t5_910.setText(textShow);
                break;
        }
    }

    private void SetTextForButtonThu6(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t6_123.setText(textShow);
                break;

            case "12345":
                btn_t6_123.setText(textShow);
                btn_t6_123.setMaxHeight(170);
                btn_t6_45.setVisible(false);
                break;

            case "1234":
                btn_t6_123.setText(textShow);
                btn_t6_123.setMaxHeight(136);
                break;

            case "45":
                btn_t6_45.setText(textShow);
                break;

            case "678":
                btn_t6_678.setText(textShow);
                break;

            case "67890":
                btn_t6_678.setText(textShow);
                btn_t6_678.setMaxHeight(170);
                btn_t6_910.setVisible(false);
                break;

            case "6789":
                btn_t6_678.setText(textShow);
                btn_t6_678.setMaxHeight(136);
                break;

            case "90":
                btn_t6_910.setText(textShow);
                break;
        }
    }

    private void SetTextForButtonThu7(LopHoc lop, String textShow) {
        switch (lop.getTiet()) {
            case "123":
                btn_t7_123.setText(textShow);
                break;

            case "12345":
                btn_t7_123.setText(textShow);
                btn_t7_123.setMaxHeight(170);
                btn_t7_45.setVisible(false);
                break;

            case "1234":
                btn_t7_123.setText(textShow);
                btn_t7_123.setMaxHeight(136);
                break;

            case "45":
                btn_t7_45.setText(textShow);
                break;

            case "678":
                btn_t7_678.setText(textShow);
                break;

            case "67890":
                btn_t7_678.setText(textShow);
                btn_t7_678.setMaxHeight(170);
                btn_t7_910.setVisible(false);
                break;

            case "6789":
                btn_t7_678.setText(textShow);
                btn_t7_678.setMaxHeight(136);
                break;

            case "90":
                btn_t7_910.setText(textShow);
                break;
        }
    }

    private void LoadSchedule() {
        if (ThuatToanTaoTKB.listTimeTables.isEmpty()) {
            lb_SubjectNotFound.setText("Can't create, please choose other day-off");
            return;
        }

        for (LopHoc lop : ThuatToanTaoTKB.listTimeTables.get(0).getListLopHocs()) {

            String textShow = lop.getMaLop() + "\n"
                    + lop.getTenGiangVien() + "\n P. "
                    + lop.getPhong() + "\n"
                    + lop.getNgayBatDau() + "\n"
                    + lop.getNgayKetThuc();

            switch (lop.getThu()) {
                case "2":
                    SetTextForButtonThu2(lop, textShow);
                    break;
                case "3":
                    SetTextForButtonThu3(lop, textShow);
                    break;
                case "4":
                    SetTextForButtonThu4(lop, textShow);
                    break;
                case "5":
                    SetTextForButtonThu5(lop, textShow);
                    break;
                case "6":
                    SetTextForButtonThu6(lop, textShow);
                    break;
                case "7":
                    SetTextForButtonThu7(lop, textShow);
                    break;
            }
        }

        if (!ThuatToanTaoTKB.dsMaMonNotFound.isEmpty()) {
            lb_SubjectNotFound.setText("Những môn học không mở: " + ThuatToanTaoTKB.dsMaMonNotFound.toString());

        }
    }

    private void MaKeSchedule() {
        List<String> lsMaMon = new ArrayList<>();

        lsMaMon.clear();
        for (MonHoc m : Global.lsMonHocSelected.values()) {
            lsMaMon.add(m.getMaMonHoc());
            System.out.print(m.getMaMonHoc() + ", ");
        }

        ThuatToanTaoTKB.NapDanhSachMaMonHoc(lsMaMon);
        ThuatToanTaoTKB.SetHeDaoTao("CQUI");

        try {
            ThuatToanTaoTKB.init();
        } catch (SQLException ex) {
            Logger.getLogger(CreateTimetableNowController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        ThuatToanTaoTKB.Try(0);
        System.out.println("=========LIST TKB RECOMEND FOR YOU  ( " + ThuatToanTaoTKB.countCase + " ) ============\n");
        ThuatToanTaoTKB.listTimeTables.forEach((table) -> {
            table.Export();
        });

        if (!ThuatToanTaoTKB.dsMaMonNotFound.isEmpty()) {
            lb_SubjectNotFound.setText("Những môn học không mở: " + ThuatToanTaoTKB.dsMaMonNotFound.toString());
            System.out.println("Subject not open in this semeter: " + ThuatToanTaoTKB.dsMaMonNotFound.size() + " - " + ThuatToanTaoTKB.dsMaMonNotFound.toString());

        }

    }
}
