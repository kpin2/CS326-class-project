import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SubSceneExample extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        ToolBar toolbar = new ToolBar();
        root.setTop(toolbar);

        StackPane stackPane = new StackPane();
        Rectangle mainBackground = new Rectangle(1200, 800);
        mainBackground.setFill(Color.LIGHTBLUE);
        stackPane.getChildren().add(mainBackground);

        // Create the overlay scene
        StackPane overlayScene = new StackPane();

        Scene overlay = new Scene(overlayScene, 1200, 800);

        /*Rectangle overlayBackground = new Rectangle(600, 500);
        overlayBackground.setFill(Color.BLACK);*/
        //overlayScene.getChildren().add(overlayBackground);

        // Add the help and exit buttons to the overlay scene
        Button helpButton = new Button("Help");
        Button exitButton = new Button("Exit");
        HBox buttonBox = new HBox(helpButton, exitButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);
        buttonBox.setStyle("-fx-background-color: rgb(0,182,15);");
//        overlayScene.getChildren().add(buttonBox);
        toolbar.getItems().addAll(helpButton, exitButton);

        // Add the banner to the overlay scene
        Rectangle banner = new Rectangle(800, 100);
        banner.setFill(Color.DARKBLUE);
        Text bannerText = new Text("Banner");
        bannerText.setFill(Color.WHITE);
        bannerText.setStyle("-fx-font-size: 36px;");
        StackPane.setAlignment(banner, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(bannerText, Pos.BOTTOM_CENTER);
        StackPane.setMargin(bannerText, new Insets(10));
        StackPane.setAlignment(toolbar, Pos.TOP_CENTER);

        overlayScene.getChildren().addAll(toolbar,banner, bannerText);

        // Add the main and overlay scenes to the root pane
        root.setCenter(stackPane);
        root.getChildren().add(overlayScene);


        primaryStage.setScene(overlayScene.getScene());

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}










/*
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
        Scene scene = new Scene(root, 600, 600);
        scene.setFill(Color.RED);
        Button button = new Button("Click Me");
        button.setLayoutX(100);
        button.setLayoutY(100);
        root.getChildren().add(button);
        Text text = new Text("Hello World");
        text.setLayoutX(50);
        text.setLayoutY(50);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Space Mission", 36));
        root.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create a SubScene
        SubScene subScene = new SubScene(new Group(), 300, 450);
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

}*/
