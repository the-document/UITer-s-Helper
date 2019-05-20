package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Advertise;
import BLL.Course;
import BLL.Deadline;
import BLL.WebCommunicate;
import BLL.Global;
import BLL.WebDriverMode;
import DTO.Calender;
import Exception.NotLoggedInException;
import GUI.Calendar;
import GUI.NgayThang;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
// </editor-fold>

public class HomeController implements Initializable {

    // <editor-fold desc="Static varriables zone">
    String form;
    Stage window;
    int isExpandChung = 1;
    int isExpandDeadline = 1;
    int isExpandDangKy = 1;
    int currentMonth = java.time.LocalDate.now().getMonthValue();
    int currentYear = java.time.LocalDate.now().getYear();

    //Danh sách các courses hiện có của người dùng.
    ArrayList<Course> curCourses;

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_home;

    @FXML
    private Label lbl_path;

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
    private Label lbl_date;

    @FXML
    private Label lbl_day;

  

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_forward;

    @FXML
    private HBox panel_calender;

    @FXML
    private JFXButton btn_day00;

    @FXML
    private JFXButton btn_day10;

    @FXML
    private JFXButton btn_day20;

    @FXML
    private JFXButton btn_day30;

    @FXML
    private JFXButton btn_day40;

    @FXML
    private JFXButton btn_day50;

    @FXML
    private JFXButton btn_day01;

    @FXML
    private JFXButton btn_day11;

    @FXML
    private JFXButton btn_day21;

    @FXML
    private JFXButton btn_day31;

    @FXML
    private JFXButton btn_day41;

    @FXML
    private JFXButton btn_day51;

    @FXML
    private JFXButton btn_day02;

    @FXML
    private JFXButton btn_day12;

    @FXML
    private JFXButton btn_day22;

    @FXML
    private JFXButton btn_day32;

    @FXML
    private JFXButton btn_day42;

    @FXML
    private JFXButton btn_day52;

    @FXML
    private JFXButton btn_day03;

    @FXML
    private JFXButton btn_day13;

    @FXML
    private JFXButton btn_day23;

    @FXML
    private JFXButton btn_day33;

    @FXML
    private JFXButton btn_day43;

    @FXML
    private JFXButton btn_day53;

    @FXML
    private JFXButton btn_day04;

    @FXML
    private JFXButton btn_day14;

    @FXML
    private JFXButton btn_day24;

    @FXML
    private JFXButton btn_day34;

    @FXML
    private JFXButton btn_day44;

    @FXML
    private JFXButton btn_day54;

    @FXML
    private JFXButton btn_day05;

    @FXML
    private JFXButton btn_day15;

    @FXML
    private JFXButton btn_day25;

    @FXML
    private JFXButton btn_day35;

    @FXML
    private JFXButton btn_day45;

    @FXML
    private JFXButton btn_day55;

    @FXML
    private JFXButton btn_day06;

    @FXML
    private JFXButton btn_day16;

    @FXML
    private JFXButton btn_day26;

    @FXML
    private JFXButton btn_day36;

    @FXML
    private JFXButton btn_day46;

    @FXML
    private JFXButton btn_day56;

    @FXML
    private VBox panel_lichtrinh;

    @FXML
    private Label lbl_allday;

    @FXML
    private JFXListView<Label> lv_holiday;

    @FXML
    private JFXListView<Label> lv_lichtrinh;

    @FXML
    private Label lbl_thongbao;

    @FXML
    private JFXButton btn_rest;

    @FXML
    private JFXButton btn_deadline;

    @FXML
    private JFXButton btn_new;

    @FXML
    private JFXListView<Label> lv_new;

    @FXML
    private Label lbl_congcu;

    @FXML
    private Pane btn_create;

    @FXML
    private Button btn_create2;

    @FXML
    private JFXButton btn_addLichtrinh;

    @FXML
    private JFXToggleButton toggle_mode;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_newClick(ActionEvent event) {
        init_lv_deadline();
    }

    @FXML
    void btn_deadlineClick(ActionEvent event) {
        init_lv_deadline();
    }

    @FXML
    void btn_restClick(ActionEvent event) {
        init_lv_deadline();
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        currentMonth--;
        if (currentMonth == 0) {
            currentYear--;
            currentMonth = 12;
        }
        initCalendar(1, currentMonth, currentYear);
    }

    @FXML
    void btn_createClick(MouseEvent event) {
        System.err.println("a");
        btn_create2.fire();
    }

    @FXML
    void btn_createFire(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/Home.fxml");
        form = "../view/SelectSemester.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        StaticFunctions.ExitEvent(AnchorPaneMain);
    }

  
    @FXML
    void btn_forwardClick(ActionEvent event) {
        currentMonth++;
        if (currentMonth == 13) {
            currentYear++;
            currentMonth = 1;
        }
        initCalendar(1, currentMonth, currentYear);

    }

    @FXML
    void btn_homeClick(ActionEvent event) {
        form = "../view/Home.fxml";
        madeFadeOut(event);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        StaticFunctions.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_notification_Click(ActionEvent event) {

    }

    @FXML
    void btn_settingClick(ActionEvent event) throws IOException {
        form = "../view/Setting.fxml";
        madeFadeOut(event);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void lbl_congcuClick(MouseEvent event) {

    }

    @FXML
    void lbl_dateClick(MouseEvent event) {
        System.out.println("d");
        JFXDatePicker pk_date = new JFXDatePicker();
        pk_date.show();
    }

    @FXML
    void lbl_dayClick(MouseEvent event) {

    }

    @FXML
    void lbl_thongbaoClick(MouseEvent event) {

    }

    @FXML
    void lbl_alldayClick(MouseEvent event) {

    }

    @FXML
    void lbl_themlichtrinhClick(MouseEvent event) {

    }

//    @FXML
//    void btn_createClick(ActionEvent event) {
//        StaticFunctions.stack_link.push("../view/Home.fxml");
//        form = "../view/SelectSemester.fxml";
//        madeFadeOut(event);
//
//    }
//    @FXML
//    void btn_showdateClick(ActionEvent event) {
//        StaticFunctions.stack_link.push("../view/Home.fxml");
//        form = "../view/Calender.fxml";
//        madeFadeOut(event);
//    }
//
//    @FXML
//    void cbb_userClick(ActionEvent event) {
//
//    }
//    @FXML
//    void btn_settingClick(ActionEvent event) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("../view/Subscribed.fxml"));
//        Scene scene = null;
//        try {
//            scene = new Scene(fxmlLoader.load(), 530, 292);
//        } catch (IOException ex) {
//            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
//    }
    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();

        String name = "Xin chào, 17520433";

        try {
            Global.webCM.hashCode();
        } catch (Exception e) {
            Global.webCM = new WebCommunicate(WebDriverMode.HtmlUnitDriver, "17520350", "1654805354");
        }
        initCalendar(java.time.LocalDate.now().getDayOfMonth(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear());
        init_cbb_user(name);
        init_lv_lichtrinh();
        init_lv_holiday();
        init_button_thongbao();
        btn_rest.fire();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_create2.fire();
                    break;
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
        btn_create.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_create2.fire();
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
                Logger.getLogger(WelcomeController.class
                        .getName()).log(Level.SEVERE, null, ex);
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

    public void init_lv_lichtrinh() {
        //Lịch học và vị trí phòng học.
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("LOCA" + i);
            lv_lichtrinh.getItems().addAll(lb);
        }
        lv_lichtrinh.setOnMouseClicked(e -> {
            String id = lv_lichtrinh.getSelectionModel().getSelectedItem().getText();
            switch (id) {

            }
        });

        lv_lichtrinh.setCellFactory(e -> {
            JFXListCell<Label> cell = new JFXListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: transparent;");

            MenuItem infoItem = new MenuItem();
            infoItem.setText("Chi tiết");
            infoItem.setStyle("-fx-text-fill: white;");

            infoItem.setOnAction(event -> {

            });

            MenuItem deleteItem = new MenuItem();
            deleteItem.setText("Xóa");
            deleteItem.setStyle("-fx-text-fill: white;");
            deleteItem.setOnAction(event -> {
                lv_lichtrinh.getItems().remove(cell.getItem());
            });

            contextMenu.getItems().addAll(infoItem, deleteItem);

            //cell.textProperty().bind(cell.itemProperty());
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });

    }

    public void init_lv_holiday() {
        //Đây là mục "Chung".
        lv_holiday.getItems().clear();
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("NEWS" + i);
            // Xử lý sự kiện tại đây
            lv_holiday.getItems().addAll(lb);
        }
        lv_holiday.setOnMouseClicked(e -> {
            String id = lv_holiday.getSelectionModel().getSelectedItem().getText();

        });
    }

//    public void init_lv_news(Course course) {
//        lv_news.getItems().clear();
//        if (lv_news != null) {
//            lv_news.getItems().clear();
//        }
//
//        ArrayList<Advertise> adv = Global.webCM.GetCourseAdvertisesByCourse(course, false);
//
//        if (adv == null) {
//            return;
//        }
//
//        for (int i = 0; i < adv.size(); i++) {
//            Label lb = new Label(adv.get(i).getName());
//            // Xử lý sự kiện tại đây
//            lv_news.getItems().addAll(lb);
//        }
//        lv_news.setOnMouseClicked(e -> {
//            String id = lv_news.getSelectionModel().getSelectedItem().getText();
//
//        });
//    }
//
//    public void init_lv_dangky(boolean wantUpdate) {
//        lv_dangky.getItems().clear();
//        try {
//            Global.webCM.ExecuteLogin();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        curCourses = new ArrayList<Course>();
//
//        try {
//            curCourses = f.GetCoursesList(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < curCourses.size(); i++) {
//            Label lb = new Label(curCourses.get(i).getCourseName());
//            lv_dangky.getItems().addAll(lb);
//        }
//        lv_dangky.setOnMouseClicked(e -> {
//            int id = lv_dangky.getSelectionModel().getSelectedIndex();
//            Course course = curCourses.get(id);
//            init_lv_deadline(course);
//            init_lv_news(course);
//        });
//    }
//
    public void init_lv_deadline() {
        //Đây là nơi hiển thị deadline.
        lv_new.getItems().clear();
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            // Xử lý sự kiện tại đây
            lv_new.getItems().addAll(lb);
        }
        lv_new.setOnMouseClicked(e -> {
            String id = lv_new.getSelectionModel().getSelectedItem().getText();

            switch (id) {

            }
        });

        lv_new.setCellFactory(e -> {
            JFXListCell<Label> cell = new JFXListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: transparent;");

            MenuItem infoItem = new MenuItem();
            infoItem.setText("Chi tiết");
            infoItem.setStyle("-fx-text-fill: white;");

            infoItem.setOnAction(event -> {

            });
            contextMenu.getItems().addAll(infoItem);

            //cell.textProperty().bind(cell.itemProperty());
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });
    }

    //Hàm này hiển thị deadline của 1 courseID cho trước.
    public void init_lv_deadline(Course course) {
        //Đây là nơi hiển thị deadline.
        lv_new.getItems().clear();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        try {
            deadlines = Global.webCM.GetDeadlinesByCourse(course, false);
        } catch (NotLoggedInException ex) {
            System.out.println("Lỗi đăng nhập.");
        }
        lv_new.getItems().clear();

        for (int i = 0; i < deadlines.size(); i++) {
            Label lb = new Label(deadlines.get(i).getDeadLineName());
            // Xử lý sự kiện tại đây
            lv_new.getItems().addAll(lb);
        }
        lv_new.setOnMouseClicked(e -> {
            String id = lv_new.getSelectionModel().getSelectedItem().getText();

        });
    }

    public void initCalendar(int day, int month, int year) {
        int i = 0;

        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek thu = date.getDayOfWeek();
        lbl_day.setText("Thứ " + thu.getValue());
        lbl_date.setText("Tháng " + Integer.toString(month) + " " + Integer.toString(year));

        NgayThang nt = new NgayThang(year, 1, 1);
        int first_day_in_month = nt.NgayTrongTuan(year, month, 1);
        int count_day_of_month = nt.SoNgayTrongThang(year, month);
        Calendar cld = new Calendar();
        int a[][] = new int[6][7];
        a = cld.Xuat(first_day_in_month, count_day_of_month);

        btn_day00.setText(Integer.toString(a[0][0]));
        btn_day01.setText(Integer.toString(a[0][1]));
        btn_day02.setText(Integer.toString(a[0][2]));
        btn_day03.setText(Integer.toString(a[0][3]));
        btn_day04.setText(Integer.toString(a[0][4]));
        btn_day05.setText(Integer.toString(a[0][5]));
        btn_day06.setText(Integer.toString(a[0][6]));

        btn_day10.setText(Integer.toString(a[1][0]));
        btn_day11.setText(Integer.toString(a[1][1]));
        btn_day12.setText(Integer.toString(a[1][2]));
        btn_day13.setText(Integer.toString(a[1][3]));
        btn_day14.setText(Integer.toString(a[1][4]));
        btn_day15.setText(Integer.toString(a[1][5]));
        btn_day16.setText(Integer.toString(a[1][6]));

        btn_day20.setText(Integer.toString(a[2][0]));
        btn_day21.setText(Integer.toString(a[2][1]));
        btn_day22.setText(Integer.toString(a[2][2]));
        btn_day23.setText(Integer.toString(a[2][3]));
        btn_day24.setText(Integer.toString(a[2][4]));
        btn_day25.setText(Integer.toString(a[2][5]));
        btn_day26.setText(Integer.toString(a[2][6]));

        btn_day30.setText(Integer.toString(a[3][0]));
        btn_day31.setText(Integer.toString(a[3][1]));
        btn_day32.setText(Integer.toString(a[3][2]));
        btn_day33.setText(Integer.toString(a[3][3]));
        btn_day34.setText(Integer.toString(a[3][4]));
        btn_day35.setText(Integer.toString(a[3][5]));
        btn_day36.setText(Integer.toString(a[3][6]));

        btn_day40.setText(Integer.toString(a[4][0]));
        btn_day41.setText(Integer.toString(a[4][1]));
        btn_day42.setText(Integer.toString(a[4][2]));
        btn_day43.setText(Integer.toString(a[4][3]));
        btn_day44.setText(Integer.toString(a[4][4]));
        btn_day45.setText(Integer.toString(a[4][5]));
        btn_day46.setText(Integer.toString(a[4][6]));

        btn_day50.setText(Integer.toString(a[5][0]));
        btn_day51.setText(Integer.toString(a[5][1]));
        btn_day52.setText(Integer.toString(a[5][2]));
        btn_day53.setText(Integer.toString(a[5][3]));
        btn_day54.setText(Integer.toString(a[5][4]));
        btn_day55.setText(Integer.toString(a[5][5]));
        btn_day56.setText(Integer.toString(a[5][6]));

        btn_day56.setVisible(false);
    }

    public void init_cbb_user(String text) {
        ObservableList<String> list = FXCollections.observableArrayList("Trang chủ", "Thời khóa biểu", "Cài đặt", "Đăng xuất");
        cbb_user.setPromptText(text);
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            StaticFunctions.stack_link.push("../view/Home.fxml");
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

    public void init_button_thongbao() {
        ObservableList<String> list = FXCollections.observableArrayList("Thông báo 1", "Thông báo 2");

        JFXListCell<Label> cell = new JFXListCell<>();
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setStyle("-fx-background-color: transparent;");
        for (String item : list) {
            MenuItem infoItem = new MenuItem();
            infoItem.setText(item);
            infoItem.setStyle("-fx-text-fill: white;");

            infoItem.setOnAction(event -> {

            });
            contextMenu.getItems().addAll(infoItem);

        }

        btn_notification.setContextMenu(contextMenu);
    }

    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {
            toggle_mode.setText("Dark mode");
        } else {
            toggle_mode.setText("Nornal mode");
        }
    }

    @FXML
    void btn_addLichTrinhClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/ThemLichTrinh.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 352, 238);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
