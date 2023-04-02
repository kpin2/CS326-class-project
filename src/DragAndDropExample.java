import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragAndDropExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage) throws Exception {

        //stage is the top level container, setting title displays the title in the title bar
        stage.setTitle("Mission: Math!");

        //root is the highest level pane and the root node of the scene graph
        Pane root = new Pane();

        //scene is the container for all content so far
        Scene scene = new Scene(root, 960, 540);

        //setting the background image
        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1200, 800, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(175, 85, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission-rgyw9.otf", 98));
        text.setFill(Color.RED);

        //setting the exit button image and placement
        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        Button exitButton = new Button("", exitButtonImageView);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(5);
        exitButton.setStyle("-fx-background-color: transparent;");


        exitButton.setOnAction(e -> {
            stage.close();
        });


//        toolBar.getItems().addAll(exitButton);
        root.getChildren().addAll(exitButton, text);
        stage.setScene(scene);
        stage.show();
    }


}