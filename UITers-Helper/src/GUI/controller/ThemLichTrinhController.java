package GUI.controller;

// <editor-fold desc="import zone">
import BLL.CalenderBLL;
import BLL.Global;
import DTO.Calender;
import GUI.LichTrinh;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
// </editor-fold>

public class ThemLichTrinhController implements Initializable {

    // <editor-fold desc="FXML variables zone">
    
   @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXTimePicker pk_time;

    @FXML
    private JFXTextField txt_location;

    @FXML
    private JFXTextField txt_desc;

    @FXML
    private JFXButton btn_add;

  
    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_exit.fire();
                    break;
            }
        });
        pk_time.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case TAB:
                    txt_location.requestFocus();
            }
        });
        txt_location.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case TAB:
                    txt_desc.requestFocus();
            }
        });
         txt_desc.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_add.fire();
            }
        });
    }

   

    @FXML
    void btn_addClick(ActionEvent event) {
        LocalTime time =  pk_time.getValue();
        String location = txt_location.getText();
        String desc = txt_desc.getText();
        LichTrinh temp = new LichTrinh(time, location, desc);
        StaticFunctions.lichTrinh = temp;
        
        //save other calendar-nhp----------
        if (time==null) {
            return;
        }

        String timeToSave=Global.dateCalendarSelected+" "+ time+":00";
        System.out.println(timeToSave);
        
        
        Calender calender = new Calender(timeToSave, location, desc);
        CalenderBLL bll = new CalenderBLL();

        try {
            bll.InsertCalender(calender);
        } catch (SQLException ex) {
            Logger.getLogger(ThemLichTrinhController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btn_exit.fire();
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();

    }

}
