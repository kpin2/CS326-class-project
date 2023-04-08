import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SubSceneExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.RED);
        Button button = new Button("Click Me");
        button.setLayoutX(100);
        button.setLayoutY(100);
        root.getChildren().add(button);
        Text text = new Text("Hello World");
        text.setLayoutX(50);
        text.setLayoutY(50);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Mission Space", 36));
        root.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create a SubScene
        SubScene subScene = new SubScene(new Group(), 300, 500);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(new PerspectiveCamera());

        // Add UI controls to the SubScene
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setLayoutY(150);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black;");
        vbox.getChildren().addAll(new Label("Username:"), new TextField(), new Label("Password:"), new PasswordField(), new Button("Login"));

        // Add the SubScene to the root
        root.getChildren().add(subScene);

        // Add the UI controls to the SubScene
        subScene.setRoot(vbox);
    }

    public static void main(String[] args) {
        launch(args);
    }


}