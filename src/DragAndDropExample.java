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
import javafx.scene.web.WebView;

import java.util.*;

public class DragAndDropExample extends Application {
    String[] questions = {
            "What is 2 + 2?",
            "How many different shapes are called quadrilaterals?",
            "What is 16/4?",
            "How many sides does a triangle have?",
            "What is 4-2?"
    };
    Map<String, String> answers = new HashMap<>();
    Random rand = new Random();

    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1360, 750);

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1360, 800, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);

       /*class YouTubeViewer extends Application {
           public static void main(String[] args) { launch(args); }

           @Override public void start(Stage stage) throws Exception {



               stage.setScene(new Scene(webview));
               stage.show();
           }
       }
*/

        char k = 'k';

        Questions questions1 = new Questions(k);


        //creating toolbar for the reset and exit buttons
        ToolBar toolBar = new ToolBar();

        Button exitButton = new Button("Exit");
        Button resetButton = new Button("Reset");

        Image exitButtonImage = new Image("file:resources/assets/Exit Button.png");
        exitButton.setGraphic(new ImageView(exitButtonImage));
        String answer =new String(String.valueOf(questions1.getAnswer()));

        Label questionLabel = new Label(questions1.getQuestion());
        // Assuming the correct answer is stored in the variable "correctAnswer"
        // Create a Random object
        Random random = new Random();

// Generate three random incorrect answers
        int incorrect1 = random.nextInt(10) + 1; // generates a random number between 1 and 10
        int incorrect2 = random.nextInt(10) + 1; // generates a different random number between 1 and 10
        while (incorrect2 == incorrect1) { // make sure the second incorrect answer is different from the first
            incorrect2 = random.nextInt(10) + 1;
        }
        int incorrect3 = random.nextInt(10) + 1; // generates a third different random number between 1 and 10
        while (incorrect3 == incorrect1 || incorrect3 == incorrect2) { // make sure the third answer is different from the first two
            incorrect3 = random.nextInt(10) + 1;
        }

// Add the correct and incorrect answers to the radio buttons
        RadioButton choice1 = new RadioButton(answer);
        RadioButton choice2 = new RadioButton(String.valueOf(incorrect1));
        RadioButton choice3 = new RadioButton(String.valueOf(incorrect2));
        RadioButton choice4 = new RadioButton(String.valueOf(incorrect3));


        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

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
            //TODO: this should figure out which button is correct and then check and see if selectedChoice is the same as it
            //right now... it ALWAYS says choice4 is correct
            if (selectedChoice == selectedChoice) {
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
        return scene;
    }

}
