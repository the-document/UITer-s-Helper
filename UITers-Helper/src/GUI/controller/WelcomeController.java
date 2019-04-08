package GUI.controller;
/// import zone ///
import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
/// import zone ///

public class WelcomeController implements Initializable {
    /// static var zone ///
    Stage window;
    String form;
    /// static var zone ///
    
    /// fxml var zone ///
     @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXButton btn_launch;

    @FXML
    private HBox toolbox;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;
    /// fxml var zone ///
    
    /// fxml function zone ///
    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_launchClick(ActionEvent event) {
        form = "../view/Login.fxml";
        madeFadeOut(event); 
    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
         Global.MinimizeEvent(event, AnchorPaneMain);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Global.AnimationShow(AnchorPaneMain);
    }
    /// fxml function zone ///
    
    public void madeFadeOut(ActionEvent event) 
    {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished( e -> {
            try {
                LoadNextScene(event);
            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void LoadNextScene(ActionEvent event) throws Exception 
    {      
        Parent root = FXMLLoader.load(getClass().getResource(form));      
        Scene tableViewScene = new Scene(root);     
        window = (Stage)((Node)event.getSource()).getScene().getWindow();    
        window.setScene(tableViewScene);
        Global.SetStageDrag(root, window, event);
        window.show();
    }
 
          
}
