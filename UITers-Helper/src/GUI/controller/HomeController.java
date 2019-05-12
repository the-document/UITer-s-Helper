package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Course;
import BLL.Deadline;
import BLL.WebCommunicate;
import BLL.Global;
import BLL.WebDriverMode;
import Exception.NotLoggedInException;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    //Danh sách các courses hiện có của người dùng.
    ArrayList<Course> curCourses; 
    
    //Đối tượng WebCommunicate dùng để tương tác với courses.
    WebCommunicate webCM;
    
    // </editor-fold>
    
    // <editor-fold desc="FXML variables zone">
    @FXML
    private JFXButton btn_setting;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

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
    
    // <editor-fold desc="FXML functions zone">
    
    @FXML
    void btn_expandChungClick(ActionEvent event) {
        if (isExpandChung == 1) {
            lv_news.setVisible(false);
            lv_news.setMaxHeight(0f);
            lv_news.setMinHeight(0f);
            isExpandChung = 0;
            String css = this.getClass().getResource("../css/button_down.css").toExternalForm();
            btn_expandChung.getStylesheets().add(css);
        } else {
            lv_news.setVisible(true);
            lv_news.setMinHeight(360 / (1 + (isExpandDangKy) + (isExpandDeadline)));
            lv_news.setMaxHeight(360 / (1 + (isExpandDangKy) + (isExpandDeadline)));
            String css = this.getClass().getResource("../css/button_up.css").toExternalForm();
            btn_expandChung.getStylesheets().clear();
            btn_expandChung.getStylesheets().add(css);
            isExpandChung = 1;
            if (isExpandDangKy == 1) {
                lv_dangky.setMinHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_dangky.setMaxHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandDeadline == 1) {
                lv_deadline.setMinHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_deadline.setMaxHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
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
            String css = this.getClass().getResource("../css/button_down.css").toExternalForm();
            btn_expandDangKy.getStylesheets().add(css);
        } else {
            lv_dangky.setVisible(true);
            lv_dangky.setMinHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
            lv_dangky.setMaxHeight(3060 / (1 + (isExpandChung) + (isExpandDeadline)));
            btn_expandDangKy.getStylesheets().clear();
            String css = this.getClass().getResource("../css/button_up.css").toExternalForm();
            btn_expandDangKy.getStylesheets().add(css);
            isExpandDangKy = 1;
            if (isExpandChung == 1) {
                lv_news.setMinHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_news.setMaxHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandDeadline == 1) {
                lv_deadline.setMinHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_deadline.setMaxHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
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
            String css = this.getClass().getResource("../css/button_down.css").toExternalForm();
            btn_expandDeadline.getStylesheets().add(css);
        } else {
            lv_deadline.setVisible(true);
            lv_deadline.setMinHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
            lv_deadline.setMaxHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
            btn_expandDeadline.getStylesheets().clear();
            isExpandDeadline = 1;
            String css = this.getClass().getResource("../css/button_up.css").toExternalForm();
            btn_expandDeadline.getStylesheets().add(css);
            if (isExpandDangKy == 1) {
                lv_dangky.setMinHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
                lv_dangky.setMaxHeight(360 / (1 + (isExpandChung) + (isExpandDeadline)));
            }
            if (isExpandChung == 1) {
                lv_news.setMinHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
                lv_news.setMaxHeight(360 / (1 + (isExpandDangKy) + (isExpandChung)));
            }

        }
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);

    }

    @FXML
    void btn_createClick(ActionEvent event) {
        form = "../view/SelectSemester.fxml";
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
    void btn_nofiClick(ActionEvent event) {

    }

    @FXML
    void btn_showdateClick(ActionEvent event) {
        form = "../view/Calender.fxml";
        madeFadeOut(event);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void btn_settingClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Subscribed.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 530, 292);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();

        String name = "Xin chào, 17520433";
        
        //Ta tạo 1 đối tượng WebCommunicate để trao đổi và tương tác với courses, lấy thông tin.
        webCM = new WebCommunicate(WebDriverMode.HtmlUnitDriver,"17520350","1654805354");
        
        init_cbb_user(name);
        init_lv_dangky();
        init_lv_deadline();
        init_lv_location();
        init_lv_news();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_create.fire();
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
                    btn_create.fire();
                    break;
            }
        });
    }

    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/Home.fxml");
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

    public void init_lv_location() {
        //Lịch học và vị trí phòng học.
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("LOCA" + i);
            lv_location.getItems().addAll(lb);
        }
        lv_location.setOnMouseClicked(e -> {
            String id = lv_location.getSelectionModel().getSelectedItem().getText();
            switch (id) {

            }
        });
    }

    public void init_lv_news() {
        //Đây là mục "Chung".
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("NEWS" + i);
            // Xử lý sự kiện tại đây
            lv_news.getItems().addAll(lb);
        }
        lv_news.setOnMouseClicked(e -> {
            String id = lv_news.getSelectionModel().getSelectedItem().getText();
            
        });
    }

    public void init_lv_dangky() {
        
        try {
            webCM.ExecuteLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        curCourses = new ArrayList<Course>();
        
        try {
            curCourses = webCM.GetCoursesList(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < curCourses.size(); i++) {
            Label lb = new Label(curCourses.get(i).getCourseName());
            lv_dangky.getItems().addAll(lb);
        }
        lv_dangky.setOnMouseClicked(e -> {
            int id = lv_dangky.getSelectionModel().getSelectedIndex();
            Course course = curCourses.get(id);
            init_lv_deadline(course);
        });
    }

    public void init_lv_deadline() {
        //Đây là nơi hiển thị deadline.
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            // Xử lý sự kiện tại đây
            lv_deadline.getItems().addAll(lb);
        }
        lv_deadline.setOnMouseClicked(e -> {
            String id = lv_deadline.getSelectionModel().getSelectedItem().getText();
            switch (id) {

            }
        });
    }
    
    
    //Hàm này hiển thị deadline của 1 courseID cho trước.
    public void init_lv_deadline(Course course)
    {
        //Đây là nơi hiển thị deadline.
        ArrayList<Deadline> deadlines = new ArrayList<>();
        try
        {
            deadlines = webCM.GetDeadlinesByCourse(course);
        }
        catch (NotLoggedInException ex)
        {
            System.out.println("Lỗi đăng nhập.");
        }
        
        lv_deadline.getItems().clear();
        
        for (int i = 0; i < deadlines.size(); i++) {
            Label lb = new Label(deadlines.get(i).getDeadLineName());
            // Xử lý sự kiện tại đây
            lv_deadline.getItems().addAll(lb);
        }
        lv_deadline.setOnMouseClicked(e -> {
            String id = lv_deadline.getSelectionModel().getSelectedItem().getText();
            
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

}
