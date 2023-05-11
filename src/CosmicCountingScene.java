/* CSCI362 Software Engineering
 * Class Project - Mission: Math!
 * CosmicCountingScene.java - This is the main class for Cosmic counting.
 * It impliments the True and False Questions
 *
 * Produced: 4/25/2023
 *
 * @author Zakaria Lazzouni
 * */
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.util.Random;
/**
 * Class: CosmicCountingScene
 * Generates the scene for True and False questions
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
    private String formattedQuestion;
    private char grade;
    private Questions questions1;

    private String[] answers = {
            "True",
            "False",
            "True",
            "True",
            "False"
    };

    private int indx;

    public Alert difficulty;
    ImageView imageView;
    /**
     * This is the constructor for Cosmic counting scene class
     * This class impliments the True and False questions
     */
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

        //code to generate random True or False question
        grade = 'k';
        questions1 = new Questions(grade);
        randomQuestion = questions1.getTFQuestion();
        int newAns = questions1.getAnswer();
        int incorrectAns = rand.nextInt(10) + 1; // generates a different random number between 1 and 10
        while (incorrectAns == newAns) { // make sure the second incorrect answer is different from the first
            incorrectAns = rand.nextInt(10) + 1;
        }

        indx = rand.nextInt(2);
        if(indx == 1) {
            formattedQuestion = randomQuestion.concat(String.valueOf(incorrectAns));
        }
        else {
            formattedQuestion = randomQuestion.concat(String.valueOf(newAns));
        }

        //randomQuestion = questions[indx];

        BackgroundImage myBI = new BackgroundImage(new Image("file:resources/assets/planet_background_1.jpg", 1366, 768, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));


        //imageView to hold medals image
        imageView = new ImageView();
        Scale scaleTransformation = new Scale();
        scaleTransformation.setX(0.70);
        scaleTransformation.setY(0.70);
        scaleTransformation.setPivotX(600);
        scaleTransformation.setPivotY(400);
        imageView.getTransforms().add(scaleTransformation);
        imageView.getTransforms().add(scaleTransformation);
        imageView.getTransforms().add(scaleTransformation);

        //Setting the font
        Text text = new Text(375, 130, "Mission: Math!");
        text.setFont(Font.loadFont("file:resources/font/SpaceMission.otf", 64));
        text.setFill(Color.RED);


        Image resetImage = new Image("file:resources/assets/reset1-removebg-preview.png");
        ImageView resetImageView = new ImageView(resetImage);
        resetImageView.setFitHeight(96);
        resetImageView.setFitWidth(96);
        resetButton = new Button("", resetImageView);
        resetButton.setLayoutX(250);
        resetButton.setLayoutY(5);
        resetButton.setStyle("-fx-background-color: transparent;");


        questionLabel = new Label(formattedQuestion);
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
    /**
     * Helper method to get handle to the scene
     * @return Scene handle to fill in the blank scene
     */
    public Scene getScene() {
        return cosmicScene;
    }
    /**
     * Helper method to get handle to exit button
     * @return Button handle to exit button
     */
    public Button getExitButton() {

        return exitButton;
    }
    /**
     * Helper method to get handle to reset button
     * @return handle to reset button
     */
    public Button getResetButton() {

        return resetButton;
    }
    /**
     * Method returning a String showing the Result/Progress of player
     * @return String to be placed beside the planets
     */
    public String getScoreResult() {
        if (score.getTrys() > 0) {
            return "Result : " + score.getScore() + " / " + score.getTrys();
        } else {
            return "";
        }
    }
    /**
     * Helper method to find the score of player
     * @return Integer showing how many questions answered were correct
     */
    public int getScore() {
        return score.getScore();
    }

    // Gives a random variable
    public int getrandnum(int num) {
        Random rand = new Random();
        return (rand.nextInt(num));
    }
    /**
     * Method to check if the answer provided is correct
     * Updates the scoreboard with information
     * @param ans : User provided answer
     */
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
    /**
     * Method to generate a new question and update the questionLabel Text node
     */
    public void updateQuestionLabel() {
        //These lines generate a new random question each time Submit is pressed
        if (score.getTrys() < 5) {
            Random rand = new Random();
            indx = getrandnum(2);
            questions1 = new Questions(grade);
            randomQuestion = questions1.getTFQuestion();
            int newAns = questions1.getAnswer();
            int incorrectAns = rand.nextInt(10) + 1; // generates a different random number between 1 and 10
            while (incorrectAns == newAns) { // make sure the second incorrect answer is different from the first
                incorrectAns = rand.nextInt(10) + 1;
            }

            indx = rand.nextInt(2);
            if(indx == 1) {
                formattedQuestion = randomQuestion.concat(String.valueOf(incorrectAns));
            }
            else {
                formattedQuestion = randomQuestion.concat(String.valueOf(newAns));
            }
            questionLabel.setText(formattedQuestion);
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
            if(root.getChildren().contains(imageView)) {
                root.getChildren().remove(imageView);
            }

        } else {
            randomQuestion = score.getresult();
            questionLabel.setText(randomQuestion);
            questionLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
            //hide the submit button and true/false buttons once we have reached 5 trys
            root.getChildren().removeAll(choice1, choice2, submitButton);

            //The lines below are for showing the medals once the results are displayed

            Image medalBronze = new Image("file:resources/assets/medalbronze.png");
            //ImageView imageViewBronze = new ImageView(medalBronze);

            Image medalSilver = new Image("file:resources/assets/medalsilver.png");
            //ImageView imageViewSilver = new ImageView(medalSilver);

            Image medalGold = new Image("file:resources/assets/medalgold.png");

            if(root.getChildren().contains(imageView)) {
                root.getChildren().remove(imageView);
            }

            if(score.getScore() == 3){
                imageView.setImage(medalBronze);
                root.getChildren().add(imageView);
            }
            if(score.getScore() == 4){
                imageView.setImage(medalSilver);
                root.getChildren().add(imageView);
            }
            if(score.getScore() == 5){
                imageView.setImage(medalGold);
                root.getChildren().add(imageView);
            }

        }
    }
    /**
     * Helper method to set grade variable
     * @param gr charater for grade level
     */
    public void setGrade(char gr) {
        this.grade = gr;
    }

}

