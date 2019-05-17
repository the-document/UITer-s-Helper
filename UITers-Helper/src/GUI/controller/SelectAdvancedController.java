package GUI.controller;

// <editor-fold desc="import zone">
import BLL.Global;
import BLL.ThuatToanTaoTKB;
import DTO.LopHoc;
import DTO.MonHoc;
import GUI.StaticFunctions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
// </editor-fold>

public class SelectAdvancedController implements Initializable {

    //ls lop hoc da mo co the chon vao tkb
    List<LopHoc> lsLopHocLT = new ArrayList<>();
    List<LopHoc> lsLopHocTH = new ArrayList<>();
    List<LopHoc> lsLopHocSelected = new ArrayList<>();

    // <editor-fold desc="Static variables zone">
    Stage window;
    String form;
    String style = "-fx-border-color : white";
    String style2 = "-fx-border-color : transparent";
    String clickStyle="-fx-border-color: #18A0FB";
    String buttonHighLightStyle="-fx-border-color: #fffafa";

    // </editor-fold>
    // <editor-fold desc="FXML variables zone">
    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private JFXComboBox<String> cbb_user;

    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_exit;

    @FXML
    private JFXButton btn_minimize;

    @FXML
    private ScrollPane pane_subject;

    @FXML
    private JFXButton btn_t3_123;

    @FXML
    private JFXButton btn_t3_45;

    @FXML
    private JFXButton btn_t2_678;

    @FXML
    private JFXButton btn_t2_910;

    @FXML
    private JFXButton btn_t2_123;

    @FXML
    private JFXButton btn_t2_45;

    @FXML
    private JFXButton btn_t3_678;

    @FXML
    private JFXButton btn_t3_910;

    @FXML
    private JFXButton btn_t4_123;

    @FXML
    private JFXButton btn_t4_45;

    @FXML
    private JFXButton btn_t4_678;

    @FXML
    private JFXButton btn_t4_910;

    @FXML
    private JFXButton btn_t5_123;

    @FXML
    private JFXButton btn_t5_45;

    @FXML
    private JFXButton btn_t5_678;

    @FXML
    private JFXButton btn_t5_910;

    @FXML
    private JFXButton btn_t6_678;

    @FXML
    private JFXButton btn_t6_910;

    @FXML
    private JFXButton btn_t6_123;

    @FXML
    private JFXButton btn_t6_45;

    @FXML
    private JFXButton btn_t7_123;

    @FXML
    private JFXButton btn_t7_45;

    @FXML
    private JFXButton btn_t7_678;

    @FXML
    private JFXButton btn_t7_910;

    // </editor-fold>
    // <editor-fold desc="FXML functions zone">
    @FXML
    void btn_backClick(ActionEvent event) {
        form = StaticFunctions.stack_link.pop();
        madeFadeOut(event);
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

        StaticFunctions.stack_link.push("../view/SelectAdvanced.fxml");
        form = "../view/CreateTimetableNow.fxml";
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

        init_pane_subject();
        setKeyEvent();
        String text = "Xin chào, 17520433";
        init_cbb_user(text);
        init_arrButton();

        //clear before load ls from thuat toan tkb
        lsLopHocLT.clear();
        lsLopHocTH.clear();
        lsLopHocSelected.clear();
        HideTextOfButton();
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

    }

    public void init_pane_subject() {
        pane_subject.setContent(null);
        HBox root = new HBox();
        root.setSpacing(20);

        for (MonHoc monhoc : Global.lsMonHocSelected.values()) {
            Button btn = new Button();
            btn.setText(monhoc.getTenMonHoc());
            btn.setId(monhoc.getMaMonHoc());
            btn.setMaxSize(120, 40);
            btn.setMinSize(120, 40);
            initButton(btn);
            root.getChildren().add(btn);
        }

        pane_subject.setContent(root);
    }

    //button in menur bar subject----------------------------------------------
    public void initButton(Button btn) {
        String css = this.getClass().getResource("../css/button_chipitem.css").toExternalForm();
        btn.getStylesheets().clear();
        btn.getStylesheets().add(css);
//        btn.setOnMouseDragged(e -> {
//
//        });
//        btn.setOnMouseReleased(e -> {
//
//            btn_t2_123.setStyle(style2);
//            btn.setStyle(style);
//            System.out.println(e.getX());
//            System.out.println(e.getX());
//            System.out.println(btn_t2_123.getLayoutX());
//            System.out.println(btn_t2_123.getLayoutX());
//            System.out.println(btn_t2_45.getLayoutX());
//            System.out.println(btn_t2_45.getLayoutX());
//
//        });

        //click subject btn event---------------------------
        btn.setOnAction((ActionEvent t) -> {
            
            btn.setStyle(clickStyle);
            
            if (lsLopHocLT.isEmpty()) {
                lsLopHocLT = ThuatToanTaoTKB.dsFilterLT;
                lsLopHocTH = ThuatToanTaoTKB.dsFilterTH;
            }

            //suggest all can choose-------------------------------------
            for (LopHoc lopHoc : lsLopHocLT) {
                if (lopHoc.getmaMonHoc().equals(btn.getId())) {
                    
                    //Get course TH (if can)------------------------------------
                    LopHoc loptemp=null;
                    for (LopHoc l : lsLopHocTH) {
                        if (l.getMaLop().equals(lopHoc.getMaLop()+".1")) {
                            loptemp=l;
                            break;
                        }
                    }
                    
                    if(loptemp==null){
                        System.out.println(lopHoc.getMaLop());
                        HighLightButtons(lopHoc,0); //flag 0 mean just hightlight temp
                    }
                    else
                    {
                        //check overlap TH with course selecred-----------------
                        boolean check=false;
                        for (LopHoc lopselected : lsLopHocSelected) {
                            if(lopselected.isOverlap(loptemp))
                            {
                                check=true;
                                break;
                            }      
                        }
                        
                        if(!check){
                            System.out.println(lopHoc.getMaLop());
                            HighLightButtons(lopHoc,0); //flag 0 mean just hightlight temp
                        }
                        
                        
                        //System.out.println(loptemp.getMaLop());
                        //HighLightButtons(loptemp,0); //flag 0 mean just hightlight temp
                        
                    }
                    
                }
            }
            
            //redraw subject selected (override)------------------------
            for (LopHoc l : lsLopHocSelected) {
                HighLightButtons(l,1); //flag 1 mean this btn has selected
            }
            System.out.println("onclick");
        });
    }

    //==========================================================================
    private void HideTextOfButton() {
        btn_t2_123.setText("");
        btn_t2_45.setText("");
        btn_t3_123.setText("");
        btn_t3_45.setText("");
        btn_t4_123.setText("");
        btn_t4_45.setText("");
        btn_t5_123.setText("");
        btn_t5_45.setText("");
        btn_t6_123.setText("");
        btn_t6_45.setText("");
        btn_t7_123.setText("");
        btn_t7_45.setText("");

        btn_t2_678.setText("");
        btn_t2_910.setText("");
        btn_t3_678.setText("");
        btn_t3_910.setText("");
        btn_t4_678.setText("");
        btn_t4_910.setText("");
        btn_t5_678.setText("");
        btn_t5_910.setText("");
        btn_t6_678.setText("");
        btn_t6_910.setText("");
        btn_t7_678.setText("");
        btn_t7_910.setText("");
        
        //reset border-------------------------------------
        btn_t2_123.setStyle(style2);
        btn_t2_45.setStyle(style2);
        btn_t3_123.setStyle(style2);
        btn_t3_45.setStyle(style2);
        btn_t4_123.setStyle(style2);
        btn_t4_45.setStyle(style2);
        btn_t5_123.setStyle(style2);
        btn_t5_45.setStyle(style2);
        btn_t6_123.setStyle(style2);
        btn_t6_45.setStyle(style2);
        btn_t7_123.setStyle(style2);
        btn_t7_45.setStyle(style2);

        btn_t2_678.setStyle(style2);
        btn_t2_910.setStyle(style2);
        btn_t3_678.setStyle(style2);
        btn_t3_910.setStyle(style2);
        btn_t4_678.setStyle(style2);
        btn_t4_910.setStyle(style2);
        btn_t5_678.setStyle(style2);
        btn_t5_910.setStyle(style2);
        btn_t6_678.setStyle(style2);
        btn_t6_910.setStyle(style2);
        btn_t7_678.setStyle(style2);
        btn_t7_910.setStyle(style2);
        
        //reset id-------------------------------------
        btn_t2_123.setId("");
        btn_t2_45.setId("");
        btn_t3_123.setId("");
        btn_t3_45.setId("");
        btn_t4_123.setId("");
        btn_t4_45.setId("");
        btn_t5_123.setId("");
        btn_t5_45.setId("");
        btn_t6_123.setId("");
        btn_t6_45.setId("");
        btn_t7_123.setId("");
        btn_t7_45.setId("");

        btn_t2_678.setId("");
        btn_t2_910.setId("");
        btn_t3_678.setId("");
        btn_t3_910.setId("");
        btn_t4_678.setId("");
        btn_t4_910.setId("");
        btn_t5_678.setId("");
        btn_t5_910.setId("");
        btn_t6_678.setId("");
        btn_t6_910.setId("");
        btn_t7_678.setId("");
        btn_t7_910.setId("");
    }

    private void SetTextForButtonThu2(LopHoc lop, String textShow ,int flag) {
        
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
        
        switch (lop.getTiet()) {
            case "123":
                btn_t2_123.setStyle(buttonHighLightStyle);
                btn_t2_123.setText(textShow);
                btn_t2_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t2_123.setStyle(buttonHighLightStyle);
                btn_t2_123.setText(textShow);
                btn_t2_123.setMaxHeight(170);
                btn_t2_45.setVisible(false);
                btn_t2_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t2_123.setStyle(buttonHighLightStyle);
                btn_t2_123.setText(textShow);
                btn_t2_123.setMaxHeight(136);
                btn_t2_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t2_45.setStyle(buttonHighLightStyle);
                btn_t2_45.setText(textShow);
                btn_t2_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t2_678.setStyle(buttonHighLightStyle);
                btn_t2_678.setText(textShow);
                btn_t2_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t2_678.setStyle(buttonHighLightStyle);
                btn_t2_678.setText(textShow);
                btn_t2_678.setMaxHeight(170);
                btn_t2_910.setVisible(false);
                btn_t2_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t2_678.setStyle(buttonHighLightStyle);
                btn_t2_678.setText(textShow);
                btn_t2_678.setMaxHeight(136);
                btn_t2_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t2_910.setStyle(buttonHighLightStyle);
                btn_t2_910.setText(textShow);
                btn_t2_910.setId(lop.getMaLop());
                break;
        }
    }

    private void SetTextForButtonThu3(LopHoc lop, String textShow,int flag) {
        
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
        
        switch (lop.getTiet()) {
            case "123":
                btn_t3_123.setStyle(buttonHighLightStyle);
                btn_t3_123.setText(textShow);
                btn_t3_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t3_123.setStyle(buttonHighLightStyle);
                btn_t3_123.setText(textShow);
                btn_t3_123.setMaxHeight(170);
                btn_t3_45.setVisible(false);
                btn_t3_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t3_123.setStyle(buttonHighLightStyle);
                btn_t3_123.setText(textShow);
                btn_t3_123.setMaxHeight(136);
                btn_t3_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t3_45.setStyle(buttonHighLightStyle);
                btn_t3_45.setText(textShow);
                btn_t3_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t3_678.setStyle(buttonHighLightStyle);
                btn_t3_678.setText(textShow);
                btn_t3_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t3_678.setStyle(buttonHighLightStyle);
                btn_t3_678.setText(textShow);
                btn_t3_678.setMaxHeight(170);
                btn_t3_910.setVisible(false);
                btn_t3_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t3_678.setStyle(buttonHighLightStyle);
                btn_t3_678.setText(textShow);
                btn_t3_678.setMaxHeight(136);
                btn_t3_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t3_910.setStyle(buttonHighLightStyle);
                btn_t3_910.setText(textShow);
                btn_t3_910.setId(lop.getMaLop());
                break;
        }
    }

   private void SetTextForButtonThu4(LopHoc lop, String textShow,int flag) {
       
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
       
        switch (lop.getTiet()) {
            case "123":
                btn_t4_123.setStyle(buttonHighLightStyle);
                btn_t4_123.setText(textShow);
                btn_t4_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t4_123.setStyle(buttonHighLightStyle);
                btn_t4_123.setText(textShow);
                btn_t4_123.setMaxHeight(170);
                btn_t4_45.setVisible(false);
                btn_t4_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t4_123.setStyle(buttonHighLightStyle);
                btn_t4_123.setText(textShow);
                btn_t4_123.setMaxHeight(136);
                btn_t4_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t4_45.setStyle(buttonHighLightStyle);
                btn_t4_45.setText(textShow);
                btn_t4_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t4_678.setStyle(buttonHighLightStyle);
                btn_t4_678.setText(textShow);
                btn_t4_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t4_678.setStyle(buttonHighLightStyle);
                btn_t4_678.setText(textShow);
                btn_t4_678.setMaxHeight(170);
                btn_t4_910.setVisible(false);
                btn_t4_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t4_678.setStyle(buttonHighLightStyle);
                btn_t4_678.setText(textShow);
                btn_t4_678.setMaxHeight(136);
                btn_t4_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t4_910.setStyle(buttonHighLightStyle);
                btn_t4_910.setText(textShow);
                btn_t4_910.setId(lop.getMaLop());
                break;
        }
    }
   
   private void SetTextForButtonThu5(LopHoc lop, String textShow,int flag) {
       
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
       
        switch (lop.getTiet()) {
            case "123":
                btn_t5_123.setStyle(buttonHighLightStyle);
                btn_t5_123.setText(textShow);
                btn_t5_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t5_123.setStyle(buttonHighLightStyle);
                btn_t5_123.setText(textShow);
                btn_t5_123.setMaxHeight(170);
                btn_t5_45.setVisible(false);
                btn_t5_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t5_123.setStyle(buttonHighLightStyle);
                btn_t5_123.setText(textShow);
                btn_t5_123.setMaxHeight(136);
                btn_t5_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t5_45.setStyle(buttonHighLightStyle);
                btn_t5_45.setText(textShow);
                btn_t5_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t5_678.setStyle(buttonHighLightStyle);
                btn_t5_678.setText(textShow);
                btn_t5_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t5_678.setStyle(buttonHighLightStyle);
                btn_t5_678.setText(textShow);
                btn_t5_678.setMaxHeight(170);
                btn_t5_910.setVisible(false);
                btn_t5_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t5_678.setStyle(buttonHighLightStyle);
                btn_t5_678.setText(textShow);
                btn_t5_678.setMaxHeight(136);
                btn_t5_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t5_910.setStyle(buttonHighLightStyle);
                btn_t5_910.setText(textShow);
                btn_t5_910.setId(lop.getMaLop());
                break;
        }
    }
   
   private void SetTextForButtonThu6(LopHoc lop, String textShow,int flag) {
       
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
       
        switch (lop.getTiet()) {
            case "123":
                btn_t6_123.setStyle(buttonHighLightStyle);
                btn_t6_123.setText(textShow);
                btn_t6_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t6_123.setStyle(buttonHighLightStyle);
                btn_t6_123.setText(textShow);
                btn_t6_123.setMaxHeight(170);
                btn_t6_45.setVisible(false);
                btn_t6_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t6_123.setStyle(buttonHighLightStyle);
                btn_t6_123.setText(textShow);
                btn_t6_123.setMaxHeight(136);
                btn_t6_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t6_45.setStyle(buttonHighLightStyle);
                btn_t6_45.setText(textShow);
                btn_t6_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t6_678.setStyle(buttonHighLightStyle);
                btn_t6_678.setText(textShow);
                btn_t6_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t6_678.setStyle(buttonHighLightStyle);
                btn_t6_678.setText(textShow);
                btn_t6_678.setMaxHeight(170);
                btn_t6_910.setVisible(false);
                btn_t6_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t6_678.setStyle(buttonHighLightStyle);
                btn_t6_678.setText(textShow);
                btn_t6_678.setMaxHeight(136);
                btn_t6_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t6_910.setStyle(buttonHighLightStyle);
                btn_t6_910.setText(textShow);
                btn_t6_910.setId(lop.getMaLop());
                break;
        }
    }
   
   private void SetTextForButtonThu7(LopHoc lop, String textShow,int flag) {
       
        if (flag == 0) {
            buttonHighLightStyle = "-fx-border-color: #fffafa";
        } else {
            buttonHighLightStyle = "-fx-border-color: #6842f4";
        }
       
        switch (lop.getTiet()) {
            case "123":
                btn_t7_123.setStyle(buttonHighLightStyle);
                btn_t7_123.setText(textShow);
                btn_t7_123.setId(lop.getMaLop());
                break;

            case "12345":
                btn_t7_123.setStyle(buttonHighLightStyle);
                btn_t7_123.setText(textShow);
                btn_t7_123.setMaxHeight(170);
                btn_t7_45.setVisible(false);
                btn_t7_123.setId(lop.getMaLop());
                break;

            case "1234":
                btn_t7_123.setStyle(buttonHighLightStyle);
                btn_t7_123.setText(textShow);
                btn_t7_123.setMaxHeight(136);
                btn_t7_123.setId(lop.getMaLop());
                break;

            case "45":
                btn_t7_45.setStyle(buttonHighLightStyle);
                btn_t7_45.setText(textShow);
                btn_t7_45.setId(lop.getMaLop());
                break;

            case "678":
                btn_t7_678.setStyle(buttonHighLightStyle);
                btn_t7_678.setText(textShow);
                btn_t7_678.setId(lop.getMaLop());
                break;

            case "67890":
                btn_t7_678.setStyle(buttonHighLightStyle);
                btn_t7_678.setText(textShow);
                btn_t7_678.setMaxHeight(170);
                btn_t7_910.setVisible(false);
                btn_t7_678.setId(lop.getMaLop());
                break;

            case "6789":
                btn_t7_678.setStyle(buttonHighLightStyle);
                btn_t7_678.setText(textShow);
                btn_t7_678.setMaxHeight(136);
                btn_t7_678.setId(lop.getMaLop());
                break;

            case "90":
                btn_t7_910.setStyle(buttonHighLightStyle);
                btn_t7_910.setText(textShow);
                btn_t7_910.setId(lop.getMaLop());
                break;
        }
    }

    private void HighLightButtons(LopHoc lop,int flag) {
        
        //flag to show type of border: temp,selected
        String textShow = lop.getMaLop() + "\n"
                + lop.getTenGiangVien() + "\n P. "
                + lop.getPhong() + "\n"
                + lop.getNgayBatDau() + "\n"
                + lop.getNgayKetThuc();

        switch (lop.getThu()) {
            case "2":
                SetTextForButtonThu2(lop, textShow,flag);
                break;
            case "3":
                SetTextForButtonThu3(lop, textShow,flag);
                break;
            case "4":
                SetTextForButtonThu4(lop, textShow,flag);
                break;
            case "5":
                SetTextForButtonThu5(lop, textShow,flag);
                break;
            case "6":
                SetTextForButtonThu6(lop, textShow,flag);
                break;
            case "7":
                SetTextForButtonThu7(lop, textShow,flag);
                break;
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
        StaticFunctions.SetStageDrag(root, window, event);
        window.show();

    }

    public void init_cbb_user(String text) {
        ObservableList<String> list = FXCollections.observableArrayList("Trang chủ", "Thời khóa biểu", "Cài đặt", "Đăng xuất");
        cbb_user.setPromptText(text);
        cbb_user.getSelectionModel().select(1);
        cbb_user.getItems().clear();
        cbb_user.setItems(list);

        cbb_user.setOnAction(e -> {
            StaticFunctions.stack_link.push("../view/SelectAdvanced.fxml");
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

    public void init_context_menu(JFXButton btn, String id) {
        ContextMenu ct = new ContextMenu();

        MenuItem item_info = new MenuItem("Chi tiết");
        item_info.setStyle("-fx-text-fill: white;");
        item_info.setOnAction(e -> {
            System.out.println("info");
        });

        MenuItem item_switch = new MenuItem("Đổi lớp");
        item_switch.setStyle("-fx-text-fill: white;");
        item_switch.setOnAction(e -> {
            System.out.println("switch");
        });

        ct.getItems().addAll(item_info, item_switch);
        ct.setStyle("-fx-background-color: transparent;");
        btn.setContextMenu(ct);
    }
    
    private void InitOnclickButtons(JFXButton btn){
        //init button in timetable
        btn.setOnAction((t) -> {
            
            for (LopHoc lopHoc : lsLopHocLT) {
                if (lopHoc.getMaLop().equals(btn.getId())) {
                    lsLopHocSelected.add(lopHoc);
                    for (LopHoc l : lsLopHocTH) {
                        if (l.getMaLop().equals(lopHoc.getMaLop()+".1")) {
                            lsLopHocSelected.add(l);
                            break;
                        }
                    }
                    break;
                }
            }
            
            HideTextOfButton();
            
            //redraw subject selected (override)------------------------
            for (LopHoc l : lsLopHocSelected) {
                HighLightButtons(l,1); //flag 1 mean this btn has selected
            }
            System.out.println("size:-"+lsLopHocSelected.size());
        });
    }

    public void init_arrButton() {
        
        //init  contex menu - onclick right mouse
        init_context_menu(btn_t2_123, btn_t2_123.getText());
        init_context_menu(btn_t2_45, btn_t2_45.getText());
        init_context_menu(btn_t2_678, btn_t2_678.getText());
        init_context_menu(btn_t2_910, btn_t2_910.getText());

        init_context_menu(btn_t3_123, btn_t3_123.getText());
        init_context_menu(btn_t3_45, btn_t3_45.getText());
        init_context_menu(btn_t3_678, btn_t3_678.getText());
        init_context_menu(btn_t3_910, btn_t3_910.getText());

        init_context_menu(btn_t5_123, btn_t5_123.getText());
        init_context_menu(btn_t5_45, btn_t5_45.getText());
        init_context_menu(btn_t5_678, btn_t5_678.getText());
        init_context_menu(btn_t5_910, btn_t5_910.getText());

        init_context_menu(btn_t6_123, btn_t6_123.getText());
        init_context_menu(btn_t6_45, btn_t6_45.getText());
        init_context_menu(btn_t6_678, btn_t6_678.getText());
        init_context_menu(btn_t6_910, btn_t6_910.getText());

        init_context_menu(btn_t7_123, btn_t7_123.getText());
        init_context_menu(btn_t7_45, btn_t7_45.getText());
        init_context_menu(btn_t7_678, btn_t7_678.getText());
        init_context_menu(btn_t7_910, btn_t7_910.getText());

        init_context_menu(btn_t4_123, btn_t4_123.getText());
        init_context_menu(btn_t4_45, btn_t4_45.getText());
        init_context_menu(btn_t4_678, btn_t4_678.getText());
        init_context_menu(btn_t4_910, btn_t4_910.getText());
        
        //init action onclick left mouse----------------------------------
        InitOnclickButtons(btn_t2_123);
        InitOnclickButtons(btn_t2_45);
        InitOnclickButtons(btn_t2_678);
        InitOnclickButtons(btn_t2_910);

        InitOnclickButtons(btn_t3_123);
        InitOnclickButtons(btn_t3_45);
        InitOnclickButtons(btn_t3_678);
        InitOnclickButtons(btn_t3_910);

        InitOnclickButtons(btn_t5_123);
        InitOnclickButtons(btn_t5_45);
        InitOnclickButtons(btn_t5_678);
        InitOnclickButtons(btn_t5_910);

        InitOnclickButtons(btn_t6_123);
        InitOnclickButtons(btn_t6_45);
        InitOnclickButtons(btn_t6_678);
        InitOnclickButtons(btn_t6_910);

        InitOnclickButtons(btn_t7_123);
        InitOnclickButtons(btn_t7_45);
        InitOnclickButtons(btn_t7_678);
        InitOnclickButtons(btn_t7_910);

        InitOnclickButtons(btn_t4_123);
        InitOnclickButtons(btn_t4_45);
        InitOnclickButtons(btn_t4_678);
        InitOnclickButtons(btn_t4_910);
    }
}
