package GUI;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class PopUp_Notification {
    public static void run(String title, String content, String type) {
         Notifications noti_builder = Notifications.create().title(title).text(content)
                .graphic(null).hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(e-> {
                    System.out.println("Clicked");
                });
        if (StaticFunctions.IsDarkMode == true) {noti_builder.darkStyle();}
        if (type.compareTo("INFO") == 0) {
             noti_builder.showInformation();
        
        }
        if (type.compareTo("FAIL") == 0) {
             noti_builder.showError();
        
        }
        if (type.compareTo("SUCCESS") == 0) {
             noti_builder.showConfirm();
        
        }
    }
    
    public static void loadNotification() {
         PopUp_Notification.run("Thông báo", "Nội dung", "INFO");
    }
}
