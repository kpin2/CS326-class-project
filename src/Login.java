import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Login extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

//        Font font = new Font("Space Mission", 20);
//        Font.loadFont(getClass().getResourceAsStream("resources/font/SpaceMission-rgyw9.otf"), 20);

        Text text = new Text(75, 30, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission-rgyw9.otf", 36));
        text.setFill(Color.RED);
        root.getChildren().add(text);
        stage.setScene(scene);
        stage.show();
    }
}
