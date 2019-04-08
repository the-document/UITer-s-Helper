package GUI.controller;

/// import zone ///
import BLL.Global;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/// import zone ///

public class LoginController implements Initializable {
    
    /// static var zone ///
    String form;
    Stage window;
    /// static var zone ///
    
    /// fxml var zone ///
     @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private Button btn_setting1;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private Button btn_setting11;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private Hyperlink btn_lose;

    @FXML
    private JFXButton btn_Login;

    @FXML
    private JFXCheckBox cb_remember;

    @FXML
    private Button btn_setting;

    /// fxml var zone ///
    
    /// fxml function zone ///
    @FXML
    void btn_exitClick(ActionEvent event) {
        Global.ExitEvent(AnchorPaneMain);
    }

    @FXML
    void btn_loginClick(ActionEvent event) {
        String user_name = txt_user.getText();
        String password = txt_password.getText();
        /////////////////////////////
        ///// Xử lý here ////////////
        /////////////////////////////
        
        stack_pane.setDisable(false);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thông báo"));
        content.setBody(new Text("Đăng nhập thành công"));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("OK");
        btn.setOnAction(e -> {
           
            dialog.close();
            Parent root = null;
            form = "../view/Home.fxml";
            madeFadeOut(event);
            Global.stack_link.push("../view/Home.fxml");
           
        });
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        dialog.show();
    }

    @FXML
    void btn_loseClick(ActionEvent event) {

    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        Global.MinimizeEvent(event, AnchorPaneMain);
    }

    @FXML
    void btn_settingClick(ActionEvent event) {
        form = "../view/Setting.fxml";
        madeFadeOut(event);
    }

    @FXML
    void cb_rememberCheck(ActionEvent event) {
         if (cb_remember.isSelected())
        {
            cb_remember.setText("Đã ghi nhớ");
        }
        else
        {
            cb_remember.setText("Nhớ mật khẩu");
        }
    }
    
    /// fxml function zone ///
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Global.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
    }    
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
