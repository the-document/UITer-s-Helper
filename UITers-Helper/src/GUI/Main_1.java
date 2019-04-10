package GUI;

// <editor-fold desc="import zone">

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// </editor-fold>

public class Main_1 extends Application {
    
    public static Stage primaryStage = null;
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Welcome.fxml"));       
        Scene scene = new Scene(root, 1174, 660);  
        primaryStage.setScene(scene);
        root.setOnMousePressed(e -> {
           xOffSet = e.getSceneX();
           yOffSet = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffSet);
            primaryStage.setY(e.getScreenY() - yOffSet);
            primaryStage.setOpacity(1f);
        });
        root.setOnMouseReleased(e -> {
            primaryStage.setOpacity(1.0f);
        });
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
