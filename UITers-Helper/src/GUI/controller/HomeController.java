package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Advertise;
import BLL.CalenderBLL;
import BLL.Course;
import BLL.DeadLineDateBufferMode;
import BLL.Deadline;
import BLL.WebCommunicate;
import BLL.Global;
import BLL.ThongBao;
import BLL.WebDriverMode;
import DTO.Calender;
import Exception.CannotBrowseCourseException;
import Exception.CannotLoginException;
import Exception.NotLoggedInException;
import GUI.Calendar;
import GUI.NgayThang;
import GUI.PopUp_Notification;
import GUI.StaticFunctions;
import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;

import com.jfoenix.controls.JFXToggleButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javafx.stage.Popup;
import javafx.util.Duration;
import javax.swing.JButton;
import org.apache.xalan.lib.ExsltDatetime;
import org.controlsfx.control.Notifications;
// </editor-fold>

public class HomeController implements Initializable {

    // <editor-fold desc="Static varriables zone">
    String form;
    Stage window;
    int isExpandChung = 1;
    int isExpandDeadline = 1;
    int isExpandDangKy = 1;

    Integer currentDay = java.time.LocalDate.now().getDayOfMonth();
    Integer currentMonth = java.time.LocalDate.now().getMonthValue();
    Integer currentYear = java.time.LocalDate.now().getYear();

    //Danh sách các courses hiện có của người dùng.
    ArrayList<Course> curCourses;

    //them btn trong lich nay vao mang de de quan ly - nhp
    List<JFXButton> lsBtnCalendar = new ArrayList<>();

    //lich trinh cua thang dang focus
    List<Calender> lsCalenders = new ArrayList<>();

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
        init_lv_thongbao(null, false);
        System.out.println("News");
    }

    @FXML
    void btn_deadlineClick(ActionEvent event) {
        init_lv_deadline(null);
        System.out.println("Deadline");
    }

    @FXML
    void btn_restClick(ActionEvent event) {
        //Nếu muốn test load 1 ngày cố định, thay null bằng test và chỉnh ngày tương ứng.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US);
        LocalDate test = LocalDate.parse("12/06/2019", dtf);
        init_lv_rest(null);
        System.out.println("Rest");
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.webCM.Dispose();
        StaticFunctions.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        currentMonth--;
        if (currentMonth == 0) {
            currentYear--;
            currentMonth = 12;
        }
        initCalendar(1, currentMonth, currentYear);

        //using for DAL
        Global.CurrentMonth = currentMonth;
        Global.CurrentYear = currentYear;
        ResetColorListButtonTableCalendar();
        ReLoadLichTrinhThangHienTai();
        fillCalendarWithDayHaveDeadline();
    }

    @FXML
    void btn_createClick(MouseEvent event) {
        // Do panel ko có action evnet nên phải dùng tạm cái này
        btn_create2.fire();
    }

    @FXML
    void btn_createFire(ActionEvent event) {
        StaticFunctions.stack_link.push("Home");
        form = "SelectSemester";
        madeFadeOut(event);
    }

    @FXML
    void btn_notification_Click(ActionEvent event) {
        PopUp_Notification.loadNotification();
    }

    @FXML

    void btn_settingClick(ActionEvent event) throws IOException {
        StaticFunctions.stack_link.push("Home");
        form = "Setting";
        madeFadeOut(event);
    }

    @FXML
    void btn_forwardClick(ActionEvent event) {
        currentMonth++;
        if (currentMonth == 13) {
            currentYear++;
            currentMonth = 1;
        }
        initCalendar(1, currentMonth, currentYear);

        //using for DAL
        Global.CurrentMonth = currentMonth;
        Global.CurrentYear = currentYear;
        ResetColorListButtonTableCalendar();
        ReLoadLichTrinhThangHienTai();
        fillCalendarWithDayHaveDeadline();
    }

    @FXML
    void btn_homeClick(ActionEvent event) {

    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        StaticFunctions.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void lbl_dayClick(MouseEvent event) {

    }

    @FXML

    void lbl_congcuClick(MouseEvent event) {

    }

    @FXML

    void lbl_dateClick(MouseEvent event) {
        JFXDatePicker pk_date = new JFXDatePicker();
        pk_date.show();
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
    @FXML
    void cbb_userClick(ActionEvent event) {

    }
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

    @FXML
    void lbl_pathClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        form = "Home";
        lbl_path.setText(StaticFunctions.stack_link.UpdatePath(form));
        String name = "Xin chào, " + Global.webCM.getUserName();

        try {
            Global.webCM.hashCode();
        } catch (Exception e) {

            Global.webCM = new WebCommunicate(WebDriverMode.HtmlUnitDriver, BLL.Global.username, BLL.Global.password);
        }

        InitListButtonTableCalendar();

        initCalendar(java.time.LocalDate.now().getDayOfMonth(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear());
        init_cbb_user(name);
        init_lv_lichtrinh();

        init_button_thongbao();
        btn_day00.fire();
      
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
        Parent root = FXMLLoader.load(getClass().getResource(StaticFunctions.switcher.Switch(form)));
        Scene tableViewScene = new Scene(root);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        StaticFunctions.SetStageDrag(root, window, event);
        window.show();
    }

    public static MenuItem getMenuItem(String content) {
        MenuItem item = new MenuItem();
        item.setText(content);
        if (StaticFunctions.IsDarkMode == true) {
            item.setStyle("-fx-text-fill: white;");
        } else {
            item.setStyle("-fx-text-fill: black;");
        }
        return item;
    }

    public void init_lv_lichtrinh() {

        ReLoadLichTrinhThangHienTai();

        //fill data for calendar table
        fillCalendarWithDayHaveDeadline();

        lv_lichtrinh.setOnMouseClicked(e -> {
            String id = lv_lichtrinh.getSelectionModel().getSelectedItem().getText();
            System.out.println("CLicked : " + id);
            switch (id) {

            }
        });

        lv_lichtrinh.setCellFactory(e -> {
            JFXListCell<Label> cell = new JFXListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: transparent;");

            MenuItem infoItem = getMenuItem("Chi tiết");
            infoItem.setOnAction(event -> {

                Global.ModeThemLichTrinh = 0; //xem chi tiet
                for (Calender c : lsCalenders) {
                    if (c.getDescribe().equals(lv_lichtrinh.getSelectionModel().getSelectedItem().getText())) {
                        Global.LichTrinhDaChonDeXem = c;
                    }
                }

                //open them lich trinh form
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(StaticFunctions.switcher.Switch("ThemLichTrinh")));
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
            });

            MenuItem deleteItem = getMenuItem("Xóa");
            deleteItem.setOnAction(event -> {
                try {

                    System.out.println("delete -" + cell.getItem().getId());
                    CalenderBLL bll = new CalenderBLL();
                    bll.DeleteCalender(cell.getItem().getId());
                    lv_lichtrinh.getItems().remove(cell.getItem());

                    //cancle seting notify with system
                    //khong can thiet, se check lai trong DB truoc khi remind
                } catch (SQLException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            contextMenu.getItems().addAll(infoItem, deleteItem);
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

    public void init_lv_deadline(LocalDate dateOfDeadline) {
        //Đây là nơi hiển thị deadline.

        if (dateOfDeadline == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d M uuuu", Locale.US);
            String selectedDate = currentDay.toString() + " " + currentMonth.toString() + " " + currentYear.toString();
            dateOfDeadline = LocalDate.parse(selectedDate, dtf);
        }

        ArrayList<Deadline> thisDateDeadline = new ArrayList<>();

        try {
            thisDateDeadline = Global.webCM.GetDeadlinesByLocalDate(dateOfDeadline, DeadLineDateBufferMode.MONTHLY, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lv_new.getItems().clear();

        if (thisDateDeadline == null) {
            System.out.println("Không có deadline trong ngày " + dateOfDeadline.toString() + " (Hooray).");
            return;
        }

        for (Deadline d : thisDateDeadline) {
            Label lb;
            Course deadlineCourse = d.getCourseOfDeadlines();

            if (deadlineCourse == null) {
                lb = new Label(d.getDeadLineName());
            } else {
                lb = new Label("[" + d.getCourseOfDeadlines().getCourseName() + "] " + d.getDeadLineName());
            }

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

            MenuItem infoItem = getMenuItem("Chi tiết");

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

    //Hàm này để hiển thị thông báo chung.
    public void init_lv_thongbao(Integer pageNo, boolean wantUpdate) {
        if (pageNo == null) {
            pageNo = 0;
        }

        ArrayList<ThongBao> dsThongBao = Global.webCM.GetThongBao(pageNo, wantUpdate);

        lv_new.getItems().clear();

        if (dsThongBao == null) {
            System.out.println("Không có thông báo nào");
            return;
        }

        for (ThongBao tb : dsThongBao) {
            Label lb = new Label(tb.getName());
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

            MenuItem infoItem = getMenuItem("Chi tiết");

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

    //Hàm này hiển thị thông báo nghỉ.
    public void init_lv_rest(LocalDate dateToShowRest) {
        if (dateToShowRest == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d M uuuu", Locale.US);
            String selectedDate = currentDay.toString() + " " + currentMonth.toString() + " " + currentYear.toString();
            dateToShowRest = LocalDate.parse(selectedDate, dtf);
        }

        ArrayList<Advertise> thisDateAdvertises = new ArrayList<>();

        try {
            thisDateAdvertises = Global.webCM.GetAdvertisesByDate(dateToShowRest, false);
        } catch (CannotBrowseCourseException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotLoginException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lv_new.getItems().clear();

        if (thisDateAdvertises == null) {
            System.out.println("Không có môn nào được nghỉ trong ngày " + dateToShowRest.toString());
            return;
        }

        for (Advertise a : thisDateAdvertises) {
            Label lb = new Label(a.getName());
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

            MenuItem infoItem = getMenuItem("Chi tiết");

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

    public void Button_DateClick(int day) {
        NgayThang nt = new NgayThang(currentYear, currentMonth, day);

        //using for create new calendar-bottom
        Global.dateCalendarSelected = Global.CurrentYear + "-" + Global.CurrentMonth + "-" + day;
        Global.CurrentDay = day;

        if (nt.NgayTrongTuan(currentYear, currentMonth, day) != 8) {
            lbl_day.setText("Thứ " + nt.NgayTrongTuan(currentYear, currentMonth, day) + " Ngày " + day);

        } else {
            lbl_day.setText("Chủ nhật " + " Ngày " + day);

        }
        currentDay = day;
        System.out.println("Selected date : " + day);
      
    }

    //lich trinh - bang lich =============================================
    public void InitListButtonTableCalendar() {
        //them btn trong lich nay vao mang de de quan ly - nhp
        //List<JFXButton> lsBtnCalendar=new ArrayList<>();
        lsBtnCalendar.add(btn_day00);
        lsBtnCalendar.add(btn_day01);
        lsBtnCalendar.add(btn_day02);
        lsBtnCalendar.add(btn_day03);
        lsBtnCalendar.add(btn_day04);
        lsBtnCalendar.add(btn_day05);
        lsBtnCalendar.add(btn_day06);
        lsBtnCalendar.add(btn_day10);
        lsBtnCalendar.add(btn_day11);
        lsBtnCalendar.add(btn_day12);
        lsBtnCalendar.add(btn_day13);
        lsBtnCalendar.add(btn_day14);
        lsBtnCalendar.add(btn_day15);
        lsBtnCalendar.add(btn_day16);
        lsBtnCalendar.add(btn_day20);
        lsBtnCalendar.add(btn_day21);
        lsBtnCalendar.add(btn_day22);
        lsBtnCalendar.add(btn_day23);
        lsBtnCalendar.add(btn_day24);
        lsBtnCalendar.add(btn_day25);
        lsBtnCalendar.add(btn_day26);
        lsBtnCalendar.add(btn_day30);
        lsBtnCalendar.add(btn_day31);
        lsBtnCalendar.add(btn_day32);
        lsBtnCalendar.add(btn_day33);
        lsBtnCalendar.add(btn_day34);
        lsBtnCalendar.add(btn_day35);
        lsBtnCalendar.add(btn_day36);
        lsBtnCalendar.add(btn_day40);
        lsBtnCalendar.add(btn_day41);
        lsBtnCalendar.add(btn_day42);
        lsBtnCalendar.add(btn_day43);
        lsBtnCalendar.add(btn_day44);
        lsBtnCalendar.add(btn_day45);
        lsBtnCalendar.add(btn_day46);
        lsBtnCalendar.add(btn_day50);
        lsBtnCalendar.add(btn_day51);
        lsBtnCalendar.add(btn_day52);
        lsBtnCalendar.add(btn_day53);
        lsBtnCalendar.add(btn_day54);
        lsBtnCalendar.add(btn_day55);
        lsBtnCalendar.add(btn_day56);
    }

    public void ResetColorListButtonTableCalendar() {
        for (JFXButton btn : lsBtnCalendar) {
            btn.setStyle("-fx-background-color: transparent");

        }
    }

    public void fillCalendarWithDayHaveDeadline() {

        if (lsCalenders != null) {
            for (Calender calendar : lsCalenders) {

            //split to get day of current calender
            System.out.println(calendar.getTime());
            String day = calendar.getTime().split("-")[2];
            day = day.split(" ")[0];
            for (JFXButton btn : lsBtnCalendar) {
                if (btn.getText().equals(day)) //btn.setStyle("-fx-background-color:  #505abe");
                {
                    btn.setStyle("-fx-background-color:  #f44156");
                }
            }
        }
        }

        

        //Phần tô màu những ngày có deadline của Nghị.
        //Tô màu xanh lá cây cho thông báo nghỉ/bù.
        ArrayList<LocalDate> thisMonthAdvertise = Global.webCM.getDatesHaveAdvertise(currentMonth, currentYear, false);

        boolean flagGreen[] = new boolean[32];

        for (int i = 0; i < 32; ++i) {
            flagGreen[i] = false;
        }

        for (LocalDate d : thisMonthAdvertise) {
            flagGreen[d.getDayOfMonth()] = true;
            System.out.println("Date have advertise : " + d.getDayOfMonth());
        }

        for (JFXButton btn : lsBtnCalendar) {
            boolean condition;
            try {
                condition = flagGreen[Integer.parseInt(btn.getText())];
            } catch (NumberFormatException e) {
                condition = false;
            }

            if (condition) {
                btn.setStyle("-fx-background-color:  #00FA9A");
            } else {
                System.out.println("Day " + btn.getText() + " failed !");
            }
        }

        //Tô màu đỏ cho deadline.
        ArrayList<LocalDate> thisMonthDeadLine = Global.webCM.getDateHaveDeadlines(currentMonth, currentYear, false);

        boolean[] flagRed = new boolean[32];

        for (int i = 0; i < 32; ++i) {
            flagRed[i] = false;
        }

        for (LocalDate d : thisMonthDeadLine) {
            flagRed[d.getDayOfMonth()] = true;
            System.out.println("Date have deadlines : " + d.getDayOfMonth());
        }

        for (JFXButton btn : lsBtnCalendar) {
            boolean condition;
            try {
                condition = flagRed[Integer.parseInt(btn.getText())];
            } catch (NumberFormatException e) {
                condition = false;
            }

            if (condition) {
                btn.setStyle("-fx-background-color:  #f44156");
            } else {
                System.out.println("Day " + btn.getText() + " failed !");
            }
        }

        //Tô màu tím cho những ngày có cả deadline và thông báo nghỉ/bù.
        boolean[] flagPurple = new boolean[32];

        for (int i = 0; i < 32; ++i) {
            flagPurple[i] = (flagGreen[i] && flagRed[i]);
        }

        for (JFXButton btn : lsBtnCalendar) {
            boolean condition;
            try {
                condition = flagPurple[Integer.parseInt(btn.getText())];
            } catch (NumberFormatException e) {
                condition = false;
            }

            if (condition) {
                btn.setStyle("-fx-background-color:  #ba55d3");
            } else {
                System.out.println("Day " + btn.getText() + " failed !");
            }
        }

    }

    public void ReLoadLichTrinhThangHienTai() {
        //load data for current month
        CalenderBLL cabllBLL = new CalenderBLL();
        try {
            //lsCalenders = cabllBLL.GetPersonalCalenderInfuture();
            if (lsCalenders != null) {
                lsCalenders.clear();
            }

            lsCalenders = cabllBLL.GetPersonalCalenderCurrentMonth();

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Lịch cá nhân tự tạo.
        lv_lichtrinh.getItems().clear();

        if (lsCalenders == null) {
            return;
        }

        for (Calender calender : lsCalenders) {
            Label lb = new Label(calender.getDescribe());
            lb.setId(calender.getKey());
            lv_lichtrinh.getItems().addAll(lb);
        }
    }
    //===================================================================

    public void initCalendar(int day, int month, int year) {

//        JFXButton arrButton[][] = new JFXButton[6][7];
        Calendar cld = new Calendar();
        String a[][] = new String[6][7];
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek thu = date.getDayOfWeek();
        NgayThang nt = new NgayThang(year, 1, 1);
        int first_day_in_month = nt.NgayTrongTuan(year, month, 1);
        int count_day_of_month = nt.SoNgayTrongThang(year, month);

        lbl_day.setText("Thứ " + thu.getValue());
        lbl_date.setText("Tháng " + Integer.toString(month) + " " + Integer.toString(year));
        a = cld.Xuat(first_day_in_month, count_day_of_month);

        btn_day00.setText((a[0][0]));
        btn_day00.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day00.getText()));
            if (btn_day00.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day00.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][0].compareTo("") == 0) {
            btn_day00.setDisable(true);
        } else {
            btn_day00.setDisable(false);
        }

        btn_day01.setText((a[0][1]));
        btn_day01.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day01.getText()));
            if (btn_day01.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day01.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][1].compareTo("") == 0) {
            btn_day01.setDisable(true);
        } else {
            btn_day01.setDisable(false);
        }

        btn_day02.setText((a[0][2]));
        btn_day02.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day02.getText()));
            if (btn_day02.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day02.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][2].compareTo("") == 0) {
            btn_day02.setDisable(true);
        } else {
            btn_day02.setDisable(false);
        }

        btn_day03.setText((a[0][3]));
        btn_day03.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day03.getText()));
            if (btn_day03.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day03.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][3].compareTo("") == 0) {
            btn_day03.setDisable(true);
        } else {
            btn_day03.setDisable(false);
        }

        btn_day04.setText((a[0][4]));
        btn_day04.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day04.getText()));
            if (btn_day04.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day04.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][4].compareTo("") == 0) {
            btn_day04.setDisable(true);
        } else {
            btn_day04.setDisable(false);
        }

        btn_day05.setText((a[0][5]));
        btn_day05.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day05.getText()));
            if (btn_day05.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day05.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][5].compareTo("") == 0) {
            btn_day05.setDisable(true);
        } else {
            btn_day05.setDisable(false);
        }

        btn_day06.setText((a[0][6]));
        btn_day06.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day06.getText()));
            if (btn_day06.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day06.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[0][6].compareTo("") == 0) {
            btn_day06.setDisable(true);
        } else {
            btn_day06.setDisable(false);
        }

        btn_day10.setText((a[1][0]));
        btn_day10.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day10.getText()));
            if (btn_day10.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day10.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][0].compareTo("") == 0) {
            btn_day10.setDisable(true);
        } else {
            btn_day10.setDisable(false);
        }

        btn_day11.setText((a[1][1]));
        btn_day11.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day11.getText()));
            if (btn_day11.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day11.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][1].compareTo("") == 0) {
            btn_day11.setDisable(true);
        } else {
            btn_day11.setDisable(false);
        }

        btn_day12.setText((a[1][2]));
        btn_day12.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day12.getText()));
            if (btn_day12.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day12.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][2].compareTo("") == 0) {
            btn_day12.setDisable(true);
        } else {
            btn_day12.setDisable(false);
        }

        btn_day13.setText((a[1][3]));
        btn_day13.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day13.getText()));
            if (btn_day13.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day13.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][3].compareTo("") == 0) {
            btn_day13.setDisable(true);
        } else {
            btn_day13.setDisable(false);
        }

        btn_day14.setText((a[1][4]));
        btn_day14.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day14.getText()));
            if (btn_day14.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day14.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][4].compareTo("") == 0) {
            btn_day14.setDisable(true);
        } else {
            btn_day14.setDisable(false);
        }

        btn_day15.setText((a[1][5]));
        btn_day15.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day15.getText()));
            if (btn_day15.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day15.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }

        });
        if (a[1][5].compareTo("") == 0) {
            btn_day15.setDisable(true);
        } else {
            btn_day15.setDisable(false);
        }

        btn_day16.setText((a[1][6]));
        btn_day16.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day16.getText()));
            if (btn_day16.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day16.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[1][6].compareTo("") == 0) {
            btn_day16.setDisable(true);
        } else {
            btn_day16.setDisable(false);
        }

        btn_day20.setText((a[2][0]));
        btn_day20.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day20.getText()));
            if (btn_day20.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day20.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][0].compareTo("") == 0) {
            btn_day20.setDisable(true);
        } else {
            btn_day20.setDisable(false);
        }

        btn_day21.setText((a[2][1]));
        btn_day21.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day21.getText()));
            if (btn_day21.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day21.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][1].compareTo("") == 0) {
            btn_day21.setDisable(true);
        } else {
            btn_day21.setDisable(false);
        }

        btn_day22.setText((a[2][2]));
        btn_day22.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day22.getText()));
            if (btn_day22.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day22.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][2].compareTo("") == 0) {
            btn_day22.setDisable(true);
        } else {
            btn_day22.setDisable(false);
        }
        btn_day23.setText((a[2][3]));
        btn_day23.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day23.getText()));
            if (btn_day23.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day23.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][3].compareTo("") == 0) {
            btn_day23.setDisable(true);
        } else {
            btn_day23.setDisable(false);
        }

        btn_day24.setText((a[2][4]));
        btn_day24.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day24.getText()));
            if (btn_day24.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day24.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][4].compareTo("") == 0) {
            btn_day24.setDisable(true);
        } else {
            btn_day24.setDisable(false);
        }

        btn_day25.setText((a[2][5]));
        btn_day25.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day25.getText()));
            if (btn_day25.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day25.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][5].compareTo("") == 0) {
            btn_day25.setDisable(true);
        } else {
            btn_day25.setDisable(false);
        }

        btn_day26.setText((a[2][6]));
        btn_day26.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day26.getText()));
            if (btn_day26.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day26.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[2][6].compareTo("") == 0) {
            btn_day26.setDisable(true);
        } else {
            btn_day26.setDisable(false);
        }

        btn_day30.setText((a[3][0]));
        btn_day30.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day30.getText()));
            if (btn_day30.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day30.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][0].compareTo("") == 0) {
            btn_day30.setDisable(true);
        } else {
            btn_day30.setDisable(false);
        }
        btn_day31.setText((a[3][1]));
        btn_day31.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day31.getText()));
            if (btn_day31.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day31.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][1].compareTo("") == 0) {
            btn_day31.setDisable(true);
        } else {
            btn_day31.setDisable(false);
        }

        btn_day32.setText((a[3][2]));
        btn_day32.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day32.getText()));
            if (btn_day32.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day32.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][2].compareTo("") == 0) {
            btn_day32.setDisable(true);
        } else {
            btn_day32.setDisable(false);
        }

        btn_day33.setText((a[3][3]));
        btn_day33.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day33.getText()));
            if (btn_day33.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day33.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][3].compareTo("") == 0) {
            btn_day33.setDisable(true);
        } else {
            btn_day33.setDisable(false);
        }

        btn_day34.setText((a[3][4]));
        btn_day34.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day34.getText()));
            if (btn_day34.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day34.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][4].compareTo("") == 0) {
            btn_day34.setDisable(true);
        } else {
            btn_day34.setDisable(false);
        }

        btn_day35.setText((a[3][5]));
        btn_day35.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day35.getText()));
            if (btn_day35.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day35.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][5].compareTo("") == 0) {
            btn_day35.setDisable(true);
        } else {
            btn_day35.setDisable(false);
        }

        btn_day36.setText((a[3][6]));
        btn_day36.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day36.getText()));
            if (btn_day36.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day36.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[3][6].compareTo("") == 0) {
            btn_day36.setDisable(true);
        } else {
            btn_day36.setDisable(false);
        }

        btn_day40.setText((a[4][0]));
        btn_day40.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day40.getText()));
            if (btn_day40.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day40.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][0].compareTo("") == 0) {
            btn_day40.setDisable(true);
        } else {
            btn_day40.setDisable(false);
        }

        btn_day41.setText((a[4][1]));
        btn_day41.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day41.getText()));
            if (btn_day41.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day41.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][1].compareTo("") == 0) {
            btn_day41.setDisable(true);
        } else {
            btn_day41.setDisable(false);
        }
        btn_day42.setText((a[4][2]));
        btn_day42.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day42.getText()));
            if (btn_day42.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day42.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][2].compareTo("") == 0) {
            btn_day42.setDisable(true);
        } else {
            btn_day42.setDisable(false);
        }

        btn_day43.setText((a[4][3]));
        btn_day43.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day43.getText()));
            if (btn_day43.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day43.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][3].compareTo("") == 0) {
            btn_day43.setDisable(true);
        } else {
            btn_day43.setDisable(false);
        }

        btn_day44.setText((a[4][4]));
        btn_day44.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day44.getText()));
            if (btn_day44.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day44.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][4].compareTo("") == 0) {
            btn_day44.setDisable(true);
        } else {
            btn_day44.setDisable(false);
        }

        btn_day45.setText((a[4][5]));
        btn_day45.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day45.getText()));
            if (btn_day45.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day45.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][5].compareTo("") == 0) {
            btn_day45.setDisable(true);
        } else {
            btn_day45.setDisable(false);
        }

        btn_day46.setText((a[4][6]));
        btn_day46.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day46.getText()));
            if (btn_day46.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day46.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[4][6].compareTo("") == 0) {
            btn_day46.setDisable(true);
        } else {
            btn_day46.setDisable(false);
        }

        btn_day50.setText((a[5][0]));
        btn_day50.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day50.getText()));
            if (btn_day50.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day50.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][0].compareTo("") == 0) {
            btn_day50.setDisable(true);
        } else {
            btn_day50.setDisable(false);
        }
        
        btn_day51.setText((a[5][1]));
        btn_day51.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day51.getText()));
               if (btn_day51.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day51.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][1].compareTo("") == 0) {
            btn_day51.setDisable(true);
        } else {
            btn_day51.setDisable(false);
        }
        btn_day52.setText((a[5][2]));
        btn_day52.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day52.getText()));
               if (btn_day52.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day52.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][2].compareTo("") == 0) {
            btn_day52.setDisable(true);
        } else {
            btn_day52.setDisable(false);
        }
        
        btn_day53.setText((a[5][3]));
        btn_day53.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day53.getText()));
               if (btn_day53.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day53.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][3].compareTo("") == 0) {
            btn_day53.setDisable(true);
        } else {
            btn_day53.setDisable(false);
        }
        
        btn_day54.setText((a[5][4]));
        btn_day54.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day54.getText()));
               if (btn_day54.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day54.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][4].compareTo("") == 0) {
            btn_day54.setDisable(true);
        } else {
            btn_day54.setDisable(false);
        }
        
        btn_day55.setText((a[5][5]));
        btn_day55.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day55.getText()));
               if (btn_day55.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day55.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][5].compareTo("") == 0) {
            btn_day55.setDisable(true);
        } else {
            btn_day55.setDisable(false);
        }
        
        btn_day56.setText((a[5][6]));
        btn_day56.setOnAction(e -> {
            Button_DateClick(Integer.parseInt(btn_day56.getText()));
               if (btn_day56.getStyle().compareTo("-fx-background-color:  #00FA9A") == 0) {
                btn_rest.fire();
            }
            if (btn_day56.getStyle().compareTo("-fx-background-color:  #f44156") == 0) {
                btn_deadline.fire();
            }
        });
        if (a[5][6].compareTo("") == 0) {
            btn_day56.setDisable(true);
        } else {
            btn_day56.setDisable(false);
        }

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

    public void init_button_thongbao() {
        ObservableList<String> list = FXCollections.observableArrayList("Thông báo 1", "Thông báo 2");

        JFXListCell<Label> cell = new JFXListCell<>();
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setStyle("-fx-background-color: transparent;");
        for (String item : list) {
            MenuItem infoItem = getMenuItem(item);

            infoItem.setOnAction(event -> {

            });
            contextMenu.getItems().addAll(infoItem);

        }

        btn_notification.setContextMenu(contextMenu);
    }

    @FXML
    void toggle_modeClick(ActionEvent event) {
        if (toggle_mode.isSelected()) {
            StaticFunctions.IsDarkMode = true;

        } else {
            StaticFunctions.IsDarkMode = false;
        }
        form = "Home";
        madeFadeOut(event);
    }

    @FXML
    void btn_addLichTrinhClick(ActionEvent event) {
        
         int curentDay = java.time.LocalDate.now().getDayOfMonth();
        System.out.println("day now -" + curentDay);

        if (Global.CurrentDay < curentDay) {
            PopUp_Notification.run("Cảnh báo", "Bạn không thể tạo ngày nghỉ nhỏ hơn ngày hiện tại", "INFO");
            return;
        }

        //don't create LichTrinh khi chua chon ngay
        if (Global.dateCalendarSelected == null) {
            PopUp_Notification.run("Cảnh báo", "Bạn chưa chọn ngày", "INFO");
            return;
        }

        Global.ModeThemLichTrinh = 1;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(StaticFunctions.switcher.Switch("ThemLichTrinh")));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 352, 286);
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
