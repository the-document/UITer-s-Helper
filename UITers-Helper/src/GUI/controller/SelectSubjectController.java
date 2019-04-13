package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Global;
import GUI.StaticFunctions;
import BLL.MonHocBLL;
import BLL.NganhHocBLL;
import DTO.MonHoc;
import DTO.NganhHoc;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

// </editor-fold>
public class SelectSubjectController implements Initializable {

    // <editor-fold desc="Static variables zone">
    String form;
    Stage window;
    
    //Phuc_______________________________________________________
    List<NganhHoc> lsNganhHoc;
    List<MonHoc> lsMonHocs;

    // </editor-fold>
    
    // <editor-fold desc="FXML variabls zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXListView<Label> lv_subject;

    @FXML
    private JFXTextField txt_subject;

    @FXML
    private JFXComboBox<String> cbb_subject;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXChipView<String> cv_subject;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;
    
    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private StackPane stack_pane;
    
    @FXML
    private Label lb_Notify;

    // </editor-fold>
    
    
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_addClick(ActionEvent event)  {
        String key = txt_subject.getText();
        txt_subject.clear();
        //check empty-----------------------------------------
        if(key.isEmpty())
        {
            lb_Notify.setText("Mã môn không được để trống.");
            lb_Notify.setVisible(true);
            return;
        }
        
        //check valid-----------------------------------------
        MonHocBLL mhbll=new MonHocBLL();
        MonHoc monHoc;
        try {
            monHoc = mhbll.GetMonHoc(key);
        } catch (SQLException ex) {
            Logger.getLogger(SelectSubjectController.class.getName()).log(Level.SEVERE, null, ex);
            
            lb_Notify.setText("Lỗi kết nối đến hệ thống.");
            lb_Notify.setVisible(true);
            return;
        }
        if(monHoc==null){
            lb_Notify.setText("Mã môn không tồn tại trong hệ thống.");
            lb_Notify.setVisible(true);
            return;
        }
                
        //add to list ui and ls logic------------------------
        int checkCount=Global.lsMonHocSelected.size();
            
        Global.lsMonHocSelected.put(key,monHoc);
        System.out.println(Global.lsMonHocSelected.toString());
            
        //check add sucess or exist------------------------------------
        if(checkCount!=Global.lsMonHocSelected.size())
        {
            cv_subject.getChips().add(key+"("+monHoc.getTenMonHoc()+")");      
            SetVisibleNextButton();
        }
        
        System.out.println(Global.lsMonHocSelected.toString());
        lb_Notify.setVisible(false);
    }

    @FXML
    void btn_backClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
    }
    
    @FXML
    void btn_deleteClick(ActionEvent event) {
        cv_subject.getChips().clear();
        
        //--------------------------------------------------
        Global.lsMonHocSelected.clear();
        SetVisibleNextButton();
    }


    @FXML
    void btn_exitClick(ActionEvent event) {
        StaticFunctions.ExitEvent(AnchorPaneMain);

    }

    @FXML
    void btn_minimizeClick(ActionEvent event) {
        StaticFunctions.MinimizeEvent(event, AnchorPaneMain);

    }

    @FXML
    void btn_nextClick(ActionEvent event) {
        
        //------------------------------------------
        form = "../view/SelectMethodCreate.fxml";
        madeFadeOut(event); 
    }

    @FXML
    void cbb_userClick(ActionEvent event) {

    }

    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticFunctions.AnimationShow(AnchorPaneMain);
        stack_pane.setDisable(true);
        Platform.runLater(AnchorPaneMain::requestFocus);

        btn_next.setVisible(false);
        lb_Notify.setVisible(false);
        lv_subject.getItems().clear();
        Global.NGANHHOC="";
        Global.lsMonHocSelected.clear();
        
        
        String text = "Xin chào, 17520433";
        init_cbb_user(text);
        
        try {
            init_cbb_subjecT();
        } catch (SQLException ex) {
            System.out.println("ERR load Nganh Hoc");
            Logger.getLogger(SelectSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        init_lv_subject();
        setKeyEvent();

    }
    
    
    public void madeFadeOut(ActionEvent event) {
        StaticFunctions.stack_link.push("../view/SelectSubject.fxml");
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
        StaticFunctions.SetStageDrag(root, window, event);
        window.show();
    }

    public void setKeyEvent() {
        AnchorPaneMain.setOnKeyPressed(e -> {
            switch (e.getCode()) {

                case ESCAPE:
                    btn_minimize.fire();
                    break;
                case LEFT:
                    btn_back.fire();
                    break;

                default:
                    break;
            }
        });
        btn_next.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_next.fire();
                    break;

            }
        });
        txt_subject.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    btn_add.fire();
                    break;

            }
        });
    }
    
    public void init_lv_subject() {         
        try
        {
           LoadAllSubjectOfUser();
        }
        catch (SQLException e)
        {
            Label MonHocLabel = new Label("No connect");
            lv_subject.getItems().addAll(MonHocLabel);         
        }

        lv_subject.setOnMouseClicked(e -> {
            
            //add subject here--------------------------------
            int checkCount=Global.lsMonHocSelected.size();
            
            MonHoc monHoc=lsMonHocs.get(lv_subject.getSelectionModel().getSelectedIndex());
            Global.lsMonHocSelected.put(monHoc.getMaMonHoc(),monHoc);
            System.out.println(Global.lsMonHocSelected.toString());
            
            //check add sucess or exist------------------------------------
            if(checkCount!=Global.lsMonHocSelected.size())
            {
                String id = lv_subject.getSelectionModel().getSelectedItem().getText();
                cv_subject.getChips().add(id);                      
                SetVisibleNextButton();
            }
            System.out.println(Global.lsMonHocSelected.toString());
        });
    }

    public void init_cbb_user(String text) {
        ObservableList<String> list = FXCollections.observableArrayList("Trang chủ", "Thời khóa biểu", "Cài đặt", "Đăng xuất");
        cbb_user.setPromptText(text);
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            switch (cbb_user.getValue()) {
                case "Trang chủ":
                    form = "../view/Home.fxml";
                    madeFadeOut(e);
                    break;
                case "Thời khóa biểu":
                    form = "../view/CreateTimetableNow.fxml";
                    madeFadeOut(e);
                    break;
                case "Cài đặt":
                    form = "../view/Setting.fxml";
                    madeFadeOut(e);
                    break;
                case "Đăng xuất":
                    form = "../view/Login.fxml";
                    madeFadeOut(e);
                    break;
                default:
                    break;
            }

        });
    }

    public void init_cbb_subjecT() throws SQLException {
        
        //lay tat ca nganh hoc dang co------------------------------------
        NganhHocBLL nhbll=new NganhHocBLL();
        lsNganhHoc=new ArrayList<NganhHoc>();
        lsNganhHoc=nhbll.GetAllNganhHoc();
        
        List<String> lsNameNganhHoc=new ArrayList<String>();
        for (NganhHoc nh : lsNganhHoc) {
            lsNameNganhHoc.add(nh.getTenNganh());
        }
        
        ObservableList<String> list = FXCollections.observableArrayList(lsNameNganhHoc);

        cbb_subject.setPromptText("Chọn khoa");
        cbb_subject.getSelectionModel().select(1);
        cbb_subject.getItems().clear();
        cbb_subject.setItems(list);

        cbb_subject.setOnAction(e -> {
            
            int index=cbb_subject.getSelectionModel().getSelectedIndex();
            Global.NGANHHOC = lsNganhHoc.get(index).getMaNganh();
              
            //reload subject theo khoa-------------------------------
            try {    
                LoadAllSubjectOfUser();
            } catch (SQLException ex) {
                Logger.getLogger(SelectSubjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //setsize------------------------------------
            int c=cbb_subject.getItems().size() ;
            c=c<5?c:5;
            cbb_subject.setVisibleRowCount(c);
        });
    

    }

    //-----------------------------------------------------------------
    private void LoadAllSubjectOfUser() throws SQLException{
        
        lv_subject.getItems().clear();
        
        MonHocBLL mhbll = new MonHocBLL();       
        lsMonHocs = mhbll.GetAllMonHoc(Global.MSSV, Global.NGANHHOC, Global.HOCKY);
        
        if (lsMonHocs != null) {
            for (MonHoc m : lsMonHocs) {
                Label MonHocLabel = new Label(m.getMaMonHoc() + " - " + m.getTenMonHoc());
                lv_subject.getItems().addAll(MonHocLabel);
            }
            
            int h=lv_subject.getItems().size() * 40;
            h=h<240?h:240;
            lv_subject.setPrefHeight(h);
        } else {
            Label MonHocLabel = new Label("No connect");
            lv_subject.getItems().addAll(MonHocLabel);
        }
    }
    
    private void SetVisibleNextButton(){
        if(!cv_subject.getChips().isEmpty())
            btn_next.setVisible(true);
        else
            btn_next.setVisible(false);
    }
}
