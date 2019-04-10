package GUI.controller;

// <editor-fold desc="import zone">
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
// </editor-fold>

public class TimeController implements Initializable {
    
    // <editor-fold desc="Static variables zone">
    LocalDate date;
    LocalTime time;
    // </editor-fold>

    // <editor-fold desc="FXML variables zone">
    
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXDatePicker pk_date;

    @FXML
    private JFXTimePicker pk_time;

    @FXML
    private JFXButton btn_ok;
    
    // </editor-fold>

    // <editor-fold desc="FXML functions zone">
    
    @FXML
    void btn_okClick(ActionEvent event) { 
        date = pk_date.getValue();
        time = pk_time.getValue();
        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();
                
    }

    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    

}
