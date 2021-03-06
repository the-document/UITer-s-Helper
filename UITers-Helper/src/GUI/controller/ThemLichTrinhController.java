package GUI.controller;

// <editor-fold desc="import zone">
import BLL.CalenderBLL;
import BLL.Global;
import DTO.Calender;
import GUI.LichTrinh;
import GUI.PopUp_Notification;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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

    @FXML
    private DatePicker date_time;

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();

        date_time.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                LocalDate co=LocalDate.of(today.getYear(), today.getMonth(),Global.CurrentDay);
                setDisable(empty || date.compareTo(co) > 0||date.compareTo(today)<0);
            }
        });

        if (Global.ModeThemLichTrinh == 0) {
            btn_add.setText("OK");

            //view detail
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            try {
                System.out.println(Global.LichTrinhDaChonDeXem.getTime());
                date = formatter.parse(Global.LichTrinhDaChonDeXem.getTime());
                System.out.println(date.getHours() + date.getMinutes() + "");
                pk_time.setValue(LocalTime.of(date.getHours(), date.getMinutes()));

                LocalDate dayRemind = LocalDate.of(date.getYear(), date.getMonth(), Global.LichTrinhDaChonDeXem.getRemindDay());
                date_time.setValue(dayRemind);

            } catch (ParseException ex) {
                Logger.getLogger(ThemLichTrinhController.class.getName()).log(Level.SEVERE, null, ex);
            }

            txt_location.setText(Global.LichTrinhDaChonDeXem.getLocation());
            txt_desc.setText(Global.LichTrinhDaChonDeXem.getDescribe());
        }

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

    private void MakeRingTone() {
        try {
            String startDir = System.getProperty("user.dir") + "\\LibFile\\ringer.wav";
            System.out.println(startDir);
            File ringToneFile = new File(startDir);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(ringToneFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            //whatevers
        }
    }

    @FXML
    void btn_addClick(ActionEvent event) {

        //
        System.out.println("ADding...");
        //add new
        if (Global.ModeThemLichTrinh == 1) {
            System.out.println("insertinh...");
            LocalTime time = pk_time.getValue();
            String location = txt_location.getText();
            String desc = txt_desc.getText();
            LichTrinh temp = new LichTrinh(time, location, desc);
            StaticFunctions.lichTrinh = temp;

            //save other calendar-nhp----------
            if (time == null) {
                return;
            }

            String timeToSave = Global.dateCalendarSelected + " " + time + ":00";
            //System.out.println(timeToSave);

            int day = date_time.getValue().getDayOfMonth();
            Calender calender = new Calender(timeToSave, location, desc, day);
            CalenderBLL bll = new CalenderBLL();

            try {
                bll.InsertCalender(calender);
                System.out.println(calender.getDescribe());
                //if insert sucess then setnotify
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            // ...//System.out.println("timer-running---------------");              
                            @Override
                            public void run() {
                                //System.out.println("notifing"+calender.getDescribe());
                                try {
                                    List<Calender> ls = bll.GetPersonalCalenderInfuture();
                                    for (Calender l : ls) {
                                        //System.out.println(l.getDescribe());
                                        //neu khong tim thay co nghia la da xoa, khong cna thong bao
                                        if (l.getDescribe().equals(calender.getDescribe())) {
                                            // System.out.println("timer-running---------------");
                                            PopUp_Notification.run("Thông báo", calender.getDescribe(), "INFO");

                                            MakeRingTone();
                                            break;
                                        }
                                    }

                                } catch (SQLException ex) {
                                    Logger.getLogger(ThemLichTrinhController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });

                    }
                };

                //Calendar of system---------------------
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, Global.CurrentMonth - 1);
                calendar.set(Calendar.DATE, day);
                calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
                calendar.set(Calendar.MINUTE, time.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                //System.out.println(calendar.toString());
                Date dateSchedule = calendar.getTime();

                Timer timer = new Timer(calender.getDescribe());
                timer.schedule(timerTask, dateSchedule);

            } catch (SQLException ex) {
                Logger.getLogger(ThemLichTrinhController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //else just show detail.
        btn_exit.fire();
    }

    @FXML
    void btn_exitClick(ActionEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();

    }
}
