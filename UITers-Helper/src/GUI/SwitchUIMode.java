/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Admin
 */
public class SwitchUIMode {

    public String fxml_name;

    public SwitchUIMode() {
        fxml_name = "";
    }

    public String Switch(String _fxml_name) {
        fxml_name = "";
        fxml_name += "../view/";
        fxml_name += _fxml_name;
        if (StaticFunctions.IsDarkMode == true) {
        
            fxml_name += ".fxml";
            return fxml_name;
        }
        else {
      
            fxml_name += "_Normal.fxml";
            return fxml_name;
        }

    }
}
