package GUI.controller;

//<editor-fold desc="import zone">
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//</editor-fold>

public class LocationController implements Initializable {

    //<editor-fold desc="Static variables zone">
    LocalDate date;
    LocalTime time;
    // </editor-fold>
    //<editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_ok;

    @FXML
    private JFXComboBox<String> cbb_building;

    @FXML
    private JFXComboBox<String> cbb_room;

    @FXML
    private JFXTextField txt_other;
    //</editor-fold>

    @FXML
    void btn_okClick(ActionEvent event) {
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
        ObservableList<String> list = FXCollections.observableArrayList("A", "B", "C");
        cbb_building.getItems().clear();

        cbb_building.setItems(list);

        cbb_building.setOnAction(e -> {

        });
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_ok.fire();
                    break;
                default:
                    break;
            }
        });
        btn_ok.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_ok.fire();
                    break;
            }
        });
    }

}
