package GUI.controller;

// <editor-fold desc="import zone">
import BLL.MakeSchelude;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

// </editor-fold>
public class LoaderController implements Initializable {

    // <editor-fold defaultstate="collapsed" desc="Static zone">
    Stage window;
    String form;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="FXML variables zone">
    @FXML
    private JFXButton btn_showResult;
    
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXSpinner spn_loader;

    @FXML
    private HBox toolbox;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;
     // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="FXML functions zone">
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
        Platform.runLater(AnchorPaneMain::requestFocus);
        setKeyEvent();

        this.btn_showResult.setVisible(false);
        Loading(spn_loader, 1);
        MakeSchelude schedule=new MakeSchelude();
        schedule.start();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    btn_minimize.fire();
                    break;
                default:
                    break;
            }
        });
    }

    public void Loading(JFXSpinner spn_loader, double time_value)
    {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(spn_loader.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(time_value),
                        new KeyValue(spn_loader.progressProperty(), 1)
                )
        );
        
        timeline.setCycleCount((int) time_value);
        timeline.play();
        
        timeline.setOnFinished(eh->{
            this.btn_showResult.setVisible(true);    
        });
        
    }
    
    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/Subscribed.fxml");
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

    public void btn_showResult(ActionEvent event){
        form = "../view/CreateTimetableNow.fxml";
            madeFadeOut(event);
    }
}
