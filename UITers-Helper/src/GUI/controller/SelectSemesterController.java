package GUI.controller;

import BLL.Global;
import BLL.HocKyBLL;
import DTO.HocKy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectSemesterController implements Initializable {

    String form;
    Stage window;
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXListView<Label> lv_semester;

    @FXML
    private JFXButton btn_next;

    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    public void initialize(URL url, ResourceBundle rb) {
        
        Global.AnimationShow(AnchorPaneMain);
        Global.AnimationMouseDragButton(btn_exit, "red");
        
        try {
            lv_semesterClick();
        } catch (SQLException ex) {
            Logger.getLogger(SelectSemesterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

        Parent root = FXMLLoader.load(getClass().getResource(form));
        Scene tableViewScene = new Scene(root);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }

    public void btn_backClick(ActionEvent event) throws Exception {
        form = "../view/Home.fxml";
        madeFadeOut(event);

    }

    public void btn_nextClick(ActionEvent event) throws Exception {
        form = "../view/SelectSubject.fxml";
        madeFadeOut(event);
    }
    public void lv_semesterClick() throws SQLException {
        // Sự kiện thêm item   
        HocKyBLL hkbll=new HocKyBLL();
        List<HocKy> lsHocKy= hkbll.GetAllHocKy();
        
        for (HocKy hocKy : lsHocKy) {
            Label lb = new Label(hocKy.getTenHK());
            lv_semester.getItems().addAll(lb);
        }
        
        lv_semester.setOnMouseClicked(e -> {
          //==============================
          // init hoc ky for Global here
          //==============================
        });
    }
}
