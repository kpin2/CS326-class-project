import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class practiceExamScene extends Scene {

    private Scene practiceExamScene;
    private Button exitButton;

    public practiceExamScene() {

        super(new Pane(),1366, 768);
        Pane root = new Pane();
        practiceExamScene = new Scene(root, 1366, 768);


        String[] questions = {
                "What is 2 + 2?",
                "How many different shapes are called quadrilaterals?",
                "What is 16/4?",
                "How many sides does a triangle have?",
                "What is 4-2"
        };

        Random rand = new Random();

        String randomQuestion = questions[rand.nextInt(questions.length)];

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);

        Button resetButton = new Button("Reset");
        resetButton.setLayoutX(250);

        landingScene landingScene = new landingScene();
        Button homeButton = landingScene.getAsteroidHomeButton();
        homeButton.setLayoutX(-100);
        homeButton.setLayoutY(200);
        homeButton.setStyle("-fx-background-color: transparent;");

        Image exitButtonImage = new Image("file:resources/assets/exit.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setFitHeight(96);
        exitButtonImageView.setFitWidth(96);
        exitButton = new Button("", exitButtonImageView);
        exitButton.setLayoutX(-5);
        exitButton.setLayoutY(-5);
        exitButton.setStyle("-fx-background-color: transparent;");

        ImageView smallShip = landingScene.getSmallShipImageView();
        smallShip.setLayoutX(95);
        smallShip.setLayoutY(152);
        smallShip.setRotate(-25);

        Image helpButtonImage = new Image("file:resources/assets/help2.png");
        ImageView helpButtonImageView = new ImageView(helpButtonImage);
        helpButtonImageView.setFitHeight(96);
        helpButtonImageView.setFitWidth(96);
        Button helpButton = new Button("", helpButtonImageView);
        helpButton.setLayoutX(96);
        helpButton.setLayoutY(5);
        helpButton.setStyle("-fx-background-color: transparent;");
        helpButton.setVisible(false);

        root.getChildren().addAll(smallShip, homeButton, helpButton,exitButton);


        Label questionLabel = new Label(randomQuestion);
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



        root.getChildren().addAll(resetButton, text, questionLabel, choice1, choice2, choice3, choice4, submitButton);
        double sceneWidth = practiceExamScene.getWidth();
        double sceneHeight = practiceExamScene.getHeight();

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

    }

    public Scene getScene(){
        return practiceExamScene;
    }


}
