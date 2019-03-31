/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import com.sun.glass.ui.Window;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class Main_1 extends Application {
    public static Stage primaryStage = null;
    private double xOffSet = 0;
    private double yOffSet = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/Welcome.fxml"));       
        Scene scene = new Scene(root, 960, 660);    
        primaryStage.setScene(scene);
        root.setOnMousePressed(e -> {
           xOffSet = e.getSceneX();
           yOffSet = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffSet);
            primaryStage.setY(e.getScreenY() - yOffSet);
            primaryStage.setOpacity(0.8f);
        });
        root.setOnMouseReleased(e -> {
            primaryStage.setOpacity(1.0f);
        });
        
  
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
            
         // System.out.println("URL: "+getClass().getResource("view/Welcome.fxml"));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
