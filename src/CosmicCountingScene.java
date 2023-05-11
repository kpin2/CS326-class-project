import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;
/**
 * Class: CosmicCountingScene
 * Generates the scene for Fill in the blank Questions
 */

public class CosmicCountingScene extends Scene {

    private final Scene cosmicScene;
    private Pane root;
    // Scoreboard class used to track number of questions answered and correct responses
    private scoreboard score;
    // Toolbar buttons to exit or reset the scene
    private Button exitButton;
    private Button resetButton;
    // Button used to indicate response is ready
    private Button submitButton;
    private RadioButton choice1, choice2;
    private Label questionLabel;
    private String randomQuestion;
    private char grade;


    private String[] questions = {
            "2 + 2 equals 4",
            "A quadrilateral has 3 sides",
            "16/4 equals 4",
            "A triangle has 3 sides",
            "4 - 2 equals 6"
    };
    private String[] answers = {
            "True",
            "False",
            "True",
            "True",
            "False"
    };

    private int indx;

    public Alert difficulty;

    public CosmicCountingScene() {

        super(new Pane(), 1366, 768);
        root = new Pane();
        cosmicScene = new Scene(root, 1366, 768);
        score = new scoreboard();
        Random rand = new Random();


        difficulty = new Alert(Alert.AlertType.CONFIRMATION);
        difficulty.setTitle("Select difficulty");
        difficulty.setHeaderText("Select difficulty");
        difficulty.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Easy");
        ButtonType buttonTypeTwo = new ButtonType("Medium");
        ButtonType buttonTypeThree = new ButtonType("Hard");
        difficulty.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        indx = rand.nextInt(questions.length);

        randomQuestion = questions[indx];

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/background.png", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);


        resetButton = new Button("Reset");
        resetButton.setLayoutX(250);
        resetButton.setLayoutY(10);


        questionLabel = new Label(randomQuestion);
        questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice1 = new RadioButton("True");
        choice2 = new RadioButton("False");


        ToggleGroup choicesGroup = new ToggleGroup();
        choicesGroup.getToggles().addAll(choice1, choice2);
        submitButton = new Button("Submit");

        // Setting the font and color for the answers
        choice1.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        choice2.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");


        submitButton.setOnAction(event -> {
            RadioButton selectedChoice = (RadioButton) choicesGroup.getSelectedToggle();
            String ansString = selectedChoice.getText();
            checkAns(ansString);
            updateQuestionLabel();
        });


        resetButton.setOnAction(e -> {
            score.setScore(0);
            score.setTrys(0);
            updateQuestionLabel();
        });

        root.getChildren().addAll(resetButton, text, questionLabel, choice1, choice2, submitButton);
        double sceneWidth = cosmicScene.getWidth();
        double sceneHeight = cosmicScene.getHeight();

        questionLabel.setLayoutX(sceneWidth / 2 - questionLabel.getWidth() / 2);
        questionLabel.setLayoutY(sceneHeight / 2 - 80);
        choice1.setLayoutX(sceneWidth / 2 - choice1.getWidth() / 2);
        choice1.setLayoutY(sceneHeight / 2 - 50);
        choice2.setLayoutX(sceneWidth / 2 - choice2.getWidth() / 2);
        choice2.setLayoutY(sceneHeight / 2 - 20);
        submitButton.setLayoutX(sceneWidth / 2 - submitButton.getWidth() / 2);
        submitButton.setLayoutY(sceneHeight / 2 + 100);


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

        root.getChildren().addAll(smallShip, homeButton, helpButton, exitButton);


    }

    public Scene getScene() {
        return cosmicScene;
    }

    public Button getExitButton() {

        return exitButton;
    }

    public Button getResetButton() {

        return resetButton;
    }

    public String getScoreResult() {
        if (score.getTrys() > 0) {
            return "Result : " + score.getScore() + " / " + score.getTrys();
        } else {
            return "";
        }
    }

    public int getScore() {
        return score.getScore();
    }

    // Gives a random variable
    public int getrandnum(int num) {
        Random rand = new Random();
        return (rand.nextInt(num));
    }

    public void checkAns(String ans) {
        if (ans.equals(answers[indx])) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
            alert.showAndWait();
            score.addScore();
            score.addTrys();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
            alert.showAndWait();
            score.addTrys();
        }
    }

    public void updateQuestionLabel() {
        //These lines generate a new random question each time Submit is pressed
        if (score.getTrys() < 5) {
            indx = getrandnum(questions.length);
            randomQuestion = questions[indx];
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
            choice1.setSelected(false);
            choice2.setSelected(false);
            if(!root.getChildren().contains(submitButton)) {
                root.getChildren().add(submitButton);
            }
            if(!root.getChildren().contains(choice1)) {
                root.getChildren().add(choice1);
            }
            if(!root.getChildren().contains(choice2)) {
                root.getChildren().add(choice2);
            }

        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            //hide the submit button and true/false buttons once we have reached 5 trys
            root.getChildren().removeAll(choice1, choice2, submitButton);
        }
    }

    public void setGrade(char gr) {
        this.grade = gr;
    }

}

