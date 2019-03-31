/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nguyen Hong Phuc
 */

enum EducationProgram{
    CQUI,
    CTTT,
    CLC,
    CNTN,
    KSTN
}
public class Global {
    private static double xOffSet = 0;
    private static double yOffSet = 0;
    public static final String CQUI = "CQUI";
    public static final String CTTT = "CTTT";
    public static final String CLC = "CLC";
    public static final String CNTN = "CNTN";
    public static final String KSTN = "KSTN";
    static public void SetStageDrag(Parent root, Stage window, ActionEvent event)
    {
       
        root.setOnMousePressed(e -> {
           xOffSet = e.getSceneX();
           yOffSet = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() - xOffSet);
            window.setY(e.getScreenY() - yOffSet);
            window.setOpacity(0.8f);
        });
        root.setOnMouseReleased(e -> {
            window.setOpacity(1.0f);
        });
      
  
        window.initStyle(StageStyle.UNDECORATED);
        window.setResizable(false);
    }
}

