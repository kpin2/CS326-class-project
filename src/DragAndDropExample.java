import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragAndDropExample extends Application {

    Pane root = new Pane();
    Scene helpScene = new Scene(root, 1360, 750);

    public void start(Stage stage) throws Exception {


        Scene scene = new Scene(root, 1360, 750);

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1360, 800, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);


        //creating toolbar for the reset and exit buttons
        ToolBar toolBar = new ToolBar();

        Button exitButton = new Button("Exit");
        Button resetButton = new Button("Reset");

        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));

        Label questionLabel = new Label("What is 2 + 2?");
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        RadioButton choice1 = new RadioButton("1");
        RadioButton choice2 = new RadioButton("2");
        RadioButton choice3 = new RadioButton("3");
        RadioButton choice4 = new RadioButton("4");
        ToggleGroup choicesGroup = new ToggleGroup();
        choicesGroup.getToggles().addAll(choice1, choice2, choice3, choice4);
        Button submitButton = new Button("Submit");

        // Setting the font and color for the answers
        choice1.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice2.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice3.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice4.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        submitButton.setOnAction(event -> {
            RadioButton selectedChoice = (RadioButton) choicesGroup.getSelectedToggle();
            if (selectedChoice == choice4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
                alert.showAndWait();
            }
        });


        exitButton.setOnAction(e -> {
            stage.close();
        });

        toolBar.getItems().addAll(resetButton, exitButton);
        root.getChildren().addAll(toolBar, text, questionLabel, choice1, choice2, choice3, choice4, submitButton);
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();

        questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        choice1.setLayoutX(sceneWidth / 2 - choice1.getWidth() / 2);
        choice1.setLayoutY(sceneHeight / 2 - 50);
        choice2.setLayoutX(sceneWidth / 2 - choice2.getWidth() / 2);
        choice2.setLayoutY(sceneHeight / 2 - 20);
        choice3.setLayoutX(sceneWidth / 2 - choice3.getWidth() / 2);
        choice3.setLayoutY(sceneHeight / 2 + 10);
        choice4.setLayoutX(sceneWidth / 2 - choice4.getWidth() / 2);
        choice4.setLayoutY(sceneHeight / 2 + 40);
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);
        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene(Scene scene){
        return this.helpScene;
    }

}
