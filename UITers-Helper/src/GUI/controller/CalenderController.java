package GUI.controller;

// <editor-fold desc="import zone">
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
//</editor-fold>

public class CalenderController implements Initializable {

    // <editor-fold desc="Static variables zone">
    String form;
    Stage window;
    ObservableList<String> list = FXCollections.observableArrayList("Thời khóa biểu", "Cài đặt", "Đăng xuất");
    //</editor-fold>

    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXListView<Label> lv_lichtrinh;

    @FXML
    private JFXButton btn_time;

    @FXML
    private JFXButton btn_loca;

    @FXML
    private JFXTextField txt_decription;

    @FXML
    private JFXButton btn_add;

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
    private JFXListView<Label> lv_holiday;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXComboBox<String> cbb_user;

    //</editor-fold>
    //<editor-fold desc="FXML functions zone">
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
        StaticFunctions.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    @FXML
    void btn_timeClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Time.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 220, 200);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_locaClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/Location.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 220, 230);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btn_addClick(ActionEvent event) {
        txt_decription.clear();
    }

    //</editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);

        init_lv_holiday();
        init_lv_lichtrinh();
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
        txt_decription.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_add.fire();
                    break;
                default:
                    break;
            }
        });

    }

    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/Calender.fxml");
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

    public void init_lv_holiday() {
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            lv_holiday.getItems().addAll(lb);
        }

        lv_holiday.setOnMouseClicked(e -> {
            String id = lv_holiday.getSelectionModel().getSelectedItem().getText();
            switch (id) {

            }
        });
    }

    public void init_lv_lichtrinh() {
        for (int i = 0; i < 4; i++) {
            Label lb = new Label("C10" + i);
            //init_context_menu(lb, lb.getText());
            lv_lichtrinh.getItems().addAll(lb);

        }
        lv_lichtrinh.setOnMouseClicked((MouseEvent e) -> {
            String id = lv_lichtrinh.getSelectionModel().getSelectedItem().getText();
            switch (id) {

            }
        });

        lv_lichtrinh.setCellFactory(e -> {
            JFXListCell<Label> cell = new JFXListCell<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-background-color: transparent;");

            MenuItem addItem = new MenuItem();
            addItem.setText("Thêm");
            addItem.setStyle("-fx-text-fill: white;");

            addItem.setOnAction(event -> {

            });

            MenuItem editItem = new MenuItem();
            editItem.setText("Sửa");
            editItem.setStyle("-fx-text-fill: white;");
            editItem.setOnAction(event -> {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/EditSchedule.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 563, 180);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            });

            MenuItem deleteItem = new MenuItem();
            deleteItem.setText("Xóa");
            deleteItem.setStyle("-fx-text-fill: white;");
            deleteItem.setOnAction(event -> {
                lv_lichtrinh.getItems().remove(cell.getItem());
            });

            contextMenu.getItems().addAll(addItem, editItem, deleteItem);

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
