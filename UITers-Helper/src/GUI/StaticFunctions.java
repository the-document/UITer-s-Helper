/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.Global;
import GUI.controller.WelcomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StaticFunctions {

    public static Stack<String> stack_link = new Stack<String>();
    private static double xOffSet = 0;
    private static double yOffSet = 0;
    private String URL_form;
    public static boolean IsDarkMode = true;
    public static LichTrinh lichTrinh = new LichTrinh();
    static public void SetStageDrag(Parent root, Stage window, ActionEvent event) {
        //window.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(e -> {
            xOffSet = e.getSceneX();
            yOffSet = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() - xOffSet);
            window.setY(e.getScreenY() - yOffSet);
        });
        root.setOnMouseReleased(e -> {
        });

      
        window.setResizable(false);
    }

    static public void AnimationShow(AnchorPane AnchorPaneMain) {
        AnchorPaneMain.setOpacity(0);
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(0);
        fade_trands.setToValue(1);
        fade_trands.play();
    }

    static public void ExitEvent(AnchorPane AnchorPaneMain) {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(800));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished(e -> {
            try {
                System.exit(0);

            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    static public void MinimizeEvent(ActionEvent event, AnchorPane AnchorPaneMain) {
        @SuppressWarnings("UnusedAssignment")
        Stage stage = (Stage) AnchorPaneMain.getScene().getWindow();
        stage = (Stage) ((JFXButton) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void madeFadeOut(ActionEvent event, AnchorPane AnchorPaneMain) {
        FadeTransition fade_trands = new FadeTransition();
        fade_trands.setDuration(new Duration(500));
        fade_trands.setNode(AnchorPaneMain);
        fade_trands.setFromValue(1);
        fade_trands.setToValue(0);
        fade_trands.play();
        fade_trands.setOnFinished(e -> {
            try {
                Stage window;
                Parent root = FXMLLoader.load(getClass().getResource("../view/" + this.URL_form));
                Scene tableViewScene = new Scene(root);
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                Global.SetStageDrag(root, window, event);
                window.show();
            } catch (Exception ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

   

    static public JFXDialog getDialogStatic(StackPane stack_pane, String heading, String body, JFXButton btn) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        return dialog;
    }

    static public JFXDialog getDialog(StackPane stack_pane, String heading, String body) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stack_pane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("OK");
        btn.setOnAction(e -> {
            dialog.close();

        });
        btn.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn.fire();
                    break;               
                default:
                    break;
            }
        });     
        content.setActions(btn);
        dialog.setOnDialogClosed(e -> stack_pane.setDisable(true));
        return dialog;
    }
    
    
    static public void WriteSettingLog(String str_old_status, String str_new_status) throws FileNotFoundException, IOException {
        int i = 0;
        String save_tmp[] = new String[100];

        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            //FileOutputStream fos = new FileOutputStream("C:/Users/Admin/Documents/GitHub/UITer-s-Helper/UITers-Helper/src/GUI/SettingLog.txt");
            // DataOutputStream dos = new DataOutputStream(fos);
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/Admin/Documents/GitHub/UITer-s-Helper/UITers-Helper/src/GUI/SettingLog.txt"));

            //Bước 2: Ghi dữ liệu
            //dos.writeBytes("Status setting: 2");
            String textInALine = br.readLine();

            while (textInALine != null) {
                System.out.println(textInALine);
                save_tmp[i++] = textInALine;
                textInALine = br.readLine();

            }
            br.close();
            //Bước 3: Đóng luồng
            //fos.close();
            //dos.close();
            System.out.println("Done!");
        } catch (IOException ex) {
        }

        DataOutputStream dos;
        try (FileOutputStream fos = new FileOutputStream("C:/Users/Admin/Documents/GitHub/UITer-s-Helper/UITers-Helper/src/GUI/SettingLog.txt")) {
            dos = new DataOutputStream(fos);
            for (int j = 0; j < i; j++) {
                if (save_tmp[j].compareTo(str_old_status) == 0) {
                    save_tmp[j] = str_new_status;
                }
                dos.writeBytes(save_tmp[j]);
                dos.write(System.getProperty("line.separator").getBytes());
            }
        }
        dos.close();
    }
}
